package tdtu.thinh6.restaurent_management_comnha.domain;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Nhacungcap {

    @Id
    @Column(nullable = false, updatable = false, length = 10)
    private String maNhaCungCap;

    @Column(nullable = false, length = 50)
    private String moTaNhaCungCap;

    @Column(nullable = false, length = 50)
    private String tenNhaCungCap;

    @Column(nullable = false, length = 100)
    private String diaChi;

    @Column(nullable = false, length = 20)
    private String soDienThoai;

    @OneToMany(mappedBy = "maNhaCungCap")
    private Set<Phieunhap> maNhaCungCapPhieunhaps;

}
