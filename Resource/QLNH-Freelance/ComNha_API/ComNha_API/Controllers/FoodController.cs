using ComNha_API.Models;
using Microsoft.AspNetCore.Cors;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace ComNha_API.Controllers
{
    [EnableCors("AllowOrigin")]
    [ApiController]
    [Route("[controller]")]
    public class FoodController : ControllerBase
    {
        private readonly ILogger<FoodController> _logger;

        public FoodController(ILogger<FoodController> logger)
        {
            _logger = logger;
        }


        [EnableCors("AllowOrigin")]
        [HttpPost("/api/comnha/create-food-group")]
        public IActionResult CreateFoodGroup(Nhommonan nhommonan)
        {
            using (var context = new quanlynhahangContext())
            {
                var foodGroup = context.Nhommonans.FirstOrDefault(group => group.TenNhom.Equals(nhommonan.TenNhom));

                if (foodGroup != null)
                {
                    return new JsonResult(new { status = false, message = "Nhóm món ăn đã tồn tại ! Vui lòng nhập lại !" })
                    {
                        StatusCode = StatusCodes.Status200OK // Status code here 
                    };
                }

                var maxFoodGroupID = context.Nhommonans.Max(group => group.MaNhom);

                if (maxFoodGroupID != null)
                {
                    int currentMax = int.Parse(maxFoodGroupID.ToString().Substring(maxFoodGroupID.Length - 3));
                    currentMax += 1;

                    maxFoodGroupID = maxFoodGroupID.ToString().Substring(0, maxFoodGroupID.Length - 3) + currentMax.ToString().PadLeft(3, '0');
                }
                else
                {
                    maxFoodGroupID = "NMA001";
                }

                context.Nhommonans.Add(new Nhommonan() { MaNhom = maxFoodGroupID, TenNhom = nhommonan.TenNhom });           
                context.SaveChanges();

                return new JsonResult(new { status = true, message = "Thêm nhóm món ăn thành công !" })
                {
                    StatusCode = StatusCodes.Status201Created // Status code here 
                };
            }
        }
   
        [EnableCors("AllowOrigin")]
        [HttpDelete("/api/comnha/delete-food-group/{foodGroupID}")]
        public IActionResult DeleteFoodGroup(string foodGroupID)
        {          
            using (var context = new quanlynhahangContext())
            {
                var foodGroupDeleted = context.Nhommonans.FirstOrDefault(group => group.MaNhom.Equals(foodGroupID));

                if (foodGroupDeleted == null)
                {
                    return new JsonResult(new { status = false, message = "Nhóm món ăn không tồn tại ! Vui lòng nhập lại !" })
                    {
                        StatusCode = StatusCodes.Status200OK // Status code here 
                    };
                }

                context.Nhommonans.Attach(foodGroupDeleted);
                context.Nhommonans.Remove(foodGroupDeleted);

                if (context.SaveChanges() > 0)
                {
                    return new JsonResult(new { status = true, message = "Xóa nhóm món ăn thành công !", foodGroupDeleted })
                    {
                        StatusCode = StatusCodes.Status200OK // Status code here 
                    };
                }

                return new JsonResult(new { status = false, message = "Xóa nhóm món ăn không thành công !", foodGroupDeleted })
                {
                    StatusCode = StatusCodes.Status200OK // Status code here 
                };
            }
        }

        [EnableCors("AllowOrigin")]
        [HttpPut("/api/comnha/edit-food-group")]
        public IActionResult EditFoodGroup(Nhommonan nhommonan)
        {
            using (var context = new quanlynhahangContext())
            {
                var updatedFoodGroup = context.Nhommonans.FirstOrDefault(group => group.MaNhom.Equals(nhommonan.MaNhom));

                if (updatedFoodGroup == null)
                {
                    return new JsonResult(new { status = false, message = "Nhóm món ăn không tồn tại ! Vui lòng nhập lại !" })
                    {
                        StatusCode = StatusCodes.Status200OK // Status code here 
                    };
                }
                
                context.Entry(updatedFoodGroup).CurrentValues.SetValues(nhommonan);

                if (context.SaveChanges() > 0)
                {
                    return new JsonResult(new { status = true, message = "Cập nhật thông tin nhóm món ăn thành công !", nhommonan })
                    {
                        StatusCode = StatusCodes.Status200OK // Status code here 
                    };
                }
                else
                {
                    return new JsonResult(new { status = false, message = "Cập nhật thông tin nhóm món ăn không thành công !" })
                    {
                        StatusCode = StatusCodes.Status200OK // Status code here 
                    };
                }
            }
        }

        [EnableCors("AllowOrigin")]
        [HttpGet("/api/comnha/detail-food-group/{foodGroupID}")]
        public Nhommonan DetailFoodGroup(string foodGroupID)
        {
            using (var context = new quanlynhahangContext())
            {
                return context.Nhommonans.FirstOrDefault(nma => nma.MaNhom.Equals(foodGroupID) );
            }
        }


        [EnableCors("AllowOrigin")]
        [HttpPost("/api/comnha/create-food")]
        public IActionResult CreateFood(Monan monan)
        {
            using (var context = new quanlynhahangContext())
            {
                var maxFoodID = context.Monans.Max(group => group.MaMonAn);

                if (maxFoodID != null)
                {
                    int currentMax = int.Parse(maxFoodID.ToString().Substring(maxFoodID.Length - 3));
                    currentMax += 1;

                    maxFoodID = maxFoodID.ToString().Substring(0, maxFoodID.Length - 3) + currentMax.ToString().PadLeft(3, '0');
                }
                else
                {
                    maxFoodID = "MA001";
                }

                monan.MaMonAn = maxFoodID;

                context.Monans.Add(monan);
                context.SaveChanges();

                return new JsonResult(new { status = true, message = "Thêm nhóm món ăn thành công !" })
                {
                    StatusCode = StatusCodes.Status201Created // Status code here 
                };
            }
        }

        [EnableCors("AllowOrigin")]
        [HttpDelete("/api/comnha/delete-food/{foodID}")]
        public IActionResult DeleteFood(string foodID)
        {
            using (var context = new quanlynhahangContext())
            {
                var foodDeleted = context.Monans.FirstOrDefault(food => food.MaMonAn.Equals(foodID));

                if (foodDeleted == null)
                {
                    return new JsonResult(new { status = false, message = "Nhóm món ăn không tồn tại ! Vui lòng nhập lại !" })
                    {
                        StatusCode = StatusCodes.Status200OK // Status code here 
                    };
                }

                context.Monans.Attach(foodDeleted);
                context.Monans.Remove(foodDeleted);

                if (context.SaveChanges() > 0)
                {
                    return new JsonResult(new { status = true, message = "Xóa món ăn thành công !", foodDeleted })
                    {
                        StatusCode = StatusCodes.Status200OK // Status code here 
                    };
                }

                return new JsonResult(new { status = false, message = "Xóa món ăn không thành công !", foodDeleted })
                {
                    StatusCode = StatusCodes.Status200OK // Status code here 
                };
            }
        }

        [EnableCors("AllowOrigin")]
        [HttpPut("/api/comnha/edit-food")]
        public IActionResult EditFood(Monan monan)
        {
            using (var context = new quanlynhahangContext())
            {
                var updatedFood = context.Monans.FirstOrDefault(ma => ma.MaMonAn.Equals(monan.MaMonAn));

                if (updatedFood == null)
                {
                    return new JsonResult(new { status = false, message = "Món ăn không tồn tại ! Vui lòng nhập lại !" })
                    {
                        StatusCode = StatusCodes.Status200OK // Status code here 
                    };
                }

                if (monan.HinhAnh.Equals("null"))
                {
                    monan.HinhAnh = updatedFood.HinhAnh;
                }

                //monan.MaNhomNavigation = null;

                context.Entry(updatedFood).CurrentValues.SetValues(monan);

                if (context.SaveChanges() > 0)
                {
                    return new JsonResult(new { status = true, message = "Cập nhật thông tin món ăn thành công !", data = monan })
                    {
                        StatusCode = StatusCodes.Status200OK // Status code here 
                    };
                }
                else
                {
                    return new JsonResult(new { status = false, message = "Cập nhật thông tin món ăn không thành công !" })
                    {
                        StatusCode = StatusCodes.Status200OK // Status code here 
                    };
                }
            }
        }
    }
}
