package uz.example.billing_service.Billing.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import uz.example.billing_service.Billing.Entities.Order;

public interface OrdersRepository extends JpaRepository<Order, Long> {   
}
