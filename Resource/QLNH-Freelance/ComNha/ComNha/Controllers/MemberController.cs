using ComNha.Models;
using Microsoft.AspNetCore.Mvc;
using Newtonsoft.Json;
using System.Net.Http.Headers;

namespace ComNha.Controllers
{
    public class MemberController : Controller
    {
        public static readonly string VIEW_PATH = "~/Views/RestaurentManagement/Member/";
        private const string API_URL = "https://localhost:5556/api/comnha/";


        [HttpGet]
        [Route("comnha/members")]
        public async Task<ActionResult> Index()
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

                HttpResponseMessage Res = await client.GetAsync("get-members");

                //Checking the response is successful or not which is sent using HttpClient
                if (Res.IsSuccessStatusCode)
                {
                    //Storing the response details recieved from web api
                    var response = Res.Content.ReadAsStringAsync().Result;

                    //Deserializing the response recieved from web api and storing into the FoodGroup List
                    ViewData["MemberList"] = JsonConvert.DeserializeObject<List<Hoivien>>(response);

                    Nhanvien nhanvien = JsonConvert.DeserializeObject<Nhanvien>(HttpContext.Session.GetString("STAFF"));
                    return View(VIEW_PATH + "Index.cshtml", nhanvien);
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
        [Route("comnha/members/create")]
        public IActionResult Create()
        {
            if (HttpContext.Session.GetInt32("IS_LOGIN") != 1)
            {
                return Redirect("/comnha/login");
            }

            Nhanvien nhanvien = JsonConvert.DeserializeObject<Nhanvien>(HttpContext.Session.GetString("STAFF"));
            return View(VIEW_PATH + "Create.cshtml", nhanvien);
        }

        [HttpGet]
        [Route("comnha/members/detail/{memberID}")]
        public async Task<ActionResult> Detail(string memberID)
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

                HttpResponseMessage Res = await client.GetAsync("get-member/" + memberID);

                //Checking the response is successful or not which is sent using HttpClient
                if (Res.IsSuccessStatusCode)
                {
                    //Storing the response details recieved from web api
                    var response = Res.Content.ReadAsStringAsync().Result;

                    var memberDefinition = new
                    {
                        tenHoiVien = "string",
                        soDienThoai = "string",
                        tongSoTienThanhToan = 0,
                        email = "string",
                        diaChi = "string",
                        quyenLoi = "string"
                    };

                    var responseDefinition = new { status = false, message = "", data = memberDefinition };


                    var result = JsonConvert.DeserializeAnonymousType(response, responseDefinition);

                    if (result != null && result.status)
                    {
                        var member = result.data;

                        Hoivien hoivien_detail = new Hoivien()
                        {
                            TenHoiVien = member.tenHoiVien,
                            SoDienThoai = member.soDienThoai,
                            TongSoTienThanhToan = member.tongSoTienThanhToan,
                            Email = member.email,
                            DiaChi = member.diaChi,
                            QuyenLoi = member.quyenLoi,
                            MatKhau = GetDiscount(member.tongSoTienThanhToan).ToString()
                        };

                        ViewData["DETAIL_MEMBER"] = hoivien_detail;
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

        public double GetDiscount(double TongSoTienThanhToan)
        {
            if (TongSoTienThanhToan > 20000000)
            {
                return 0.15;
            }
            if (TongSoTienThanhToan > 15000000)
            {
                return 0.12;
            }
            if (TongSoTienThanhToan > 10000000)
            {
                return 0.1;
            }
            if (TongSoTienThanhToan > 5000000)
            {
                return 0.08;
            }
            if (TongSoTienThanhToan > 3000000)
            {
                return 0.05;
            }

            return 0.02;
        }

        [HttpGet]
        [Route("comnha/members/edit/{memberID}")]
        public async Task<ActionResult> Edit(string memberID)
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

                HttpResponseMessage Res = await client.GetAsync("get-member/" + memberID);

                //Checking the response is successful or not which is sent using HttpClient
                if (Res.IsSuccessStatusCode)
                {
                    //Storing the response details recieved from web api
                    var response = Res.Content.ReadAsStringAsync().Result;

                    var memberDefinition = new
                    {
                        tenHoiVien = "string",
                        soDienThoai = "string",
                        tongSoTienThanhToan = 0,
                        email = "string",
                        diaChi = "string",
                        quyenLoi = "string"
                    };

                    var responseDefinition = new { status = false, message = "", data = memberDefinition };


                    var result = JsonConvert.DeserializeAnonymousType(response, responseDefinition);

                    if (result != null && result.status)
                    {
                        var member = result.data;

                        Hoivien hoivien_detail = new Hoivien()
                        {
                            TenHoiVien = member.tenHoiVien,
                            SoDienThoai = member.soDienThoai,
                            TongSoTienThanhToan = member.tongSoTienThanhToan,
                            Email = member.email,
                            DiaChi = member.diaChi,
                            QuyenLoi = member.quyenLoi,
                            MatKhau = null
                        };

                        ViewData["DETAIL_MEMBER"] = hoivien_detail;
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
