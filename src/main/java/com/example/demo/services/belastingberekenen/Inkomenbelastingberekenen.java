package com.example.demo.services.belastingberekenen;

public class Inkomenbelastingberekenen implements Belastingberekenen {
    @Override
    public double berekenen(double geld) {
        if(geld<0){
            return 0;
        }
        else if(geld<20000){
            return geld*0.2;
        } else if (geld<40000) {
            return geld*0.3;
        }
        else if (geld<60000){
            return geld*0.4;
        }
        else
            return geld*0.5;
    }
}
