using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Windows.Forms;
using WinFormsApp1;

namespace Courier_Application
{
    internal static class Program
    {
        /// <summary>
        /// The main entry point for the application.
        /// </summary>
        [STAThread]
        static void Main()
        {
            Application.EnableVisualStyles();
            Application.SetCompatibleTextRenderingDefault(false);
            PackageService packageService = new PackageService();
            packageService.createConnection();
            CourierService courierService = new CourierService();
            courierService.createConnection();
            Application.Run(new Form1());
        }
    }
}
