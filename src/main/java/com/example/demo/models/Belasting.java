package com.example.demo.models;

import com.example.demo.models.Users.User;

public class Belasting {
    Long userid;
    String belastingsoort; // inkomen, onroerendezaak, dividend
    int belastingJaar;
    double inkomem;
    double belastingBedrag;

    public Belasting(Long userid, String belastingsoort, int belastingJaar, double inkomem, double belastingBedrag) {
        this.userid = userid;
        this.belastingsoort = belastingsoort;
        this.belastingJaar = belastingJaar;
        this.inkomem = inkomem;
        this.belastingBedrag = belastingBedrag;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getBelastingsoort() {
        return belastingsoort;
    }

    public void setBelastingsoort(String belastingsoort) {
        this.belastingsoort = belastingsoort;
    }

    public int getBelastingJaar() {
        return belastingJaar;
    }

    public void setBelastingJaar(int belastingJaar) {
        this.belastingJaar = belastingJaar;
    }

    public double getInkomem() {
        return inkomem;
    }

    public void setInkomem(double inkomem) {
        this.inkomem = inkomem;
    }

    public double getBelastingBedrag() {
        return belastingBedrag;
    }

    public void setBelastingBedrag(double belastingBedrag) {
        this.belastingBedrag = belastingBedrag;
    }
}
