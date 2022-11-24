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
@Table(name="hoivien")
public class HoiVien {
    @Id
    @GeneratedValue
    private Long TenHoiVien;
    private String SoDienThoai;
    private double TongSoTienThanhToan;
    private String Email;
    private String MatKhau;
    private String DiaChi;
    private String QuyenLoi;
}