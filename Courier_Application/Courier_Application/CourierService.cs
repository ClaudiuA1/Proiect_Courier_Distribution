using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Text;
using System.Text.Json;
using System.Threading.Tasks;
using WinFormsApp1;

namespace Courier_Application
{
    internal class CourierService
    {
        static HttpClient client = new HttpClient();

        public void createConnection()
        {
            // Update port # in the following line.
            client.BaseAddress = new Uri("http://localhost:8080/");
            client.DefaultRequestHeaders.Accept.Clear();
            client.DefaultRequestHeaders.Accept.Add(
                new MediaTypeWithQualityHeaderValue("application/json"));
        }

        public List<Courier> GetCouriers()
        {
            List<Courier> couriers = null;
            HttpResponseMessage response = client.GetAsync("courier").Result;
            if (response.IsSuccessStatusCode)
            {
                string resultString = response.Content.ReadAsStringAsync().Result;
                Console.WriteLine("received : " + resultString);
                couriers = JsonSerializer.Deserialize<List<Courier>>(resultString);
                return couriers;

            }
            return null;
        }
        public List<Courier> getAllCouriersWithoutPendingPackages()
        {
            List<Courier> couriers = null;
            HttpResponseMessage response = client.GetAsync("courier/without-pending-packages").Result;
            if (response.IsSuccessStatusCode)
            {
                string resultString = response.Content.ReadAsStringAsync().Result;
                Console.WriteLine("received : " + resultString);
                couriers = JsonSerializer.Deserialize<List<Courier>>(resultString);
                return couriers;

            }
            return null;

        }

        public List<CourierWithDeliveredCount> getManagersAndDeliveredCount()
        {
            List<CourierWithDeliveredCount> couriersWithCount = new List<CourierWithDeliveredCount>();
            HttpResponseMessage response = client.GetAsync("courier/all-managers-delivered").Result;

            if (response.IsSuccessStatusCode)
            {
                string resultString = response.Content.ReadAsStringAsync().Result;
                Console.WriteLine("received : " + resultString);

                // Deserializare manuală
                var jsonArray = JsonSerializer.Deserialize<List<JsonElement[]>>(resultString);

                foreach (var item in jsonArray)
                {
                    var courier = JsonSerializer.Deserialize<Courier>(item[0].GetRawText());
                    int deliveredCount = item[1].GetInt32();

                    couriersWithCount.Add(new CourierWithDeliveredCount
                    {
                        Courier = courier,
                        DeliveredCount = deliveredCount
                    });
                }

                return couriersWithCount;
            }

            return null;
        }




    }
}
