using ComNha.Models;
using Microsoft.AspNetCore.Mvc;
using Newtonsoft.Json;
using System.Net.Http.Headers;

namespace ComNha.Controllers
{
    public class AppointmentController : Controller
    {
        public static readonly string VIEW_PATH = "~/Views/RestaurentManagement/Appointment/";
        private const string API_URL = "https://localhost:5556/api/comnha/";

        [HttpGet]
        [Route("comnha/received-notes")]
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

                HttpResponseMessage Res = await client.GetAsync("get-received-notes");

                //Checking the response is successful or not which is sent using HttpClient
                if (Res.IsSuccessStatusCode)
                {
                    //Storing the response details recieved from web api
                    var response = Res.Content.ReadAsStringAsync().Result;

                    //Deserializing the response recieved from web api and storing into the FoodGroup List
                    ViewData["ReceivedNotes"] = JsonConvert.DeserializeObject<List<Phieunhap>>(response);
                }
                else
                {
                    // Error message here
                    HttpContext.Session.Clear();
                    return Redirect("/comnha/login");
                }
            }

            Nhanvien nhanvien = JsonConvert.DeserializeObject<Nhanvien>(HttpContext.Session.GetString("STAFF"));
            return View(VIEW_PATH + "Index.cshtml", nhanvien);
        }

        [HttpGet]
        [Route("comnha/received-notes/create")]
        public async Task<ActionResult> Create()
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

                HttpResponseMessage Res = await client.GetAsync("get-suppliers");

                //Checking the response is successful or not which is sent using HttpClient
                if (Res.IsSuccessStatusCode)
                {
                    //Storing the response details recieved from web api
                    var response = Res.Content.ReadAsStringAsync().Result;

                    //Deserializing the response recieved from web api and storing into the FoodGroup List
                    ViewData["SupplierList"] = JsonConvert.DeserializeObject<List<Nhacungcap>>(response);
                }
                else
                {
                    // Error message here
                    HttpContext.Session.Clear();
                    return Redirect("/comnha/login");
                }
            }

            using (var client = new HttpClient())
            {
                //Passing service base url
                client.BaseAddress = new Uri(API_URL);
                client.DefaultRequestHeaders.Clear();

                //Define request data format
                client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));

                HttpResponseMessage Res = await client.GetAsync("get-materials");

                //Checking the response is successful or not which is sent using HttpClient
                if (Res.IsSuccessStatusCode)
                {
                    //Storing the response details recieved from web api
                    var response = Res.Content.ReadAsStringAsync().Result;

                    //Deserializing the response recieved from web api and storing into the FoodGroup List
                    ViewData["MaterialList"] = JsonConvert.DeserializeObject<List<Nguyenvatlieu>>(response);
                }
                else
                {
                    // Error message here
                    HttpContext.Session.Clear();
                    return Redirect("/comnha/login");
                }
            }

            Nhanvien nhanvien = JsonConvert.DeserializeObject<Nhanvien>(HttpContext.Session.GetString("STAFF"));
            return View(VIEW_PATH + "Create.cshtml", nhanvien);
        }

        [HttpGet]
        [Route("comnha/received-notes/detail/{noteID}")]
        public async Task<ActionResult> Detail(string noteID)
        {
            if (HttpContext.Session.GetInt32("IS_LOGIN") != 1)
            {
                return Redirect("/comnha/login");
            }

            Phieunhap phieunhap = null;
            using (var client = new HttpClient())
            {
                //Passing service base url
                client.BaseAddress = new Uri(API_URL);
                client.DefaultRequestHeaders.Clear();

                //Define request data format
                client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));

                HttpResponseMessage Res = await client.GetAsync("get-received-note/" + noteID);

                //Checking the response is successful or not which is sent using HttpClient
                if (Res.IsSuccessStatusCode)
                {
                    //Storing the response details recieved from web api
                    var response = Res.Content.ReadAsStringAsync().Result;

                    phieunhap = JsonConvert.DeserializeObject<Phieunhap>(response);
                    ViewData["DETAIL_RN"] = phieunhap;
                }
                else
                {
                    // Error message here
                    HttpContext.Session.Clear();
                    return Redirect("/comnha/login");
                }
            }

            using (var client = new HttpClient())
            {
                //Passing service base url
                client.BaseAddress = new Uri(API_URL);
                client.DefaultRequestHeaders.Clear();

                //Define request data format
                client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));

                HttpResponseMessage Res = await client.GetAsync("get-supplier/" + phieunhap.MaNhaCungCap);

                //Checking the response is successful or not which is sent using HttpClient
                if (Res.IsSuccessStatusCode)
                {
                    //Storing the response details recieved from web api
                    var response = Res.Content.ReadAsStringAsync().Result;

                    //Deserializing the response recieved from web api and storing into the FoodGroup List
                    ViewData["DETAIL_SUPPLIER"] = JsonConvert.DeserializeObject<Nhacungcap>(response);
                }
                else
                {
                    // Error message here
                    HttpContext.Session.Clear();
                    return Redirect("/comnha/login");
                }
            }

            using (var client = new HttpClient())
            {
                //Passing service base url
                client.BaseAddress = new Uri(API_URL);
                client.DefaultRequestHeaders.Clear();

                //Define request data format
                client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));

                HttpResponseMessage Res = await client.GetAsync("get-staff/" + phieunhap.MaNhanVien);

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
                    }                  
                }
                else
                {
                    // Error message here
                    HttpContext.Session.Clear();
                    return Redirect("/comnha/login");
                }
            }

            Nhanvien nhanvien = JsonConvert.DeserializeObject<Nhanvien>(HttpContext.Session.GetString("STAFF"));
            return View(VIEW_PATH + "Detail.cshtml", nhanvien);
        }

        [HttpGet]
        [Route("comnha/received-notes/edit/{noteID}")]
        public async Task<ActionResult> Edit(string noteID)
        {
            if (HttpContext.Session.GetInt32("IS_LOGIN") != 1)
            {
                return Redirect("/comnha/login");
            }

            Nhanvien nhanvien = JsonConvert.DeserializeObject<Nhanvien>(HttpContext.Session.GetString("STAFF"));
            return View(VIEW_PATH + "Edit.cshtml", nhanvien);
        }
    }
}
