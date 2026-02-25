package com.example.demo.controllers;

import com.example.demo.models.Belasting;
import com.example.demo.services.BelastingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/belasting")
public class BelastingController {

    BelastingService belastingService=new BelastingService();

    @PostMapping("/InkomenBelasting/{id}/{inkomen}/{jaar}")
    public List<Belasting> NieuweInkomenBelasting(@PathVariable Long id,@PathVariable double inkomen,@PathVariable int jaar){

        if(belastingService.BestaatAlInkomenBelasting(id, jaar)){
            throw new IllegalStateException("Er bestaat al een belastingaangifte");
        }

        return belastingService.NieuweInkomenBelastingToevoegen(id,inkomen,jaar);
    }
}
