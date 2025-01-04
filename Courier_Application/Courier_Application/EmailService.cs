using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Text;
using System.Text.Json;
using System.Threading.Tasks;

namespace Courier_Application
{
    internal class EmailService
    {
        private static readonly HttpClient client = new HttpClient();

        public void createConnection()
        {
            // Update port # in the following line.
            client.BaseAddress = new Uri("http://localhost:8080/");
            client.DefaultRequestHeaders.Accept.Clear();
            client.DefaultRequestHeaders.Accept.Add(
                new MediaTypeWithQualityHeaderValue("application/json"));
        }

        public async Task SendEmailsAsync(List<string> emails, string message, string subject)
        {
            var emailRequest = new
            {
                emails = emails,
                message = message,
                subject = subject
            };

            var json = JsonSerializer.Serialize(emailRequest);
            var content = new StringContent(json, Encoding.UTF8, "application/json");

            try
            {
                // Adaptează URL-ul conform endpoint-ului din server
                HttpResponseMessage response = await client.PostAsync("http://localhost:8080/email/send", content);

                if (response.IsSuccessStatusCode)
                {
                    Console.WriteLine("Emails sent successfully.");
                }
                else
                {
                    Console.WriteLine($"Failed to send emails: {response.StatusCode} - {response.ReasonPhrase}");
                }
            }
            catch (Exception ex)
            {
                Console.WriteLine($"Error sending emails: {ex.Message}");
            }
        }
    }
}
