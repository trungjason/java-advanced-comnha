package tdtu.advanced.java.thinh68.rest_controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tdtu.advanced.java.thinh68.models.MonAn;
import tdtu.advanced.java.thinh68.services.MonAnService;

@RestController
public class MenuRestController {
	@Autowired
	private MonAnService monAnService;
	
	@GetMapping(value = "/api/menu")
	public ResponseEntity<List<MonAn>> getAllFoodByFoodGroupID(@RequestParam(name="maNhomMonAn") Long maNhomMonAn) {
		List<MonAn> monAn = monAnService.getMonAnByMaNhom(maNhomMonAn);
		
		return new ResponseEntity<List<MonAn>>(monAn, HttpStatus.OK);
	}
	
	@GetMapping(value = "/api/menu/chitiet/{maMonAn}")
	public ResponseEntity<MonAn> getFoodDetailByFoodId(@PathVariable(required= true) Long maMonAn) {
		MonAn monAn = monAnService.get(maMonAn);
		
		if (monAn == null) {
			new ResponseEntity<MonAn>(monAn, HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<MonAn>(monAn, HttpStatus.OK);
	}
}
