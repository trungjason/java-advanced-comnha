package tdtu.advanced.java.thinh68.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tdtu.advanced.java.thinh68.models.NhomMonAn;

@Repository
public interface NhomMonAnRepository extends JpaRepository<NhomMonAn, Long> {

}