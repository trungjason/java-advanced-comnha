package tdtu.thinh6.restaurent_management_comnha.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Chitietgoimon {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer soLuongMonAn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ma_mon_an_id", nullable = false)
    private Monan maMonAn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ma_order_id", nullable = false)
    private Phieugoimon maOrder;

}
