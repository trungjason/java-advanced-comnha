package tdtu.thinh6.restaurent_management_comnha.domain;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Khachhang {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer maKhachHang;

    @Column(nullable = false, length = 20)
    private String soDienThoai;

    @Column(nullable = false, length = 50)
    private String tenKhachHang;

    @Column(length = 50)
    private String diaChi;

    @Column(length = 50)
    private String email;

    @OneToMany(mappedBy = "maKhachHang")
    private Set<Hoadon> maKhachHangHoadons;

    @OneToMany(mappedBy = "maKhachHang")
    private Set<Lichhen> maKhachHangLichhens;

}
