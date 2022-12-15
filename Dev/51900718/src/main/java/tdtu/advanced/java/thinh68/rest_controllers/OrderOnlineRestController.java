package tdtu.advanced.java.thinh68.rest_controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tdtu.advanced.java.thinh68.models.ChiTietGoiMon;
import tdtu.advanced.java.thinh68.models.HoaDon;
import tdtu.advanced.java.thinh68.models.HoiVien;
import tdtu.advanced.java.thinh68.models.KhachHang;
import tdtu.advanced.java.thinh68.models.MonAn;
import tdtu.advanced.java.thinh68.models.PhieuGoiMon;
import tdtu.advanced.java.thinh68.jsonmodels.DatMonTrucTuyen;
import tdtu.advanced.java.thinh68.jsonmodels.MonAnTrucTuyen;
import tdtu.advanced.java.thinh68.jsonmodels.OrderJsonReturnFormat;
import tdtu.advanced.java.thinh68.services.ChiTietGoiMonService;
import tdtu.advanced.java.thinh68.services.HoaDonService;
import tdtu.advanced.java.thinh68.services.HoiVienService;
import tdtu.advanced.java.thinh68.services.KhachHangService;
import tdtu.advanced.java.thinh68.services.MonAnService;
import tdtu.advanced.java.thinh68.services.PhieuGoiMonService;

@RestController
public class OrderOnlineRestController {
	@Autowired
	private HoaDonService hoaDonService;
	
	@Autowired
	private MonAnService monAnService;
	
	@Autowired
	private HoiVienService hoiVienService;
	
	@Autowired
	private KhachHangService khachHangService;
	
	@Autowired
	private ChiTietGoiMonService chiTietGoiMonService;
	
	@Autowired
	private PhieuGoiMonService phieuGoiMonService;
	
	@PostMapping(value="/api/order-online")
	@ResponseBody
	public ResponseEntity<OrderJsonReturnFormat> orderFoodOnline(@RequestBody DatMonTrucTuyen datMonTrucTuyen) {
		
		// Kiểm tra món ăn có hợp lệ không, đồng thời tổng tiền lại có bằng tiền tính tạm không
        if (!checkFoodsAndPrice(datMonTrucTuyen.getOrderFood(), datMonTrucTuyen.getTempPrice())) {
            OrderJsonReturnFormat orderJsonReturnFormat = 
            		new OrderJsonReturnFormat(false, 
            				"Vui lòng đặt món lại ! Hệ thống không hỗ trợ giảm tiền ăn chùa !", 
            				4);
           
            return new ResponseEntity<OrderJsonReturnFormat>(orderJsonReturnFormat, HttpStatus.OK);
        }

        // Kiểm tra phí ship
        if ( !checkShippingFee(datMonTrucTuyen.getShippingFee(), datMonTrucTuyen.getTempPrice()) ) {
            OrderJsonReturnFormat orderJsonReturnFormat = 
            		new OrderJsonReturnFormat(false, 
            				"Phí ship không hợp lệ", 
            				4);
           
            return new ResponseEntity<OrderJsonReturnFormat>(orderJsonReturnFormat, HttpStatus.OK);
        }
        
        
        // Kiểm tra xem không có mã khách hàng mà có số tiền giảm
        if (datMonTrucTuyen.getMemberID() != null && 
        		datMonTrucTuyen.getMemberID().equals("") && 
        		datMonTrucTuyen.getDiscountFee() != 0)
        {
            OrderJsonReturnFormat orderJsonReturnFormat = 
            		new OrderJsonReturnFormat(false, 
            				"Vui lòng đặt món lại ! Hệ thống không hỗ trợ giảm tiền ăn chùa !", 
            				4, 
            				0);
           
            return new ResponseEntity<OrderJsonReturnFormat>(orderJsonReturnFormat, HttpStatus.OK);
    	}
        
        HoiVien hoiVien = null;
        // Xem khách hàng có sử dụng mã hội viên không
        if (datMonTrucTuyen.getMemberID() != null && 
        		datMonTrucTuyen.getMemberID().equals(""))
        {
            // Kiểm tra mã hội viên và số tiền giảm có hợp lệ không
            if (checkMemberAndDiscount(datMonTrucTuyen.getMemberID(), 
            		datMonTrucTuyen.getTempPrice(), 
            		datMonTrucTuyen.getShippingFee(), 
            		datMonTrucTuyen.getDiscountFee()) )
            {
                double discountPercent = (datMonTrucTuyen.getDiscountFee()) / (datMonTrucTuyen.getTempPrice());
                
                // Kiểm tra tổng tiền lúc sau có hợp lệ không
                if (checkTotalPrice(datMonTrucTuyen.getTempPrice(), 
                		datMonTrucTuyen.getShippingFee(), 
                		datMonTrucTuyen.getDiscountFee(), 
                		datMonTrucTuyen.getTotalPrice()) )
                {
                    hoiVien = hoiVienService.get(Integer.parseInt(datMonTrucTuyen.getMemberID()));
                    
                    hoiVien.setTongSoTienThanhToan(
                    		hoiVien.getTongSoTienThanhToan() + datMonTrucTuyen.getTotalPrice());
                    
                    hoiVien = hoiVienService.saveWithReturn(hoiVien);
                }
                else
                {
                    OrderJsonReturnFormat orderJsonReturnFormat = 
                    		new OrderJsonReturnFormat(false, 
                    				"Mã hội viên không hợp lệ", 
                    				4, 
                    				0);
                   
                    return new ResponseEntity<OrderJsonReturnFormat>(orderJsonReturnFormat, HttpStatus.OK);
                }
            }                    
        }

        // Kiểm tra tổng tiền lúc sau có hợp lệ không
        if (!checkTotalPrice(datMonTrucTuyen.getTempPrice(), 
        		datMonTrucTuyen.getShippingFee(), 
        		datMonTrucTuyen.getDiscountFee(), 
        		datMonTrucTuyen.getTotalPrice()) )
        {
        	OrderJsonReturnFormat orderJsonReturnFormat = 
            		new OrderJsonReturnFormat(false, 
            				"Vui lòng không chỉnh sửa giá tiền", 
            				4, 
            				0);
           
            return new ResponseEntity<OrderJsonReturnFormat>(orderJsonReturnFormat, HttpStatus.OK);
        }
        
        KhachHang khachHang = KhachHang.builder()
        		.soDienThoai(datMonTrucTuyen.getPhone())
        		.tenKhachHang(datMonTrucTuyen.getName())
        		.diaChi(datMonTrucTuyen.getAddress())
        		.email(datMonTrucTuyen.getEmail())
        		.build();
        
        khachHang = khachHangService.saveWithReturn(khachHang);
        
        PhieuGoiMon phieuGoiMon = PhieuGoiMon.builder()
        		.ghiChuMonAn(datMonTrucTuyen.getMessage())
        		.build();
        
        phieuGoiMon = phieuGoiMonService.saveWithReturn(phieuGoiMon);
        
        
        System.out.println("Phieu Goi Mon");
        System.out.println(phieuGoiMon.getMaOrder());
        	
        for (MonAnTrucTuyen monAnTrucTuyen : datMonTrucTuyen.getOrderFood()) {
        	MonAn foodDetail = monAnService.get(monAnTrucTuyen.getFoodID());
        	
        	System.out.println("Mon An");
            System.out.println(foodDetail.getMaMonAn());
            System.out.println(monAnTrucTuyen.getQuantity());
        	
        	ChiTietGoiMon chiTietGoiMon = ChiTietGoiMon.builder()
        			.phieuGoiMon(phieuGoiMon)
        			.monAn(foodDetail)
        			.soLuongMonAn(monAnTrucTuyen.getQuantity())
        			.build();
        	
        	chiTietGoiMon = new ChiTietGoiMon(
        			foodDetail.getMaMonAn(), 
        			phieuGoiMon.getMaOrder(), 
        			monAnTrucTuyen.getQuantity());
        	
        	chiTietGoiMon.setMonAn(foodDetail);
        	chiTietGoiMon.setPhieuGoiMon(phieuGoiMon);
        	
        	chiTietGoiMonService.save(chiTietGoiMon);
        };
        
        
        
        double total = datMonTrucTuyen.getTempPrice() + datMonTrucTuyen.getShippingFee();
        double discountPercent1 = (datMonTrucTuyen.getDiscountFee()) / (datMonTrucTuyen.getTempPrice());
        HoaDon hoaDon = HoaDon.builder()
        		.tongTien(total)
        		.chietKhau(discountPercent1)
        		.tinhTrang(1)
        		.khachHang(khachHang)
        		.hoiVien(hoiVien)
        		.phieuGoiMon(phieuGoiMon)
        		.build();
        
        hoaDon = hoaDonService.saveWithReturnModel(hoaDon);
        
        OrderJsonReturnFormat orderJsonReturnFormat = 
        		new OrderJsonReturnFormat(true, 
        				"Đặt món trực tuyến thành công", 
        				1, 
        				hoaDon.getMaHoaDon());
        
        System.out.println(orderJsonReturnFormat);
        		
		return new ResponseEntity<OrderJsonReturnFormat>(orderJsonReturnFormat, HttpStatus.OK);
	}
	
	private double getDiscount(double totalMoney)
    {
        double discountPercent = 0.02;
        if (totalMoney <= 3000000) {
            discountPercent = 0.02;
        } else if (totalMoney <= 5000000) {
            discountPercent = 0.05;
        } else if (totalMoney <= 10000000) {
            discountPercent = 0.08;
        } else if (totalMoney <= 15000000) {
            discountPercent = 0.1;
        } else if (totalMoney <= 20000000) {
            discountPercent = 0.12;
        } else
        {
            discountPercent = 0.15;
        }

        return discountPercent;
    }

    // Kiểm tra Id hội viên được gửi lên và số tiền giảm giá có khớp nhau không
    private boolean checkMemberAndDiscount(String memberID, double tempPrice, double shippingFee, double discountFee)
    {
    	HoiVien hoiVien = hoiVienService.getHoiVienByPhoneNumber(memberID);
    	
        if ( hoiVien == null)
        {
            return false;
        }

        double discountPercent = getDiscount(hoiVien.getTongSoTienThanhToan());
        
        if ( discountFee == (tempPrice + shippingFee) * discountPercent)
        {
            return true;
        }
        
        return false;
    }

    // Kiểm tra món ăn và tổng tiền có hợp lệ không
    private boolean checkFoodsAndPrice(List<MonAnTrucTuyen> orderFood, double tempPrice)
    {
        double price = 0;

        for (MonAnTrucTuyen monAnTrucTuyen : orderFood) {
        	MonAn foodDetail = monAnService.get(monAnTrucTuyen.getFoodID());

            if ( foodDetail == null)
            {
                return false;
            }

            if (monAnTrucTuyen.getQuantity() * monAnTrucTuyen.getPrice() 
            		!= monAnTrucTuyen.getQuantity() * foodDetail.getDonGia())
            { 
                return false;
            };

            price += foodDetail.getDonGia() * monAnTrucTuyen.getQuantity();
        }

        return price == tempPrice;
    }

    // Kiểm tra phí ship
    private boolean checkShippingFee(double shippingFee, double tempPrice)
    {
        if (tempPrice <= 1000000 && shippingFee != 50000) {
            return false;
        } else if (tempPrice > 1000000 && shippingFee != 0) {
            return false;
        }

        return true;
    }

    // Kiểm tra số tiền cuối cùng
    private boolean checkTotalPrice(double tempPrice, double shippingFee, double discountFee, double totalPrice)
    {
        if (totalPrice != (tempPrice + shippingFee - discountFee)) {
            return false;
        }
        return true;
    }
}
