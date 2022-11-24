package tdtu.advanced.java.thinh68.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tdtu.advanced.java.thinh68.models.LichHen;
import tdtu.advanced.java.thinh68.repositories.LichHenRepository;

@Service
@Transactional
public class LichHenService {
	@Autowired
    private LichHenRepository lichHenRepository;
	
	public List<LichHen> listAll() {
        return lichHenRepository.findAll();
    }
     
    public void save(LichHen product) {
    	lichHenRepository.save(product);
    }
     
    public LichHen get(long id) {
        return lichHenRepository.findById(id).get();
    }
     
    public void delete(long id) {
    	lichHenRepository.deleteById(id);
    }
}
