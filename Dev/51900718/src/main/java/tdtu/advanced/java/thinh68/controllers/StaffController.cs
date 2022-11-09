using ComNha.Helper;
using ComNha.Models;
using Microsoft.AspNetCore.Mvc;
using Newtonsoft.Json;
using System.Net.Http.Headers;

namespace ComNha.Controllers
{
    public class StaffController : Controller
    {
        public static readonly string VIEW_PATH = "~/Views/RestaurentManagement/Staff/";
        //Hosted web API REST Service base url
        private const string API_URL = "https://localhost:5556/api/comnha/";

        [HttpGet]
        [Route("comnha/staffs")]
        public async Task<ActionResult> Index()
        {
            if (HttpContext.Session.GetInt32("IS_LOGIN") != 1)
            {
                return Redirect("/comnha/login");
            }
        
            Nhanvien nhanvien = JsonConvert.DeserializeObject<Nhanvien>(HttpContext.Session.GetString("STAFF"));
            return View(VIEW_PATH+ "Index.cshtml", nhanvien);
        }

        [HttpGet]
        [Route("comnha/staffs/create")]
        public async Task<ActionResult> Create()
        {
            if (HttpContext.Session.GetInt32("IS_LOGIN") != 1)
            {
                return Redirect("/comnha/login");
            }
            
            Nhanvien nhanvien = JsonConvert.DeserializeObject<Nhanvien>(HttpContext.Session.GetString("STAFF"));
            return View(VIEW_PATH + "Create.cshtml", nhanvien);
        }

        [HttpGet]
        [Route("comnha/staffs/detail/{staffID}")]
        public async Task<ActionResult> Detail(string staffID)
        {
            if (HttpContext.Session.GetInt32("IS_LOGIN") != 1)
            {
                return Redirect("/comnha/login");
            }

            using (var client = new HttpClient())
            {
                //Passing service base url
                client.BaseAddress = new Uri(API_URL);
                client.DefaultRequestHeaders.Clear();

                //Define request data format
                client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));

                HttpResponseMessage Res = await client.GetAsync("get-staff/" + staffID);

                //Checking the response is successful or not which is sent using HttpClient
                if (Res.IsSuccessStatusCode)
                {
                    //Storing the response details recieved from web api
                    var response = Res.Content.ReadAsStringAsync().Result;

                    var staffDefinition = new
                    {
                        maNhanVien = "string",
                        tenNhanVien = "string",
                        soDienThoai = "string",
                        luong = 0,
                        chucVu = "string",
                        diaChi = "string",
                        email = "string"
                    };

                    var responseDefinition = new { status = false, message = "", data = staffDefinition };


                    var result = JsonConvert.DeserializeAnonymousType(response, responseDefinition);

                    if (result != null && result.status)
                    {
                        var staff = result.data;

                        Nhanvien nhanvien_detail = new Nhanvien()
                        {
                            MaNhanVien = staff.maNhanVien,
                            TenNhanVien = staff.tenNhanVien,
                            SoDienThoai = staff.soDienThoai,
                            ChucVu = staff.chucVu,
                            DiaChi = staff.diaChi,
                            Email = staff.email,
                            Luong = staff.luong
                        };

                        ViewData["DETAIL_STAFF"] = nhanvien_detail;
                        Nhanvien nhanvien = JsonConvert.DeserializeObject<Nhanvien>(HttpContext.Session.GetString("STAFF"));
                        return View(VIEW_PATH + "Detail.cshtml", nhanvien);
                    }
                    else
                    {
                        ViewData["ErrorStatus"] = true;
                        ViewData["ErrorMessage"] = result.message;
                        return RedirectToAction("Index");
                    }
                }
                else
                {
                    // Error message here
                    HttpContext.Session.Clear();
                    return Redirect("/comnha/login");
                }
            }        
        }

        [HttpGet]
        [Route("comnha/staffs/edit/{staffID}")]
        public async Task<ActionResult> Edit(string staffID)
        {
            if (HttpContext.Session.GetInt32("IS_LOGIN") != 1)
            {
                return Redirect("/comnha/login");
            }

            using (var client = new HttpClient())
            {
                //Passing service base url
                client.BaseAddress = new Uri(API_URL);
                client.DefaultRequestHeaders.Clear();

                //Define request data format
                client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));

                HttpResponseMessage Res = await client.GetAsync("get-staff/" + staffID);

                //Checking the response is successful or not which is sent using HttpClient
                if (Res.IsSuccessStatusCode)
                {
                    //Storing the response details recieved from web api
                    var response = Res.Content.ReadAsStringAsync().Result;

                    var staffDefinition = new
                    {
                        maNhanVien = "string",
                        tenNhanVien = "string",
                        soDienThoai = "string",
                        luong = 0,
                        chucVu = "string",
                        diaChi = "string",
                        email = "string"
                    };

                    var responseDefinition = new { status = false, message = "", data = staffDefinition };


                    var result = JsonConvert.DeserializeAnonymousType(response, responseDefinition);

                    if (result != null && result.status)
                    {
                        var staff = result.data;

                        Nhanvien nhanvien_detail = new Nhanvien()
                        {
                            MaNhanVien = staff.maNhanVien,
                            TenNhanVien = staff.tenNhanVien,
                            SoDienThoai = staff.soDienThoai,
                            ChucVu = staff.chucVu,
                            DiaChi = staff.diaChi,
                            Email = staff.email,
                            Luong = staff.luong
                        };

                        ViewData["DETAIL_STAFF"] = nhanvien_detail;
                        Nhanvien nhanvien = JsonConvert.DeserializeObject<Nhanvien>(HttpContext.Session.GetString("STAFF"));
                        return View(VIEW_PATH + "Edit.cshtml", nhanvien);
                    }
                    else
                    {
                        ViewData["ErrorStatus"] = true;
                        ViewData["ErrorMessage"] = result.message;
                        return RedirectToAction("Index");
                    }
                }
                else
                {
                    // Error message here
                    HttpContext.Session.Clear();
                    return Redirect("/comnha/login");
                }
            }
        }
    }
}
