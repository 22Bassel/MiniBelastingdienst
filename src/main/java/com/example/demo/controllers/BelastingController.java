package com.example.demo.controllers;


import com.example.demo.services.BelastingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Belasting")
public class BelastingController {

    BelastingService belastingService;

    public BelastingController(BelastingService belastingService) {
        this.belastingService = belastingService;
    }

    @PostMapping("/InkomenBelasting/{id}/{inkomen}/{jaar}")
    public ResponseEntity<?> NieuweInkomenBelasting(@PathVariable Long id, @PathVariable double inkomen, @PathVariable int jaar){

        if(belastingService.BestondAlInkomenBelasting(id, jaar)){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("message","De belasting in dit jaar bestond al!!"));
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(belastingService.NieuweInkomenBelastingToevoegen(id,inkomen,jaar));
    }
}
