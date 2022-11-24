package tdtu.advanced.java.thinh68.models;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
 
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "ban_an")
public  class BanAn {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name="ma_ban_an")
    private Long maBanAn;
    
    @Column(name="tinh_trang", nullable = false)
    private String tinhTrang;
    	
    @Column(name="suc_chua", nullable = false)
    private Integer sucChua;
    
    @Column(name="ghi_chu")
    private String ghiChu;
    
    @OneToMany(mappedBy = "banAn")
    private List<LichHen> banAn_LichHens;

    @OneToMany(mappedBy = "banAn")
    private List<PhieuGoiMon> banAn_PhieuGoiMons;
}