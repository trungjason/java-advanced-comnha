using ComNha_API.Models;
using Microsoft.AspNetCore.Cors;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace ComNha_API.Controllers
{
    [EnableCors("AllowOrigin")]
    [ApiController]
    [Route("[controller]")]
    public class SupplierController : ControllerBase
    {
        private readonly ILogger<SupplierController> _logger;

        public SupplierController(ILogger<SupplierController> logger)
        {
            _logger = logger;
        }

        [EnableCors("AllowOrigin")]
        [HttpGet("/api/comnha/get-suppliers")]
        public IEnumerable<Nhacungcap> GetAllSupplier()
        {
            using (var context = new quanlynhahangContext())
            {
                return context.Nhacungcaps.ToList();
            }
        }

        [EnableCors("AllowOrigin")]
        [HttpPost("/api/comnha/create-supplier")]
        public IActionResult CreateSupplier(Nhacungcap nhacungcap)
        {
            using (var context = new quanlynhahangContext())
            {
                var supplier = context.Nhacungcaps.FirstOrDefault(ncc => ncc.SoDienThoai.Equals(nhacungcap.SoDienThoai));

                if (supplier != null)
                {
                    return new JsonResult(new { status = false, message = "Nhà cung cấp đã tồn tại ! Vui lòng nhập lại !" })
                    {
                        StatusCode = StatusCodes.Status200OK // Status code here 
                    };
                }

                var maxsupplierID = context.Nhacungcaps.Max(nv => nv.MaNhaCungCap);

                if (maxsupplierID != null)
                {
                    int currentMax = int.Parse(maxsupplierID.ToString().Substring(maxsupplierID.Length - 3));
                    currentMax += 1;

                    maxsupplierID = maxsupplierID.ToString().Substring(0, maxsupplierID.Length - 3) + currentMax.ToString().PadLeft(3, '0');
                }
                else
                {
                    maxsupplierID = "NCC001";
                }

                nhacungcap.MaNhaCungCap = maxsupplierID.ToString();

                context.Nhacungcaps.Add(nhacungcap);
                context.SaveChanges();

                return new JsonResult(new { status = true, message = "Thêm nhà cung cấp thành công !" })
                {
                    StatusCode = StatusCodes.Status201Created // Status code here 
                };
            }
        }

        [EnableCors("AllowOrigin")]
        [HttpGet("/api/comnha/get-supplier/{supplierID}")]
        public Nhacungcap DetailSupplier(string supplierID)
        {
            using (var context = new quanlynhahangContext())
            {
               return context.Nhacungcaps.FirstOrDefault(ncc => ncc.MaNhaCungCap.Equals(supplierID));
            }
        }

        [EnableCors("AllowOrigin")]
        [HttpDelete("/api/comnha/delete-supplier/{supplierID}")]
        public IActionResult DeleteSupplier(string supplierID)
        {
            using (var context = new quanlynhahangContext())
            {
                var supplier = context.Nhacungcaps.FirstOrDefault(ncc => ncc.MaNhaCungCap.Equals(supplierID));

                if (supplier == null)
                {
                    return new JsonResult(new { status = false, message = "Nhà cung cấp không tồn tại ! Vui lòng nhập lại !" })
                    {
                        StatusCode = StatusCodes.Status200OK // Status code here 
                    };
                }

                context.Nhacungcaps.Attach(supplier);
                context.Nhacungcaps.Remove(supplier);

                if (context.SaveChanges() > 0)
                {
                    return new JsonResult(new { status = true, message = "Xóa nhà cung cấp thành công !", data= supplier })
                    {
                        StatusCode = StatusCodes.Status200OK // Status code here 
                    };
                }

                return new JsonResult(new { status = false, message = "Xóa nhân viên không thành công !", data = supplier })
                {
                    StatusCode = StatusCodes.Status200OK // Status code here 
                };
            }
        }

        [EnableCors("AllowOrigin")]
        [HttpPut("/api/comnha/edit-supplier")]
        public IActionResult EditSupplier(Nhacungcap nhacungcap)
        {
            using (var context = new quanlynhahangContext())
            {
                var supplier = context.Nhacungcaps.FirstOrDefault(ncc => ncc.MaNhaCungCap.Equals(nhacungcap.MaNhaCungCap));

                if (supplier == null)
                {
                    return new JsonResult(new { status = false, message = "Nhà cung cấp không tồn tại ! Vui lòng nhập lại !" })
                    {
                        StatusCode = StatusCodes.Status200OK // Status code here 
                    };
                }

                context.Entry(supplier).CurrentValues.SetValues(nhacungcap);

                if (context.SaveChanges() > 0)
                {
                    return new JsonResult(new { status = true, message = "Cập nhật thông tin nhà cung cấp thành công !", data = nhacungcap })
                    {
                        StatusCode = StatusCodes.Status200OK // Status code here 
                    };
                }
                else
                {
                    return new JsonResult(new { status = false, message = "Cập nhật thông tin nhà cung cấp không thành công !" })
                    {
                        StatusCode = StatusCodes.Status200OK // Status code here 
                    };
                }
            }
        }    
    }
}
