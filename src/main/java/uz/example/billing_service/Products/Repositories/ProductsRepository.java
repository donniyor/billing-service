package uz.example.billing_service.Products.Repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import uz.example.billing_service.Products.Entities.Product;

public interface ProductsRepository extends JpaRepository<Product, Long> {

    public Optional<Product> findByIdAndDeletedAtIsNull(Long id);

    public Optional<Product> findById(Long id);

    public Page<Product> findAllByDeletedAtIsNull(Pageable pageable);
}
