using ComNha_API.Models;
using Microsoft.AspNetCore.Cors;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace ComNha_API.Controllers
{
    [EnableCors("AllowOrigin")]
    [ApiController]
    [Route("[controller]")]
    public class ScheduleController : ControllerBase
    {
        private readonly ILogger<ScheduleController> _logger;

        public ScheduleController(ILogger<ScheduleController> logger)
        {
            _logger = logger;
        }

        [EnableCors("AllowOrigin")]
        [HttpGet("/api/comnha/get-schedule")]
        public IEnumerable<Lichhen> GetAllSchedule()
        {
            using (var context = new quanlynhahangContext())
            {
                return context.Lichhens.ToList();
            }
        }
  
        [EnableCors("AllowOrigin")]
        [HttpGet("/api/comnha/detail-schedule/{scheduleID}")]
        public Lichhen DetailSchedule(string scheduleID)
        {
            using (var context = new quanlynhahangContext())
            {
                return context.Lichhens.FirstOrDefault(lh => lh.MaLichHen.Equals(scheduleID));
            }
        }

        [EnableCors("AllowOrigin")]
        [HttpGet("/api/comnha/detail-customer/{customerID}")]
        public Khachhang DetailCustomer (int customerID)
        {
            using (var context = new quanlynhahangContext())
            {               
                return context.Khachhangs.FirstOrDefault(kh => kh.MaKhachHang == customerID); 
            }
        }

        [EnableCors("AllowOrigin")]
        [HttpPost("/api/comnha/update-schedule/{scheduleID}")]
        public IActionResult UpdateSchedule(string scheduleID, string tableID)
        {
            using (var context = new quanlynhahangContext())
            {
                var schedule = context.Lichhens.FirstOrDefault(lh => lh.MaLichHen.Equals(scheduleID));

                if (schedule == null)
                {
                    return new JsonResult(new { status = false, message = "Không tồn tại lịch hẹn !" })
                    {
                        StatusCode = StatusCodes.Status200OK // Status code here 
                    };
                }

                var table = context.Banans.FirstOrDefault(banan => banan.MaBanAn.Equals(tableID));

                if (table == null)
                {
                    return new JsonResult(new { status = false, message = "Không tìm thấy bàn ăn ! Vui lòng nhập lại !" })
                    {
                        StatusCode = StatusCodes.Status200OK // Status code here 
                    };
                }

                schedule.MaBanAn = tableID;
                schedule.TinhTrang = 1;
                table.TinhTrang = "Đặt hẹn trước";

                context.SaveChanges();

                return new JsonResult(new { status = true, message = "Đặt lịch cho bàn ăn thành công !" })
                {
                    StatusCode = StatusCodes.Status200OK // Status code here 
                };
            }
        }

        [EnableCors("AllowOrigin")]
        [HttpPost("/api/comnha/cancel-schedule/{scheduleID}")]
        public IActionResult CancelSchedule(string scheduleID)
        {
            using (var context = new quanlynhahangContext())
            {
                var schedule = context.Lichhens.FirstOrDefault(lh => lh.MaLichHen.Equals(scheduleID));

                if (schedule == null)
                {
                    return new JsonResult(new { status = false, message = "Không tồn tại lịch hẹn !" })
                    {
                        StatusCode = StatusCodes.Status200OK // Status code here 
                    };
                }

                var table = context.Banans.FirstOrDefault(banan => banan.MaBanAn.Equals(schedule.MaBanAn));

                if (table == null)
                {
                    return new JsonResult(new { status = false, message = "Không tìm thấy bàn ăn ! Vui lòng nhập lại !" })
                    {
                        StatusCode = StatusCodes.Status200OK // Status code here 
                    };
                }

                schedule.TinhTrang = -1;
                table.TinhTrang = "Trống";

                context.SaveChanges();

                return new JsonResult(new { status = true, message = "Hủy lịch cho bàn ăn thành công !" })
                {
                    StatusCode = StatusCodes.Status200OK // Status code here 
                };
            }
        }


        [EnableCors("AllowOrigin")]
        [HttpDelete("/api/comnha/delete-schedule/{scheduleID}")]
        public IActionResult DeleteSchedule(string scheduleID)
        {
            using (var context = new quanlynhahangContext())
            {
                var schedule = context.Lichhens.FirstOrDefault(lh => lh.MaLichHen.Equals(scheduleID));

                if (schedule == null)
                {
                    return new JsonResult(new { status = false, message = "Không tồn tại lịch hẹn !" })
                    {
                        StatusCode = StatusCodes.Status200OK // Status code here 
                    };
                }

                context.Lichhens.Attach(schedule);
                context.Lichhens.Remove(schedule);

                if (context.SaveChanges() > 0)
                {
                    return new JsonResult(new { status = true, message = "Xóa lịch hẹn thành công !", data = schedule })
                    {
                        StatusCode = StatusCodes.Status200OK // Status code here 
                    };
                }

                return new JsonResult(new { status = false, message = "Xóa lịch hẹn không thành công !", data = schedule })
                {
                    StatusCode = StatusCodes.Status200OK // Status code here 
                };
            }
        }
    }
}
