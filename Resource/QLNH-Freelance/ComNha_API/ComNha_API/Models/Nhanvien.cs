using System;
using System.Collections.Generic;

namespace ComNha_API.Models
{
    public partial class Nhanvien
    {
        public string MaNhanVien { get; set; } = null!;
        public string TenNhanVien { get; set; } = null!;
        public string SoDienThoai { get; set; } = null!;
        public double Luong { get; set; }
        public string ChucVu { get; set; } = null!;
        public string DiaChi { get; set; } = null!;
        public string Email { get; set; } = null!;
    }
}
