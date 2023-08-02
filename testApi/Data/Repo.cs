using System.Data;
using Microsoft.Data.SqlClient;
using testApi.Dtos;
using testApi.Models;

namespace testApi.Data
{
  public class Repo : IRepo
  {
    private readonly AppDbContext _context;

    public Repo(AppDbContext context)
    {
      _context = context;
    }

    public void createUser(User user)
    {
      if (user != null)
      {
        _context.Users.Add(user);
      }
    }

    public List<UserDto> getAllUsers(string connectionstring)
    {
      List<UserDto> users = new List<UserDto>();
      DataTable dt = new DataTable();
      using (SqlConnection conn = new SqlConnection(connectionstring))
      {
        string query = "select * from users";
        conn.Open();
        using (SqlDataAdapter adapt = new SqlDataAdapter(query, conn))
        {
          adapt.Fill(dt);
          foreach (DataRow dr in dt.Rows)
          {
            UserDto u = new UserDto
            {
              Id = Convert.ToInt32(dr["id"].ToString()),
              username = dr["username"].ToString(),
              firstname = dr["firstname"].ToString(),
              lastname = dr["lastname"].ToString(),
              email = dr["email"].ToString()
            };
            users.Add(u);
          }
        }

        conn.Close();
      }
      return users;
    }

    public User getUserById(int id, string connectionstring)
    {
      DataTable dt = new();
      User user = new User();
      using (SqlConnection conn = new SqlConnection(connectionstring))
      {
        conn.Open();
        string query = "select * from users where id=" + id;
        using (SqlDataAdapter adapt = new(query, conn))
        {
          adapt.Fill(dt);
          foreach (DataRow dr in dt.Rows)
          {
            user = new User
            {
              Id = Convert.ToInt32(dr["id"].ToString()),
              firstname = dr["firstname"].ToString(),
              lastname = dr["lastname"].ToString()
            };
          }
        }

        conn.Close();
      }
      return user;
    }

    public List<Hour> getUserHours(int id, string connectionstring)
    {
      DataTable dt = new DataTable();
      List<Hour> hours = new List<Hour>();
      using (SqlConnection conn = new SqlConnection(connectionstring))
      {
        string sql = "select work_date, userid, start_time, end_time, total_hours from hours where userid=1";
        conn.Open();
        using (SqlDataAdapter adapt = new SqlDataAdapter(sql, conn))
        {
          adapt.Fill(dt);
          foreach (DataRow dr in dt.Rows)
          {
            Hour h = new Hour
            {
              WorkDate = Convert.ToDateTime(dr["work_date"].ToString()),
              UserId = Convert.ToInt32(dr["userid"].ToString()),
              StartTime = Convert.ToDateTime(dr["start_time"].ToString()),
              EndTime = Convert.ToDateTime(dr["end_time"].ToString()),
              TotalHours = Convert.ToDecimal(dr["total_hours"].ToString())
            };
            hours.Add(h);
          }
        }
        conn.Close();
      }

      return hours;
    }

    public bool canLogIn(string username, string email, string connectionstring)
    {
      DataTable dt = new DataTable();
      bool success = false;
      using (SqlConnection conn = new SqlConnection(connectionstring))
      {
        conn.Open();
        string query = "select * from users where username='" + username + "'";
        using (SqlDataAdapter adapt = new SqlDataAdapter(query, conn))
        {
          adapt.Fill(dt);
          foreach (DataRow dr in dt.Rows)
          {
            if (dr["email"].ToString() == email)
            {
              success = true;
            }
          }
        }
        conn.Close();
      }
      return success;
    }

    public bool SaveChanges()
    {
      return (_context.SaveChanges() >= 0);
    }
  }
}