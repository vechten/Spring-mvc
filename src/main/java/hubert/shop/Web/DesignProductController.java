package hubert.shop.Web;

import hubert.shop.data.CartRepository;
import hubert.shop.data.OrderRepository;
import hubert.shop.data.ProductRepository;
import hubert.shop.model.Cart;
import hubert.shop.model.Order;
import hubert.shop.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignProductController {

    private final ProductRepository productRepository;

    private CartRepository cartRepository;

    @Autowired
    public DesignProductController(ProductRepository productRepository,
                                   CartRepository cartRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }

    @ModelAttribute(name = "design")
    public Cart design() {
        return new Cart();
    }

    @GetMapping
    public String showDesignForm(Model model) {
        List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(i -> products.add(i));

        Product.Type[] types = Product.Type.values();
        for (Product.Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(products, type));
        }
        return "design";
    }

    @PostMapping
    public String processDesign(@Valid Cart cart, Errors errors,
                                @ModelAttribute Order order) {
        if (errors.hasErrors()) {
            return "design";
        }


        Cart saved = cartRepository.save(cart);
        order.addDesign(saved);
        return "redirect:/orders/current";
    }

    private List<Product> filterByType(
            List<Product> products, Product.Type type) {
        return products
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}
