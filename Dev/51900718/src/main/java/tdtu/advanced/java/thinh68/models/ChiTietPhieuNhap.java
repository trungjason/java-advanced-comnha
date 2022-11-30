package tdtu.advanced.java.thinh68.models;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
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
@Table(name = "chi_tiet_phieu_nhap")
public class ChiTietPhieuNhap {
	@EmbeddedId
	private ChiTietPhieuNhapKey id;

	@Column(name = "so_luong_nhap")
	private int soLuongNhap;
	
	@JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "chi_tiet_phieu_nhap_nguyen_vat_lieu_foreign_key", referencedColumnName = "ma_nguyen_vat_lieu", nullable = false)
	private NguyenVatLieu nguyenVatLieu;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "chi_tiet_phieu_nhap_phieu_nhap_foreign_key", referencedColumnName = "ma_phieu_nhap", nullable = false)
	private PhieuNhap phieuNhap;
}