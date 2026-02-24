package com.example.demo.models.Users;

public abstract class User {
    Long id= 0L;
    String name="";
    String email="";
    String password="";
    Boolean rol=false ; // false: Gewone User, true: Admin

    public abstract void isAdmin();

    public Boolean getRol() {
        return rol;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
