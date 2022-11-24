import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import models.HoaDon;
import repositories.HoaDonRepository;

@Service
@Transactional
public class HoaDonService {
	@Autowired
    private HoaDonRepository hoaDonRepository;
	
	public List<HoaDon> listAll() {
        return hoaDonRepository.findAll();
    }
     
    public void save(HoaDon product) {
    	hoaDonRepository.save(product);
    }
     
    public HoaDon get(long id) {
        return hoaDonRepository.findById(id).get();
    }
     
    public void delete(long id) {
    	hoaDonRepository.deleteById(id);
    }
}
