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
public class Monan {

    @Id
    @Column(nullable = false, updatable = false, length = 10)
    private String maMonAn;

    @Column(nullable = false, length = 50)
    private String tenMonAn;

    @Column(nullable = false)
    private Double donGia;

    @Column(nullable = false, length = 50)
    private String donVi;

    @Column(nullable = false, length = 500)
    private String moTa;

    @Column(nullable = false, length = 100)
    private String moTaNgan;

    @Column(nullable = false, length = 100)
    private String hinhAnh;

    @OneToMany(mappedBy = "maMonAn")
    private Set<Chitietgoimon> maMonAnChitietgoimons;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ma_nhom_id", nullable = false)
    private Nhommonan maNhom;

}
