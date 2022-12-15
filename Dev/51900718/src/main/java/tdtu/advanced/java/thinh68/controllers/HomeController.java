package tdtu.advanced.java.thinh68.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import tdtu.advanced.java.thinh68.models.KhachHang;
import tdtu.advanced.java.thinh68.models.LichHen;

@Controller
@RequestMapping(value = { "", "/", "comnha", "ComNha" })
public class HomeController {

    @GetMapping(value={"/","home"})
    public String index(Model model) {
    	model.addAttribute("pageTitle", "Trang chủ");
    	
        return "ComNha/index";
    }
    
    @GetMapping("/book")
    public String book(Model model) {
    	model.addAttribute("pageTitle", "Đặt bàn");
    	model.addAttribute("khachHang", new KhachHang());
    	model.addAttribute("lichHen", new LichHen());
    	
        return "ComNha/book";
    }

    @GetMapping("/contact")
    public String contact(Model model) {
    	model.addAttribute("pageTitle", "Liên hệ");
    	
        return "ComNha/contact";
    }
    
    @GetMapping("/introduce")
    public String introduce(Model model) {
    	model.addAttribute("pageTitle", "Giới thiệu");
    	
        return "ComNha/introduce";
    }
    
    @GetMapping("/member")
    public String member(Model model) {
    	model.addAttribute("pageTitle", "Hội viên");
    	
        return "ComNha/member";
    }
}
