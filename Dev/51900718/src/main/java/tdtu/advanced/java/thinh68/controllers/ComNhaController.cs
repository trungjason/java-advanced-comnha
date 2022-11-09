using ComNha.Models;
using Microsoft.AspNetCore.Mvc;
using System.Diagnostics;
using System.Net.Http.Headers;

namespace ComNha.Controllers
{
    public class ComNhaController : Controller
    {
        private readonly ILogger<ComNhaController> _logger;
        private IWebHostEnvironment Environment;

        public ComNhaController(ILogger<ComNhaController> logger, IWebHostEnvironment _environment)
        {
            _logger = logger;
            Environment = _environment;
        }

        [HttpGet]
        [Route("/")]
        [Route("ComNha")]
        [Route("ComNha/home")]
        [Route("ComNha/index")]
        public IActionResult Index()
        {
            return View();
        }

        [HttpGet]
        [Route("ComNha/book")]
        [Route("ComNha/pages/book")]
        public IActionResult Book()
        {
            return View();
        }

        [HttpGet]
        [Route("ComNha/contact")]
        [Route("ComNha/pages/contact")]
        public IActionResult Contact()
        {
            return View();
        }

        [HttpGet]  
        [Route("ComNha/introduce")]
        [Route("ComNha/pages/introduce")]
        public IActionResult Introduce()
        {
            return View();
        }

        [HttpGet]
        [Route("ComNha/member")]
        public IActionResult Member()
        {
            return View();
        }


        private readonly string[] SupportedExtension = new string[] { ".png", ".jpg", ".jpeg", ".gif" };

        [HttpPost, DisableRequestSizeLimit]
        [Route("ComNha/api/upload-image")]
        public JsonResult Upload()
        {
            try
            {
                var file = Request.Form.Files[0];
                var imagePath = Path.Combine(this.Environment.WebRootPath, "images");
                Debug.WriteLine(this.Environment.WebRootPath);
                var pathToSave = Path.Combine(imagePath, "food-images");

                if (file.Length > 0)
                {
                    var fileName = ContentDispositionHeaderValue.Parse(file.ContentDisposition).FileName.Trim('"');

                    if ( !SupportedExtension.Contains(Path.GetExtension(fileName)))
                    {
                        return Json(new { status = false, message = "Hệ thống chỉ hỗ trợ định dạng ảnh PNG, JPEG, GIF."});
                    }

                    fileName = DateTime.Now.ToFileTime().ToString() + Path.GetExtension(fileName);
                    var fullPath = Path.Combine(pathToSave, fileName);

                    using (var stream = new FileStream(fullPath, FileMode.Create))
                    {
                        file.CopyTo(stream);
                    }
                    return Json(new { status = true, message = "Upload ảnh thành công.", data = fileName });
                }
                return Json(new { status = false, message = "Không thấy ảnh nào cần upload." });
            }
            catch (Exception ex)
            {
                Debug.WriteLine(ex);
                return new JsonResult(new { status = false, message = "Có lỗi xảy ra khi upload ảnh." })
                {
                    StatusCode = StatusCodes.Status400BadRequest // Status code here 
                };
            }
        }

        [ResponseCache(Duration = 0, Location = ResponseCacheLocation.None, NoStore = true)]
        [Route("ComNha/Error/{statusCode?}")]
        public IActionResult Error(int statusCode)
        {
            return View(new ErrorViewModel { RequestId = Activity.Current?.Id ?? HttpContext.TraceIdentifier });
        }


    }
}