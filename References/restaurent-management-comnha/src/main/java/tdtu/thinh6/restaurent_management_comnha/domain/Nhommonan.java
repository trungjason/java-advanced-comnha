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
public class Nhommonan {

    @Id
    @Column(nullable = false, updatable = false, length = 10)
    private String maNhom;

    @Column(nullable = false, length = 50)
    private String tenNhom;

    @OneToMany(mappedBy = "maNhom")
    private Set<Monan> maNhomMonans;

}
