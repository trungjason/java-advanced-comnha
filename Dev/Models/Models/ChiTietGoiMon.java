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
@Table(name = "chitietgoimon")
public class ChiTietGoiMon  {
    @EmbeddedId
    ChitietgoimonKey id;

    @ManyToOne
    @MapsId("MaMonAn")
    @JoinColumn(name = "MaMonAn")
    Monan monan;


    @ManyToOne
    @MapsId("MaOrder")
    @JoinColumn(name = "MaOrder")
    Phieugoimon phieugoimon;

    private int SoLuongMonAn;
}