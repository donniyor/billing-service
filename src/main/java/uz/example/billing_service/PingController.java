package uz.example.billing_service;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {

    public record PingResponse(String status, LocalDateTime serverTime) {}

    @GetMapping("/ping")
    public PingResponse ping()
    {
        return new PingResponse("ok", LocalDateTime.now());
    }
}
