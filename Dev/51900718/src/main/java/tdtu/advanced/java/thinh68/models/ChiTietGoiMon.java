package tdtu.advanced.java.thinh68.models;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// Bảng ChiTietGoiMon là bảng trung gian để hiện thực mối quan hệ N-N giữa MonAn và PhieuGoiMon
// ChiTietGoiMonKey là class trung gian đóng vai trò làm CompositeKey
// Trong ChiTietGoiMonKey chứa 2 keys phụ là MaMonAn và MaOrder
// Sử dụng cách CompositeKey vì có thể thêm trường vào bảng phụ

@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "chi_tiet_goi_mon")
public class ChiTietGoiMon {

	@EmbeddedId
	private ChiTietGoiMonKey id;

	@Column(name = "so_luong_mon_an")
	private int soLuongMonAn;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "chi_tiet_goi_mon_mon_an_foreign_key", referencedColumnName = "ma_mon_an", nullable = false)
	private MonAn monAn;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "chi_tiet_goi_mon_phieu_goi_mon_foreign_key", referencedColumnName = "ma_order", nullable = false)
	private PhieuGoiMon phieuGoiMon;
}