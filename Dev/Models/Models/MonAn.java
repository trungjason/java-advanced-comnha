import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Table(name="monan")
public  class MonAn {
    @Id
    @GeneratedValue
    private Long MaMonAn;

    private String TenMonAn;

    private double DonGia;

    private String DonVi;

    private String MoTa;

    private String MoTaNgan;

    private String HinhAnh;

    private String MaNhom;

    @OneToMany(mappedBy = "monan")
    Set<Chitietgoimon> chitietgoimons;
}