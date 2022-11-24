package tdtu.advanced.java.thinh68.models;

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
@Table(name="taikhoan")
public class TaiKhoan {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name="ma_tai_khoan")
    private Long id;
    
    @Column(name="ten_tai_khoan", unique=true)
    private String tenTaiKhoan;
    
    @Column(name="mat_khau", nullable=false)
    private String matKhau;
}