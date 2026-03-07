package com.example.demo.models.UsersDTO.belasting;


import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestNieuweBelasting {
    Long userid;
    String belastingsoort; // inkomen, onroerendezaak, dividend
    int belastingJaar;
    double inkomem;
    double belastingBedrag;
}
