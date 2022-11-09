using ComNha_API.Models;
using Microsoft.AspNetCore.Cors;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace ComNha_API.Controllers
{
    [EnableCors("AllowOrigin")]
    [ApiController]
    [Route("[controller]")]
    public class BillController : ControllerBase
    {
        private readonly ILogger<BillController> _logger;

        public BillController(ILogger<BillController> logger)
        {
            _logger = logger;
        }

        [EnableCors("AllowOrigin")]
        [HttpPost("/api/comnha/revenue")]
        public IActionResult GetRevenue(DateTime fromTime, DateTime toTime)
        {
            using (var context = new quanlynhahangContext())
            {
                var doanhthu = from hoadon in context.Hoadons
                               join nhanvien in context.Nhanviens on hoadon.MaNhanVien equals nhanvien.MaNhanVien
                               where hoadon.ThoiGianThanhToan.CompareTo(fromTime) >= 0
                               where hoadon.ThoiGianThanhToan.CompareTo(toTime) <= 0
                               where hoadon.TinhTrang == 1
                               orderby hoadon.ThoiGianThanhToan descending
                               select new
                               {
                                   maHoaDon = hoadon.MaHoaDon,
                                   thoiGianThanhToan = hoadon.ThoiGianThanhToan,
                                   tongTien = hoadon.TongTien,
                                   chietKhau = hoadon.ChietKhau,
                                   tenNhanVien = nhanvien.TenNhanVien
                               };

                var doanhthu1 = from hoadon in context.Hoadons                              
                               where hoadon.ThoiGianThanhToan.CompareTo(fromTime) >= 0
                               where hoadon.ThoiGianThanhToan.CompareTo(toTime) <= 0
                               where hoadon.TinhTrang == 1
                               where hoadon.MaNhanVien == null
                               orderby hoadon.ThoiGianThanhToan descending
                               select new
                               {
                                   maHoaDon = hoadon.MaHoaDon,
                                   thoiGianThanhToan = hoadon.ThoiGianThanhToan,
                                   tongTien = hoadon.TongTien,
                                   chietKhau = hoadon.ChietKhau,
                                   tenNhanVien = "Đặt món trực tuyến"
                               };

                var test = DateTime.Now.CompareTo(fromTime);
                var test1 = DateTime.Now.CompareTo(toTime);

                if (doanhthu == null)
                {
                    return new JsonResult(new { status = false, message = "Không tìm thấy hóa đơn nào !" })
                    {
                        StatusCode = StatusCodes.Status200OK // Status code here 
                    };
                }

                return new JsonResult(new { status = true, message = "Thống kê doanh thu thành công.", data = doanhthu.ToList(), data1 = doanhthu1.ToList() })
                {
                    StatusCode = StatusCodes.Status202Accepted // Status code here 
                };
            }
        }       
    }
}
