import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import models.KhachHang;
import repositories.KhachHangRepository;

@Service
@Transactional
public class KhachHangService {
	@Autowired
    private KhachHangRepository khachHangRepository;
	
	public List<KhachHang> listAll() {
        return khachHangRepository.findAll();
    }
     
    public void save(KhachHang product) {
    	khachHangRepository.save(product);
    }
     
    public KhachHang get(long id) {
        return khachHangRepository.findById(id).get();
    }
     
    public void delete(long id) {
    	khachHangRepository.deleteById(id);
    }
}
