﻿using System;
using System.Collections.Generic;

namespace ComNha_API.Models
{
    public partial class Banan
    {
        public string MaBanAn { get; set; } = null!;
        public string TinhTrang { get; set; } = null!;
        public int SucChua { get; set; }
        public string GhiChu { get; set; } = null!;
    }
}
