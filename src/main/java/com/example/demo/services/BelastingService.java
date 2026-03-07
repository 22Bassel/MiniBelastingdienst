package com.example.demo.services;

import com.example.demo.database.BelastingRepo;
import com.example.demo.database.UserRepo;
import com.example.demo.models.entities.BelastingEntity;
import com.example.demo.models.entities.UserEntity;
import com.example.demo.models.usersDTO.users.ResponseUser;
import com.example.demo.models.usersDTO.belasting.ResponseBelasting;
import com.example.demo.services.belastingberekenen.Inkomenbelastingberekenen;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BelastingService {

    private final BelastingRepo belastingRepo;
    private final UserRepo userRepo;

    public BelastingService(BelastingRepo belastingRepo, UserRepo userRepo) {
        this.belastingRepo = belastingRepo;
        this.userRepo = userRepo;
    }

    public List<ResponseBelasting> NieuweInkomenBelastingToevoegen(Long id, double inkomen, int jaar) {
        Optional<UserEntity> optionalUserEntity = Optional.of(userRepo.findById(id).orElseThrow(() -> new RuntimeException("USER NOT FOUND!")));

        UserEntity user=optionalUserEntity.get();
        user.addBelastingen(nieuweBelastingmaken("Inkomen", inkomen, jaar));

        userRepo.save(user);

        return ResponseUser.NaarDTO(user).getBelastingen();
    }



    private BelastingEntity nieuweBelastingmaken(String soort, double inkomen, int jaar) {
        double belastingbedrag = 0;
        switch (soort) {
            case "Inkomen":
                belastingbedrag = new Inkomenbelastingberekenen().berekenen(inkomen);

        }

        BelastingEntity belasting=new BelastingEntity();
        belasting.setBelastingBedrag(belastingbedrag);
        belasting.setBelastingJaar(jaar);
        belasting.setInkomen(inkomen);
        belasting.setBelastingsoort(soort);

        return belasting;
    }


    public boolean BestondAlInkomenBelasting(Long id, int jaar) {
        return belastingRepo.existsByUserIdAndBelastingJaarAndBelastingsoort(id, jaar, "Inkomen");


    }

}