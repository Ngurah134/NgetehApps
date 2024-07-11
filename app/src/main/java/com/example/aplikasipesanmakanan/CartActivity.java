package com.example.aplikasipesanmakanan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class CartActivity extends AppCompatActivity {

    TextView txtTotal;

    RecyclerView recPesanan;
    String namaUser;

    FirebaseUser user;
    FirebaseAuth mAuth;

    FirebaseFirestore fireDb;
    PesananAdapter adapter;

    int totalHargaFinal = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recPesanan = findViewById(R.id.rec_records);
        recPesanan.setLayoutManager(new LinearLayoutManager(this));

        fireDb = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        namaUser = user.getEmail();

        txtTotal = findViewById(R.id.txt_total);
        txtTotal.setText("0");

        // Calculate and display total
        getSumTotal();

        // Setup checkout button click listener
        checkout();
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        // Set up FirestoreRecyclerAdapter to display user's orders
        Query query = fireDb.collection("pesanan")
                .whereEqualTo("userId", user.getUid());

        FirestoreRecyclerOptions<Pesanan> options = new FirestoreRecyclerOptions.Builder<Pesanan>()
                .setQuery(query, Pesanan.class).build();
        adapter = new PesananAdapter(options);
        recPesanan.setAdapter(adapter);
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        // Stop listening to Firestore updates when activity is stopped
        adapter.stopListening();
    }

    @SuppressLint("SetTextI18n")
    public void getSumTotal() {
        // Calculate total amount of orders in cart
        Query query = fireDb.collection("pesanan").whereEqualTo("userId", user.getUid());

        txtTotal.setText("Counting...");

        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    int total = 0;
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        int hargaPesanan = document.getLong("hargaPesanan").intValue();
                        int jumlahPesanan = document.getLong("jumlahPesanan").intValue();
                        total += hargaPesanan * jumlahPesanan;
                        Log.d("debugz", "totalHarga:" + total);
                    }
                    totalHargaFinal = total;
                    Log.d("debugz", "totalHarga in get sum:" + totalHargaFinal);
                    // Format total amount and display in TextView
                    Locale locale = new Locale("in", "ID");
                    NumberFormat format = NumberFormat.getCurrencyInstance(locale);
                    format.setMaximumFractionDigits(0);
                    txtTotal.setText(format.format(total));
                } else {
                    Log.d("CartActivity", "Error getting documents: ", task.getException());
                }
            }
        });
    }

    public void checkout() {
        // Handle checkout button click
        Button checkoutButton = findViewById(R.id.btn_checkout);
        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Move orders from 'pesanan' collection to 'checkout' or process checkout logic
                moveOrdersToCheckout();
            }
        });
    }

    public void moveOrdersToCheckout() {
        // Query 'pesanan' collection for the user's orders
        Query query = fireDb.collection("pesanan").whereEqualTo("userId", user.getUid());

        query.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                int totalHarga = 0;
                List<Map<String, Object>> pesananList = new ArrayList<>();

                // Iterate through each order document and prepare data for checkout
                for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                    Pesanan pesanan = document.toObject(Pesanan.class);
                    Map<String, Object> pesananMap = new HashMap<>();
                    pesananMap.put("hargaPesanan", pesanan.hargaPesanan);
                    pesananMap.put("jumlahPesanan", pesanan.jumlahPesanan);
                    pesananMap.put("namaPesanan", pesanan.namaPesanan);
                    pesananList.add(pesananMap);

                    // Calculate totalHarga
                    totalHarga += pesanan.hargaPesanan * pesanan.jumlahPesanan;

                    // Delete the order from 'pesanan' collection
                    fireDb.collection("pesanan").document(document.getId())
                            .delete()
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d("CartActivity", "Order deleted from pesanan collection");
                                    // Recalculate and update total amount
                                    getSumTotal();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w("CartActivity", "Error deleting order", e);
                                }
                            });
                }
                if (totalHarga==0){
                    return;
                }

                // Prepare checkout data structure
                Map<String, Object> checkoutData = new HashMap<>();
                checkoutData.put("email", user.getEmail());
                checkoutData.put("uid", null); // Assuming uid is not needed in the document
                checkoutData.put("userId", user.getUid());
                checkoutData.put("pesanan", pesananList);
                checkoutData.put("totalHarga", totalHarga);

                // Add checkout data to 'checkout' collection
                fireDb.collection("checkout")
                        .add(checkoutData)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.d("CartActivity", "Checkout data added successfully");

                                // Navigate to checkout success screen
                                Intent successIntent = new Intent(CartActivity.this, CheckoutSuccessActivity.class);
                                Log.d("debugz", "totalHarga in checkout:" + totalHargaFinal);
                                successIntent.putExtra("totalHarga", totalHargaFinal);
                                startActivity(successIntent);
                                finish(); // Optional: Finish current activity
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w("CartActivity", "Error adding checkout data", e);
                            }
                        });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w("CartActivity", "Error querying orders", e);
            }
        });
    }

}
