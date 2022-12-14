package tdtu.advanced.java.thinh68.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import tdtu.advanced.java.thinh68.models.NhanVien;
import tdtu.advanced.java.thinh68.models.TaiKhoan;
import tdtu.advanced.java.thinh68.services.MonAnService;
import tdtu.advanced.java.thinh68.services.NhanVienService;
import tdtu.advanced.java.thinh68.services.NhomMonAnService;
import tdtu.advanced.java.thinh68.services.TaiKhoanService;

@Controller
@RequestMapping(value = { "Admin", "admin" })
public class AdminController {
	@Autowired
	private NhomMonAnService nhomMonAnService;
	@Autowired
	private TaiKhoanService taiKhoanService;
	@Autowired
	private NhanVienService nhanVienService;
	@Autowired
	private MonAnService monAnService;

	@GetMapping(value = { "", "/" })
	public String index(HttpSession session, Model model) {

		if (session.getAttribute("account_id") == null) {
			return "redirect:/admin/login";
		}

		NhanVien nhanVien = nhanVienService.getByAccountId((long) session.getAttribute("account_id"));
		session.setAttribute("role", nhanVien.getChucVu());
		
		model.addAttribute("pageTitle", "Quản lý nhà hàng");
		model.addAttribute("nhanvien", nhanVien);
		return "RestaurentManagement/Index";
	}

	@RequestMapping("/login")
	public String login(HttpSession session, HttpServletRequest request, Model model) {
		if (session.getAttribute("account_id") != null) {
			return "redirect:/admin";
		}

		model.addAttribute("pageTitle", "Đăng nhập");

		if (request.getMethod().equalsIgnoreCase("GET")) {
			return "RestaurentManagement/Login";
		}

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		TaiKhoan account = taiKhoanService.get(username);

		if (account == null) {
			model.addAttribute("message", "Tài khoản không tồn tại");
		} else {
			if (BCrypt.checkpw(password, account.getMatKhau())) {
				session.setAttribute("account_id", account.getId());
				return "redirect:/admin";
			}
			model.addAttribute("message", "Sai mật khẩu");
		}
		return "RestaurentManagement/Login";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {

		if (session.getAttribute("account_id") == null) {
			return "redirect:/admin/login";
		}

		session.invalidate();
		return "redirect:/admin";
	}

}
