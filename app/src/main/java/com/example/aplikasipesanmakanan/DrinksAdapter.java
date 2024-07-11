package com.example.aplikasipesanmakanan;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class DrinksAdapter extends RecyclerView.Adapter<DrinksAdapter.ViewHolder> {

    ViewHolder holder;

    public DrinksAdapter(ArrayList<Drinks> listDrinks) {
        this.listDrinks = listDrinks;
    }

    private ArrayList<Drinks> listDrinks;

    @NonNull
    @Override
    public DrinksAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        ViewHolder holder =  new ViewHolder(inflater.inflate(R.layout.item_menu, parent, false));

        return holder;
    }

    public String rp(int txt){
        Locale locale = new Locale("in", "ID");
        NumberFormat format = NumberFormat.getCurrencyInstance(locale);
        format.setMaximumFractionDigits(0);
        return format.format(txt); // Integer.toString(total);
    }

    @Override
    public void onBindViewHolder(@NonNull DrinksAdapter.ViewHolder holder, int position) {
        Drinks drinks = listDrinks.get(position);
        holder.txtNamaDrinks.setText(drinks.getNamaDrinks());
        holder.txtHargaDrinks.setText(rp(Integer.parseInt(drinks.getHargaDrinks())));
        holder.imgDrinks.setImageResource(drinks.getImgDrinks());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listDrinks.get(position).getNamaDrinks().equals("Es Teh Original")) {
                    Intent intent = new Intent(holder.itemView.getContext(), MenuDetailActivity.class);
                    intent.putExtra("GAMBAR_DEFAULT", R.drawable.ori);
                    intent.putExtra("NAMA_DEFAULT", "Es Teh Original");
                    intent.putExtra("DESKRIPSI_DEFAULT", "Es Teh Original adalah minuman teh yang disajikan dengan es batu, memberikan sensasi segar dan menyejukkan. Teh ini memiliki rasa klasik dari daun teh berkualitas yang diseduh dengan sempurna, tanpa tambahan perasa atau pemanis buatan. Cocok dinikmati kapan saja untuk melepas dahaga.");
                    intent.putExtra("HARGA_DEFAULT", "6000");
                    holder.itemView.getContext().startActivity(intent);
                }
                if (listDrinks.get(position).getNamaDrinks().equals("Es Teh Hijau")) {
                    Intent intent = new Intent(holder.itemView.getContext(), MenuDetailActivity.class);
                    intent.putExtra("GAMBAR_DEFAULT", R.drawable.greentea);
                    intent.putExtra("NAMA_DEFAULT", "Es Teh Hijau");
                    intent.putExtra("DESKRIPSI_DEFAULT", "Es Teh Hijau adalah teh hijau yang disajikan dingin dengan es batu. Teh hijau terkenal dengan kandungan antioksidannya yang tinggi dan manfaat kesehatannya. Minuman ini memiliki rasa yang ringan dan sedikit pahit, memberikan sensasi menyegarkan dan menyehatkan sekaligus.");
                    intent.putExtra("HARGA_DEFAULT", "10000");
                    holder.itemView.getContext().startActivity(intent);
                }
                if (listDrinks.get(position).getNamaDrinks().equals("Es Teh Lemon")) {
                    Intent intent = new Intent(holder.itemView.getContext(), MenuDetailActivity.class);
                    intent.putExtra("GAMBAR_DEFAULT", R.drawable.lemontea);
                    intent.putExtra("NAMA_DEFAULT", "Es Teh Lemon");
                    intent.putExtra("DESKRIPSI_DEFAULT", "Es Teh Lemon adalah kombinasi teh dingin dengan perasan lemon segar. Rasa asam dari lemon berpadu dengan rasa teh yang khas, menciptakan minuman yang menyegarkan dan menyenangkan. Es Teh Lemon adalah pilihan yang sempurna untuk hari yang panas, memberikan rasa yang tajam dan menyegarkan.");
                    intent.putExtra("HARGA_DEFAULT", "8000");
                    holder.itemView.getContext().startActivity(intent);
                }
                if (listDrinks.get(position).getNamaDrinks().equals("Thai Tea")) {
                    Intent intent = new Intent(holder.itemView.getContext(), MenuDetailActivity.class);
                    intent.putExtra("GAMBAR_DEFAULT", R.drawable.thaitea);
                    intent.putExtra("NAMA_DEFAULT", "Thai Tea");
                    intent.putExtra("DESKRIPSI_DEFAULT", "Thai Tea adalah minuman khas Thailand yang terbuat dari teh hitam yang diseduh dengan campuran rempah-rempah seperti bunga lawang, kayu manis, dan kapulaga. Setelah diseduh, teh ini dicampur dengan susu kental manis dan gula, memberikan rasa yang kaya dan creamy. Minuman ini biasanya disajikan dingin dengan es batu, memberikan sensasi menyegarkan dengan rasa manis yang khas dan aroma yang menggugah selera. Thai Tea sangat populer di banyak negara dan sering dinikmati sebagai minuman penutup yang menyegarkan.");
                    intent.putExtra("HARGA_DEFAULT", "5000");
                    holder.itemView.getContext().startActivity(intent);
                }
                if (listDrinks.get(position).getNamaDrinks().equals("Es Teh Mangga")) {
                    Intent intent = new Intent(holder.itemView.getContext(), MenuDetailActivity.class);
                    intent.putExtra("GAMBAR_DEFAULT", R.drawable.manggotea);
                    intent.putExtra("NAMA_DEFAULT", "Es Teh Mangga");
                    intent.putExtra("DESKRIPSI_DEFAULT", "Es Teh Mangga adalah perpaduan antara teh dingin dengan rasa manis dan segar dari buah mangga. Minuman ini menawarkan keseimbangan sempurna antara rasa teh yang kuat dan aroma serta rasa mangga yang tropis, menciptakan pengalaman minum yang eksotis dan menyenangkan..");
                    intent.putExtra("HARGA_DEFAULT", "12000");
                    holder.itemView.getContext().startActivity(intent);
                }
                if (listDrinks.get(position).getNamaDrinks().equals("Es Teh Milo")) {
                    Intent intent = new Intent(holder.itemView.getContext(), MenuDetailActivity.class);
                    intent.putExtra("GAMBAR_DEFAULT", R.drawable.milo);
                    intent.putExtra("NAMA_DEFAULT", "Es Teh Milo");
                    intent.putExtra("DESKRIPSI_DEFAULT", "Es Teh Milo adalah minuman inovatif yang menggabungkan teh dingin dengan bubuk Milo, memberikan rasa cokelat yang kaya dan lezat. Minuman ini sangat cocok bagi pecinta cokelat yang ingin menikmati kesegaran teh dengan sentuhan rasa cokelat yang khas, menjadikannya minuman yang nikmat dan memuaskan.");
                    intent.putExtra("HARGA_DEFAULT", "8000");
                    holder.itemView.getContext().startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listDrinks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtNamaDrinks, txtHargaDrinks;
        public ImageView imgDrinks;
        public ConstraintLayout itemView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNamaDrinks = (TextView) itemView.findViewById(R.id.txtNamaItem);
            txtHargaDrinks = (TextView) itemView.findViewById(R.id.txtHargaItem);
            imgDrinks = (ImageView) itemView.findViewById(R.id.imgItem);
            this.itemView = (ConstraintLayout) itemView.findViewById(R.id.itemLayout);
        }
    }
}
