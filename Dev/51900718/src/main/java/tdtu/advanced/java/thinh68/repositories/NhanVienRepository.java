package tdtu.advanced.java.thinh68.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tdtu.advanced.java.thinh68.models.NhanVien;

@Repository
public interface NhanVienRepository extends JpaRepository<NhanVien, Long> {
	@Query
	NhanVien findByTaiKhoan_Id(long id);

	@Query
	List<NhanVien> findAllByChucVuNotIgnoreCase(String chucVu);
}