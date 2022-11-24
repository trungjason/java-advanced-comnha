import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import models.ChiTietGoiMon;
import repositories.ChiTietGoiMonRepository;

@Service
@Transactional
public class ChiTietGoiMonService {
	@Autowired
    private ChiTietGoiMonRepository chiTietGoiMonRepository;
	
	public List<ChiTietGoiMon> listAll() {
        return chiTietGoiMonRepository.findAll();
    }
     
    public void save(ChiTietGoiMon product) {
    	chiTietGoiMonRepository.save(product);
    }
     
    public ChiTietGoiMon get(long id) {
        return chiTietGoiMonRepository.findById(id).get();
    }
     
    public void delete(long id) {
    	chiTietGoiMonRepository.deleteById(id);
    }
}
