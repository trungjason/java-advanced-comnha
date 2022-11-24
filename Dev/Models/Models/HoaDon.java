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
@Table(name="hoadon")
public class HoaDon {
    @Id
    @GeneratedValue
    private Long MaHoaDon;
    private DateTime ThoiGianThanhToan;
    private double TongTien;
    private double ChietKhau;
    private int TinhTrang;
    private int MaKhachHang;
    private String MaHoiVien;
    private String MaNhanVien;
    private String MaOrder;
}