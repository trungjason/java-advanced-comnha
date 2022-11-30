package tdtu.advanced.java.thinh68.models;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name="lich_hen")
public  class LichHen {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "ma_lich_hen")
    private Long maLichHen;
    
    @Column(name = "nhu_cau")
    private String nhuCau;
    
    @Column(name = "so_luong_khach")
    private int soLuongKhach;
    
    @Column(name = "thoi_gian")
    public Timestamp thoiGian;
    
    @Column(name = "ngay_hen")
    private Date ngayHen;
    
    @Column(name = "tinh_trang")
    private int tinhTrang;
    
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lich_hen_ban_an_foreign_key", referencedColumnName="ma_ban_an")
    private BanAn banAn;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lich_hen_khach_hang_foreign_key", referencedColumnName="ma_khach_hang", nullable = false)
    private KhachHang khachHang;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lich_hen_nhan_vien_foreign_key", referencedColumnName="ma_nhan_vien")
    private NhanVien nhanVien;
}