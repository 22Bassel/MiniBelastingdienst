package com.example.demo.models.UsersDTO;

public class Admin extends User{

    public Admin(Long id,
                      String name,
                      String email,
                      String password) {
        this.id=id;
        this.name=name;
        this.email=email;
        this.password=password;
        isAdmin();
    }

    @Override
    public void isAdmin() {
        isAdmin=true;
    }


}
