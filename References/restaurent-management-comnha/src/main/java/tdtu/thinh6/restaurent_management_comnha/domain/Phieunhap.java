package tdtu.thinh6.restaurent_management_comnha.domain;

import java.time.OffsetDateTime;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Phieunhap {

    @Id
    @Column(nullable = false, updatable = false, length = 10)
    private String maPhieuNhap;

    @Column(nullable = false)
    private OffsetDateTime ngayNhapHang;

    @Column(nullable = false)
    private Double tongGiaNhap;

    @Column(nullable = false, length = 100)
    private String ghiChu;

    @OneToMany(mappedBy = "maPhieuNhap")
    private Set<Chitietphieunhap> maPhieuNhapChitietphieunhaps;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ma_nha_cung_cap_id", nullable = false)
    private Nhacungcap maNhaCungCap;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ma_nhan_vien_id", nullable = false)
    private Nhanvien maNhanVien;

}
