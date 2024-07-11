package com.example.aplikasipesanmakanan;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

public class Pesanan implements Serializable {
    public String uid;
    public String userId;
    public String email;
    public String namaPesanan;
    public int hargaPesanan;
    public int jumlahPesanan;

    public Pesanan() {
    }

    public Pesanan(String userId, String email, String namaPesanan, int hargaPesanan, int jumlahPesanan) {
        this.userId = userId;
        this.email = email;
        this.namaPesanan = namaPesanan;
        this.hargaPesanan = hargaPesanan;
        this.jumlahPesanan = jumlahPesanan;
    }
}
