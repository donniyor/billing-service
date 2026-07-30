package uz.example.billing_service.Controllers.v1;

import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import uz.example.billing_service.DTO.OrderDTO;
import uz.example.billing_service.Service.OrderService;

@RestController
public class OrderController {
    private OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    private record OrderSaveResponse(Long id) {}

    @PostMapping("/v1/orders/save")
    public OrderSaveResponse save(@Valid @RequestBody OrderDTO dto)
    {
        return new OrderSaveResponse(service.createOrder(dto).getId());
    }
}
