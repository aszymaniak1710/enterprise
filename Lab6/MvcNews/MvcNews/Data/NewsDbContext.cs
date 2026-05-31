using System.Collections.Generic;
using System.Data.Entity;
using Microsoft.EntityFrameworkCore;
using MvcNews.Models;

namespace MvcNews.Data
{
    public class NewsDbContext : Microsoft.EntityFrameworkCore.DbContext
    {
        public NewsDbContext(DbContextOptions<NewsDbContext> options) :
        base(options)
        { }
        public Microsoft.EntityFrameworkCore.DbSet<NewsItem> News { get; set; } = null!;
    }

}
