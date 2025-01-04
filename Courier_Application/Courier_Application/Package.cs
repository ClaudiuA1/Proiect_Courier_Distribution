using Newtonsoft.Json;
using System.Text.Json.Serialization;
using static System.Windows.Forms.VisualStyles.VisualStyleElement;
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
        public override string ToString()
        {
            return $"Package ID: {id}, Address: {deliveryAddress}, Status: {status} ";
        }
    }
    

    [JsonConverter(typeof(JsonStringEnumConverter))]
    internal enum PackageStatus
    {
        NEW,
        PENDING,
        DELIVERED
    }
}