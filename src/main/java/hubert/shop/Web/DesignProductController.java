package hubert.shop.Web;

import hubert.shop.model.Order;
import hubert.shop.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignProductController {
    @ModelAttribute
    public void addProductsToModel(Model model) {
        List<Product> products = Arrays.asList(
                new Product(UUID.randomUUID(), "Pizza de la mount", Product.Type.PIZZA, "Italian, food, pizza"),
                new Product(UUID.randomUUID(), "Spaghetti Carbonare", Product.Type.PASTA, "Italian, pasta, food"),
                new Product(UUID.randomUUID(), "Coke", Product.Type.DRINK, "coke, drink, cola"),
                new Product(UUID.randomUUID(), "Nachos", Product.Type.NACHOS, "coke, drink, cola"),
                new Product(UUID.randomUUID(), "Sauce BBQ", Product.Type.SAUCE, "coke, drink, cola")
        );
        Product.Type[] types = Product.Type.values();
        for (Product.Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(products, type));
        }
    }

    @GetMapping
    public String showDesignForm(Model model) {
        model.addAttribute("design", new Order());
        return "design";
    }

    @PostMapping
    public String processDesign(@Valid @ModelAttribute("design") Order design, Errors errors, Model model) {
        if (errors.hasErrors()) {
            return "design";
        }


        log.info("Processing design: " + design);

        return "redirect:/orders/current";
    }

    private List<Product> filterByType(
            List<Product> ingredients, Product.Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}
