package com.example.aplikasipesanmakanan;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class PesananAdapter extends FirestoreRecyclerAdapter<Pesanan, PesananAdapter.ViewHoler> {

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public PesananAdapter(@NonNull FirestoreRecyclerOptions<Pesanan> options) {
        super(options);
    }

    public String rp(int txt){
        Locale locale = new Locale("in", "ID");
        NumberFormat format = NumberFormat.getCurrencyInstance(locale);
        format.setMaximumFractionDigits(0);
        return format.format(txt); // Integer.toString(total);
    }

    @Override
    protected void onBindViewHolder(@NonNull PesananAdapter.ViewHoler holder, int position, @NonNull Pesanan model) {
        holder.txtPesanan.setText(model.namaPesanan + " (" + String.valueOf(model.jumlahPesanan) +
                " x " + rp(Integer.parseInt(String.valueOf(model.hargaPesanan))) + ')');
        holder.txtJmlHarga.setText(rp(Integer.parseInt(String.valueOf(model.hargaPesanan * model.jumlahPesanan))));

        holder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = holder.itemLayout.getContext();
                Intent it = new Intent(context, DetailCartActivity.class);
                it.putExtra("pesanan", model);
                context.startActivity(it);
            }
        });
    }

    @NonNull
    @Override
    public PesananAdapter.ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        ViewHoler holder = new ViewHoler(inflater.inflate(R.layout.item_records, parent, false));
        return holder;
    }


    public class ViewHoler extends RecyclerView.ViewHolder{
        public TextView txtPesanan, txtJmlHarga;
        public ConstraintLayout itemLayout;

        public ViewHoler(@NonNull View itemView) {
            super(itemView);

            txtPesanan = itemView.findViewById(R.id.txt_pesanan);
            txtJmlHarga = itemView.findViewById(R.id.txt_jmlharga);
            itemLayout = itemView.findViewById(R.id.item_layout);

        }
    }
}
