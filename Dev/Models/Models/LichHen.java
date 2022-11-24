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
@Table(name="lichhen")
public  class LichHen {
    @Id
    @GeneratedValue
    private Long MaLichHen;
    private String NhuCau;
    private int SoLuongKhach;
    public TimeSpan ThoiGian;
    private DateTime NgayHen;
    private int TinhTrang;
    private int MaKhachHang;
    private String MaNhanVien;
    private String MaBanAn;
}