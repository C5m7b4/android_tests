using System.Net;
using Microsoft.EntityFrameworkCore;
using testApi.Data;

var builder = WebApplication.CreateBuilder(args);

// Add services to the container.

builder.Services.AddControllers();
// Learn more about configuring Swagger/OpenAPI at https://aka.ms/aspnetcore/swashbuckle
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();

builder.Services.AddDbContext<AppDbContext>(opt =>
        opt.UseSqlServer(builder.Configuration.GetConnectionString("conn")));


builder.Services.AddScoped<IRepo, Repo>();

builder.WebHost.UseKestrel(opt =>
{
  var host = Dns.GetHostEntry("c5.io");
  opt.Listen(host.AddressList[0], 5000);
  // opt.ListenAnyIP(5000);
  opt.Listen(host.AddressList[0], 5001, listOpt =>
  {
    //listOpt.UseHttps(builder.Configuration["CertPath"], builder.Configuration["CertPassword"]);
    listOpt.UseHttps("D:\\myCert.pfx", "Pa55word!");
  });
});

var app = builder.Build();

// Configure the HTTP request pipeline.
if (app.Environment.IsDevelopment())
{
  app.UseSwagger();
  app.UseSwaggerUI();
}

//app.UseHttpsRedirection();

app.UseAuthorization();

app.MapControllers();

app.Run();
