package uz.example.billing_service.Controllers.v1;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import uz.example.billing_service.DTO.OrderDTO;
import uz.example.billing_service.Entities.Order;
import uz.example.billing_service.Repository.OrdersRepository;

@RestController
public class OrderController {
    private final OrdersRepository repository;

    public OrderController(OrdersRepository repository) {
        this.repository = repository;
    }

    private record OrderSaveResponse(Long id) {}

    @PostMapping("/v1/orders/save")
    public OrderSaveResponse save(@RequestBody OrderDTO dto)
    {
        Order order = new Order();

        order.setStatus(dto.status());
        order.setTotalAmount(dto.totalAmount());
        order.setComment(dto.comment());
        order.setCurrency(dto.currency());

        Order result = repository.save(order);

        return new OrderSaveResponse(result.getId());
    }
}
