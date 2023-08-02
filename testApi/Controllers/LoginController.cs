using Microsoft.AspNetCore.Mvc;
using testApi.Data;

namespace testApi.Controllers
{


  [ApiController]
  [Route("api/login")]
  public class LoginController : ControllerBase
  {
    private readonly IRepo _repo;
    private readonly IConfiguration _config;

    public LoginController(IRepo repo, IConfiguration config)
    {
      _repo = repo;
      _config = config;
    }


    [HttpGet]
    public ActionResult Login(string username, string email)
    {
      try
      {
        bool success = _repo.canLogIn(username, email, Utils.GetConnectionString(_config));
        return Ok(new
        {
          error = 0,
          success
        });
      }
      catch (System.Exception ex)
      {
        return Ok(new
        {
          error = 1,
          success = true,
          msg = ex.Message
        });
      }
    }


  }
}