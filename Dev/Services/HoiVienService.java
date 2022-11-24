import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import models.HoiVien;
import repositories.HoiVienRepository;

@Service
@Transactional
public class HoiVienService {
	@Autowired
    private HoiVienRepository hoiVienRepository;
	
	public List<HoiVien> listAll() {
        return hoiVienRepository.findAll();
    }
     
    public void save(HoiVien product) {
    	hoiVienRepository.save(product);
    }
     
    public HoiVien get(long id) {
        return hoiVienRepository.findById(id).get();
    }
     
    public void delete(long id) {
    	hoiVienRepository.deleteById(id);
    }
}
