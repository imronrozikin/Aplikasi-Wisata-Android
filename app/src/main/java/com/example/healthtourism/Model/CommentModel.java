package com.example.healthtourism.Model;

import java.util.Map;

public class CommentModel {
    private float Rating;
    private String Comment, Nama;

    public CommentModel() {

    }

    public CommentModel(float rating, String comment, String nama) {
        Rating = rating;
        Comment = comment;
        Nama = nama;
    }

    public float getRating() {
        return Rating;
    }

    public void setRating(float rating) {
        Rating = rating;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        Nama = nama;
    }
}
