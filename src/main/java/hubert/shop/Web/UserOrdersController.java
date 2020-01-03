package hubert.shop.Web;

import hubert.shop.data.OrderRepository;
import hubert.shop.model.Order;
import hubert.shop.model.ProductPattern;
import hubert.shop.model.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("user_orders")
public class UserOrdersController {
    private OrderRepository orderRepository;

    public UserOrdersController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;

    }

    @GetMapping
    public String allOrders(@AuthenticationPrincipal User user,
                            Model model) {
        List<Order> list = orderRepository.findByUser(user);
        System.out.println(list);
        model.addAttribute("orders", list);
        model.addAttribute("productPattern", new ProductPattern());
        return "user_orders";
    }

    @RequestMapping(value = "/order/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable Long id) {
        orderRepository.deleteById(id);
        return "redirect:/user_orders";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(@ModelAttribute("productPattern") ProductPattern pattern,
                         @AuthenticationPrincipal User user,
                         HttpSession  session){
        List<Order> orderWithPattern = new ArrayList<>();
        List<Order> list = orderRepository.findByUser(user);
        for (Order order : list) {
            boolean flag = order.getCart().getProducts().stream()
                    .anyMatch(product -> product.getName().contains(pattern.getPattern()));
            if(flag){
                orderWithPattern.add(order);
            }
        }
        session.setAttribute("patternOrders", orderWithPattern);
        return "redirect:/searchOrder";
    }
}
