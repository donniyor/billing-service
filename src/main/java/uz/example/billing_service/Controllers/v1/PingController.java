package uz.example.billing_service.Controllers.v1;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public final class PingController {

    private record PingResponse(String status, LocalDateTime serverTime) {}

    @GetMapping("/v1/ping")
    public PingResponse ping()
    {
        return new PingResponse("ok", LocalDateTime.now());
    }
}
