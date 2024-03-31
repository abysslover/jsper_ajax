using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace TestHelloSmartClient
{
    public partial class TestHelloSmartClient : Form
    {
        public TestHelloSmartClient()
        {
            InitializeComponent();
        }

        private void TestHelloSmartClient_Resize(object sender, EventArgs e)
        {
            this.helloBoard.Size = this.Size;
        }

        private void btnPage_Click(object sender, EventArgs e)
        {
            this.helloBoard.CurrentPage = long.Parse(this.txtPage.Text);
        }

     }
}