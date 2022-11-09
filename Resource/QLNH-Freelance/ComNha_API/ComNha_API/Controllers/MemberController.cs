using ComNha_API.Models;
using Microsoft.AspNetCore.Cors;
using Microsoft.AspNetCore.Mvc;

namespace ComNha_API.Controllers
{
    [EnableCors("AllowOrigin")]
    [ApiController]
    [Route("[controller]")]
    public class MemberController : ControllerBase
    {
        private readonly ILogger<MemberController> _logger;

        public MemberController(ILogger<MemberController> logger)
        {
            _logger = logger;
        }

        [EnableCors("AllowOrigin")]
        [HttpGet("{sdt}/discount")]
        public IActionResult GetMemberDiscount(string sdt)
        {
            using (var context = new quanlynhahangContext())
            {
                var hoivien = context.Hoiviens.FirstOrDefault(m => m.SoDienThoai == sdt);
                if (hoivien == null) return new JsonResult(new { status = false, message = "Mã hội viên không tồn tại." });

                if (hoivien.TongSoTienThanhToan > 20000000)
                {
                    return new JsonResult(new { status = true, discount = 0.15 });
                }
                if (hoivien.TongSoTienThanhToan > 15000000)
                {
                    return new JsonResult(new { status = true, discount = 0.12 });
                }
                if (hoivien.TongSoTienThanhToan > 10000000)
                {
                    return new JsonResult(new { status = true, discount = 0.1 });
                }
                if (hoivien.TongSoTienThanhToan > 5000000)
                {
                    return new JsonResult(new { status = true, discount = 0.08 });
                }
                if (hoivien.TongSoTienThanhToan > 3000000)
                {
                    return new JsonResult(new { status = true, discount = 0.05 });
                }
                return new JsonResult(new { status = true, discount = 0.02 });
            }
        }

        [EnableCors("AllowOrigin")]
        [HttpPost("{sdt}/change-password")]
        public IActionResult ChangeMemberPassword(string sdt, Password password)
        {
            using (var context = new quanlynhahangContext())
            {
                var hoivien = context.Hoiviens.FirstOrDefault(m => m.SoDienThoai == sdt);
                if(hoivien==null) return new JsonResult(new { status = false, message = "Mã hội viên không tồn tại." });

                if (!BCrypt.Net.BCrypt.Verify(password.oldPassword, hoivien.MatKhau))
                {
                    return new JsonResult(new { status = false, message = "Mật khẩu cũ không chính xác." });
                }
                hoivien.MatKhau = BCrypt.Net.BCrypt.HashPassword(password.newPassword);
                context.SaveChanges();
                return new JsonResult(new { status = true, message = "Đổi mật khẩu thành công." });
            }
        }

        [EnableCors("AllowOrigin")]
        [HttpGet("/api/comnha/get-members")]
        public IEnumerable<Hoivien> GetAllMember()
        {
            using (var context = new quanlynhahangContext())
            {
                return context.Hoiviens.ToList();
            }
        }

        [EnableCors("AllowOrigin")]
        [HttpPost("/api/comnha/create-member")]
        public IActionResult CreateMember(Hoivien hoivien)
        {
            using (var context = new quanlynhahangContext())
            {
                var existsEmail = context.Hoiviens.FirstOrDefault(hv => hv.Email.Equals(hoivien.Email));

                if (existsEmail != null)
                {
                    return new JsonResult(new { status = false, message = "Email đã tồn tại ! Vui lòng nhập lại !" })
                    {
                        StatusCode = StatusCodes.Status200OK // Status code here 
                    };
                }

                var existsPhoneNumber = context.Hoiviens.FirstOrDefault(hv => hv.SoDienThoai.Equals(hoivien.SoDienThoai));

                if (existsPhoneNumber != null)
                {
                    return new JsonResult(new { status = false, message = "Số điện thoại đã tồn tại ! Vui lòng nhập lại !" })
                    {
                        StatusCode = StatusCodes.Status200OK // Status code here 
                    };
                }

                string hash = BCrypt.Net.BCrypt.HashPassword(hoivien.SoDienThoai);
                hoivien.MatKhau = hash;
                context.Hoiviens.Add(hoivien);
              
                context.SaveChanges();

                return new JsonResult(new { status = true, message = "Thêm hội viên thành công !" })
                {
                    StatusCode = StatusCodes.Status201Created // Status code here 
                };
            }
        }

        [EnableCors("AllowOrigin")]
        [HttpGet("/api/comnha/get-member/{memberPhoneNumber}")]
        public IActionResult DetailMember(string memberPhoneNumber)
        {
            using (var context = new quanlynhahangContext())
            {
                var member = context.Hoiviens.FirstOrDefault(hv => hv.SoDienThoai.Equals(memberPhoneNumber));

                if (member == null)
                {
                    return new JsonResult(new { status = false, message = "Không tồn tại hội viên !", data = "" })
                    {
                        StatusCode = StatusCodes.Status200OK // Status code here 
                    };
                }

                return new JsonResult(new { status = true, message = "Lấy thông tin chi tiết hội viên thành công !", data = member })
                {
                    StatusCode = StatusCodes.Status200OK // Status code here 
                };
            }
        }

        [EnableCors("AllowOrigin")]
        [HttpDelete("/api/comnha/delete-member/{memberPhoneNumber}")]
        public IActionResult DeleteStaff(string memberPhoneNumber)
        {
            using (var context = new quanlynhahangContext())
            {
                var deletedMember = context.Hoiviens.FirstOrDefault(hv => hv.SoDienThoai.Equals(memberPhoneNumber));

                if (deletedMember == null)
                {
                    return new JsonResult(new { status = false, message = "Không tồn tại hội viên !" })
                    {
                        StatusCode = StatusCodes.Status200OK // Status code here 
                    };
                }

                context.Hoiviens.Attach(deletedMember);
                context.Hoiviens.Remove(deletedMember);

                if (context.SaveChanges() > 0)
                {
                    return new JsonResult(new { status = true, message = "Xóa hội viên thành công !", data = deletedMember })
                    {
                        StatusCode = StatusCodes.Status200OK // Status code here 
                    };
                }

                return new JsonResult(new { status = false, message = "Xóa hội viên không thành công !", data = deletedMember })
                {
                    StatusCode = StatusCodes.Status200OK // Status code here 
                };
            }
        }

        [EnableCors("AllowOrigin")]
        [HttpPut("/api/comnha/edit-member")]
        public IActionResult EditMember(Hoivien hoivien)
        {
            using (var context = new quanlynhahangContext())
            {
                var updatedStaff = context.Hoiviens.FirstOrDefault(hv => hv.SoDienThoai.Equals(hoivien.SoDienThoai));

                if (updatedStaff == null)
                {
                    return new JsonResult(new { status = false, message = "Không tồn tại hội viên !" })
                    {
                        StatusCode = StatusCodes.Status200OK // Status code here 
                    };
                }

                hoivien.MatKhau = updatedStaff.MatKhau;
                hoivien.TongSoTienThanhToan = updatedStaff.TongSoTienThanhToan;
                context.Entry(updatedStaff).CurrentValues.SetValues(hoivien);

                if (context.SaveChanges() > 0)
                {
                    return new JsonResult(new { status = true, message = "Cập nhật thông tin nhân viên thành công !", data = updatedStaff })
                    {
                        StatusCode = StatusCodes.Status200OK // Status code here 
                    };
                }
                else
                {
                    return new JsonResult(new { status = false, message = "Cập nhật thông tin nhân viên không thành công !", data = "" })
                    {
                        StatusCode = StatusCodes.Status200OK // Status code here 
                    };
                }
            }
        }
    }

    public class Password
    {
        public string oldPassword { get; set; }
        public string newPassword { get; set; }
    }
}