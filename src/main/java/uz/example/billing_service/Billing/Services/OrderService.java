package uz.example.billing_service.Billing.Services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import uz.example.billing_service.App.Exceptions.ServiceException;
import uz.example.billing_service.Billing.DTO.CreateOrderDTO;
import uz.example.billing_service.Billing.DTO.OrderDTO;
import uz.example.billing_service.Billing.Entities.Order;
import uz.example.billing_service.Billing.Exceptions.OrderNotFoundException;
import uz.example.billing_service.Billing.Repository.OrdersRepository;

@Service
public final class OrderService {
    public static final int MAX_LIMIT = 500;

    private OrdersRepository repository;

    public OrderService(OrdersRepository repository) {
        this.repository = repository;
    }

    public Order createOrder(CreateOrderDTO dto) {
        Order order = new Order();

        order.setStatus(dto.status().getStatusCode());
        order.setTotalAmount(dto.totalAmount());
        order.setComment(dto.comment());
        order.setCurrency(dto.currency());

        return repository.save(order);
    }

    public OrderDTO getOrder(Long id) {
        if (id <= 0) {
            throw ServiceException.negativeOrZeroValue();
        }

        Order order = repository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));

        return OrderDTO.fromEnity(order);
    }

    public Page<OrderDTO> getOrders(int page, int size) {
        if (size > 500) {
            throw ServiceException.limitExceeded();
        }

        if (size <= 0 || page < 0) {
            throw ServiceException.invalidPagination();
        }

        return repository
            .findAll(PageRequest.of(page, size))
            .map(OrderDTO::fromEnity);
    }
}
