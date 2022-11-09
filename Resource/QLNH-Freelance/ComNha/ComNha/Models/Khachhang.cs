namespace ComNha.Models
{
    public class Khachhang
    {
        public int MaKhachHang { get; set; }
        public string SoDienThoai { get; set; } = null!;
        public string TenKhachHang { get; set; } = null!;
        public string? DiaChi { get; set; }
        public string? Email { get; set; }
    }
}
