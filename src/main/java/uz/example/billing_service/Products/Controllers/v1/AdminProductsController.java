package uz.example.billing_service.Products.Controllers.v1;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import uz.example.billing_service.Products.DTO.CreateProductDTO;
import uz.example.billing_service.Products.DTO.ProductDTO;
import uz.example.billing_service.Products.DTO.UpdateProductDTO;
import uz.example.billing_service.Products.Services.ProductsService;

import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
public class AdminProductsController {

    private final ProductsService service;

    public AdminProductsController(ProductsService service) {
        this.service = service;
    }

    @GetMapping("/api/v1/admin/products/{id}")
    public ProductDTO getProduct(@Positive @PathVariable Long id) {
        return service.getProductById(id, true);
    }

    @GetMapping("/api/v1/admin/products")
    public Page<ProductDTO> getProducts(
        @RequestParam(defaultValue = "0") @Min(0) int page,
        @RequestParam(defaultValue = "20") @Min(1) @Max(500) int size
    ) {
        return service.getProducts(page, size, true);
    }

    @PostMapping("/api/v1/admin/products")
    public ProductDTO createProduct(@Valid @RequestBody CreateProductDTO dto) {
        return service.createProduct(dto);
    }

    @PutMapping("/api/v1/admin/products/{id}")
    public ProductDTO updateProduct(
        @PathVariable Long id,
        @RequestBody @Valid UpdateProductDTO dto
    ) {
        return service.updateProduct(id, dto);
    }

    private record ProductDeletedResponse(String status, boolean deleted) {}

    @DeleteMapping("/api/v1/admin/products/{id}")
    public ProductDeletedResponse deleteProduct(@PathVariable Long id) {
        service.deleteProduct(id);

        return new ProductDeletedResponse("ok", true);
    }
}
