package uz.example.billing_service.Auth.Controllers.v1;

import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import uz.example.billing_service.Auth.DTO.LoginDTO;
import uz.example.billing_service.Auth.DTO.LoginResponseDTO;
import uz.example.billing_service.Auth.Services.AuthService;
import uz.example.billing_service.Billing.DTO.RegistrationDTO;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class AuthController {
    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    private record RegistrationResponse(String status, Long id) {}

    @PostMapping("/api/v1/auth/registration")
    public RegistrationResponse registration(@Valid @RequestBody RegistrationDTO dto) {
        return new RegistrationResponse("ok", this.service.registration(dto).getId());
    }

    @PostMapping("/api/v1/auth/login")
    public LoginResponseDTO login(@Valid @RequestBody LoginDTO dto) {
        return service.login(dto);
    }
}
