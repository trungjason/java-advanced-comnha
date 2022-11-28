package tdtu.thinh6.restaurent_management_comnha.domain;

import java.time.LocalDate;
import java.time.LocalTime;
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
public class Lichhen {

    @Id
    @Column(nullable = false, updatable = false, length = 10)
    private String maLichHen;

    @Column(length = 600)
    private String nhuCau;

    @Column(nullable = false)
    private Integer soLuongKhach;

    @Column(nullable = false)
    private LocalTime thoiGian;

    @Column(nullable = false)
    private LocalDate ngayHen;

    @Column
    private Boolean tinhTrang;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ma_ban_an_id")
    private Banan maBanAn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ma_khach_hang_id", nullable = false)
    private Khachhang maKhachHang;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ma_nhan_vien_id")
    private Nhanvien maNhanVien;

}
