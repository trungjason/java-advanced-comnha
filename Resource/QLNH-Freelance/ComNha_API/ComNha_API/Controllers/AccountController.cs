using ComNha_API.Models;
using Microsoft.AspNetCore.Cors;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace ComNha_API.Controllers
{
    [EnableCors("AllowOrigin")]
    [ApiController]
    [Route("[controller]")]
    public class AccountController : ControllerBase
    {
        private readonly ILogger<AccountController> _logger;

        public AccountController(ILogger<AccountController> logger)
        {
            _logger = logger;
        }

        [EnableCors("AllowOrigin")]
        [HttpPost("/api/comnha/login")]
        public IActionResult ValidateLogin(Login login)
        {
            if ( login.username == null || login.password == null)
            {
                return new JsonResult(new { status = false, message = "Sai tên tài khoản hoặc mật khẩu ! Vui lòng nhập lại !" })
                {
                    StatusCode = StatusCodes.Status200OK // Status code here 
                };
            }

            using (var context = new quanlynhahangContext())
            {
                var taikhoan = context.Taikhoans.FirstOrDefault( account => account.TenTaiKhoan.Equals(login.username));
                
                if ( taikhoan == null)
                {
                    return new JsonResult(new { status = false, message = "Sai tên tài khoản hoặc mật khẩu ! Vui lòng nhập lại !", data = "" })
                    {
                        StatusCode = StatusCodes.Status200OK // Status code here 
                    };
                }

                if (!BCrypt.Net.BCrypt.Verify(login.password, taikhoan.MatKhau))
                {
                    return new JsonResult(new { status = false, message = "Sai tên tài khoản hoặc mật khẩu ! Vui lòng nhập lại !" , data = "" })
                    {
                        StatusCode = StatusCodes.Status200OK // Status code here 
                    };
                }

                // DÙNG JWT ĐỂ BẢO MẬT API
                return new JsonResult(new { status = true, message = "Đăng nhập thành công.", data = taikhoan.MaNhanVien })
                {
                    StatusCode = StatusCodes.Status202Accepted // Status code here 
                };
            }
        }

        [EnableCors("AllowOrigin")]
        [HttpPost("/api/comnha/change-password")]
        public IActionResult ChangePassword(TempAccount password)
        {
            if ( !ModelState.IsValid)
            {
                return new JsonResult(new { status = false, message = "Vui lòng nhập điền đầy đủ thông tin !" })
                {
                    StatusCode = StatusCodes.Status200OK // Status code here 
                };
            }

            if ( !password.newPassword.Equals(password.newPassword_Confirm) )
            {
                return new JsonResult(new { status = false, message = "Mật khẩu xác nhận không khớp !" })
                {
                    StatusCode = StatusCodes.Status200OK // Status code here 
                };
            }

            using (var context = new quanlynhahangContext())
            {
                var taikhoan = context.Taikhoans.FirstOrDefault(account => account.MaNhanVien.Equals(password.staffID));

                if (taikhoan == null)
                {
                    return new JsonResult(new { status = false, message = "Sai tên tài khoản hoặc mật khẩu ! Vui lòng nhập lại !" })
                    {
                        StatusCode = StatusCodes.Status200OK // Status code here 
                    };
                }

                if (!BCrypt.Net.BCrypt.Verify(password.oldPassword, taikhoan.MatKhau))
                {
                    return new JsonResult(new { status = false, message = "Mật khẩu cũ không chính xác ! Vui lòng nhập lại !" })
                    {
                        StatusCode = StatusCodes.Status200OK // Status code here 
                    };
                }

                taikhoan.MatKhau = BCrypt.Net.BCrypt.HashPassword(password.newPassword);
                context.SaveChanges();
                return new JsonResult(new { status = true, message = "Đổi mật khẩu thành công." })
                {
                    StatusCode = StatusCodes.Status200OK // Status code here 
                };
            }
        }
    }
}

public class TempAccount
{
    public string staffID { get; set; } = null!;
    public string oldPassword { get; set; } = null!;

    public string newPassword { get; set; } = null!;

    public string newPassword_Confirm { get; set; } = null!;
}

public class Login
{
    public string username { get; set; } = null!;
    public string password { get; set; } = null!;
}
