using ComNha_API.Models;
using Microsoft.AspNetCore.Cors;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace ComNha_API.Controllers
{
    [EnableCors("AllowOrigin")]
    [ApiController]
    [Route("[controller]")]
    public class StaffController : ControllerBase
    {
        private readonly ILogger<StaffController> _logger;

        public StaffController(ILogger<StaffController> logger)
        {
            _logger = logger;
        }

        [EnableCors("AllowOrigin")]
        [HttpGet("/api/comnha/get-staffs")]
        public IEnumerable<Nhanvien> GetAllStaff()
        {
            using (var context = new quanlynhahangContext())
            {
                return context.Nhanviens.ToList();
            }
        }

        [EnableCors("AllowOrigin")]
        [HttpPost("/api/comnha/create-staff")]
        public IActionResult CreateStaff(Nhanvien nhanvien)
        {
            using (var context = new quanlynhahangContext())
            {
                var existsEmail = context.Nhanviens.FirstOrDefault(nv => nv.Email.Equals(nhanvien.Email) );

                if ( existsEmail != null)
                {
                    return new JsonResult(new { status = false, message = "Email đã tồn tại ! Vui lòng nhập lại !" })
                    {
                        StatusCode = StatusCodes.Status203NonAuthoritative // Status code here 
                    };
                }

                var existsPhoneNumber = context.Nhanviens.FirstOrDefault(nv => nv.SoDienThoai.Equals(nhanvien.SoDienThoai) );

                if (existsPhoneNumber != null)
                {
                    return new JsonResult(new { status = false, message = "Số điện thoại đã tồn tại ! Vui lòng nhập lại !" })
                    {
                        StatusCode = StatusCodes.Status203NonAuthoritative // Status code here 
                    };
                }

                var maxStaffID = context.Nhanviens.Max(nv => nv.MaNhanVien);

                if (maxStaffID != null)
                {
                    int currentMax = int.Parse(maxStaffID.ToString().Substring(maxStaffID.Length - 3));
                    currentMax += 1;

                    maxStaffID = maxStaffID.ToString().Substring(0, maxStaffID.Length - 3) + currentMax.ToString().PadLeft(3, '0');
                }
                else
                {
                    maxStaffID = "NV001";
                }

                nhanvien.MaNhanVien = maxStaffID.ToString();

                context.Nhanviens.Add(nhanvien);

                string hash = BCrypt.Net.BCrypt.HashPassword(nhanvien.SoDienThoai);
                context.Taikhoans.Add(new Taikhoan { MaNhanVien = maxStaffID.ToString(), TenTaiKhoan = nhanvien.Email, MatKhau = hash });
                context.SaveChanges();

                return new JsonResult(new { status = true, message = "Thêm nhân viên thành công ! " +
                    "Tài khoản nhân viên đã được tạo mặc định với " +
                    "Tên tài khoản = Email nhân viên | Mật khẩu = Số điện thoại nhân viên !" })
                {
                    StatusCode = StatusCodes.Status201Created // Status code here 
                };
            }
        }

        [EnableCors("AllowOrigin")]
        [HttpGet("/api/comnha/get-staff/{staffID}")]
        public IActionResult DetailStaff(string staffID)
        {
            using (var context = new quanlynhahangContext())
            {
                var checkIsExist = context.Nhanviens.FirstOrDefault(nv => nv.MaNhanVien.Equals(staffID));

                if (checkIsExist == null)
                {
                    return new JsonResult(new { status = false, message = "Không tồn tại nhân viên với mã nhân viên !" })
                    {
                        StatusCode = StatusCodes.Status200OK // Status code here 
                    };
                }

                return new JsonResult(new { status = true, message = "Lấy thông tin chi tiết nhân viên thành công !", data = checkIsExist })
                {
                    StatusCode = StatusCodes.Status200OK // Status code here 
                };
            }
        }

        [EnableCors("AllowOrigin")]
        [HttpDelete("/api/comnha/delete-staff/{staffID}")]
        public IActionResult DeleteStaff(string staffID)
        {
            using (var context = new quanlynhahangContext())
            {
                var deletedStaff = context.Nhanviens.FirstOrDefault(nv => nv.MaNhanVien.Equals(staffID));
                var deletedAccount = context.Taikhoans.FirstOrDefault(account => account.MaNhanVien.Equals(staffID));

                if (deletedStaff == null || deletedAccount == null)
                {
                    return new JsonResult(new { status = false, message = "Không tồn tại nhân viên với mã nhân viên !" })
                    {
                        StatusCode = StatusCodes.Status200OK // Status code here 
                    };
                }

                context.Nhanviens.Attach(deletedStaff);
                context.Nhanviens.Remove(deletedStaff);

                context.Taikhoans.Attach(deletedAccount);
                context.Taikhoans.Remove(deletedAccount);

                if ( context.SaveChanges() > 0)
                {
                    return new JsonResult(new { status = true, message = "Xóa nhân viên thành công !", data = deletedStaff })
                    {
                        StatusCode = StatusCodes.Status200OK // Status code here 
                    };
                }

                return new JsonResult(new { status = false, message = "Xóa nhân viên không thành công !", data = deletedStaff })
                {
                    StatusCode = StatusCodes.Status200OK // Status code here 
                };
            }
        }

        [EnableCors("AllowOrigin")]
        [HttpPut("/api/comnha/edit-staff")]
        public IActionResult EditStaff(Nhanvien nhanvien)
        {
            using (var context = new quanlynhahangContext())
            {
                var updatedStaff = context.Nhanviens.FirstOrDefault(nv => nv.MaNhanVien.Equals(nhanvien.MaNhanVien));

                if (updatedStaff == null)
                {
                    return new JsonResult(new { status = false, message = "Không tồn tại nhân viên với mã nhân viên !" })
                    {
                        StatusCode = StatusCodes.Status200OK // Status code here 
                    };
                }

                context.Entry(updatedStaff).CurrentValues.SetValues(nhanvien);
                if ( context.SaveChanges() > 0)
                {
                    return new JsonResult(new { status = true, message = "Cập nhật thông tin nhân viên thành công !", data = nhanvien })
                    {
                        StatusCode = StatusCodes.Status200OK // Status code here 
                    };
                } else
                {
                    return new JsonResult(new { status = false, message = "Cập nhật thông tin nhân viên không thành công !" })
                    {
                        StatusCode = StatusCodes.Status200OK // Status code here 
                    };
                }
            }
        }

        [EnableCors("AllowOrigin")]
        [HttpPost("/api/comnha/staffs/reset-password/{staffID}")]
        public IActionResult ChangePassword(string staffID)
        {
            using (var context = new quanlynhahangContext())
            {
                var taikhoan = context.Taikhoans.FirstOrDefault(account => account.MaNhanVien.Equals(staffID));
                var nhanvien = context.Nhanviens.FirstOrDefault(nv => nv.MaNhanVien.Equals(staffID));

                if (taikhoan == null || nhanvien == null)
                {
                    return new JsonResult(new { status = false, message = "Không tìm thấy tài khoản vui lòng thử lại !" })
                    {
                        StatusCode = StatusCodes.Status200OK // Status code here 
                    };
                }

                taikhoan.MatKhau = BCrypt.Net.BCrypt.HashPassword(nhanvien.SoDienThoai);
                context.SaveChanges();
                return new JsonResult(new { status = true, message = "Reset mật khẩu thành công. Mật khẩu mặc định là số điện thoại của nhân viên" })
                {
                    StatusCode = StatusCodes.Status200OK // Status code here 
                };
            }
        }
    }
}
