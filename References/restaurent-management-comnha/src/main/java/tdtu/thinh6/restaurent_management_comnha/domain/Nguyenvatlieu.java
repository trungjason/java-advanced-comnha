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
public class Nguyenvatlieu {

    @Id
    @Column(nullable = false, updatable = false, length = 10)
    private String maNguyenVatLieu;

    @Column(nullable = false, length = 50)
    private String tenNguyenVatLieu;

    @Column(nullable = false)
    private Double donGia;

    @Column(nullable = false, length = 50)
    private String donVi;

    @Column(nullable = false, length = 50)
    private String tinhTrang;

    @Column(nullable = false)
    private Integer soLuongTonKho;

    @OneToMany(mappedBy = "maNguyenVatLieu")
    private Set<Chitietphieunhap> maNguyenVatLieuChitietphieunhaps;

}
