package tdtu.advanced.java.thinh68.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tdtu.advanced.java.thinh68.models.HoaDon;

@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon, Long> {

}