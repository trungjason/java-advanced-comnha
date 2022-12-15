package tdtu.advanced.java.thinh68.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tdtu.advanced.java.thinh68.models.NhanVien;
import tdtu.advanced.java.thinh68.repositories.NhanVienRepository;

@Service
@Transactional
public class NhanVienService {
	@Autowired
	private NhanVienRepository nhanVienRepository;

	public List<NhanVien> listAll() {
		return nhanVienRepository.findAllByChucVuNotIgnoreCase("Quản lý nhà hàng");
	}

	public NhanVien save(NhanVien product) {
		return nhanVienRepository.save(product);
	}

	public NhanVien get(long id) {
		return nhanVienRepository.findById(id).get();
	}

	public NhanVien getByAccountId(long id) {
		return nhanVienRepository.findByTaiKhoan_Id(id);
	}

	public void delete(long id) {
		nhanVienRepository.deleteById(id);
	}
}
