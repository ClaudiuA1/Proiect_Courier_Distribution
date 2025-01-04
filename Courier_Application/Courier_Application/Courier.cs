using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.Json;
using System.Threading.Tasks;
using System.Xml.XPath;
using static System.Windows.Forms.VisualStyles.VisualStyleElement;

namespace WinFormsApp1
{
    internal class Courier
    {
        public int id { get; set; }
        public string name { get; set; }
        public string email { get; set; } 
        public int? manager_id { get; set; }

        public override string ToString()
        {
            return $"Courier ID: {id}, Name: {name}, Email: {email}, Mannager: {manager_id}";
        }
    }
}