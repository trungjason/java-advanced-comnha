package tdtu.advanced.java.thinh68.models;

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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
@Table(name = "mon_an")
public class MonAn {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ma_mon_an")
	private Long maMonAn;

	@Column(name = "ten_mon_an")
	private String tenMonAn;

	@Column(name = "don_gia")
	private double donGia;

	@Column(name = "don_vi")
	private String donVi;

	@Column(name = "mo_ta")
	private String moTa;

	@Column(name = "mo_ta_ngan")
	private String moTaNgan;

	@Column(name = "hinh_anh")
	private String hinhAnh;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "mon_an_nhom_mon_an_foreign_key", referencedColumnName = "ma_nhom", nullable = false)
	private NhomMonAn nhomMonAn;
	
	@JsonManagedReference
	@OneToMany(mappedBy="monAn")
	private List<ChiTietGoiMon> monAn_ChiTietGoiMons;
}