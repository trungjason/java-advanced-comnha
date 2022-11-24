package tdtu.advanced.java.thinh68.models;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "phieu_nhap")
public class PhieuNhap {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ma_phieu_nhap")
	private Long maPhieuNhap;

	@Column(name = "ngay_nhap_hang")
	private Timestamp ngayNhapHang = new Timestamp(System.currentTimeMillis());

	@Column(name = "tong_gia_nhap")
	private double tongGiaNhap;

	@Column(name = "ghi_chu")
	private String ghiChu;

	@OneToMany(mappedBy = "phieuNhap")
	private List<ChiTietPhieuNhap> phieuNhap_ChiTietPhieuNhaps;

	@ManyToOne
	@JoinColumn(name = "phieu_nhap_nha_cung_cap_foreign_key", referencedColumnName = "ma_nha_cung_cap", nullable = false)
	private NhaCungCap nhaCungCap;

	@ManyToOne
	@JoinColumn(name = "phieu_nhap_nhan_vien_foreign_key", referencedColumnName = "ma_nhan_vien", nullable = false)
	private NhanVien nhanVien;
}