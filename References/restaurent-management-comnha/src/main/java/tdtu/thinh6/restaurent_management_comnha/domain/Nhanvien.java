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
public class Nhanvien {

    @Id
    @Column(nullable = false, updatable = false, length = 10)
    private String maNhanVien;

    @Column(nullable = false, length = 100)
    private String tenNhanVien;

    @Column(nullable = false, length = 20)
    private String soDienThoai;

    @Column(nullable = false)
    private Double luong;

    @Column(nullable = false, length = 50)
    private String chucVu;

    @Column(nullable = false, length = 100)
    private String diaChi;

    @Column(nullable = false, length = 100)
    private String email;

    @OneToMany(mappedBy = "maNhanVien")
    private Set<Lichhen> maNhanVienLichhens;

    @OneToMany(mappedBy = "maNhanVien")
    private Set<Phieugoimon> maNhanVienPhieugoimons;

    @OneToMany(mappedBy = "maNhanVien")
    private Set<Phieunhap> maNhanVienPhieunhaps;

    @OneToMany(mappedBy = "maNhanVien")
    private Set<Taikhoan> maNhanVienTaikhoans;

}
