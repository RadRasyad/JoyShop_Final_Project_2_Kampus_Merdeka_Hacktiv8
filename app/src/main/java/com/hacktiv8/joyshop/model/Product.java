package com.hacktiv8.joyshop.model;

public class Product {

    private int id;
    private String nama, brand, deskripsi, img, tipe, hrg, stok, gender, kategori;

    public Product() {

    }

    public Product(int id, String nama, String brand, String deskripsi, String img, String tipe, String hrg, String stok, String gender, String kategori) {
        this.id = id;
        this.nama = nama;
        this.brand = brand;
        this.deskripsi = deskripsi;
        this.img = img;
        this.tipe = tipe;
        this.hrg = hrg;
        this.stok = stok;
        this.gender = gender;
        this.kategori = kategori;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getStok() {
        return stok;
    }

    public void setStok(String stok) {
        this.stok = stok;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }


}
