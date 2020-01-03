package hubert.shop.Web;

import hubert.shop.model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/searchOrder")
public class SearchOrderController {

    @GetMapping
    public String showDesignForm(Model model, Principal principal,
                                 HttpSession session) {
       model.addAttribute("patternOrders", session.getAttribute("patternOrders"));
       return "searchOrder";
    }
}
