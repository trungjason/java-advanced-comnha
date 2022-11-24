import org.springframework.data.jpa.repository.JpaRepository;

import models.NhanVien;


public interface NhanVienRepository extends JpaRepository<NhanVien, Long> {
	 
}