package tdtu.thinh6.restaurent_management_comnha.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Taikhoan {

    @Id
    @Column(nullable = false, updatable = false, length = 50)
    private String tenTaiKhoan;

    @Column(nullable = false)
    private String matKhau;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ma_nhan_vien_id", nullable = false)
    private Nhanvien maNhanVien;

}
