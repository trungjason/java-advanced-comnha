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
@Table(name="nhanvien")
public  class NhanVien {
    @Id
    @GeneratedValue
    private Long MaNhanVien;
    private String TenNhanVien;
    private String SoDienThoai;
    private double Luong;
    private String ChucVu;
    private String DiaChi;
    private String Email;

}