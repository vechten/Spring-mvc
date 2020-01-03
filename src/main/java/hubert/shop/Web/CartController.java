package hubert.shop.Web;

import hubert.shop.data.CartRepository;
import hubert.shop.data.ProductRepository;
import hubert.shop.data.UserRepository;
import hubert.shop.model.Cart;
import hubert.shop.model.Order;
import hubert.shop.model.Product;
import hubert.shop.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/design")
@SessionAttributes("order")
@Slf4j
public class CartController {

    private final ProductRepository productRepository;
    private CartRepository cartRepository;
    private UserRepository userRepository;

    @Autowired
    public CartController(ProductRepository productRepository,
                          CartRepository cartRepository,
                          UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
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
    public String showDesignForm(Model model, Principal principal) {
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
        log.info("   --- Saving cart");

        if (errors.hasErrors()) {
            System.out.println("error");
            return "design";
        }
        Cart saved = cartRepository.save(cart);
        order.setCart(cart);
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
