package tdtu.advanced.java.thinh68.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name = "nha_cung_cap")
public class NhaCungCap {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ma_nha_cung_cap")
	private Long maNhaCungCap;

	@Column(name = "mo_ta_nha_cung_cap")
	private String moTaNhaCungCap;

	@Column(name = "ten_nha_cung_cap")
	private String tenNhaCungCap;

	@Column(name = "dia_chi")
	private String diaChi;

	@Column(name = "so_dien_thoai", unique = true, nullable = false)
	private String soDienThoai;

	@OneToMany(mappedBy = "nhaCungCap")
	private List<PhieuNhap> nhaCungCap_PhieuNhaps;
}