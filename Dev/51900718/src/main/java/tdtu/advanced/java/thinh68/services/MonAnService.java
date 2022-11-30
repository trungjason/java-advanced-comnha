package tdtu.advanced.java.thinh68.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tdtu.advanced.java.thinh68.models.MonAn;
import tdtu.advanced.java.thinh68.repositories.MonAnRepository;

@Service
@Transactional
public class MonAnService {
	@Autowired
    private MonAnRepository monAnRepository;
	
	public List<MonAn> listAll() {
        return monAnRepository.findAll();
    }
     
    public void save(MonAn product) {
    	monAnRepository.save(product);
    }
     
    public MonAn get(long id) {
        try {
        	return monAnRepository.findById(id).get();
        } catch ( NoSuchElementException ex ){
        	return null;
        }
    }
     
    public void delete(long id) {
    	monAnRepository.deleteById(id);
    }
    
    public List<MonAn> getMonAnByMaNhom(Long maNhomMonAn) {
    	return monAnRepository.getMonAnByMaNhom(maNhomMonAn);
    }
}
