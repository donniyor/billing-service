package uz.example.billing_service.Controllers.v1;

import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import uz.example.billing_service.DTO.RegistrationDTO;
import uz.example.billing_service.Service.UserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class AuthController {
    private final UserService service;

    public AuthController(UserService service) {
        this.service = service;
    }

    private record RegistrationResponse(String status, Long id) {}

    @PostMapping("/api/v1/auth/registration")
    public RegistrationResponse registration(@Valid @RequestBody RegistrationDTO dto) {
        return new RegistrationResponse("ok", this.service.registration(dto).getId());
    }
}
