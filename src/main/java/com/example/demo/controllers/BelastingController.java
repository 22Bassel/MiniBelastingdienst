package com.example.demo.controllers;


import com.example.demo.models.usersDTO.users.ResponseUser;
import com.example.demo.services.BelastingService;
import com.example.demo.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Belasting")
public class BelastingController {

    BelastingService belastingService;
    UserService userService;


    public BelastingController(BelastingService belastingService,UserService userService) {
        this.belastingService = belastingService;
        this.userService=userService;
    }

    @PreAuthorize("#id == authentication.principal.id")
    @PostMapping("/InkomenBelasting/{id}/{inkomen}/{jaar}")
    public ResponseEntity<?> NieuweInkomenBelasting(@PathVariable Long id, @PathVariable double inkomen, @PathVariable int jaar){

        if(belastingService.BestondAlInkomenBelasting(id, jaar)){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("message","De belasting in dit jaar bestond al!!"));
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(belastingService.NieuweInkomenBelastingToevoegen(id,inkomen,jaar));
    }


    @PreAuthorize("#id == authentication.principal.id or hasRole('Admin')")
    @GetMapping("User/{id}")
    public ResponseEntity<?> getAlleBelastingVanDezeUser(@PathVariable Long id){

        if(!userService.BestondAlDezeUser(id)){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message","Deze id staat niet in DataBase!! "));
        }

        return ResponseEntity.status(HttpStatus.OK).body(belastingService.GetBelastingen(id));
    }

    @PreAuthorize("#id == authentication.principal.id or hasRole('Admin')")
    @GetMapping("User/{id}/{jaar}")
    public ResponseEntity<?> getBelastingenVanDezeUserInJaar(@PathVariable Long id,@PathVariable int jaar){

        if(!userService.BestondAlDezeUser(id)){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message","Deze id staat niet in DataBase!! "));
        }

        return ResponseEntity.status(HttpStatus.OK).body(belastingService.GetBelastingeninJaar(id,jaar));
    }
}
