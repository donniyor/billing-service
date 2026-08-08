package uz.example.billing_service.Auth.Services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import uz.example.billing_service.Auth.Entities.User;
import uz.example.billing_service.Billing.Repository.UserJpaRepository;

@Service
public class UserDetailService implements UserDetailsService {
    private final UserJpaRepository repository;

    public UserDetailService(UserJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("Useer not found"));

        return org.springframework.security.core.userdetails.User
            .withUsername(user.getEmail())
            .password(user.getPasswordHash())
            .roles("USER") // todo add more roles
            .build();
    }
}
