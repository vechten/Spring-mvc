package hubert.shop.Web;

import hubert.shop.data.OrderRepository;
import hubert.shop.model.Order;
import hubert.shop.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.util.UUID;

@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {
    private OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    @GetMapping("/current")
    public String orderForm(@AuthenticationPrincipal User user,
                            @ModelAttribute Order order) {

        return "orderForm";
    }

    @GetMapping
    public String allOrders(){
        return "orders";
    }

    @PostMapping
    public String processOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus,
                               @AuthenticationPrincipal User user) {
        if (errors.hasErrors()) {
            return "orderForm";
        }

        order.setUser(user);
        orderRepository.save(order);
        sessionStatus.setComplete();

        return "redirect:/orders";
    }
}
