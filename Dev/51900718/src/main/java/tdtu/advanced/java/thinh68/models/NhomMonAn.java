package tdtu.advanced.java.thinh68.models;

import java.util.Set;

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
@Table(name = "nhom_mon_an")
public class NhomMonAn {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ma_nhom")
	private Long maNhom;

	@Column(name = "ten_nhom")
	private String tenNhom;

	@Column(name = "mo_ta")
	private String moTa;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "nhomMonAn")
	private Set<MonAn> nhomMonAn_MonAns;
}