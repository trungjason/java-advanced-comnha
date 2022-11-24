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
public class ChiTietGoiMonKey implements Serializable {

	@Column(name = "ma_mon_an")
	private Long maMonAn;

	@Column(name = "ma_order")
	private Long maOrder;

	@Override
	public int hashCode() {
		return Objects.hash(maMonAn, maOrder);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChiTietGoiMonKey other = (ChiTietGoiMonKey) obj;
		return Objects.equals(maMonAn, other.maMonAn) && Objects.equals(maOrder, other.maOrder);
	}
}