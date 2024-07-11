package com.example.aplikasipesanmakanan;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;
import java.util.Locale;

public class CheckoutSuccessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_success);

//        TextView txtOrderDetails = findViewById(R.id.txt_order_details);
        Button btnContinueShopping = findViewById(R.id.btn_continue_shopping);

        // Retrieve order details from intent
        Intent intent = getIntent();
//        if (intent != null && intent.hasExtra("totalHarga")) {
//            int totalHarga = intent.getIntExtra("totalHarga", 0);
//
//            // Format totalHarga as currency
//            Locale locale = new Locale("in", "ID");
//            NumberFormat format = NumberFormat.getCurrencyInstance(locale);
//            format.setMaximumFractionDigits(0);
//            String formattedTotal = format.format(totalHarga);
//
//            // Display order details
//            String orderDetails = "Total: " + formattedTotal;
//            txtOrderDetails.setText(orderDetails);
//        } else {
//            txtOrderDetails.setText("Order details not available");
//        }

        // Handle continue shopping button click
        btnContinueShopping.setOnClickListener(v -> {
            // Navigate back to main activity or any desired destination
            Intent mainIntent = new Intent(CheckoutSuccessActivity.this, MainActivity.class);
            mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(mainIntent);
            finish();
        });
    }
}
