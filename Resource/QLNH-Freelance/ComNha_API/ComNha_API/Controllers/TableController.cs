using ComNha_API.Models;
using Microsoft.AspNetCore.Cors;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace ComNha_API.Controllers
{
    [EnableCors("AllowOrigin")]
    [ApiController]
    [Route("[controller]")]
    public class TableController : ControllerBase
    {
        private readonly ILogger<TableController> _logger;

        public TableController(ILogger<TableController> logger)
        {
            _logger = logger;
        }

        [EnableCors("AllowOrigin")]
        [HttpGet("/api/comnha/get-tables")]
        public IEnumerable<Banan> GetAllTable()
        {
            using (var context = new quanlynhahangContext())
            {
                return context.Banans.ToList();
            }
        }

        [EnableCors("AllowOrigin")]
        [HttpPut("/api/comnha/set-tables/{tableID}")]
        public IActionResult SetTableState(string tableID, string tableState)
        {
            using (var context = new quanlynhahangContext())
            {
                var table = context.Banans.FirstOrDefault(banan => banan.MaBanAn.Equals(tableID));

                if ( table == null)
                {
                    return new JsonResult(new { status = false, message = "Không tìm thấy bàn ăn ! Vui lòng nhập lại !" })
                    {
                        StatusCode = StatusCodes.Status200OK // Status code here 
                    };
                }

                table.TinhTrang = tableState;

                context.Entry(table).CurrentValues.SetValues(table);

                if (!(context.SaveChanges() > 0))
                {
                    return new JsonResult(new { status = false, message = "Cập nhật thông tin bàn ăn không thành công !" })
                    {
                        StatusCode = StatusCodes.Status200OK // Status code here 
                    };
                }

                return new JsonResult(new { status = true, message = "Cập nhật thông tin bàn ăn thành công !" })
                {
                    StatusCode = StatusCodes.Status200OK // Status code here 
                };
            }
        }

        [EnableCors("AllowOrigin")]
        [HttpGet("/api/comnha/get-tables/{tableID}")]
        public IActionResult GetTableByID(string tableID)
        {
            using (var context = new quanlynhahangContext())
            {
                var table = context.Banans.FirstOrDefault(banan => banan.MaBanAn.Equals(tableID));

                if (table == null)
                {
                    return new JsonResult(new { status = false, message = "Không tìm thấy bàn ăn ! Vui lòng nhập lại !" })
                    {
                        StatusCode = StatusCodes.Status204NoContent // Status code here 
                    };
                }

                return new JsonResult(new { status = true, message = "OKE", data = table })
                {
                    StatusCode = StatusCodes.Status200OK // Status code here 
                };
            }
        }
    }
}
