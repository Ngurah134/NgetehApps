package com.example.aplikasipesanmakanan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {

    String namaUser;
    TextView txtNama;

    FirebaseUser user;
    FirebaseAuth mAuth;

    FirebaseFirestore fireDb;

    private RecyclerView recDrinks;
    private ArrayList<Drinks> listDrinks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        initFab();

        fireDb = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        namaUser = user.getEmail();

        recDrinks = findViewById((R.id.rec_drinks));
        initDataDrinks();


        recDrinks.setAdapter(new DrinksAdapter(listDrinks));
        recDrinks.setLayoutManager(new LinearLayoutManager(this));

        txtNama = findViewById(R.id.txtNama);
        txtNama.setText("Hi, " + namaUser);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initFab(){
        FloatingActionButton fabCart = findViewById(R.id.fabCart);
        fabCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), CartActivity.class));
            }
        });

        FloatingActionButton fabLogout = findViewById(R.id.fabLogout);
        fabLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                startActivity(new Intent(getBaseContext(), MainActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
            }
        });
    }

    private void initDataDrinks(){
        this.listDrinks = new ArrayList<>();
        listDrinks.add(new Drinks("Es Teh Original", "6000", R.drawable.ori));
        listDrinks.add(new Drinks("Es Teh Hijau", "10000", R.drawable.greentea));
        listDrinks.add(new Drinks("Es Teh Lemon", "8000", R.drawable.lemontea));
        listDrinks.add(new Drinks("Thai Tea", "15000", R.drawable.thaitea));
        listDrinks.add(new Drinks("Es Teh Mangga", "12000", R.drawable.manggotea));
        listDrinks.add(new Drinks("Es Teh Milo", "8000", R.drawable.milo));
    }
}