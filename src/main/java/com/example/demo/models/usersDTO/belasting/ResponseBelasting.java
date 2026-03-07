package com.example.demo.models.usersDTO.belasting;

import com.example.demo.models.entities.BelastingEntity;
import lombok.*;

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
