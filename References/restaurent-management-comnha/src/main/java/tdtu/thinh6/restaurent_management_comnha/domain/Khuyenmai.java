package tdtu.thinh6.restaurent_management_comnha.domain;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Khuyenmai {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer maKm;

    @Column(nullable = false, length = 500)
    private String tenChuongTrinh;

    @Column(nullable = false, columnDefinition = "longtext")
    private String moTa;

    @Column(nullable = false)
    private Integer giaTriKhuyenMai;

    @Column(nullable = false)
    private LocalDate ngayBatDau;

    @Column(nullable = false)
    private LocalDate ngayKetThuc;

}
