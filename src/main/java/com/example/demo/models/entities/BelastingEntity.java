package com.example.demo.models.entities;


import com.example.demo.models.usersDTO.belasting.RequestNieuweBelasting;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name ="BELASTINGEN")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BelastingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "USERID")
    private UserEntity user;
    @Column(name = "BELASTINGSOORT")
    private String belastingsoort; // inkomen, onroerendezaak, dividend
    @Column(name = "BELASTINGJAAR")
    private int belastingJaar;
    @Column(name = "INKOMEN")
    private double inkomen;
    @Column(name = "BELASTINGBEDRAG")
    private double belastingBedrag;

    public static BelastingEntity vanDTO(RequestNieuweBelasting belasting,UserEntity user){
        return BelastingEntity.builder()
                .user(user)
                .belastingsoort(belasting.getBelastingsoort())
                .belastingJaar(belasting.getBelastingJaar())
                .inkomen(belasting.getInkomem())
                .belastingBedrag(belasting.getBelastingBedrag())
                .build();
    }

}
