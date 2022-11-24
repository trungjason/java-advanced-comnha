﻿package tdtu.advanced.java.thinh68.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name="nguyen_vat_lieu")
public class NguyenVatLieu {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "ma_nguyen_vat_lieu")
    private Long maNguyenVatLieu;
    
    @Column(name = "ten_nguyen_vat_lieu")
    private String tenNguyenVatLieu;
    
    @Column(name = "don_gia")
    private double donGia;
    
    @Column(name = "don_vi")
    private String donVi;
    
    @Column(name = "tinh_trang")
    private String tinhTrang;
    
    @Column(name = "so_luong_ton_kho")
    private int soLuongTonKho;
    
    @OneToMany(mappedBy="nguyenVatLieu")
    private List<ChiTietPhieuNhap> nguyenVatLieu_ChiTietPhieuNhaps;
}