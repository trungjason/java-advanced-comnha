namespace ComNha.Models
{
    public class Lichhen
    {
        public string MaLichHen { get; set; } = null!;
        public string? NhuCau { get; set; }
        public int SoLuongKhach { get; set; }
        public TimeSpan ThoiGian { get; set; }
        public DateTime NgayHen { get; set; }
        public int? TinhTrang { get; set; }
        public int MaKhachHang { get; set; }
        public string? MaNhanVien { get; set; }
        public string? MaBanAn { get; set; }
    }
}
