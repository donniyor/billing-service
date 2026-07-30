package uz.example.billing_service.Service;

import org.springframework.stereotype.Service;

import uz.example.billing_service.DTO.OrderDTO;
import uz.example.billing_service.Entities.Order;
import uz.example.billing_service.Repository.OrdersRepository;

@Service
public class OrderService {
    private OrdersRepository repository;

    public OrderService(OrdersRepository repository) {
        this.repository = repository;
    }

    public Order createOrder(OrderDTO dto) {
        Order order = new Order();

        order.setStatus(dto.status().getStatusCode());
        order.setTotalAmount(dto.totalAmount());
        order.setComment(dto.comment());
        order.setCurrency(dto.currency());

        return repository.save(order);
    }
}
