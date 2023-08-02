using Microsoft.AspNetCore.Mvc;
using testApi.Data;
using testApi.Dtos;
using testApi.Models;

namespace testApi.Controllers
{
  [ApiController]
  [Route("api/users")]
  public class UsersController : ControllerBase
  {
    private readonly IRepo _repo;
    private readonly IConfiguration _config;

    public UsersController(IRepo repo, IConfiguration config)
    {
      _repo = repo;
      _config = config;
    }

    [HttpGet]
    public ActionResult GetUsers()
    {
      try
      {
        string connectionString = Utils.GetConnectionString(_config);
        List<UserDto> users = _repo.getAllUsers(connectionString);
        if (users.Count > 0)
        {
          return Ok(new
          {
            error = 0,
            success = true,
            users
          });
        }
        else
        {
          return Ok(new
          {
            error = 0,
            success = true,
            msg = "no users found"
          });
        }
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



    [HttpGet]
    [Route("userid")]
    public ActionResult getUserById(int id)
    {
      try
      {
        var user = _repo.getUserById(id, Utils.GetConnectionString(_config));
        if (user != null)
        {
          return Ok(new
          {
            error = 0,
            success = true,
            user
          });
        }
        else
        {
          return Ok(new
          {
            error = 2,
            success = true,
            msg = "user not found"
          });
        }
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


    [HttpGet]
    [Route("hours")]
    public ActionResult getUserHours(int userid)
    {
      try
      {
        List<Hour> hours = _repo.getUserHours(userid, Utils.GetConnectionString(_config));
        if (hours.Count > 0)
        {
          return Ok(new
          {
            error = 0,
            success = true,
            hours
          });
        }
        else
        {
          return Ok(new
          {
            error = 2,
            success = true,
            msg = "No hours found"
          });
        }
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