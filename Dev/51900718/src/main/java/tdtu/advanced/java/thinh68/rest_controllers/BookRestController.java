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

import tdtu.advanced.java.thinh68.jsonmodels.DatBan;
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
	
    @PostMapping(value = "/api/book", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<JsonResponseFormat> book(
    		@RequestBody DatBan datBan
    		) {
    	try {
    		JsonResponseFormat response = new JsonResponseFormat(true, "Đặt lịch hẹn thành công");
        	
        	KhachHang khachHang = KhachHang.builder()
        			.tenKhachHang(datBan.getTenKhachHang())
        			.soDienThoai(datBan.getSoDienThoai())
        			.build();
        	
        	khachHang = khachHangService.saveWithReturn(khachHang);
        
        	
        	LichHen lichHen = LichHen.builder()
        			.soLuongKhach(datBan.getSoLuongKhach())
        			.ngayHen(datBan.getNgayHen())
        			.thoiGian(datBan.getThoiGian())
        			.nhuCau(datBan.getNhuCau())
        			.khachHang(khachHang)
        			.build();
        	
        	lichHenService.save(lichHen);
        	
        	return new ResponseEntity<JsonResponseFormat>(response, HttpStatus.OK);
    	} catch (Exception ex) {
    		JsonResponseFormat response = new JsonResponseFormat(false, ex.getMessage());
    		return new ResponseEntity<JsonResponseFormat>(response, HttpStatus.OK);
    	}
    }     
}


