package hubert.shop.Web;

import hubert.shop.data.OrderRepository;
import hubert.shop.model.Order;
import hubert.shop.model.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        return "user_orders";
    }

    @RequestMapping(value = "/order/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable Long id) {
        orderRepository.deleteById(id);
        return "redirect:/user_orders";
    }
}
