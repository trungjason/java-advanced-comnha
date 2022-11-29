package tdtu.advanced.java.thinh68.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value={"", "/", "comnha", "ComNha"})
public class CartController {
	@GetMapping("/cart")
    public String index(Model model) {
    	model.addAttribute("pageTitle", "Giỏ hàng");
    	
        return "Cart/index";
    }
}
