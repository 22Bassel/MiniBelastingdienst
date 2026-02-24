package com.example.demo.models.Users;


import com.example.demo.models.Belasting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GewoneUser extends User {

    //Integer = Het Jaar
    private Map<Integer,List<Belasting>> belastingMap=new HashMap<Integer,List<Belasting>>();

    public GewoneUser(Long id,
    String name,
    String email,
    String password) {
        this.id=id;
        this.name=name;
        this.email=email;
        this.password=password;
        isAdmin();
    }

    public List<Belasting> getBelastingListMetJaar(int jaar) {
        return belastingMap.get(jaar);
    }

    public void nieuweBelastingMetJaar(Belasting belasting) {
        if(belastingMap.containsKey(belasting.getBelastingJaar())){
            belastingMap.get(belasting.getBelastingJaar()).add(belasting);
        }
        else{
            List<Belasting> list=new ArrayList<Belasting>();
            list.add(belasting);
            belastingMap.put(belasting.getBelastingJaar(),list );
        }
    }

    public void setBelastingToevoegen(HashMap<Integer,List<Belasting>> belastingMap){
        this.belastingMap=belastingMap;
    }

    @Override
    public void isAdmin() {
        rol=false;
    }
}
