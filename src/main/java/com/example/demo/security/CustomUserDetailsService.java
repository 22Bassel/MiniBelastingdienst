package com.example.demo.security;

import com.example.demo.database.UserRepo;
import com.example.demo.models.entities.UserEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepo userRepo;

    public CustomUserDetailsService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserEntity> user = Optional.ofNullable(userRepo.findByEmail(email));
        user.orElseThrow(() -> new UsernameNotFoundException("User not found"));

        UserEntity userEntity =user.get();

        CustomUserDetails customUserDetails= CustomUserDetails.builder()
                .id(userEntity.getId())
                .username(userEntity.getEmail())
                .password("{noop}" + userEntity.getPassword()) // normaal
                .authorities(List.of(new SimpleGrantedAuthority("ROLE_"+userEntity.getRole().name().toUpperCase())))
                .build();

        return customUserDetails;
    }
}
