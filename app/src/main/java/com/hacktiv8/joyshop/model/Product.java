package com.hacktiv8.joyshop.model;

public class Product {
    private String id, nama, brand, kategory, deskripsi, img, tipe;
    private String hrg, stock;

    public Product() {

    }

    public Product(String id, String nama, String stock, String img, String tipe, String hrg, String deskripsi, String brand, String kategory){
        this.id = id;
        this.nama = nama;
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

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
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

    public String getHrg() {
        return hrg;
    }

    public void setHrg(String hrg) {
        this.hrg = hrg;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }
}
