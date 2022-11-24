import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import models.NhaCungCap;
import repositories.NhaCungCapRepository;

@Service
@Transactional
public class NhaCungCapService {
	@Autowired
    private NhaCungCapRepository nhaCungCapRepository;
	
	public List<NhaCungCap> listAll() {
        return nhaCungCapRepository.findAll();
    }
     
    public void save(NhaCungCap product) {
    	nhaCungCapRepository.save(product);
    }
     
    public NhaCungCap get(long id) {
        return nhaCungCapRepository.findById(id).get();
    }
     
    public void delete(long id) {
    	nhaCungCapRepository.deleteById(id);
    }
}
