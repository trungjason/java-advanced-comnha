package tdtu.advanced.java.thinh68.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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
@Table(name = "hoi_vien")
public class HoiVien {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ma_hoi_vien", nullable = false)
	private Long maHoiVien;

	@Column(name = "ten_hoi_vien", nullable = false)
	private String tenHoiVien;

	@Column(name = "so_dien_thoai", nullable = false)
	private String soDienThoai;

	@Column(name = "tong_so_tien_thanh_toan")
	private double tongSoTienThanhToan;

	@Column(name = "email", unique = true, nullable = false)
	private String email;

	@Column(name = "mat_khau", nullable = false)
	private String matKhau;

	@Column(name = "dia_chi")
	private String diaChi;

	@Column(name = "quyen_loi")
	private String quyenLoi;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "hoiVien")
	private List<HoaDon> hoiVien_HoaDons;
}