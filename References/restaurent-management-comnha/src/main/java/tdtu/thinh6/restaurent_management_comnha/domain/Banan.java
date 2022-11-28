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
public class Banan {

    @Id
    @Column(nullable = false, updatable = false, length = 10)
    private String maBanAn;

    @Column(nullable = false, length = 20)
    private String tinhTrang;

    @Column(nullable = false)
    private Integer sucChua;

    @Column(nullable = false, length = 50)
    private String ghiChu;

    @OneToMany(mappedBy = "maBanAn")
    private Set<Lichhen> maBanAnLichhens;

    @OneToMany(mappedBy = "maBanAn")
    private Set<Phieugoimon> maBanAnPhieugoimons;

}
