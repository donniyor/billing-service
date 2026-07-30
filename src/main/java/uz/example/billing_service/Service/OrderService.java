package uz.example.billing_service.Service;

import org.springframework.stereotype.Service;

import uz.example.billing_service.DTO.OrderDTO;
import uz.example.billing_service.DTO.OrderResponseDTO;
import uz.example.billing_service.Entities.Order;
import uz.example.billing_service.Exceptions.OrderNotFoundException;
import uz.example.billing_service.Exceptions.ServiceException;
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

    public OrderResponseDTO getOrder(Long id) {
        if (id <= 0) {
            throw ServiceException.negativeOrZeroValue();
        }

        Order order = repository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));

        return new OrderResponseDTO(
                order.getId(),
                order.getStatus(),
                order.getTotalAmount(),
                order.getCurrency(),
                order.getComment(),
                order.getCreatedAt(),
                order.getUpdatedAt());
    }
}
