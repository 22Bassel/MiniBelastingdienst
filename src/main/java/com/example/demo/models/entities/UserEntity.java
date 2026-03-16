package com.example.demo.models.entities;

import com.example.demo.enums.Role;
import com.example.demo.models.usersDTO.users.RequestNieuweUser;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="USERS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name = "VOORNAAM")
    private String voorNaam;
    @Column(name = "ACHTERNAAM")
    private String achterNaam;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "ROLE")
    @Enumerated(EnumType.STRING)
    private Role Role;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BelastingEntity> belastingen=new ArrayList<>();

    public static UserEntity vanDto(RequestNieuweUser user){
        return UserEntity.builder()
                .voorNaam(user.getVoorName())
                .achterNaam(user.getAchterName())
                .email(user.getEmail())
                .password(user.getPassword())
                .Role(user.getRole())
                .build();
    }


    public void addBelastingen(BelastingEntity belasting){
        belasting.setUser(this);
        belastingen.add(belasting);
    }
}
