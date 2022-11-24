package tdtu.advanced.java.thinh68.models;

import java.util.List;
import java.util.Set;

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
@Table(name = "phieu_goi_mon")
public class PhieuGoiMon {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ma_order")
	private Long maOrder;

	@Column(name = "ghi_chu_mon_an")
	private String ghiChuMonAn;

	@ManyToOne
	@JoinColumn(name = "phieu_goi_mon_ban_an_foreign_key", referencedColumnName = "ma_ban_an", nullable = false)
	private BanAn banAn;

	@ManyToOne
	@JoinColumn(name = "phieu_goi_mon_nhan_vien_foreign_key", referencedColumnName = "ma_nhan_vien", nullable = false)
	private NhanVien nhanVien;

	@OneToMany(mappedBy = "phieuGoiMon")
	private List<ChiTietGoiMon> phieuGoiMon_ChiTietGoiMons;
	
	@OneToMany(mappedBy = "phieuGoiMon")
	private List<HoaDon> phieuGoiMon_HoaDons;
}