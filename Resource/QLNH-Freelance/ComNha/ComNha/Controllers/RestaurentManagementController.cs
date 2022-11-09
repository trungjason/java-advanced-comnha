using ComNha.Helper;
using ComNha.Models;
using Microsoft.AspNetCore.Mvc;
using Newtonsoft.Json;
using System.Net.Http.Headers;
using System.Text;

namespace ComNha.Controllers
{
    public class RestaurentManagementController : Controller
    {
        private readonly ILogger<RestaurentManagementController> _logger;
        private IWebHostEnvironment Environment;

        //Hosted web API REST Service base url
        private const string API_URL = "https://localhost:5556/api/comnha/";

        public RestaurentManagementController(ILogger<RestaurentManagementController> logger, IWebHostEnvironment _environment)
        {
            _logger = logger;
            Environment = _environment;
        }

        [HttpGet]
        [Route("comnha/private")]
        public async Task<ActionResult> Index()
        {
            if ( HttpContext.Session.GetInt32("IS_LOGIN") != 1)
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

                HttpResponseMessage Res = await client.GetAsync("get-staff/"+ HttpContext.Session.GetString("STAFF_ID"));

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

                        Nhanvien nhanvien = new Nhanvien()
                        {
                            MaNhanVien = staff.maNhanVien,
                            TenNhanVien = staff.tenNhanVien,
                            SoDienThoai = staff.soDienThoai,
                            ChucVu = staff.chucVu,
                            DiaChi = staff.diaChi,
                            Email = staff.email,
                            Luong = staff.luong
                        };

                        HttpContext.Session.SetString("STAFF_NAME", staff.tenNhanVien);
                        HttpContext.Session.SetString("STAFF_ROLE", staff.chucVu);
                        HttpContext.Session.SetString("STAFF", JsonConvert.SerializeObject(nhanvien));


                        return View(nhanvien);
                    }
                    else
                    {
                        HttpContext.Session.Clear();
                        return Redirect("/comnha/login");
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
        [Route("comnha/login")]
        public IActionResult Login()
        {
            // Đã login rồi
            if (HttpContext.Session.GetInt32("IS_LOGIN") == 1)
            {
                return RedirectToAction("Index");
            }

            ViewData["ErrorStatus"] = TempData["ErrorStatus"];
            ViewData["ErrorMessage"] = TempData["ErrorMessage"];
            ViewData["Username"] = TempData["Username"];
            ViewData["Password"] = TempData["Password"];

            return View();
        }

        [HttpPost]
        [Route("comnha/login")]
        public async Task<ActionResult> ValidateLogin(Account account)
        {
            // Đã login rồi
            if (HttpContext.Session.GetInt32("IS_LOGIN") == 1)
            {
                return RedirectToAction("Index");
            }

            if ( String.IsNullOrEmpty(account.username) || String.IsNullOrEmpty(account.password))
            {
                TempData["ValidateError"] = true;
                TempData["ValidateMessage"] = "Vui lòng nhập đầy đủ thông tin !";
                TempData["Username"] = account.username;
                TempData["Password"] = account.password;
                return Redirect("/comnha/login");
            }

            using (var client = new HttpClient())
            {
                //Passing service base url
                client.BaseAddress = new Uri(API_URL);
                client.DefaultRequestHeaders.Clear();

                //Define request data format
                client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));

                HttpResponseMessage Res = await client.PostAsJsonAsync("login", account);

                //Checking the response is successful or not which is sent using HttpClient
                if (Res.IsSuccessStatusCode)
                {
                    //Storing the response details recieved from web api
                    var response = Res.Content.ReadAsStringAsync().Result;

                    var responseDefinition = new { status = false, message = "", data = "" };

                    var result = JsonConvert.DeserializeAnonymousType(response, responseDefinition);

                    if (result != null && result.status)
                    {
                        HttpContext.Session.SetInt32("IS_LOGIN", 1);
                        HttpContext.Session.SetString("STAFF_ID", result.data);
                        return RedirectToAction("Index");
                    }
                    else
                    {
                        TempData["ErrorStatus"] = true;
                        TempData["ErrorMessage"] = result.message;
                        TempData["Username"] = account.username;
                        TempData["Password"] = account.username;

                        return Redirect("/comnha/login");
                    }
                }
                else
                {
                    // Error message here
                    TempData["ErrorStatus"] = true;
                    TempData["ErrorMessage"] = "Có lỗi trong quá trình xác thực vui lòng thử lại !";
                    TempData["Username"] = account.username;
                    TempData["Password"] = account.password;
                    return Redirect("/comnha/login");
                }
            }
        }

        [Route("comnha/order")]
        public IActionResult Order()
        {
            if (HttpContext.Session.GetInt32("IS_LOGIN") != 1)
            {
                return Redirect("/comnha/login");
            }

            Nhanvien nhanvien = JsonConvert.DeserializeObject<Nhanvien>(HttpContext.Session.GetString("STAFF"));
            return View(nhanvien);
        }

        [Route("comnha/password")]
        public IActionResult Password()
        {
            if (HttpContext.Session.GetInt32("IS_LOGIN") != 1)
            {
                return Redirect("/comnha/login");
            }

            Nhanvien nhanvien = JsonConvert.DeserializeObject<Nhanvien>(HttpContext.Session.GetString("STAFF"));
            return View(nhanvien);
        }

        [Route("comnha/revenue")]
        public IActionResult Revenue()
        {
            if (HttpContext.Session.GetInt32("IS_LOGIN") != 1)
            {
                return Redirect("/comnha/login");
            }

            Nhanvien nhanvien = JsonConvert.DeserializeObject<Nhanvien>(HttpContext.Session.GetString("STAFF"));
            return View(nhanvien);
        }

        [Route("comnha/tables")]
        public async Task<ActionResult> Table()
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

                HttpResponseMessage Res = await client.GetAsync("get-tables");

                //Checking the response is successful or not which is sent using HttpClient
                if (Res.IsSuccessStatusCode)
                {
                    //Storing the response details recieved from web api
                    var response = Res.Content.ReadAsStringAsync().Result;

                    //Deserializing the response recieved from web api and storing into the FoodGroup List
                    ViewData["TableList"] = JsonConvert.DeserializeObject<List<Banan>>(response);

                    Nhanvien nhanvien = JsonConvert.DeserializeObject<Nhanvien>(HttpContext.Session.GetString("STAFF"));
                    return View(nhanvien);
                }
                else
                {
                    // Error message here
                    HttpContext.Session.Clear();
                    return Redirect("/comnha/login");
                }
            }         
        }

        [Route("comnha/logout")]
        public IActionResult Logout()
        {
            HttpContext.Session.Clear();
            return Redirect("/comnha/login");
        }
    }

    public class Account
    {
        public string username { get; set; } = null!;
        public string password { get; set; } = null!;
    }
}
