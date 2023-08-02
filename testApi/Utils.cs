namespace testApi
{
  public static class Utils
  {
    public static string GetConnectionString(IConfiguration config)
    {
      return config["ConnectionStrings:conn"];
    }
  }
}