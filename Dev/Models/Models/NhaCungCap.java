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
@Table(name="nhacungcap")
public class NhaCungCap {
    @Id
    @GeneratedValue
    private Long MaNhaCungCap;
    private String MoTaNhaCungCap;
    private String TenNhaCungCap;
    private String DiaChi;
    private String SoDienThoai;
}