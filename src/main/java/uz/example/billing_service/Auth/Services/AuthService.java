package uz.example.billing_service.Auth.Services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import uz.example.billing_service.Auth.DTO.LoginDTO;
import uz.example.billing_service.Auth.DTO.LoginResponseDTO;
import uz.example.billing_service.Auth.Entities.User;
import uz.example.billing_service.Auth.Exceptions.UserServiceException;
import uz.example.billing_service.Billing.DTO.RegistrationDTO;
import uz.example.billing_service.Billing.Repository.UserJpaRepository;

@Service
public class AuthService {
    private final UserJpaRepository repository;
    private final PasswordEncoder encoder;
    private final JwtService service;

    public AuthService(UserJpaRepository repository, PasswordEncoder encoder, JwtService service) {
        this.repository = repository;
        this.encoder = encoder;
        this.service = service;
    }

    public User registration(RegistrationDTO dto) {
        if (repository.existsByEmail(dto.email())) {
            throw UserServiceException.sameEmailError();
        }

        User user = new User();

        user.setEmail(dto.email());
        user.setFullName(dto.firstName() + " " + dto.lastName());
        user.setPasswordHash(encoder.encode(dto.password()));

        return repository.save(user);
    }

    public LoginResponseDTO login(LoginDTO dto) throws UserServiceException {
        User user = repository.findByEmail(dto.email())
            .orElseThrow(() -> UserServiceException.invalideEmailOrPassword());

        if (!encoder.matches(dto.password(), user.getPasswordHash())) {
            throw UserServiceException.invalideEmailOrPassword();
        }

        String token = service.generateToken(user);

        return new LoginResponseDTO(token, service.extractExpirationDate(token));
    }
}
