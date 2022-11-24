package tdtu.advanced.java.thinh68.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tdtu.advanced.java.thinh68.models.NguyenVatLieu;
import tdtu.advanced.java.thinh68.repositories.NguyenVatLieuRepository;

@Service
@Transactional
public class NguyenVatLieuService {
	@Autowired
    private NguyenVatLieuRepository nguyenVatLieuRepository;
	
	public List<NguyenVatLieu> listAll() {
        return nguyenVatLieuRepository.findAll();
    }
     
    public void save(NguyenVatLieu product) {
    	nguyenVatLieuRepository.save(product);
    }
     
    public NguyenVatLieu get(long id) {
        return nguyenVatLieuRepository.findById(id).get();
    }
     
    public void delete(long id) {
    	nguyenVatLieuRepository.deleteById(id);
    }
}
