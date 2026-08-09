package uz.example.billing_service.Products.Controllers.v1;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class AdminProductsController {

    @GetMapping("/api/v1/admin/products")
    public String getMethodName(@RequestParam String param) {
        return new String();
    }
}
