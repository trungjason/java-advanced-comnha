package tdtu.advanced.java.thinh68.models;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@SuppressWarnings("serial")
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Embeddable
public class ChiTietPhieuNhapKey implements Serializable {

	@Column(name = "ma_nguyen_vat_lieu")
	private Long maNguyenVatLieu;

	@Column(name = "ma_phieu_nhap")
	private Long maPhieuNhap;

	@Override
	public int hashCode() {
		return Objects.hash(maNguyenVatLieu, maPhieuNhap);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChiTietPhieuNhapKey other = (ChiTietPhieuNhapKey) obj;
		return Objects.equals(maNguyenVatLieu, other.maNguyenVatLieu) && Objects.equals(maPhieuNhap, other.maPhieuNhap);
	}
}