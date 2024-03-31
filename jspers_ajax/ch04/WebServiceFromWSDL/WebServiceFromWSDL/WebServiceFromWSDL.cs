using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;
using WebServiceFromWSDL.JavawideGreetService;
using System.Reflection;

namespace WebServiceFromWSDL
{
    public partial class WebServiceFromWSDL : Form
    {
        GreetServiceService service;
        public WebServiceFromWSDL()
        {
            InitializeComponent();
            this.service = new GreetServiceService();
        }

        private void btnInvoke_Click(object sender, EventArgs e)
        {
            object[] arguments = buildMethodArguments(cbMethod.Text);
            if (null == arguments) {
                lblResult.Text = "매개변수가 잘못되었습니다.";
                return;
            }
            invoke(arguments);
        }

        private void invoke(object[] arguments)
        {
            object result = this.service.GetType().InvokeMember(cbMethod.Text,
                BindingFlags.Default | BindingFlags.InvokeMethod,
                null, this.service, arguments);
            lblResult.Text = result.ToString();
        }

        private object[] buildMethodArguments(string methodName)
        {
            object[] arguments = null;
            if ("" == txtArguments.Text) return arguments;
            
            if ("doGreet" == methodName)
            {
                arguments = new object[] { txtArguments.Text };
            }
            else
            {
                string[] argumentArray = txtArguments.Text.Split(',');
                if (argumentArray.Length > 1)
                {
                    arguments = new object[] { Convert.ToInt32(argumentArray[0]), Convert.ToInt32(argumentArray[1]) };
                }
            }
            return arguments;

        }
    }
}