using ComNha.Models;
using Microsoft.AspNetCore.Mvc;
using Newtonsoft.Json;
using System.Net.Http.Headers;

namespace ComNha.Controllers
{
    public class FoodController : Controller
    {
        public static readonly string VIEW_PATH = "~/Views/RestaurentManagement/Food/";
        private const string API_URL = "https://localhost:5556/api/comnha/";


        [HttpGet]
        [Route("comnha/foods")]
        public async Task<ActionResult> Index()
        {
            if (HttpContext.Session.GetInt32("IS_LOGIN") != 1)
            {
                return Redirect("/comnha/login");
            }

            using (var client = new HttpClient())
            {
                //Passing service base url
                client.BaseAddress = new Uri("https://localhost:5556/");
                client.DefaultRequestHeaders.Clear();

                //Define request data format
                client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));

                //Sending request to find web api REST service resource GetAllEmployees using HttpClient
                HttpResponseMessage Res = await client.GetAsync("Menu");

                //Checking the response is successful or not which is sent using HttpClient
                if (Res.IsSuccessStatusCode)
                {
                    //Storing the response details recieved from web api
                    var response = Res.Content.ReadAsStringAsync().Result;
                    //Deserializing the response recieved from web api and storing into the FoodGroup List

                    List<Nhommonan> foodGroup = JsonConvert.DeserializeObject<List<Nhommonan>>(response);

                    if ( foodGroup == null)
                    {
                        return NotFound();
                    }

                    ViewData["FoodGroupList"] = foodGroup;

                    int[] foodGroupAmount = new int[foodGroup.Count];

                    int i = 0;
                    foreach(Nhommonan nhommonan in foodGroup)
                    {
                        using (var client1 = new HttpClient())
                        {
                            //Passing service base url
                            client1.BaseAddress = new Uri("https://localhost:5556/");
                            client1.DefaultRequestHeaders.Clear();

                            //Define request data format
                            client1.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));

                            //Sending request to find web api REST service resource GetAllEmployees using HttpClient
                            // Default FoodGroup Is The First FoodGroup With Key 'NMA001'
                            HttpResponseMessage Res1 = await client1.GetAsync("Menu/" + nhommonan.MaNhom);

                            //Checking the response is successful or not which is sent using HttpClient
                            if (Res1.IsSuccessStatusCode)
                            {
                                //Storing the response details recieved from web api
                                var response1 = Res1.Content.ReadAsStringAsync().Result;
                                //Deserializing the response recieved from web api and storing into the Food List
                                List<Monan> monans = JsonConvert.DeserializeObject<List<Monan>>(response1);
                                foodGroupAmount[i] = monans.Count;
                                i++;
                            }
                            else
                            {
                                // Error message here
                                return NotFound();
                            }
                        }
                    }

                    ViewData["FoodGroupAmount"] = foodGroupAmount;
                }
                else
                {
                    // Error message here
                    return NotFound();
                }
            }
         
            Nhanvien nhanvien = JsonConvert.DeserializeObject<Nhanvien>(HttpContext.Session.GetString("STAFF"));
            return View(VIEW_PATH + "Index.cshtml", nhanvien);
        }

        [HttpGet]
        [Route("comnha/foods/create")]
        public async Task<ActionResult> CreateFood()
        {
            if (HttpContext.Session.GetInt32("IS_LOGIN") != 1)
            {
                return Redirect("/comnha/login");
            }

            using (var client = new HttpClient())
            {
                //Passing service base url
                client.BaseAddress = new Uri("https://localhost:5556/");
                client.DefaultRequestHeaders.Clear();

                //Define request data format
                client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));

                //Sending request to find web api REST service resource GetAllEmployees using HttpClient
                HttpResponseMessage Res = await client.GetAsync("Menu");

                //Checking the response is successful or not which is sent using HttpClient
                if (Res.IsSuccessStatusCode)
                {
                    //Storing the response details recieved from web api
                    var response = Res.Content.ReadAsStringAsync().Result;
                    //Deserializing the response recieved from web api and storing into the FoodGroup List

                    List<Nhommonan> foodGroup = JsonConvert.DeserializeObject<List<Nhommonan>>(response);

                    if (foodGroup == null)
                    {
                        return NotFound();
                    }

                    ViewData["FoodGroupList"] = foodGroup;
                }
                else
                {
                    // Error message here
                    return NotFound();
                }
            }

            Nhanvien nhanvien = JsonConvert.DeserializeObject<Nhanvien>(HttpContext.Session.GetString("STAFF"));
            return View(VIEW_PATH + "Create.cshtml", nhanvien);
        }

        [HttpGet]
        [Route("comnha/foods/detail/{foodID}")]
        public async Task<ActionResult> DetailFood(string foodID)
        {
            if (HttpContext.Session.GetInt32("IS_LOGIN") != 1)
            {
                return Redirect("/comnha/login");
            }


            using (var client = new HttpClient())
            {
                //Passing service base url
                client.BaseAddress = new Uri("https://localhost:5556/");
                client.DefaultRequestHeaders.Clear();

                //Define request data format
                client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));

                //Sending request to find web api REST service resource using HttpClient
                HttpResponseMessage Res = await client.GetAsync("Menu/chitiet/" + foodID);

                //Checking the response is successful or not which is sent using HttpClient
                if (Res.IsSuccessStatusCode)
                {
                    //Storing the response details recieved from web api
                    var response = Res.Content.ReadAsStringAsync().Result;
                    //Deserializing the response recieved from web api and storing into the Food Model
                    ViewData["FoodDetail"] = JsonConvert.DeserializeObject<Monan>(response);
                }
                else
                {
                    // Error message here
                    return NotFound();
                }
            }

            Nhanvien nhanvien = JsonConvert.DeserializeObject<Nhanvien>(HttpContext.Session.GetString("STAFF"));
            return View(VIEW_PATH + "Detail.cshtml", nhanvien);
        }

        [HttpGet]
        [Route("comnha/foods/edit/{foodID}")]
        public async Task<ActionResult> EditFood(string foodID)
        {
            if (HttpContext.Session.GetInt32("IS_LOGIN") != 1)
            {
                return Redirect("/comnha/login");
            }

            using (var client = new HttpClient())
            {
                //Passing service base url
                client.BaseAddress = new Uri("https://localhost:5556/");
                client.DefaultRequestHeaders.Clear();

                //Define request data format
                client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));

                //Sending request to find web api REST service resource GetAllEmployees using HttpClient
                HttpResponseMessage Res = await client.GetAsync("Menu");

                //Checking the response is successful or not which is sent using HttpClient
                if (Res.IsSuccessStatusCode)
                {
                    //Storing the response details recieved from web api
                    var response = Res.Content.ReadAsStringAsync().Result;
                    //Deserializing the response recieved from web api and storing into the FoodGroup List

                    List<Nhommonan> foodGroup = JsonConvert.DeserializeObject<List<Nhommonan>>(response);

                    if (foodGroup == null)
                    {
                        return NotFound();
                    }

                    ViewData["FoodGroupList"] = foodGroup;
                }
                else
                {
                    // Error message here
                    return NotFound();
                }
            }

            using (var client = new HttpClient())
            {
                //Passing service base url
                client.BaseAddress = new Uri("https://localhost:5556/");
                client.DefaultRequestHeaders.Clear();

                //Define request data format
                client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));

                //Sending request to find web api REST service resource using HttpClient
                HttpResponseMessage Res = await client.GetAsync("Menu/chitiet/" + foodID);

                //Checking the response is successful or not which is sent using HttpClient
                if (Res.IsSuccessStatusCode)
                {
                    //Storing the response details recieved from web api
                    var response = Res.Content.ReadAsStringAsync().Result;
                    //Deserializing the response recieved from web api and storing into the Food Model
                    ViewData["FoodDetail"] = JsonConvert.DeserializeObject<Monan>(response);
                }
                else
                {
                    // Error message here
                    return NotFound();
                }
            }

            Nhanvien nhanvien = JsonConvert.DeserializeObject<Nhanvien>(HttpContext.Session.GetString("STAFF"));
            return View(VIEW_PATH + "Edit.cshtml", nhanvien);
        }

        [HttpGet]
        [Route("comnha/foods/create-group")]
        public IActionResult CreateFoodGroup()
        {
            if (HttpContext.Session.GetInt32("IS_LOGIN") != 1)
            {
                return Redirect("/comnha/login");
            }

            Nhanvien nhanvien = JsonConvert.DeserializeObject<Nhanvien>(HttpContext.Session.GetString("STAFF"));
            return View(VIEW_PATH + "CreateGroup.cshtml", nhanvien);
        }

        [HttpGet]
        [Route("comnha/foods/detail-group/{groupID}")]
        public async Task<ActionResult> DetailFoodGroup(string groupID)
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

                //Sending request to find web api REST service resource GetAllEmployees using HttpClient
                HttpResponseMessage Res = await client.GetAsync("detail-food-group/" + groupID);

                //Checking the response is successful or not which is sent using HttpClient
                if (Res.IsSuccessStatusCode)
                {
                    //Storing the response details recieved from web api
                    var response = Res.Content.ReadAsStringAsync().Result;
                    //Deserializing the response recieved from web api and storing into the FoodGroup List

                    Nhommonan nhommonan = JsonConvert.DeserializeObject<Nhommonan>(response);

                    if (nhommonan == null)
                    {
                        return NotFound();
                    }

                    ViewData["FoodGroupName"] = nhommonan.TenNhom;
                }
                else
                {
                    // Error message here
                    return NotFound();
                }
            }

            using (var client = new HttpClient())
            {
                //Passing service base url
                client.BaseAddress = new Uri("https://localhost:5556/");
                client.DefaultRequestHeaders.Clear();

                //Define request data format
                client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));

                //Sending request to find web api REST service resource GetAllEmployees using HttpClient
                HttpResponseMessage Res = await client.GetAsync("Menu/"+groupID);

                //Checking the response is successful or not which is sent using HttpClient
                if (Res.IsSuccessStatusCode)
                {
                    //Storing the response details recieved from web api
                    var response = Res.Content.ReadAsStringAsync().Result;
                    //Deserializing the response recieved from web api and storing into the FoodGroup List

                    List<Monan> foods = JsonConvert.DeserializeObject<List<Monan>>(response);

                    if (foods == null)
                    {
                        return NotFound();
                    }

                    ViewData["FoodList"] = foods;
                    
                }
                else
                {
                    // Error message here
                    return NotFound();
                }
            }

            Nhanvien nhanvien = JsonConvert.DeserializeObject<Nhanvien>(HttpContext.Session.GetString("STAFF"));
            return View(VIEW_PATH + "DetailGroup.cshtml", nhanvien);
        }

        [HttpGet]
        [Route("comnha/foods/edit-group/{groupID}")]
        public async Task<ActionResult> EditFoodGroup(string groupID)
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

                //Sending request to find web api REST service resource GetAllEmployees using HttpClient
                HttpResponseMessage Res = await client.GetAsync("detail-food-group/" + groupID);

                //Checking the response is successful or not which is sent using HttpClient
                if (Res.IsSuccessStatusCode)
                {
                    //Storing the response details recieved from web api
                    var response = Res.Content.ReadAsStringAsync().Result;
                    //Deserializing the response recieved from web api and storing into the FoodGroup List

                    Nhommonan nhommonan = JsonConvert.DeserializeObject<Nhommonan>(response);

                    if (nhommonan == null)
                    {
                        return NotFound();
                    }

                    ViewData["DetailFoodGroup"] = nhommonan;
                }
                else
                {
                    // Error message here
                    return NotFound();
                }
            }

            Nhanvien nhanvien = JsonConvert.DeserializeObject<Nhanvien>(HttpContext.Session.GetString("STAFF"));
            return View(VIEW_PATH + "EditGroup.cshtml", nhanvien);
        }
    }
}
