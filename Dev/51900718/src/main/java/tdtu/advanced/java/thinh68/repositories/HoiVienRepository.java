package tdtu.advanced.java.thinh68.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tdtu.advanced.java.thinh68.models.HoiVien;

@Repository
public interface HoiVienRepository extends JpaRepository<HoiVien, Long> {
	HoiVien findBySoDienThoai(String soDienThoai);
}