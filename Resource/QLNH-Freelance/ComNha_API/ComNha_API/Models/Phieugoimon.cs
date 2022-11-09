using System;
using System.Collections.Generic;

namespace ComNha_API.Models
{
    public partial class Phieugoimon
    {
        public Phieugoimon()
        {
            Chitietgoimons = new HashSet<Chitietgoimon>();
        }

        public string MaOrder { get; set; } = null!;
        public string GhiChuMonAn { get; set; } = null!;
        public string? MaBanAn { get; set; }
        public string? MaNhanVien { get; set; }

        public virtual ICollection<Chitietgoimon> Chitietgoimons { get; set; }
    }
}
