package tdtu.thinh6.restaurent_management_comnha.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import tdtu.thinh6.restaurent_management_comnha.domain.Khachhang;


public interface KhachhangRepository extends JpaRepository<Khachhang, Integer> {
}