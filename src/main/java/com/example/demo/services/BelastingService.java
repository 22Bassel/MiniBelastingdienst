package com.example.demo.services;

import com.example.demo.database.Database;
import com.example.demo.models.Belasting;
import com.example.demo.models.Users.GewoneUser;
import com.example.demo.services.Belastingberekenen.Belastingberekenen;
import com.example.demo.services.Belastingberekenen.Inkomenbelastingberekenen;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BelastingService {
    private Database database=new Database();


    public List<Belasting> NieuweInkomenBelastingToevoegen(Long id,double inkomen,int jaar){
     GewoneUser user= (GewoneUser) database.UserOphalenMetID(id);

     user.nieuweBelastingMetJaar(nieuweBelastingmaken("inkomen",id,inkomen,jaar));

     database.UserBijwerken(user);

     return user.getBelastingListMetJaar(jaar);
     };


    private Belasting nieuweBelastingmaken(String soort,Long id,double inkomen,int jaar){
        double belastingbedrag=0;
        switch (soort){
            case "inkomen":
                belastingbedrag=new Inkomenbelastingberekenen().berekenen(inkomen);

        }

        return new Belasting(id,soort,jaar,inkomen,belastingbedrag);
    }


    public boolean BestaatAlInkomenBelasting(Long id,int jaar) {
        GewoneUser user = (GewoneUser) database.UserOphalenMetID(id);

        // al in Database in dit jaar
        for (Belasting belasting : (ArrayList<Belasting>) user.getBelastingListMetJaar(jaar))
            if (belasting.getBelastingsoort().equals("inkomen")) {
                return true;
            }

        return false;
    }
    }

