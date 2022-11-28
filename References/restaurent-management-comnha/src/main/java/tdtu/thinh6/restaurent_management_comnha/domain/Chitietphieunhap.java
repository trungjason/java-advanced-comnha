package tdtu.thinh6.restaurent_management_comnha.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Chitietphieunhap {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer soLuongNhap;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ma_nguyen_vat_lieu_id", nullable = false)
    private Nguyenvatlieu maNguyenVatLieu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ma_phieu_nhap_id", nullable = false)
    private Phieunhap maPhieuNhap;

}
