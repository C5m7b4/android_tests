using Microsoft.EntityFrameworkCore;
using testApi.Models;

namespace testApi.Data
{
  public class AppDbContext : DbContext
  {
    public AppDbContext(DbContextOptions<AppDbContext> opt) : base(opt)
    {

    }

    public DbSet<User> Users { get; set; }
  }
}