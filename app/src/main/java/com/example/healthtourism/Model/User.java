package com.example.healthtourism.Model;

public class User {
    private String Nama;
    private  String Email;
    private String Password;

    public User() {
    }

    public User(String nama,String email, String password){
        Nama = nama;
        Password = password;
        Email = email;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getNama()
    {
        return Nama;
    }

    public void setNama(String nama)
    {
        Nama = nama;
    }

    public String getPassword()
    {
        return Password;
    }

    public void setPassword(String password)
    {
        Password = password;
    }
}
