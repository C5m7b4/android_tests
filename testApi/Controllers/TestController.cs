using Microsoft.AspNetCore.Mvc;
using Microsoft.Data.SqlClient;
using testApi.Data;
using testApi.Models;

namespace testApi.Controllers
{
  [ApiController]
  [Route("api/test")]
  public class TestController : ControllerBase
  {
    private readonly IRepo _repo;
    private readonly IConfiguration _config;

    public TestController(IRepo repo, IConfiguration config)
    {
      _repo = repo;
      _config = config;
    }

    [HttpGet]
    public ActionResult Test()
    {
      return Ok(new
      {
        error = 0,
        success = true,
        msg = "Success"
      });
    }

    [HttpGet]
    [Route("testdb")]
    public ActionResult TestDb()
    {
      try
      {
        using (SqlConnection conn = new SqlConnection(Utils.GetConnectionString(_config)))
        {
          conn.Open();
          conn.Close();

          return Ok(new
          {
            error = 0,
            success = true,
            msg = "db success"
          });
        }
      }
      catch (System.Exception ex)
      {
        return Ok(new
        {
          error = 1,
          success = false,
          msg = ex.Message
        });
      }
    }







  }
}