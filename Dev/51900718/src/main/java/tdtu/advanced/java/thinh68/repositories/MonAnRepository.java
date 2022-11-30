package tdtu.advanced.java.thinh68.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tdtu.advanced.java.thinh68.models.MonAn;

@Repository
public interface MonAnRepository extends JpaRepository<MonAn, Long> {
	@Query("SELECT ma FROM MonAn ma WHERE ma.nhomMonAn.maNhom = :id")
	List<MonAn> getMonAnByMaNhom(@Param("id") Long maNhomMonAn);
}