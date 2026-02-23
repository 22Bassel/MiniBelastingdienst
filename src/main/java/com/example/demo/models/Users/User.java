package com.example.demo.models.Users;

public abstract class User {
    int id=0;
    String name="";
    String email="";
    String password="";
    Boolean rol=false ; // false: Gewone User, true: Admin

    public abstract void isAdmin();

}
