package tdtu.advanced.java.thinh68.rest_controllers;

import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tdtu.advanced.java.thinh68.models.JsonResponseFormat;
import tdtu.advanced.java.thinh68.models.KhachHang;
import tdtu.advanced.java.thinh68.models.LichHen;
import tdtu.advanced.java.thinh68.models.MonAn;
import tdtu.advanced.java.thinh68.services.KhachHangService;
import tdtu.advanced.java.thinh68.services.LichHenService;

@RestController
public class BookRestController {
	@Autowired
	private LichHenService lichHenService;
	
	@Autowired
	private KhachHangService khachHangService;
	
    @PostMapping(value = "/api/book")
    @ResponseBody
    public ResponseEntity<JsonResponseFormat> book(
    		@RequestBody LichHen lichHen,
    		@RequestBody KhachHang khachHang
    		) {
    	JsonResponseFormat response = new JsonResponseFormat(true, "Đặt lịch hẹn thành công");
    	
//    	System.out.println(tenKhachHang);
//    	System.out.println(soDienThoai);
//    	
//    	System.out.println(soLuongKhach);
//    	System.out.println(ngayHen);
//    	System.out.println(thoiGian);
//    	System.out.println(nhuCau);
    	
    	return new ResponseEntity<JsonResponseFormat>(response, HttpStatus.OK);
    }
}
