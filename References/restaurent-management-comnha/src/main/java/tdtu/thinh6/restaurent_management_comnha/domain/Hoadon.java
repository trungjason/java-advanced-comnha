package tdtu.thinh6.restaurent_management_comnha.domain;

import java.time.OffsetDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Hoadon {

    @Id
    @Column(nullable = false, updatable = false, length = 10)
    private String maHoaDon;

    @Column(nullable = false)
    private OffsetDateTime thoiGianThanhToan;

    @Column(nullable = false)
    private Double tongTien;

    @Column(nullable = false)
    private Double chietKhau;

    @Column(nullable = false)
    private Boolean tinhTrang;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ma_hoi_vien_id")
    private Hoivien maHoiVien;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ma_khach_hang_id")
    private Khachhang maKhachHang;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ma_order_id", nullable = false)
    private Phieugoimon maOrder;

}
