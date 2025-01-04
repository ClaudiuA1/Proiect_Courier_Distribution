using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Diagnostics;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using WinFormsApp1;

namespace Courier_Application
{
    public partial class Form1 : Form
    {
       private PackageService packageService = new PackageService();
        private CourierService courierService = new CourierService(); 
        private EmailService emailService = new EmailService();

        public Form1()
        {
            InitializeComponent();
            panelEmails.Visible = false;
        }
            
        private void button1_Click(object sender, EventArgs e)
        {

            List<Package> packages = packageService.GetPackages();

            // Golește lista înainte de populare
            // listBoxPackages.Items.Clear();

            // Adaugă fiecare pachet în ListBox
            listBoxPackages.Items.Add("Toate pachetele:");
            foreach (var package in packages)
            {
                //listBoxCouriers.Items.Add(package.courier.ToString());
                listBoxPackages.Items.Add(package.ToString());
               // Aranjari.AdjustListBoxWidth(listBoxPackages);

            }
            listBoxPackages.Items.Add("////////////////////////////////////////////////////////////////////////////");

        }

        private void buttonBusyC_Click(object sender, EventArgs e)
        {
            List<Courier> couriers = courierService.GetCouriers();

            // listBoxCouriers.Items.Clear();
            listBoxPackages.Items.Add("Toti curierii:");

            foreach (var courier in couriers)
            {
                listBoxPackages.Items.Add(courier.ToString());
               // Aranjari.AdjustListBoxWidth(listBoxCouriers);
            }
            listBoxPackages.Items.Add("////////////////////////////////////////////////////////////////////////////");
        }

        private void buttonClear_Click(object sender, EventArgs e)
        {
            listBoxPackages.Items.Clear();
        }

        private void buttonNoPending_Click(object sender, EventArgs e)
        {
            List<Courier> couriers = courierService.getAllCouriersWithoutPendingPackages();
            listBoxPackages.Items.Add("Toti curierii fara pachete in ''Pending'' :");

            foreach (var courier in couriers)
            {
                listBoxPackages.Items.Add(courier.ToString());
                // Aranjari.AdjustListBoxWidth(listBoxCouriers);
            }
            listBoxPackages.Items.Add("////////////////////////////////////////////////////////////////////////////");

        }

        private void buttonManagerDelivery_Click(object sender, EventArgs e)
        {
            var couriersWithCount = courierService.getManagersAndDeliveredCount();
            listBoxPackages.Items.Add("Toti managerii si numarul de pachete ''Delivered'' :");

            foreach (var item in couriersWithCount)
            {
                string displayText = $"Courier: {item.Courier.name}, Delivered Packages: {item.DeliveredCount}";
                listBoxPackages.Items.Add(displayText);
            }

            listBoxPackages.Items.Add("////////////////////////////////////////////////////////////////////////////");
        }

        private void gestionareToolStripMenuItem_Click(object sender, EventArgs e)
        {
            panelGets.Visible = false;
            panelEmails.Visible = true;

            listBoxNume.Items.Clear();  // ListBox pentru nume
            listBoxEmail.Items.Clear(); // ListBox pentru emailuri

            List<Courier> couriers = courierService.GetCouriers();

            foreach (Courier courier in couriers)
            {
                listBoxNume.Items.Add(courier.name);
                listBoxEmail.Items.Add(courier.email);
            }
           
        }

        private async void buttonSend_Click(object sender, EventArgs e)
        {
            string subject = textBoxSubject.Text.Trim();
            string message = textBoxMessage.Text.Trim();

            if (string.IsNullOrEmpty(subject) || string.IsNullOrEmpty(message))
            {
                MessageBox.Show("Please enter a valid subject and message!");
                return;
            }

            // Inițializează lista de emailuri
            List<string> emailuri = new List<string>();

            // Adaugă emailurile selectate în listă
            foreach (string item in listBoxEmail.SelectedItems)
            {
                emailuri.Add(item);
            }

            // Verifică dacă există emailuri selectate
            if (emailuri.Count == 0)
            {
                MessageBox.Show("Please select at least one email address!");
                return;
            }

            try
            {
                // Trimite emailurile folosind metoda asincronă
                await emailService.SendEmailsAsync(emailuri, message, subject);
                MessageBox.Show("Emails sent successfully!");
                textBoxMessage.Clear();
                textBoxSubject.Clear();
            }
            catch (Exception ex)
            {
                // Tratarea erorilor
                MessageBox.Show($"Failed to send emails: {ex.Message}");
            }
        }

        private void vizualizareToolStripMenuItem_Click(object sender, EventArgs e)
        {
            panelGets.Visible = true;
            panelEmails.Visible = false;
        }
    }
}
