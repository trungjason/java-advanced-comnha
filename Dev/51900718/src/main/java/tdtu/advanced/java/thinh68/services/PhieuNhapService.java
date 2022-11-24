package tdtu.advanced.java.thinh68.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tdtu.advanced.java.thinh68.models.PhieuNhap;
import tdtu.advanced.java.thinh68.repositories.PhieuNhapRepository;

@Service
@Transactional
public class PhieuNhapService {
	@Autowired
    private PhieuNhapRepository phieuNhapRepository;
	
	public List<PhieuNhap> listAll() {
        return phieuNhapRepository.findAll();
    }
     
    public void save(PhieuNhap product) {
    	phieuNhapRepository.save(product);
    }
     
    public PhieuNhap get(long id) {
        return phieuNhapRepository.findById(id).get();
    }
     
    public void delete(long id) {
    	phieuNhapRepository.deleteById(id);
    }
}
