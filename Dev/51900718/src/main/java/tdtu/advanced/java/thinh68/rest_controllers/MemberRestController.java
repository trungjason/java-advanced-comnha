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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tdtu.advanced.java.thinh68.jsonmodels.DatBan;
import tdtu.advanced.java.thinh68.models.HoiVien;
import tdtu.advanced.java.thinh68.models.JsonResponseFormat;
import tdtu.advanced.java.thinh68.models.KhachHang;
import tdtu.advanced.java.thinh68.models.LichHen;
import tdtu.advanced.java.thinh68.models.MonAn;
import tdtu.advanced.java.thinh68.services.HoiVienService;
import tdtu.advanced.java.thinh68.services.KhachHangService;
import tdtu.advanced.java.thinh68.services.LichHenService;

@RestController
public class MemberRestController {
	@Autowired
	private HoiVienService hoiVienService;
	
    @GetMapping(value = "/api/member-info/{maHoiVien}")
    public ResponseEntity<HoiVien> book(@PathVariable(required= true) String maHoiVien) {
    	try {
    		System.out.println(maHoiVien);
    		HoiVien hoiVien = hoiVienService.getHoiVienByPhoneNumber(maHoiVien);
    		
    		System.out.println(hoiVien);
        	return new ResponseEntity<HoiVien>(hoiVien, HttpStatus.OK);
    	} catch (Exception ex) {
    		return new ResponseEntity<HoiVien>(new HoiVien(), HttpStatus.NO_CONTENT);
    	}
    }     
}


