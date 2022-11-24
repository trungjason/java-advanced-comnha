import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import models.ChiTietPhieuNhap;
import repositories.ChiTietPhieuNhapRepository;

@Service
@Transactional
public class ChiTietPhieuNhapService {
	@Autowired
    private ChiTietPhieuNhapRepository chiTietPhieuNhapRepository;
	
	public List<ChiTietPhieuNhap> listAll() {
        return chiTietPhieuNhapRepository.findAll();
    }
     
    public void save(ChiTietPhieuNhap product) {
    	chiTietPhieuNhapRepository.save(product);
    }
     
    public ChiTietPhieuNhap get(long id) {
        return chiTietPhieuNhapRepository.findById(id).get();
    }
     
    public void delete(long id) {
    	chiTietPhieuNhapRepository.deleteById(id);
    }
}
