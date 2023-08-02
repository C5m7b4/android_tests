namespace testApi.Models
{
  public class Hour
  {
    public int id { get; set; }
    public DateTime WorkDate { get; set; }
    public int UserId { get; set; }
    public DateTime StartTime { get; set; }
    public DateTime EndTime { get; set; }
    public Decimal TotalHours { get; set; }
  }
}