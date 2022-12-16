package tdtu.advanced.java.thinh68.controllers;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.Getter;
import lombok.Setter;
import tdtu.advanced.java.thinh68.models.HoaDon;
import tdtu.advanced.java.thinh68.models.NhanVien;
import tdtu.advanced.java.thinh68.services.HoaDonService;
import tdtu.advanced.java.thinh68.services.MonAnService;
import tdtu.advanced.java.thinh68.services.NhanVienService;
import tdtu.advanced.java.thinh68.services.NhomMonAnService;
import tdtu.advanced.java.thinh68.services.TaiKhoanService;

@Controller
@RequestMapping(value = { "Admin", "admin" })
public class RevenueController {
	@Autowired 
	private HoaDonService hoaDonService;
	
	@Autowired
	private NhanVienService nhanVienService;
	
	@GetMapping(value = "/revenue")
	public String index(HttpSession session, Model model) {
		if (session.getAttribute("account_id") == null
				|| !session.getAttribute("role").toString().equalsIgnoreCase("Quản lý nhà hàng")) {
			return "redirect:/admin/login";
		} 
		
		NhanVien nhanVien = nhanVienService.getByAccountId((long) session.getAttribute("account_id"));
		model.addAttribute("nhanvien", nhanVien);

		
		return "RestaurentManagement/Revenue";
	}
	
	@PostMapping(value = "/api/revenue")
	@ResponseBody
	public ResponseJSONForRevenue getRevenue(@RequestParam("fromTime") Date fromTime,
			@RequestParam("toTime") Date toTime,
			HttpSession session) {
		if (session.getAttribute("account_id") == null
				|| !session.getAttribute("role").toString().equalsIgnoreCase("Quản lý nhà hàng")) {
			return new ResponseJSONForRevenue(false, "Bạn không có quyền sử dụng tính năng này!");
		} 
				
		Timestamp fromTimestamp =new Timestamp(fromTime.getTime());  
		Timestamp toTimestamp =new Timestamp(toTime.getTime());  
				
		return new ResponseJSONForRevenue(true, "Thống kê doanh thu thành công", hoaDonService.thongKe(fromTimestamp, toTimestamp));
	}
}

@Getter
@Setter
class ResponseJSONForRevenue {
	boolean status;
	String message;
	List<HoaDon> data;

	ResponseJSONForRevenue(boolean status, String message) {
		this.status = status;
		this.message = message;
	}
	
	ResponseJSONForRevenue(boolean status, String message, List<HoaDon> data) {
		this.status = status;
		this.message = message;
		this.data = data;
	}
}