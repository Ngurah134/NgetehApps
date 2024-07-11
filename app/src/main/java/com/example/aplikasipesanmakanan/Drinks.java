package com.example.aplikasipesanmakanan;

public class Drinks {
    private String namaDrinks;
    String hargaDrinks;
    private int imgDrinks;

    public Drinks(String namaDrinks, String hargaDrinks, int imgDrinks) {
        this.namaDrinks = namaDrinks;
        this.hargaDrinks = hargaDrinks;
        this.imgDrinks = imgDrinks;
    }

    public String getNamaDrinks() {
        return namaDrinks;
    }

    public void setNamaDrinks(String namaDrinks) {
        this.namaDrinks = namaDrinks;
    }

    public String getHargaDrinks() {
        return hargaDrinks;
    }

    public void setHargaDrinks(String hargaDrinks) {
        this.hargaDrinks = hargaDrinks;
    }

    public int getImgDrinks() {
        return imgDrinks;
    }

    public void setImgDrinks(int imgDrinks) {
        this.imgDrinks = imgDrinks;
    }
}
