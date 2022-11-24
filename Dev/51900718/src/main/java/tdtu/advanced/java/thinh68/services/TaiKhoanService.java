package tdtu.advanced.java.thinh68.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tdtu.advanced.java.thinh68.models.TaiKhoan;
import tdtu.advanced.java.thinh68.repositories.TaiKhoanRepository;

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
