package uz.example.billing_service.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import uz.example.billing_service.Entities.Order;

public interface OrdersRepository extends JpaRepository<Order, Long> {   
}
