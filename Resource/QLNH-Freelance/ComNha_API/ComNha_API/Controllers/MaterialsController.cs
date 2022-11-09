using ComNha_API.Models;
using Microsoft.AspNetCore.Cors;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace ComNha_API.Controllers
{
    [EnableCors("AllowOrigin")]
    [ApiController]
    [Route("[controller]")]
    public class MaterialsController : ControllerBase
    {
        private readonly ILogger<MaterialsController> _logger;

        public MaterialsController(ILogger<MaterialsController> logger)
        {
            _logger = logger;
        }

        [EnableCors("AllowOrigin")]
        [HttpGet("/api/comnha/get-materials")]
        public IEnumerable<Nguyenvatlieu> GetAllMaterials()
        {
            using (var context = new quanlynhahangContext())
            {
                return context.Nguyenvatlieus.ToList();
            }
        }
  
        [EnableCors("AllowOrigin")]
        [HttpGet("/api/comnha/detail-material/{materialID}")]
        public Nguyenvatlieu DetailMaterial(string materialID)
        {
            using (var context = new quanlynhahangContext())
            {
                return context.Nguyenvatlieus.FirstOrDefault(nvl => nvl.MaNguyenVatLieu.Equals(materialID));
            }
        }

        [EnableCors("AllowOrigin")]
        [HttpDelete("/api/comnha/delete-material/{materialID}")]
        public IActionResult DeleteMaterial(string materialID)
        {
            using (var context = new quanlynhahangContext())
            {
                var schedule = context.Nguyenvatlieus.FirstOrDefault(nvl => nvl.MaNguyenVatLieu.Equals(materialID));

                if (schedule == null)
                {
                    return new JsonResult(new { status = false, message = "Không tồn tại nguyên vật liệu !" })
                    {
                        StatusCode = StatusCodes.Status200OK // Status code here 
                    };
                }

                context.Nguyenvatlieus.Attach(schedule);
                context.Nguyenvatlieus.Remove(schedule);

                if (context.SaveChanges() > 0)
                {
                    return new JsonResult(new { status = true, message = "Xóa nguyên vật liệu thành công !", data = schedule })
                    {
                        StatusCode = StatusCodes.Status200OK // Status code here 
                    };
                }

                return new JsonResult(new { status = false, message = "Xóa nguyên vật liệu không thành công !", data = schedule })
                {
                    StatusCode = StatusCodes.Status200OK // Status code here 
                };
            }
        }

        [EnableCors("AllowOrigin")]
        [HttpPut("/api/comnha/edit-material")]
        public IActionResult EditMaterial(Nguyenvatlieu nguyenvatlieu)
        {
            using (var context = new quanlynhahangContext())
            {
                var material = context.Nguyenvatlieus.FirstOrDefault(nvl => nvl.MaNguyenVatLieu.Equals(nguyenvatlieu.MaNguyenVatLieu));

                if (material == null)
                {
                    return new JsonResult(new { status = false, message = "Nguyên vật liệu không tồn tại ! Vui lòng nhập lại !" })
                    {
                        StatusCode = StatusCodes.Status200OK // Status code here 
                    };
                }

                context.Entry(material).CurrentValues.SetValues(nguyenvatlieu);

                if (context.SaveChanges() > 0)
                {
                    return new JsonResult(new { status = true, message = "Cập nhật thông tin nguyên vật liệu thành công !", data = nguyenvatlieu })
                    {
                        StatusCode = StatusCodes.Status200OK // Status code here 
                    };
                }
                else
                {
                    return new JsonResult(new { status = false, message = "Cập nhật thông tin nguyên vật liệu không thành công !" })
                    {
                        StatusCode = StatusCodes.Status200OK // Status code here 
                    };
                }
            }
        }

        [EnableCors("AllowOrigin")]
        [HttpPost("/api/comnha/create-material")]
        public IActionResult CreateMaterial(Nguyenvatlieu nguyenvatlieu)
        {
            using (var context = new quanlynhahangContext())
            {
                var maxMaterialID = context.Nguyenvatlieus.Max(nv => nv.MaNguyenVatLieu);

                if (maxMaterialID != null)
                {
                    int currentMax = int.Parse(maxMaterialID.ToString().Substring(maxMaterialID.Length - 3));
                    currentMax += 1;

                    maxMaterialID = maxMaterialID.ToString().Substring(0, maxMaterialID.Length - 3) + currentMax.ToString().PadLeft(3, '0');
                }
                else
                {
                    maxMaterialID = "NVL001";
                }

                nguyenvatlieu.MaNguyenVatLieu = maxMaterialID.ToString();

                context.Nguyenvatlieus.Add(nguyenvatlieu);
                context.SaveChanges();

                return new JsonResult(new { status = true, message = "Thêm nguyên vật liệu thành công !" })
                {
                    StatusCode = StatusCodes.Status201Created // Status code here 
                };
            }
        }
    }
}
