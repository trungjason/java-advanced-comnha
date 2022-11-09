using ComNha_API.Models;
using Microsoft.AspNetCore.Cors;
using Microsoft.AspNetCore.Mvc;

namespace ComNha_API.Controllers
{
    [EnableCors("AllowOrigin")]
    [ApiController]
    [Route("[controller]")]
    public class ReceivedNoteController : ControllerBase
    {
        private readonly ILogger<ReceivedNoteController> _logger;

        public ReceivedNoteController(ILogger<ReceivedNoteController> logger)
        {
            _logger = logger;
        }

        [EnableCors("AllowOrigin")]
        [HttpGet("/api/comnha/get-received-notes")]
        public IEnumerable<Phieunhap> GetAllStaff()
        {
            using (var context = new quanlynhahangContext())
            {
                return context.Phieunhaps.ToList();
            }
        }

        [EnableCors("AllowOrigin")]
        [HttpPost("/api/comnha/create-received-note")]
        public IActionResult CreateReceivedNote(TempReceivedNote tempReceivedNote)
        {
            using (var context = new quanlynhahangContext())
            {
                var maxReceivedNoteID = context.Phieunhaps.Max(pn => pn.MaPhieuNhap);

                if (maxReceivedNoteID != null)
                {
                    int currentMax = int.Parse(maxReceivedNoteID.ToString().Substring(maxReceivedNoteID.Length - 3));
                    currentMax += 1;

                    maxReceivedNoteID = maxReceivedNoteID.ToString().Substring(0, maxReceivedNoteID.Length - 3) + currentMax.ToString().PadLeft(3, '0');
                }
                else
                {
                    maxReceivedNoteID = "PN001";
                }

                Phieunhap phieunhap = new Phieunhap();
                phieunhap.MaPhieuNhap = maxReceivedNoteID.ToString();
                phieunhap.MaNhanVien = tempReceivedNote.MaNhanVien;
                phieunhap.MaNhaCungCap = tempReceivedNote.MaNhaCungCap;
                phieunhap.TongGiaNhap = tempReceivedNote.TongGiaNhap;
                phieunhap.NgayNhapHang = tempReceivedNote.NgayNhapHang;
                phieunhap.GhiChu = tempReceivedNote.GhiChu;

                context.Phieunhaps.Add(phieunhap);
                context.SaveChanges();

                foreach (TempChiTietPhieuNhap chitietphieunhap in tempReceivedNote.chitietphieunhaps)
                {
                    var exists = context.Chitietphieunhaps.FirstOrDefault(pn => pn.MaPhieuNhap.Equals(maxReceivedNoteID.ToString())
                    && pn.MaNguyenVatLieu.Equals(chitietphieunhap.MaNguyenVatLieu));

                    var existsMaterials = context.Nguyenvatlieus.FirstOrDefault(nvl => nvl.MaNguyenVatLieu.Equals(chitietphieunhap.MaNguyenVatLieu));

                    if (existsMaterials != null)
                    {
                        existsMaterials.SoLuongTonKho = existsMaterials.SoLuongTonKho + chitietphieunhap.SoLuongNhap;
                        context.SaveChanges();
                    }

                    if (exists != null)
                    {
                        exists.SoLuongNhap = exists.SoLuongNhap + chitietphieunhap.SoLuongNhap;
                        context.SaveChanges();
                        continue;
                    }
                  
                    context.Chitietphieunhaps.Add(new Chitietphieunhap() { 
                        MaPhieuNhap = maxReceivedNoteID.ToString(), 
                        MaNguyenVatLieu = chitietphieunhap.MaNguyenVatLieu, 
                        SoLuongNhap = chitietphieunhap.SoLuongNhap
                    });
                    context.SaveChanges();
                }

                context.SaveChanges();

                return new JsonResult(new
                {
                    status = true,
                    message = "Thêm phiếu nhập thành công"
                })
                {
                    StatusCode = StatusCodes.Status201Created // Status code here 
                };
            }
        }


        [EnableCors("AllowOrigin")]
        [HttpGet("/api/comnha/get-received-note/{receivedNoteID}")]
        public Phieunhap DetailReceivedNote(string receivedNoteID)
        {
            using (var context = new quanlynhahangContext())
            {
                return context.Phieunhaps.FirstOrDefault(pn => pn.MaPhieuNhap.Equals(receivedNoteID));
            }
        }

        [EnableCors("AllowOrigin")]
        [HttpGet("/api/comnha/get-received-notes/{receivedNoteID}")]
        public IActionResult DetailReceivedNotes(string receivedNoteID)
        {
            using (var context = new quanlynhahangContext())
            {
                var result = from chitietphieunhap in context.Chitietphieunhaps
                             join nguyenvatlieu in context.Nguyenvatlieus on chitietphieunhap.MaNguyenVatLieu equals nguyenvatlieu.MaNguyenVatLieu
                             where chitietphieunhap.MaPhieuNhap.Equals(receivedNoteID)
                             select new
                             {
                                 maNguyenVatLieu = nguyenvatlieu.MaNguyenVatLieu,
                                 soLuongNhap = chitietphieunhap.SoLuongNhap,
                                 tenNguyenVatLieu = nguyenvatlieu.TenNguyenVatLieu,
                                 donVi = nguyenvatlieu.DonVi,
                                 donGia = nguyenvatlieu.DonGia
                             };

                return new JsonResult(new { data = result.ToList() })
                {
                    StatusCode = StatusCodes.Status200OK // Status code here 
                };
            }
        }

        [EnableCors("AllowOrigin")]
        [HttpDelete("/api/comnha/delete-received-note/{receivedNoteID}")]
        public IActionResult DeleteReceivedNote(string receivedNoteID)
        {
            using (var context = new quanlynhahangContext())
            {
                var deletedModel = context.Phieunhaps.FirstOrDefault(pn => pn.MaPhieuNhap.Equals(receivedNoteID));

                if (deletedModel == null)
                {
                    return new JsonResult(new { status = false, message = "Không tồn tại phiếu nhập !" })
                    {
                        StatusCode = StatusCodes.Status200OK // Status code here 
                    };
                }

                var listDeleted = context.Chitietphieunhaps.Where(x => x.MaPhieuNhap.Equals(receivedNoteID)).ToList();

                if (listDeleted != null)
                {
                    listDeleted.ForEach(chitietphieunhap =>
                    {
                        context.Chitietphieunhaps.Remove(chitietphieunhap);
                    });  
                }

                

                if (context.SaveChanges() > 0)
                {
                    return new JsonResult(new { status = true, message = "Xóa phiếu nhập thành công !" })
                    {
                        StatusCode = StatusCodes.Status200OK // Status code here 
                    };
                }

                return new JsonResult(new { status = false, message = "Xóa phiếu nhập không thành công !" })
                {
                    StatusCode = StatusCodes.Status200OK // Status code here 
                };
            }
        }

        //[EnableCors("AllowOrigin")]
        //[HttpPut("/api/comnha/edit-staff")]
        //public IActionResult EditStaff(Nhanvien nhanvien)
        //{
        //    using (var context = new quanlynhahangContext())
        //    {
        //        var updatedStaff = context.Nhanviens.FirstOrDefault(nv => nv.MaNhanVien.Equals(nhanvien.MaNhanVien));

        //        if (updatedStaff == null)
        //        {
        //            return new JsonResult(new { status = false, message = "Không tồn tại nhân viên với mã nhân viên !" })
        //            {
        //                StatusCode = StatusCodes.Status200OK // Status code here 
        //            };
        //        }

        //        context.Entry(updatedStaff).CurrentValues.SetValues(nhanvien);
        //        if (context.SaveChanges() > 0)
        //        {
        //            return new JsonResult(new { status = true, message = "Cập nhật thông tin nhân viên thành công !", data = nhanvien })
        //            {
        //                StatusCode = StatusCodes.Status200OK // Status code here 
        //            };
        //        }
        //        else
        //        {
        //            return new JsonResult(new { status = false, message = "Cập nhật thông tin nhân viên không thành công !" })
        //            {
        //                StatusCode = StatusCodes.Status200OK // Status code here 
        //            };
        //        }
        //    }
        //}
    }

    public class TempReceivedNote
    {
        public string MaPhieuNhap { get; set; } = null!;
        public DateTime NgayNhapHang { get; set; }
        public double TongGiaNhap { get; set; }
        public string GhiChu { get; set; } = null!;
        public string MaNhaCungCap { get; set; } = null!;
        public string MaNhanVien { get; set; } = null!;

        public List<TempChiTietPhieuNhap> chitietphieunhaps { get; set; }
    }

    public class TempChiTietPhieuNhap
    {
        public string MaNguyenVatLieu { get; set; } = null!;
        public string MaPhieuNhap { get; set; } = null!;
        public int SoLuongNhap { get; set; }
    }
}
