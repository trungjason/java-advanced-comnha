import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import models.BanAn;
import repositories.BanAnRepository;

@Service
@Transactional
public class BanAnService {
	@Autowired
    private BanAnRepository banAnRepository;
	
	public List<BanAn> listAll() {
        return banAnRepository.findAll();
    }
     
    public void save(BanAn product) {
    	banAnRepository.save(product);
    }
     
    public BanAn get(long id) {
        return banAnRepository.findById(id).get();
    }
     
    public void delete(long id) {
    	banAnRepository.deleteById(id);
    }
}
