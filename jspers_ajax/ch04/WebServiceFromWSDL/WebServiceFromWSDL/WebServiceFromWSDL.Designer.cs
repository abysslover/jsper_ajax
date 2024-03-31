namespace WebServiceFromWSDL
{
    partial class WebServiceFromWSDL
    {
        /// <summary>
        /// 필수 디자이너 변수입니다.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// 사용 중인 모든 리소스를 정리합니다.
        /// </summary>
        /// <param name="disposing">관리되는 리소스를 삭제해야 하면 true이고, 그렇지 않으면 false입니다.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form 디자이너에서 생성한 코드

        /// <summary>
        /// 디자이너 지원에 필요한 메서드입니다.
        /// 이 메서드의 내용을 코드 편집기로 수정하지 마십시오.
        /// </summary>
        private void InitializeComponent()
        {
            this.txtArguments = new System.Windows.Forms.TextBox();
            this.lblArguments = new System.Windows.Forms.Label();
            this.lblResult = new System.Windows.Forms.Label();
            this.cbMethod = new System.Windows.Forms.ComboBox();
            this.lblMethod = new System.Windows.Forms.Label();
            this.btnInvoke = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // txtArguments
            // 
            this.txtArguments.Location = new System.Drawing.Point(105, 6);
            this.txtArguments.Name = "txtArguments";
            this.txtArguments.Size = new System.Drawing.Size(396, 21);
            this.txtArguments.TabIndex = 0;
            // 
            // lblArguments
            // 
            this.lblArguments.AutoSize = true;
            this.lblArguments.Location = new System.Drawing.Point(12, 9);
            this.lblArguments.Name = "lblArguments";
            this.lblArguments.Size = new System.Drawing.Size(69, 12);
            this.lblArguments.TabIndex = 1;
            this.lblArguments.Text = "매개변수 값";
            // 
            // lblResult
            // 
            this.lblResult.AutoSize = true;
            this.lblResult.Location = new System.Drawing.Point(12, 63);
            this.lblResult.Name = "lblResult";
            this.lblResult.Size = new System.Drawing.Size(45, 12);
            this.lblResult.TabIndex = 2;
            this.lblResult.Text = "결과 값";
            // 
            // cbMethod
            // 
            this.cbMethod.FormattingEnabled = true;
            this.cbMethod.Items.AddRange(new object[] {
            "add",
            "minus",
            "multiply",
            "divide",
            "doGreet"});
            this.cbMethod.Location = new System.Drawing.Point(105, 34);
            this.cbMethod.Name = "cbMethod";
            this.cbMethod.Size = new System.Drawing.Size(121, 20);
            this.cbMethod.TabIndex = 3;
            // 
            // lblMethod
            // 
            this.lblMethod.AutoSize = true;
            this.lblMethod.Location = new System.Drawing.Point(12, 37);
            this.lblMethod.Name = "lblMethod";
            this.lblMethod.Size = new System.Drawing.Size(57, 12);
            this.lblMethod.TabIndex = 4;
            this.lblMethod.Text = "웹 메서드";
            // 
            // btnInvoke
            // 
            this.btnInvoke.Location = new System.Drawing.Point(426, 52);
            this.btnInvoke.Name = "btnInvoke";
            this.btnInvoke.Size = new System.Drawing.Size(75, 23);
            this.btnInvoke.TabIndex = 5;
            this.btnInvoke.Text = "Invoke";
            this.btnInvoke.UseVisualStyleBackColor = true;
            this.btnInvoke.Click += new System.EventHandler(this.btnInvoke_Click);
            // 
            // WebServiceFromWSDL
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(7F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(506, 83);
            this.Controls.Add(this.btnInvoke);
            this.Controls.Add(this.lblMethod);
            this.Controls.Add(this.cbMethod);
            this.Controls.Add(this.lblResult);
            this.Controls.Add(this.lblArguments);
            this.Controls.Add(this.txtArguments);
            this.Name = "WebServiceFromWSDL";
            this.Text = "Web Service From WSDL";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.TextBox txtArguments;
        private System.Windows.Forms.Label lblArguments;
        private System.Windows.Forms.Label lblResult;
        private System.Windows.Forms.ComboBox cbMethod;
        private System.Windows.Forms.Label lblMethod;
        private System.Windows.Forms.Button btnInvoke;
    }
}

