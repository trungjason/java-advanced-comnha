package tdtu.advanced.java.thinh68.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value={"", "/", "comnha", "ComNha"})
public class MenuController {
	@GetMapping("/menu")
    public String index(Model model) {
    	model.addAttribute("pageTitle", "Thực đơn");
    	
        return "Menu/index";
    }
	
	@GetMapping("/menu/detail/{id}")
	public String detail(@PathVariable String id, Model model) {
		System.out.println(id);
		model.addAttribute("pageTitle", "Món ăn");
		
		return "Menu/detail";
	}
}
