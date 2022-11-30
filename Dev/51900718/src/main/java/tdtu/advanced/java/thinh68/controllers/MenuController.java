package tdtu.advanced.java.thinh68.controllers;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tdtu.advanced.java.thinh68.models.MonAn;
import tdtu.advanced.java.thinh68.models.NhomMonAn;
import tdtu.advanced.java.thinh68.services.MonAnService;
import tdtu.advanced.java.thinh68.services.NhomMonAnService;
import tdtu.advanced.java.thinh68.services.PhieuGoiMonService;

@Controller
@RequestMapping(value={"", "/", "comnha", "ComNha"})
public class MenuController {
	@Autowired
	private NhomMonAnService nhomMonAnService;
	
	@Autowired
	private MonAnService monAnService;
	
	@GetMapping("/menu")
    public String index(Model model, @RequestParam(name = "ma_nhom_mon_an", required= false) Long maNhomMonAn) {
    	model.addAttribute("pageTitle", "Thực đơn");
    	List<NhomMonAn> nhomMonAns = nhomMonAnService.listAll();
    	NhomMonAn nhomMonAnCurrent = null;
    	List<MonAn> monAns = null;
    	
    	if ( maNhomMonAn == null && nhomMonAns.size() != 0 ) {
    		nhomMonAnCurrent = nhomMonAns.get(0);
    		monAns = monAnService.getMonAnByMaNhom(nhomMonAnCurrent.getMaNhom());
    	} else {
    		nhomMonAnCurrent = nhomMonAns.stream()
    				.filter(nhomMonAn -> nhomMonAn.getMaNhom() == maNhomMonAn)			
    				.findAny().orElse(null);
    		
    		monAns = monAnService.getMonAnByMaNhom(nhomMonAnCurrent.getMaNhom());
    	}
    	
    	model.addAttribute("nhomMonAn", nhomMonAns);
    	model.addAttribute("monAn", monAns);
    	
        return "Menu/index";
    }
	
	@GetMapping("/menu/chitiet/{id}")
	public String detail(@PathVariable Long id, Model model) {
		MonAn monAn = monAnService.get(id);
		
		model.addAttribute("pageTitle", "Món ăn");
		model.addAttribute("monAn", monAn);
		
		return "Menu/detail";
	}
}
