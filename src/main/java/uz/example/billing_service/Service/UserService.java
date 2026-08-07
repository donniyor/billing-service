package uz.example.billing_service.Service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import uz.example.billing_service.DTO.RegistrationDTO;
import uz.example.billing_service.Entities.User;
import uz.example.billing_service.Exceptions.UserServiceException;
import uz.example.billing_service.Repository.UserJpaRepository;

@Service
public class UserService {
    private final UserJpaRepository jpaRepository;
    private final PasswordEncoder encoder;

    public UserService(UserJpaRepository jpaRepository, PasswordEncoder encoder) {
        this.jpaRepository = jpaRepository;
        this.encoder = encoder;
    }

    public User registration(RegistrationDTO dto) {
        if (jpaRepository.existsByEmail(dto.email())) {
            throw UserServiceException.sameEmailError();
        }

        User user = new User();
        user.setEmail(dto.email());
        user.setFullName(dto.firstName() + " " + dto.lastName());
        user.setPasswordHash(encoder.encode(dto.password()));

        return jpaRepository.save(user);
    }
}
