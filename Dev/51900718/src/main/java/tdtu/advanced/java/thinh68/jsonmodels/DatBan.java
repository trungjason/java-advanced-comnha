package tdtu.advanced.java.thinh68.jsonmodels;

import java.sql.Date;

public class DatBan {
	private String tenKhachHang;
	private String soDienThoai;
	private int soLuongKhach;
	private Date ngayHen;
	private String thoiGian;
	private String nhuCau;   	
	
	public DatBan() {
	}
	
	public DatBan(String tenKhachHang, String soDienThoai, int soLuongKhach, Date ngayHen, String thoiGian,
			String nhuCau) {
		super();
		this.tenKhachHang = tenKhachHang;
		this.soDienThoai = soDienThoai;
		this.soLuongKhach = soLuongKhach;
		this.ngayHen = ngayHen;
		this.thoiGian = thoiGian;
		this.nhuCau = nhuCau;
	}
			

	public String getTenKhachHang() {
		return tenKhachHang;
	}

	public void setTenKhachHang(String tenKhachHang) {
		this.tenKhachHang = tenKhachHang;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	public int getSoLuongKhach() {
		return soLuongKhach;
	}

	public void setSoLuongKhach(int soLuongKhach) {
		this.soLuongKhach = soLuongKhach;
	}

	public Date getNgayHen() {
		return ngayHen;
	}

	public void setNgayHen(Date ngayHen) {
		this.ngayHen = ngayHen;
	}

	public String getThoiGian() {
		return thoiGian;
	}

	public void setThoiGian(String thoiGian) {
		this.thoiGian = thoiGian;
	}

	public String getNhuCau() {
		return nhuCau;
	}

	public void setNhuCau(String nhuCau) {
		this.nhuCau = nhuCau;
	}

	@Override
	public String toString() {
		return "DatBan [tenKhachHang=" + tenKhachHang + ", soDienThoai=" + soDienThoai + ", soLuongKhach="
				+ soLuongKhach + ", ngayHen=" + ngayHen + ", thoiGian=" + thoiGian + ", nhuCau=" + nhuCau + "]";
	}   	
}
