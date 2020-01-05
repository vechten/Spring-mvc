package hubert.shop.Web;

import hubert.shop.data.ProductRepository;
import hubert.shop.model.Cart;
import hubert.shop.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/products")
@SessionAttributes("order")
@Slf4j
public class ProductController {
    private ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @ModelAttribute(name = "newProduct")
    public Product design() {
        return new Product();
    }

    @ModelAttribute(name = "types")
    public Product.Type[] types() {
        return Product.Type.values();
    }

    @GetMapping
    public String showProducts(Model model) {
        model.addAttribute("products", productRepository.getAllByActiveTrue());
        return "products";
    }

    @PostMapping
    public String addProduct(@Valid Product newProduct) {
        productRepository.save(newProduct);
        return "redirect:/products";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable String id) {
        Product product = productRepository.findById(id).get();
        product.setActive(false);
        productRepository.save(product);
        return "redirect:/products";
    }
}
