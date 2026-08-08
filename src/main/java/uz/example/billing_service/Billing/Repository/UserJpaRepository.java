package uz.example.billing_service.Billing.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import uz.example.billing_service.Auth.Entities.User;

public interface UserJpaRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}
