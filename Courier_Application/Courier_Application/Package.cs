using Newtonsoft.Json;
using System.Text.Json.Serialization;
using JsonConverterAttribute = System.Text.Json.Serialization.JsonConverterAttribute;

namespace WinFormsApp1
{
    internal class Package
    {
        public int id { get; set; }
        public string deliveryAddress { get; set; }
        public string createdOn { get; set; }
        public PackageStatus status { get; set; }
        public Courier courier { get; set; }
    }

    [JsonConverter(typeof(JsonStringEnumConverter))]
    internal enum PackageStatus
    {
        NEW,
        PENDING,
        DELIVERED
    }
}