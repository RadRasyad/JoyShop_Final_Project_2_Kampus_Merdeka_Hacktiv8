package com.hacktiv8.joyshop.model;

public class Product {
    private String id, name, brand, kategory, deskripsi, img, tipe;
    private int hrg, stock;

    public  Product(String id, String name, int stock, String img, String tipe, int hrg, String deskripsi, String brand, String kategory){
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.img = img;
        this.kategory = kategory;
        this.hrg = hrg;
        this.deskripsi = deskripsi;
        this.tipe = tipe;
        this.brand = brand;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getKategory() {
        return kategory;
    }

    public void setKategory(String kategory) {
        this.kategory = kategory;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public int getHrg() {
        return hrg;
    }

    public void setHrg(int hrg) {
        this.hrg = hrg;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
