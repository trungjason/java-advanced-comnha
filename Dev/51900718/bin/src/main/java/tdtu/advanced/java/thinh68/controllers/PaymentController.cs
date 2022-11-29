using ComNha.Models;
using Microsoft.AspNetCore.Mvc;
using Newtonsoft.Json;

namespace ComNha.Controllers
{
    public class PaymentController : Controller
    {
        public static readonly string VIEW_PATH = "~/Views/RestaurentManagement/Payment/";

        [HttpGet]
        [Route("comnha/bills")]
        public IActionResult Index()
        {
            if (HttpContext.Session.GetInt32("IS_LOGIN") != 1)
            {
                return Redirect("/comnha/login");
            }

            Nhanvien nhanvien = JsonConvert.DeserializeObject<Nhanvien>(HttpContext.Session.GetString("STAFF"));
            return View(nhanvien);
        }

        [HttpGet]
        [Route("/comnha/bills/create")]
        public IActionResult Create()
        {
            if (HttpContext.Session.GetInt32("IS_LOGIN") != 1)
            {
                return Redirect("/comnha/login");
            }

            Nhanvien nhanvien = JsonConvert.DeserializeObject<Nhanvien>(HttpContext.Session.GetString("STAFF"));
            return View(nhanvien);
        }

        [HttpGet]
        [Route("/comnha/bills/detail/{billID}")]
        public IActionResult Detail(string billID)
        {
            if (HttpContext.Session.GetInt32("IS_LOGIN") != 1)
            {
                return Redirect("/comnha/login");
            }

            Nhanvien nhanvien = JsonConvert.DeserializeObject<Nhanvien>(HttpContext.Session.GetString("STAFF"));
            return View(nhanvien);
        }

        [HttpGet]
        [Route("/comnha/bills/edit/{billID}")]
        public IActionResult Edit(string billID)
        {
            if (HttpContext.Session.GetInt32("IS_LOGIN") != 1)
            {
                return Redirect("/comnha/login");
            }

            Nhanvien nhanvien = JsonConvert.DeserializeObject<Nhanvien>(HttpContext.Session.GetString("STAFF"));
            return View(nhanvien);
        }
    }
}
