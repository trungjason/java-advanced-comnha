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
@Table(name="phieunhap")
public  class PhieuNhap {
    @Id
    @GeneratedValue
    private Long MaPhieuNhap;
    private DateTime NgayNhapHang;
    private double TongGiaNhap;
    private String GhiChu;
    private String MaNhaCungCap;
    private String MaNhanVien;
}