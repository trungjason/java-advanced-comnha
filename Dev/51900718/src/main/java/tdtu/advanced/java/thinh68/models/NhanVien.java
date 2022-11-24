package tdtu.advanced.java.thinh68.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
@Table(name = "nhan_vien")
public class NhanVien {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ma_nhan_vien")
	private Long maNhanVien;

	@Column(name = "ten_nhan_vien")
	private String tenNhanVien;

	@Column(name = "so_dien_thoai")
	private String soDienThoai;

	@Column(name = "luong")
	private double luong;

	@Column(name = "chuc_vu")
	private String chucVu;

	@Column(name = "dia_chi")
	private String diaChi;

	@Column(name = "anh_dai_dien")
	private String anhDaiDien;

	@Column(name = "email", unique = true)
	private String Email;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "nhan_vien_tai_khoan_forgein_key", referencedColumnName = "ma_tai_khoan")
	private TaiKhoan taiKhoan;

	@OneToMany(mappedBy = "nhanVien")
	private List<LichHen> nhanVien_LichHens;

	@OneToMany(mappedBy = "nhanVien")
	private List<PhieuGoiMon> nhanVien_PhieuGoiMons;

	@OneToMany(mappedBy = "nhanVien")
	private List<PhieuNhap> nhanVien_PhieuNhaps;

	@OneToMany(mappedBy = "nhanVien")
	private List<HoaDon> nhanVien_HoaDons;
}