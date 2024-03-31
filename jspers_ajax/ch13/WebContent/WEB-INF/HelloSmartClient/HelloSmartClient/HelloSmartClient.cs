using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Drawing;
using System.Data;
using System.Text;
using System.Windows.Forms;
using System.IO;

namespace HelloSmartClient
{
    public partial class HelloSmartClient : UserControl
    {
        private long currentPage;
        public HelloSmartClient()
        {
            InitializeComponent();
            aJAXBoardTableAdapter.Fill(helloDataSet.AJAXBoard, 10, 10, 2, "1=1");
            File.WriteAllText(Environment.GetFolderPath(
                Environment.SpecialFolder.Desktop) +
                "\\HelloMagneto.txt", "Hello Smart Client!");
        }

        private void HelloSmartClient_Resize(object sender, EventArgs e)
        {
            this.helloSmartBoard.Size = this.Size;
        }

        public long CurrentPage
        {
            set
            {
                aJAXBoardTableAdapter.Fill(helloDataSet.AJAXBoard, (int)value, 10, 10, "1=1");
                this.currentPage = value;
            }
            get
            {
                return currentPage;
            }
        }
    }
}
