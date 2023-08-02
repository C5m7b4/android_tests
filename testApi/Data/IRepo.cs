using testApi.Models;
using testApi.Dtos;

namespace testApi.Data
{
  public interface IRepo
  {
    bool SaveChanges();
    List<UserDto> getAllUsers(string connectionstring);
    User getUserById(int id, string connectionstring);
    List<Hour> getUserHours(int id, string connectionstring);
    void createUser(User user);

    bool canLogIn(string username, string email, string connectionstring);
  }
}