package tdtu.advanced.java.thinh68.repositories;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tdtu.advanced.java.thinh68.models.HoaDon;

@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon, Long> {
	List<HoaDon> findByThoiGianThanhToanBetween(Timestamp fromTime, Timestamp toTime);
}