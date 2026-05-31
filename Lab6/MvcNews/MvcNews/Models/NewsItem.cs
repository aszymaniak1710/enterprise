using System.ComponentModel.DataAnnotations;
using System.Data;

namespace MvcNews.Models
{
    public class NewsItem
    {
        public int Id { get; set; }
        [DataType(DataType.Date)]
        [DisplayFormat(DataFormatString = "{0:dd.MM.yyyy}", ApplyFormatInEditMode = true)]
        public DateTime TimeStamp { get; set; }
        [Required]
        [StringLength(140, MinimumLength = 5)]
        public string Text { get; set; } = string.Empty;
        [Timestamp]
        public byte[]? RowVersion { get; set; }
    }
}
