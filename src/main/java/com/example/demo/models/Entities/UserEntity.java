package com.example.demo.models.Entities;

import com.example.demo.models.UsersDTO.Users.RequestNieuweUser;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="Users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name = "voorName")
    private String voorName;
    @Column(name = "achterName")
    private String achterName;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "isAdmin")
    private Boolean isAdmin;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BelastingEntity> belastingen=new ArrayList<>();

    public static UserEntity vanDto(RequestNieuweUser user){
        return UserEntity.builder()
                .voorName(user.getVoorName())
                .achterName(user.getAchterName())
                .email(user.getEmail())
                .password(user.getPassword())
                .isAdmin(user.getIsAdmin())
                .build();
    }


    public void addBelastingen(BelastingEntity belasting){
        belasting.setUser(this);
        belastingen.add(belasting);
    }
}
