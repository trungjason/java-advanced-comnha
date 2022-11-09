using ComNha.Models;
using Microsoft.AspNetCore.Mvc;
using Newtonsoft.Json;
using System.Net.Http.Headers;

namespace ComNha.Controllers
{
    public class ScheduleController : Controller
    {
        public static readonly string VIEW_PATH = "~/Views/RestaurentManagement/Schedule/";
        private const string API_URL = "https://localhost:5556/api/comnha/";

        [HttpGet]
        [Route("comnha/schedules")]
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

                HttpResponseMessage Res = await client.GetAsync("get-tables");

                //Checking the response is successful or not which is sent using HttpClient
                if (Res.IsSuccessStatusCode)
                {
                    //Storing the response details recieved from web api
                    var response = Res.Content.ReadAsStringAsync().Result;

                    //Deserializing the response recieved from web api and storing into the FoodGroup List
                    ViewData["TableList"] = JsonConvert.DeserializeObject<List<Banan>>(response);
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

                HttpResponseMessage Res = await client.GetAsync("get-schedule");

                //Checking the response is successful or not which is sent using HttpClient
                if (Res.IsSuccessStatusCode)
                {
                    //Storing the response details recieved from web api
                    var response = Res.Content.ReadAsStringAsync().Result;

                    //Deserializing the response recieved from web api and storing into the FoodGroup List
                    ViewData["ScheduleList"] = JsonConvert.DeserializeObject<List<Lichhen>>(response);

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
        [Route("comnha/schedules/create")]
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
        [Route("comnha/schedules/detail/{scheduleID}")]
        public async Task<ActionResult> Detail(string scheduleID)
        {
            if (HttpContext.Session.GetInt32("IS_LOGIN") != 1)
            {
                return Redirect("/comnha/login");
            }
            int customerID = 0;
            using (var client = new HttpClient())
            {
                //Passing service base url
                client.BaseAddress = new Uri(API_URL);
                client.DefaultRequestHeaders.Clear();

                //Define request data format
                client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));

                HttpResponseMessage Res = await client.GetAsync("detail-schedule/" + scheduleID);

                //Checking the response is successful or not which is sent using HttpClient
                if (Res.IsSuccessStatusCode)
                {
                    //Storing the response details recieved from web api
                    var response = Res.Content.ReadAsStringAsync().Result;

                    //Deserializing the response recieved from web api and storing into the FoodGroup List
                    Lichhen lichhen = JsonConvert.DeserializeObject<Lichhen>(response);
                    ViewData["DetailSchedule"] = lichhen;
                    customerID = lichhen.MaKhachHang;

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

                HttpResponseMessage Res = await client.GetAsync("detail-customer/" + customerID.ToString());

                //Checking the response is successful or not which is sent using HttpClient
                if (Res.IsSuccessStatusCode)
                {
                    //Storing the response details recieved from web api
                    var response = Res.Content.ReadAsStringAsync().Result;

                    //Deserializing the response recieved from web api and storing into the FoodGroup List
                    ViewData["Customer"] = JsonConvert.DeserializeObject<Khachhang>(response);
                }
                else
                {
                    // Error message here
                    HttpContext.Session.Clear();
                    //return Redirect("/comnha/login");
                }
            }

            Nhanvien nhanvien = JsonConvert.DeserializeObject<Nhanvien>(HttpContext.Session.GetString("STAFF"));
            return View(VIEW_PATH + "Detail.cshtml", nhanvien);
        }

        [HttpGet]
        [Route("comnha/schedules/edit/{scheduleID}")]
        public async Task<ActionResult> Edit(string scheduleID)
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

                HttpResponseMessage Res = await client.GetAsync("detail-schedule/" + scheduleID);

                //Checking the response is successful or not which is sent using HttpClient
                if (Res.IsSuccessStatusCode)
                {
                    //Storing the response details recieved from web api
                    var response = Res.Content.ReadAsStringAsync().Result;

                    //Deserializing the response recieved from web api and storing into the FoodGroup List
                    ViewData["DetailSchedule"] = JsonConvert.DeserializeObject<Hoivien>(response);
                }
                else
                {
                    // Error message here
                    HttpContext.Session.Clear();
                    return Redirect("/comnha/login");
                }
            }

            Nhanvien nhanvien = JsonConvert.DeserializeObject<Nhanvien>(HttpContext.Session.GetString("STAFF"));
            return View(VIEW_PATH + "Edit.cshtml", nhanvien);
        }
    }
}
