package tdtu.advanced.java.thinh68.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tdtu.advanced.java.thinh68.models.NhomMonAn;
import tdtu.advanced.java.thinh68.repositories.NhomMonAnRepository;

@Service
@Transactional
public class NhomMonAnService {
	@Autowired
    private NhomMonAnRepository nhomMonAnRepository;
	
	public List<NhomMonAn> listAll() {
        return nhomMonAnRepository.findAll();
    }
     
    public void save(NhomMonAn product) {
    	nhomMonAnRepository.save(product);
    }
     
    public NhomMonAn get(long id) {
        return nhomMonAnRepository.findById(id).get();
    }
     
    public void delete(long id) {
    	nhomMonAnRepository.deleteById(id);
    }
}
