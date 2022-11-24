package tdtu.advanced.java.thinh68.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
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
@Table(name = "hoa_don")
public class HoaDon {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ma_hoa_don")
	private Long maHoaDon;

	@Column(name = "thoi_gian_thanh_toan", nullable = false)
	private Timestamp thoiGianThanhToan;

	@Column(name = "tong_tien", nullable = false)
	private double tongTien;

	@Column(name = "chiet_khau")
	private double chietKhau;

	@Column(name = "tinh_trang", nullable = false)
	private int tinhTrang;

	@ManyToOne
	@JoinColumn(name = "hoa_don_khach_hang_foreign_key", referencedColumnName = "ma_khach_hang")
	private KhachHang khachHang;

	@ManyToOne
	@JoinColumn(name = "hoa_don_hoi_vien_foreign_key", referencedColumnName = "ma_hoi_vien")
	private HoiVien hoiVien;

	@ManyToOne
	@JoinColumn(name = "hoa_don_nhan_vien_foreign_key", referencedColumnName = "ma_nhan_vien")
	private NhanVien nhanVien;

	@ManyToOne
	@JoinColumn(name = "hoa_don_phieu_goi_mon_foreign_key", referencedColumnName = "ma_order")
	private PhieuGoiMon phieuGoiMon;
}