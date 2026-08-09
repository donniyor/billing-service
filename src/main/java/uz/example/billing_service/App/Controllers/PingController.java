package uz.example.billing_service.App.Controllers;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public final class PingController {

    private record PingResponse(String status, LocalDateTime serverTime) {}

    @GetMapping("/api/v1/ping")
    public PingResponse ping()
    {
        return new PingResponse("ok", LocalDateTime.now());
    }
}
