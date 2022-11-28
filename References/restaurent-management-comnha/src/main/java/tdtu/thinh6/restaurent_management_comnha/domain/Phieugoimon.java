package tdtu.thinh6.restaurent_management_comnha.domain;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Phieugoimon {

    @Id
    @Column(nullable = false, updatable = false, length = 10)
    private String maOrder;

    @Column(nullable = false, length = 600)
    private String ghiChuMonAn;

    @OneToMany(mappedBy = "maOrder")
    private Set<Chitietgoimon> maOrderChitietgoimons;

    @OneToMany(mappedBy = "maOrder")
    private Set<Hoadon> maOrderHoadons;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ma_ban_an_id")
    private Banan maBanAn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ma_nhan_vien_id")
    private Nhanvien maNhanVien;

}
