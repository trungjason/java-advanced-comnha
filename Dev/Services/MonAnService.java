import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import models.MonAn;
import repositories.MonAnRepository;

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
        return monAnRepository.findById(id).get();
    }
     
    public void delete(long id) {
    	monAnRepository.deleteById(id);
    }
}
