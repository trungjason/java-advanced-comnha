package tdtu.advanced.java.thinh68.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tdtu.advanced.java.thinh68.models.PhieuGoiMon;
import tdtu.advanced.java.thinh68.repositories.PhieuGoiMonRepository;

@Service
@Transactional
public class PhieuGoiMonService {
	@Autowired
    private PhieuGoiMonRepository phieuGoiMonRepository;
	
	public List<PhieuGoiMon> listAll() {
        return phieuGoiMonRepository.findAll();
    }
     
    public void save(PhieuGoiMon product) {
    	phieuGoiMonRepository.save(product);
    }
    
    public PhieuGoiMon saveWithReturn(PhieuGoiMon product) {
    	return phieuGoiMonRepository.save(product);
    }
     
    public PhieuGoiMon get(long id) {
        return phieuGoiMonRepository.findById(id).get();
    }
     
    public void delete(long id) {
    	phieuGoiMonRepository.deleteById(id);
    }
}
