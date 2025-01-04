using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Courier_Application
{
    internal static class Aranjari
    {
        public static void AdjustListBoxWidth(ListBox listBox)
        {
            int maxWidth = 0;
            using (Graphics g = listBox.CreateGraphics())
            {
                foreach (var item in listBox.Items)
                {
                    // Folosește TextRenderer pentru măsurare precisă
                    int itemWidth = TextRenderer.MeasureText(item.ToString(), listBox.Font).Width;
                    if (itemWidth > maxWidth)
                    {
                        maxWidth = itemWidth;
                    }
                }
            }

            // Adaugă lățimea barei de scroll și o marjă pentru un aspect mai bun
            listBox.Width = maxWidth + SystemInformation.VerticalScrollBarWidth + 10;
        }


    }
}
