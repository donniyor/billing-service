package uz.example.billing_service.Products.Services;

import java.time.Instant;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import uz.example.billing_service.App.Constants.System;
import uz.example.billing_service.App.Constants.SystemApp;
import uz.example.billing_service.App.Exceptions.ServiceException;
import uz.example.billing_service.Products.DTO.CreateProductDTO;
import uz.example.billing_service.Products.DTO.ProductDTO;
import uz.example.billing_service.Products.DTO.UpdateProductDTO;
import uz.example.billing_service.Products.Entities.Product;
import uz.example.billing_service.Products.Repositories.ProductsRepository;

@Service
public class ProductsService {

    private final ProductsRepository repository;

    public ProductsService(ProductsRepository repository) {
        this.repository = repository;
    }

    public ProductDTO getProductById(Long id) {
        return getProductById(id, false);
    }

    public ProductDTO getProductById(Long id, boolean withDeleted) throws ServiceException {
        Product product = withDeleted
            ? this.repository
                .findById(id)
                .orElseThrow(() -> ServiceException.notFoundById("Product"))
            : this.repository
                .findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> ServiceException.notFoundById("Product"));

        return ProductDTO.fromEntity(product);
    }

    public Page<ProductDTO> getProducts(int page, int size) {
        return getProducts(page, size, false);
    }

    public Page<ProductDTO> getProducts(int page, int size, boolean withDeleted) throws ServiceException {
        if (size > SystemApp.MAX_LIMIT_SIZE) {
            throw ServiceException.limitExceeded();
        }

        if (size <= 0 || page < 0) {
            throw ServiceException.invalidPagination();
        }

        Page<Product> products = withDeleted
            ? repository.findAll(PageRequest.of(page, size))
            : repository.findAllByDeletedAtIsNull(PageRequest.of(page, size));

        return products.map(ProductDTO::fromEntity);
    }

    public ProductDTO createProduct(CreateProductDTO dto) {
        Product product = new Product();

        product.setName(dto.name());
        product.setDescription(dto.description());
        product.setCurrency(dto.currency());
        product.setPrice(dto.price());

        return ProductDTO.fromEntity(repository.save(product));
    }

    @Transactional
    public ProductDTO updateProduct(Long id, UpdateProductDTO dto) {
        Product product = repository
            .findById(id)
            .orElseThrow(() -> ServiceException.entityNotFound("Product"));

        if (null != dto.name()) {
            product.setName(dto.name());
        }

        if (null != dto.description()) {
            product.setDescription(dto.description());
        }

        if (null != dto.price()) {
            product.setPrice(dto.price());
        }

        return ProductDTO.fromEntity(repository.save(product));
    }

    @Transactional
    public void deleteProduct(Long id) {
        Product product = repository
            .findByIdAndDeletedAtIsNull(id)
            .orElseThrow(() -> ServiceException.entityNotFound("Product"));

        product.setDeletedAt(Instant.now());

        repository.save(product);
    }
}
