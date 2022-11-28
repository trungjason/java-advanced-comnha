package tdtu.thinh6.restaurent_management_comnha.domain;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Hoivien {

    @Id
    @Column(nullable = false, updatable = false, length = 20)
    private String soDienThoai;

    @Column(nullable = false, length = 50)
    private String tenHoiVien;

    @Column(nullable = false)
    private Double tongSoTienThanhToan;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false, length = 100)
    private String diaChi;

    @Column(nullable = false, length = 100)
    private String quyenLoi;

    @OneToMany(mappedBy = "maHoiVien")
    private Set<Hoadon> maHoiVienHoadons;

}
