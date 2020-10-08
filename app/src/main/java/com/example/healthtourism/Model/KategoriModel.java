package com.example.healthtourism.Model;

public class KategoriModel {
    private String Name;
    private String Image;

    public KategoriModel(){

    }

    public KategoriModel(String name, String image) {
        Name = name;
        Image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
