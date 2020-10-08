package com.example.healthtourism.Model;

import java.io.Serializable;
import java.util.Map;

public class WisataModel implements Serializable {
    private String Nama;
    private String Gambar;
    private String Deskripsi;
    private String Fasilitas;
    private String Harga;
    private String JamBuka;
    private String Provinsi;
    private String Key;
    private Float RatingSum;
    private String Koordinat;
    private Float RatingValue;
    private Long RatingCount;
    private Map<String, Object> Komentar;

    public WisataModel(){

    }



    public WisataModel(String key, Map<String, Object> komentar, Float ratingSum, Float ratingValue, Long ratingCount, String harga, String koordinat, String nama, String gambar, String deskripsi, String fasilitas, String jamBuka, String provinsi) {
        Nama = nama;
        Gambar = gambar;
        Deskripsi = deskripsi;
        Fasilitas = fasilitas;
        JamBuka = jamBuka;
        Provinsi = provinsi;
        Harga = harga;
        Key = key;
        RatingValue = ratingValue;
        RatingCount = ratingCount;
        Komentar = komentar;
        RatingSum = ratingSum;
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }

    public Float getRatingSum() {
        return RatingSum;
    }

    public void setRatingSum(Float ratingSum) {
        RatingSum = ratingSum;
    }

    public Map<String, Object> getKomentar() {
        return Komentar;
    }

    public void setKomentar(Map<String, Object> komentar) {
        Komentar = komentar;
    }

    public Long getRatingCount() {
        return RatingCount;
    }

    public void setRatingCount(Long ratingCount) {
        RatingCount = ratingCount;
    }

    public Float getRatingValue() {
        return RatingValue;
    }

    public void setRatingValue(Float ratingValue) {
        RatingValue = ratingValue;
    }

    public String getHarga() {
        return Harga;
    }

    public void setHarga(String harga) {
        Harga = harga;
    }

    public String getKoordinat() {
        return Koordinat;
    }

    public void setKoordinat(String koordinat) {
        Koordinat = koordinat;
    }

    public String getProvinsi() {
        return Provinsi;
    }

    public void setProvinsi(String provinsi) {
        Provinsi = provinsi;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        Nama = nama;
    }

    public String getGambar() {
        return Gambar;
    }

    public void setGambar(String gambar) {
        Gambar = gambar;
    }

    public String getDeskripsi() {
        return Deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        Deskripsi = deskripsi;
    }

    public String getFasilitas() {
        return Fasilitas;
    }

    public void setFasilitas(String fasilitas) {
        Fasilitas = fasilitas;
    }

    public String getJamBuka() {
        return JamBuka;
    }

    public void setJamBuka(String jamBuka) {
        JamBuka = jamBuka;
    }
}
