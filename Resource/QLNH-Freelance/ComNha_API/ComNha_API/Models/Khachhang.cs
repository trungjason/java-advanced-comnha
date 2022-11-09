using System;
using System.Collections.Generic;

namespace ComNha_API.Models
{
    public partial class Khachhang
    {
        public int MaKhachHang { get; set; }
        public string SoDienThoai { get; set; } = null!;
        public string TenKhachHang { get; set; } = null!;
        public string? DiaChi { get; set; }
        public string? Email { get; set; }
    }
}
