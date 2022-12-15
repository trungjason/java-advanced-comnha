package tdtu.advanced.java.thinh68.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name="khach_hang")
public class KhachHang {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name="ma_khach_hang")
    private Long maKhachHang;
    
    @Column(name="so_dien_thoai", nullable = false)
    private String soDienThoai;
    
    @Column(name="ten_khach_hang", nullable = false)
    private String tenKhachHang;
    
    @Column(name="dia_chi", nullable = true)
    private String diaChi;
    
    @Column(name="email", nullable = true)
    private String email;
    

    @OneToMany(mappedBy="khachHang")
    @JsonIgnore
    private List<LichHen> khachHang_LichHens;
    

    @OneToMany(mappedBy="khachHang")
    @JsonIgnore
    private List<HoaDon> khachHang_HoaDons;
}