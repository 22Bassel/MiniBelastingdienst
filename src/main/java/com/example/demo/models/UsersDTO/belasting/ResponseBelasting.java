package com.example.demo.models.UsersDTO.belasting;

import com.example.demo.models.Entities.BelastingEntity;
import jakarta.persistence.Column;
import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseBelasting {
    String belastingsoort; // inkomen, onroerendezaak, dividend
    int belastingJaar;
    double inkomem;
    double belastingBedrag;

    public static ResponseBelasting NaarDTO(BelastingEntity belasting){
        return ResponseBelasting.builder()
                .belastingsoort(belasting.getBelastingsoort())
                .belastingJaar(belasting.getBelastingJaar())
                .inkomem(belasting.getInkomem())
                .belastingBedrag(belasting.getBelastingBedrag())
                .build();
    }
}
