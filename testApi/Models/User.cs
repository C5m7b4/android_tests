using System.ComponentModel.DataAnnotations;

namespace testApi.Models
{
  public class User
  {
    [Key]
    [Required]
    public int Id { get; set; }
    [Required]
    public string username { get; set; }
    public string firstname { get; set; }
    public string lastname { get; set; }
    [Required]
    public string email { get; set; }
    public int storeid { get; set; }
    public int active { get; set; }
  }
}