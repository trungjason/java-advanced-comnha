import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import models.TaiKhoan;
import repositories.TaiKhoanRepository;

@Service
@Transactional
public class TaiKhoanService {
	@Autowired
    private TaiKhoanRepository taiKhoanRepository;
	
	public List<TaiKhoan> listAll() {
        return taiKhoanRepository.findAll();
    }
     
    public void save(TaiKhoan product) {
    	taiKhoanRepository.save(product);
    }
     
    public TaiKhoan get(long id) {
        return taiKhoanRepository.findById(id).get();
    }
     
    public void delete(long id) {
    	taiKhoanRepository.deleteById(id);
    }
}
