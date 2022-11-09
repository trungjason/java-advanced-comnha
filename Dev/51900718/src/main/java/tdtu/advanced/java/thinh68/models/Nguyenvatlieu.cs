namespace ComNha.Models
{
    public class Nguyenvatlieu
    {
        public string MaNguyenVatLieu { get; set; } = null!;
        public string TenNguyenVatLieu { get; set; } = null!;
        public double DonGia { get; set; }
        public string DonVi { get; set; } = null!;
        public string TinhTrang { get; set; } = null!;
        public int SoLuongTonKho { get; set; }
    }
}
