package uz.example.billing_service.Billing.Controllers.v1;

import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import uz.example.billing_service.Billing.DTO.CreateOrderDTO;
import uz.example.billing_service.Billing.DTO.OrderDTO;
import uz.example.billing_service.Billing.Services.OrderService;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@Validated
public class OrderController {
    private OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    private record OrderSaveResponse(Long id) {}

    @PostMapping("/api/v1/orders/save")
    public OrderSaveResponse save(@Valid @RequestBody CreateOrderDTO dto) {
        return new OrderSaveResponse(service.createOrder(dto).getId());
    }

    @Valid
    @GetMapping("/api/v1/orders/{id}")
    public OrderDTO getOrder(@Positive @PathVariable Long id) {
        return service.getOrder(id);
    }

    @GetMapping("/api/v1/orders")
    public Page<OrderDTO> getOrderList(
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "20") @Min(1) @Max(500) int size) {
        return service.getOrders(page, size);
    }
}
