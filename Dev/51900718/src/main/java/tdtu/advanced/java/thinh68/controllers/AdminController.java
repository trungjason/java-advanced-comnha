package tdtu.advanced.java.thinh68.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import tdtu.advanced.java.thinh68.models.MonAn;
import tdtu.advanced.java.thinh68.models.NhanVien;
import tdtu.advanced.java.thinh68.models.NhomMonAn;
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

	@GetMapping("/staffs")
	public String staffs(HttpSession session, Model model) {

		if (session.getAttribute("account_id") == null
				|| !session.getAttribute("role").toString().equalsIgnoreCase("Quản lý nhà hàng")) {
			return "redirect:/admin/login";
		}

		NhanVien nhanVien = nhanVienService.getByAccountId((long) session.getAttribute("account_id"));
		model.addAttribute("nhanvien", nhanVien);
		model.addAttribute("pageTitle", "Quản lý nhân viên");
		return "RestaurentManagement/Staff/Index";
	}

	// API Get all nhân viên except Quản lý
	@GetMapping("/staffs/get-staffs")
	@ResponseBody
	public List<NhanVien> getStaffs(HttpSession session) {

		if (session.getAttribute("account_id") == null
				|| !session.getAttribute("role").toString().equalsIgnoreCase("Quản lý nhà hàng")) {
			return null;
		}

		return nhanVienService.listAll();
	}

	// Thêm nhân viên page
	@GetMapping("/staffs/create")
	public String createStaff(HttpSession session, Model model) {

		if (session.getAttribute("account_id") == null
				|| !session.getAttribute("role").toString().equalsIgnoreCase("Quản lý nhà hàng")) {
			return "redirect:/admin/login";
		}

		NhanVien nhanVien = nhanVienService.getByAccountId((long) session.getAttribute("account_id"));
		model.addAttribute("nhanvien", nhanVien);
		model.addAttribute("pageTitle", "Thêm nhân viên");
		return "RestaurentManagement/Staff/Create";
	}

	// Thêm nhân viên api
	@ResponseBody
	@PostMapping("/staffs/create-staff")
	public ResponseJSON createStaff(@RequestBody Map<String, Object> data, HttpSession session) {

		if (session.getAttribute("account_id") == null
				|| !session.getAttribute("role").toString().equalsIgnoreCase("Quản lý nhà hàng")) {
			return new ResponseJSON(false, "Bạn không có quyền thực hiện chức năng này");
		}

		double luong = Double.parseDouble(data.get("luong").toString());
		String tenNhanVien = (String) data.get("tenNhanVien");
		String email = data.get("email").toString().trim();
		if (luong < 1) {
			return new ResponseJSON(false, "Lương phải lớn hơn 0");
		}
		if (tenNhanVien.length() < 1) {
			return new ResponseJSON(false, "Tên nhân viên không được để trống");
		}
		if (email.length() < 1) {
			return new ResponseJSON(false, "Email không được để trống");
		}

		if (taiKhoanService.get(email) != null) {
			return new ResponseJSON(false, "Email đã tồn tại");
		}

		TaiKhoan taikhoan = new TaiKhoan();
		taikhoan.setTenTaiKhoan(email);
		taikhoan.setMatKhau(BCrypt.hashpw(email, BCrypt.gensalt()));

		taiKhoanService.save(taikhoan);

		NhanVien nhanVien = new NhanVien();
		nhanVien.setDiaChi((String) data.get("diaChi"));
		nhanVien.setChucVu((String) data.get("chucVu"));
		nhanVien.setEmail(email);
		nhanVien.setLuong(luong);
		nhanVien.setSoDienThoai((String) data.get("soDienThoai"));
		nhanVien.setTenNhanVien(tenNhanVien);
		nhanVien.setTaiKhoan(taikhoan);
		nhanVienService.save(nhanVien);

		return new ResponseJSON(true,
				"Thêm nhân viên thành công. Tài khoản và mật khẩu đăng nhập mặc định là Email nhân viên");
	}

	// Thông tin nhân viên
	@GetMapping("/staffs/detail/{id}")
	public String staff(@PathVariable("id") long id, HttpSession session, Model model) {

		if (session.getAttribute("account_id") == null
				|| !session.getAttribute("role").toString().equalsIgnoreCase("Quản lý nhà hàng")) {
			return "redirect:/admin/login";
		}

		NhanVien nhanVien = nhanVienService.get(id);

		if (nhanVien == null) {
			return "redirect:/admin/staffs";
		}

		model.addAttribute("nhanvien", nhanVien);

		return "RestaurentManagement/Staff/Detail";
	}

	// Xóa nhân viên api
	@DeleteMapping("/staffs/delete-staff/{id}")
	@ResponseBody
	public ResponseJSON deleteStaff(@PathVariable("id") long id, HttpSession session) {

		if (session.getAttribute("account_id") == null
				|| !session.getAttribute("role").toString().equalsIgnoreCase("Quản lý nhà hàng")) {
			return new ResponseJSON(false, "Bạn không có quyền thực hiện chức năng này");
		}

		NhanVien nhanVien = nhanVienService.get(id);
		if (nhanVien == null) {
			return new ResponseJSON(false, "Nhân viên không tồn tại");
		}

		taiKhoanService.delete(nhanVien.getTaiKhoan().getId());
		nhanVienService.delete(id);

		return new ResponseJSON(true, "Xóa nhân viên thành công");
	}

	// Edit nhân viên
	@GetMapping("/staffs/edit/{id}")
	public String editStaff(@PathVariable("id") long id, HttpSession session, Model model) {

		if (session.getAttribute("account_id") == null
				|| !session.getAttribute("role").toString().equalsIgnoreCase("Quản lý nhà hàng")) {
			return "redirect:/admin/login";
		}

		NhanVien nhanVien = nhanVienService.get(id);

		if (nhanVien == null) {
			return "redirect:/admin/staffs";
		}

		model.addAttribute("nhanvien", nhanVien);

		return "RestaurentManagement/Staff/Edit";
	}

	// Sửa nhân viên api
	@PutMapping("/staffs/edit-staff")
	@ResponseBody
	public ResponseJSON editStaff(@RequestBody Map<String, Object> data, HttpSession session) {

		if (session.getAttribute("account_id") == null
				|| !session.getAttribute("role").toString().equalsIgnoreCase("Quản lý nhà hàng")) {
			return new ResponseJSON(false, "Bạn không có quyền thực hiện chức năng này");
		}

		long id = Long.parseLong((String) data.get("maNhanVien"));
		NhanVien nhanVien = nhanVienService.get(id);

		if (nhanVien == null) {
			return new ResponseJSON(false, "Nhân viên không tồn tại");
		}

		double luong = Double.parseDouble(data.get("luong").toString());
		String tenNhanVien = (String) data.get("tenNhanVien");
		String email = data.get("email").toString().trim();
		if (luong < 1) {
			return new ResponseJSON(false, "Lương phải lớn hơn 0");
		}
		if (tenNhanVien.length() < 1) {
			return new ResponseJSON(false, "Tên nhân viên không được để trống");
		}
		if (email.length() < 1) {
			return new ResponseJSON(false, "Email không được để trống");
		}

		if (taiKhoanService.get(email) != null) {
			return new ResponseJSON(false, "Email đã tồn tại");
		}

		nhanVien.setDiaChi((String) data.get("diaChi"));
		nhanVien.setChucVu((String) data.get("chucVu"));
		nhanVien.setEmail(email);
		nhanVien.setLuong(luong);
		nhanVien.setSoDienThoai((String) data.get("soDienThoai"));
		nhanVien.setTenNhanVien(tenNhanVien);
		nhanVien.getTaiKhoan().setTenTaiKhoan(email);

		nhanVienService.save(nhanVien);

		return new ResponseJSON(true, "Thông tin đã được cập nhật");
	}

	// Module FOOD / FOOD GROUP
	@GetMapping("/foods")
	public String foods(HttpSession session, Model model) {

		if (session.getAttribute("account_id") == null
				|| !session.getAttribute("role").toString().equalsIgnoreCase("Quản lý nhà hàng")) {
			return "redirect:/admin/login";
		}

		NhanVien nhanVien = nhanVienService.getByAccountId((long) session.getAttribute("account_id"));
		model.addAttribute("nhanvien", nhanVien);
		model.addAttribute("pageTitle", "Quản lý thực đơn");

		model.addAttribute("nhommonans", nhomMonAnService.listAll());
		return "RestaurentManagement/Food/Index";
	}

	// Page thông tin món ăn
	@GetMapping("/foods/detail/{id}")
	public String detail(@PathVariable("id") long id, HttpSession session, Model model) {

		if (session.getAttribute("account_id") == null
				|| !session.getAttribute("role").toString().equalsIgnoreCase("Quản lý nhà hàng")) {
			return "redirect:/admin/login";
		}

		NhanVien nhanVien = nhanVienService.getByAccountId((long) session.getAttribute("account_id"));
		model.addAttribute("nhanvien", nhanVien);
		model.addAttribute("pageTitle", "Thông tin món ăn");

		model.addAttribute("monan", monAnService.get(id));
		return "RestaurentManagement/Food/Detail";
	}

	// Page thông tin nhóm món ăn
	@GetMapping("/foods/detail-group/{id}")
	public String detailGroup(@PathVariable("id") long id, HttpSession session, Model model) {

		if (session.getAttribute("account_id") == null
				|| !session.getAttribute("role").toString().equalsIgnoreCase("Quản lý nhà hàng")) {
			return "redirect:/admin/login";
		}

		NhanVien nhanVien = nhanVienService.getByAccountId((long) session.getAttribute("account_id"));
		model.addAttribute("nhanvien", nhanVien);
		model.addAttribute("pageTitle", "Quản lý nhóm món ăn");

		model.addAttribute("nhommonan", nhomMonAnService.get(id));
		return "RestaurentManagement/Food/DetailGroup";
	}

	// Page tạo món ăn
	@GetMapping("/foods/create")
	public String createFood(HttpSession session, Model model) {

		if (session.getAttribute("account_id") == null
				|| !session.getAttribute("role").toString().equalsIgnoreCase("Quản lý nhà hàng")) {
			return "redirect:/admin/login";
		}

		NhanVien nhanVien = nhanVienService.getByAccountId((long) session.getAttribute("account_id"));
		model.addAttribute("nhanvien", nhanVien);
		model.addAttribute("pageTitle", "Thêm món ăn");

		model.addAttribute("nhommonans", nhomMonAnService.listAll());
		return "RestaurentManagement/Food/Create";
	}

	// post tạo món ăn
	@PostMapping("/foods/create")
	public String createFood(@RequestParam("image") MultipartFile image, @RequestParam("name") String name,
			@RequestParam("unit") String unit, @RequestParam("price") double price, @RequestParam("group") long group,
			@RequestParam("desc") String desc, @RequestParam("desc-detail") String descDetail, HttpSession session,
			Model model) {

		if (session.getAttribute("account_id") == null
				|| !session.getAttribute("role").toString().equalsIgnoreCase("Quản lý nhà hàng")) {
			return "redirect:/admin/login";
		}

		NhanVien nhanVien = nhanVienService.getByAccountId((long) session.getAttribute("account_id"));
		model.addAttribute("nhanvien", nhanVien);
		model.addAttribute("pageTitle", "Thêm món ăn");

		model.addAttribute("nhommonans", nhomMonAnService.listAll());

		if (name.trim().length() < 1) {
			model.addAttribute("message", "Tên nhóm món ăn không được để trống");
			return "RestaurentManagement/Food/Create";
		}
		NhomMonAn nhomMonAn = nhomMonAnService.get(group);
		if (nhomMonAn == null) {
			model.addAttribute("message", "Vui lòng chọn nhóm món ăn");
			return "RestaurentManagement/Food/Create";
		}
		if (price < 1) {
			model.addAttribute("message", "Giá món ăn phải lớn hơn 0");
			return "RestaurentManagement/Food/Create";
		}
		if (image.isEmpty()) {
			model.addAttribute("message", "Vui lòng upload hình ảnh món ăn");
			return "RestaurentManagement/Food/Create";
		}

		try {
			Path path = Paths.get(System.getProperty("user.dir") + "/src/main/resources/static/images/food-images",
					image.getOriginalFilename());
			Path servePath = Paths.get(System.getProperty("user.dir") + "/target/classes/static/images/food-images",
					image.getOriginalFilename());
			Files.write(path, image.getBytes());
			Files.write(servePath, image.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
			model.addAttribute("message", "Có lỗi xảy ra khi upload ảnh");
			return "RestaurentManagement/Food/Create";
		}

		MonAn monAn = new MonAn();
		monAn.setTenMonAn(name);
		monAn.setDonGia(price);
		monAn.setDonVi(unit);
		monAn.setMoTa(descDetail);
		monAn.setMoTaNgan(desc);
		monAn.setNhomMonAn(nhomMonAn);
		monAn.setHinhAnh(image.getOriginalFilename());
		monAnService.save(monAn);

		model.addAttribute("message", "Thêm món ăn thành công");
		return "RestaurentManagement/Food/Create";
	}

	// Page tạo nhóm món ăn
	@GetMapping("/foods/create-group")
	public String createFoodGroup(HttpSession session, Model model) {

		if (session.getAttribute("account_id") == null
				|| !session.getAttribute("role").toString().equalsIgnoreCase("Quản lý nhà hàng")) {
			return "redirect:/admin/login";
		}

		NhanVien nhanVien = nhanVienService.getByAccountId((long) session.getAttribute("account_id"));
		model.addAttribute("nhanvien", nhanVien);
		model.addAttribute("pageTitle", "Thêm nhóm món ăn");

		model.addAttribute("nhommonans", nhomMonAnService.listAll());
		return "RestaurentManagement/Food/CreateGroup";
	}

	// api tạo nhóm món ăn
	@ResponseBody
	@PostMapping("/foods/create-food-group")
	public ResponseJSON createFoodGroup(@RequestBody Map<String, Object> data, HttpSession session) {

		if (session.getAttribute("account_id") == null
				|| !session.getAttribute("role").toString().equalsIgnoreCase("Quản lý nhà hàng")) {
			return new ResponseJSON(false, "Bạn không có quyền thực hiện chức năng này");
		}

		String name = (String) data.get("tenNhom").toString().trim();
		String desc = (String) data.get("moTa").toString().trim();

		if (name.length() < 1) {
			return new ResponseJSON(false, "Tên nhóm món ăn không được để trống");
		}
		if (desc.length() < 1) {
			return new ResponseJSON(false, "Mô tả nhóm món ăn không được để trống");
		}

		NhomMonAn nhomMonAn = new NhomMonAn();
		nhomMonAn.setTenNhom(name);
		nhomMonAn.setMoTa(desc);

		nhomMonAnService.save(nhomMonAn);

		return new ResponseJSON(true, "Thêm nhóm món ăn thành công");
	}

	// Page Sửa nhóm món ăn
	@GetMapping("/foods/edit-group/{id}")
	public String editFoodGroup(@PathVariable("id") long id, HttpSession session, Model model) {

		if (session.getAttribute("account_id") == null
				|| !session.getAttribute("role").toString().equalsIgnoreCase("Quản lý nhà hàng")) {
			return "redirect:/admin/login";
		}

		NhanVien nhanVien = nhanVienService.getByAccountId((long) session.getAttribute("account_id"));
		model.addAttribute("nhanvien", nhanVien);
		model.addAttribute("pageTitle", "Sửa nhóm món ăn");

		model.addAttribute("nhommonan", nhomMonAnService.get(id));
		return "RestaurentManagement/Food/EditGroup";
	}

	// api Sửa nhóm món ăn
	@ResponseBody
	@PutMapping("/foods/edit-food-group")
	public ResponseJSON editFoodGroup(@RequestBody Map<String, Object> data, HttpSession session) {

		if (session.getAttribute("account_id") == null
				|| !session.getAttribute("role").toString().equalsIgnoreCase("Quản lý nhà hàng")) {
			return new ResponseJSON(false, "Bạn không có quyền thực hiện chức năng này");
		}

		long id = Long.parseLong(data.get("maNhom").toString());
		String name = (String) data.get("tenNhom").toString().trim();
		String desc = (String) data.get("moTa").toString().trim();

		if (name.length() < 1) {
			return new ResponseJSON(false, "Tên nhóm món ăn không được để trống");
		}
		if (desc.length() < 1) {
			return new ResponseJSON(false, "Mô tả nhóm món ăn không được để trống");
		}

		NhomMonAn nhomMonAn = nhomMonAnService.get(id);

		if (nhomMonAn == null) {
			return new ResponseJSON(false, "Nhóm món ăn không tồn tại");
		}
		nhomMonAn.setTenNhom(name);
		nhomMonAn.setMoTa(desc);

		nhomMonAnService.save(nhomMonAn);

		return new ResponseJSON(true, "Cập nhật nhóm món ăn thành công");
	}

	// api XÓA nhóm món ăn
	@ResponseBody
	@DeleteMapping("/foods/delete-food-group/{id}")
	public ResponseJSON deleteFoodGroup(@PathVariable("id") long id, HttpSession session) {

		if (session.getAttribute("account_id") == null
				|| !session.getAttribute("role").toString().equalsIgnoreCase("Quản lý nhà hàng")) {
			return new ResponseJSON(false, "Bạn không có quyền thực hiện chức năng này");
		}

		NhomMonAn nhomMonAn = nhomMonAnService.get(id);

		if (nhomMonAn == null) {
			return new ResponseJSON(false, "Nhóm món ăn không tồn tại");
		}

		nhomMonAnService.delete(id);

		return new ResponseJSON(true, "Xóa nhóm món ăn thành công");
	}

	// Page sửa món ăn
	@GetMapping("/foods/edit/{id}")
	public String editFood(@PathVariable("id") long id, HttpSession session, Model model) {

		if (session.getAttribute("account_id") == null
				|| !session.getAttribute("role").toString().equalsIgnoreCase("Quản lý nhà hàng")) {
			return "redirect:/admin/login";
		}

		NhanVien nhanVien = nhanVienService.getByAccountId((long) session.getAttribute("account_id"));
		model.addAttribute("nhanvien", nhanVien);
		model.addAttribute("pageTitle", "Cập nhật món ăn");

		model.addAttribute("monan", monAnService.get(id));
		model.addAttribute("nhommonans", nhomMonAnService.listAll());
		return "RestaurentManagement/Food/Edit";
	}

	// post sửa món ăn
	@PostMapping("/foods/edit/{id}")
	public String editFood(@PathVariable("id") long id, @RequestParam("image") MultipartFile image, @RequestParam("name") String name,
			@RequestParam("unit") String unit, @RequestParam("price") double price, @RequestParam("group") long group,
			@RequestParam("desc") String desc, @RequestParam("desc-detail") String descDetail, HttpSession session,
			Model model) {

		if (session.getAttribute("account_id") == null
				|| !session.getAttribute("role").toString().equalsIgnoreCase("Quản lý nhà hàng")) {
			return "redirect:/admin/login";
		}

		NhanVien nhanVien = nhanVienService.getByAccountId((long) session.getAttribute("account_id"));
		model.addAttribute("nhanvien", nhanVien);
		model.addAttribute("pageTitle", "Cập nhật món ăn");

		model.addAttribute("nhommonans", nhomMonAnService.listAll());
		
		MonAn monAn = monAnService.get(id);
		if(monAn==null) {
			return "RestaurentManagement/Food/Edit";
		}

		if (name.trim().length() < 1) {
			model.addAttribute("message", "Tên nhóm món ăn không được để trống");
			return "RestaurentManagement/Food/Edit";
		}
		NhomMonAn nhomMonAn = nhomMonAnService.get(group);
		if (nhomMonAn == null) {
			model.addAttribute("message", "Vui lòng chọn nhóm món ăn");
			return "RestaurentManagement/Food/Edit";
		}
		if (price < 1) {
			model.addAttribute("message", "Giá món ăn phải lớn hơn 0");
			return "RestaurentManagement/Food/Edit";
		}

		monAn.setTenMonAn(name);
		monAn.setDonGia(price);
		monAn.setDonVi(unit);
		monAn.setMoTa(descDetail);
		monAn.setMoTaNgan(desc);
		monAn.setNhomMonAn(nhomMonAn);
		
		if (!image.isEmpty()) {
			try {
				Path path = Paths.get(System.getProperty("user.dir") + "/src/main/resources/static/images/food-images",
						image.getOriginalFilename());
				Path servePath = Paths.get(System.getProperty("user.dir") + "/target/classes/static/images/food-images",
						image.getOriginalFilename());
				Files.write(path, image.getBytes());
				Files.write(servePath, image.getBytes());
				monAn.setHinhAnh(image.getOriginalFilename());
			} catch (IOException e) {
				e.printStackTrace();
				model.addAttribute("message", "Có lỗi xảy ra khi upload ảnh");
				return "RestaurentManagement/Food/Create";
			}
		}
		
		monAnService.save(monAn);

		model.addAttribute("message", "Cập nhật món ăn thành công");
		model.addAttribute("monan", monAn);
		return "RestaurentManagement/Food/Edit";
	}

	// api XÓA món ăn
	@ResponseBody
	@DeleteMapping("/foods/delete-food/{id}")
	public ResponseJSON deleteFood(@PathVariable("id") long id, HttpSession session) {

		if (session.getAttribute("account_id") == null
				|| !session.getAttribute("role").toString().equalsIgnoreCase("Quản lý nhà hàng")) {
			return new ResponseJSON(false, "Bạn không có quyền thực hiện chức năng này");
		}

		MonAn monAn = monAnService.get(id);

		if (monAn == null) {
			return new ResponseJSON(false, "Món ăn không tồn tại");
		}

		monAnService.delete(id);

		return new ResponseJSON(true, "Xóa Món ăn thành công");
	}
}

@Getter
@Setter
class ResponseJSON {
	boolean status;
	String message;

	ResponseJSON(boolean status, String message) {
		this.status = status;
		this.message = message;
	}
}