package com.example.demo.models.entities;


import com.example.demo.models.usersDTO.belasting.RequestNieuweBelasting;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name ="Belastingen")
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
    @JoinColumn(name = "userid")
    private UserEntity user;
    @Column(name = "belastingsoort")
    private String belastingsoort; // inkomen, onroerendezaak, dividend
    @Column(name = "belastingJaar")
    private int belastingJaar;
    @Column(name = "inkomem")
    private double inkomem;
    @Column(name = "belastingBedrag")
    private double belastingBedrag;

    public static BelastingEntity vanDTO(RequestNieuweBelasting belasting,UserEntity user){
        return BelastingEntity.builder()
                .user(user)
                .belastingsoort(belasting.getBelastingsoort())
                .belastingJaar(belasting.getBelastingJaar())
                .inkomem(belasting.getInkomem())
                .belastingBedrag(belasting.getBelastingBedrag())
                .build();
    }

}
