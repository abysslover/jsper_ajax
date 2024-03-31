namespace TestHelloSmartClient
{
    partial class TestHelloSmartClient
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
            this.helloBoard = new HelloSmartClient.HelloSmartClient();
            this.txtPage = new System.Windows.Forms.TextBox();
            this.btnPage = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // helloBoard
            // 
            this.helloBoard.Location = new System.Drawing.Point(12, 12);
            this.helloBoard.Name = "helloBoard";
            this.helloBoard.Size = new System.Drawing.Size(532, 156);
            this.helloBoard.TabIndex = 0;
            // 
            // txtPage
            // 
            this.txtPage.Location = new System.Drawing.Point(392, 179);
            this.txtPage.Name = "txtPage";
            this.txtPage.Size = new System.Drawing.Size(59, 21);
            this.txtPage.TabIndex = 1;
            // 
            // btnPage
            // 
            this.btnPage.Location = new System.Drawing.Point(457, 177);
            this.btnPage.Name = "btnPage";
            this.btnPage.Size = new System.Drawing.Size(87, 23);
            this.btnPage.TabIndex = 2;
            this.btnPage.Text = "페이지 변경";
            this.btnPage.UseVisualStyleBackColor = true;
            this.btnPage.Click += new System.EventHandler(this.btnPage_Click);
            // 
            // TestHelloSmartClient
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(7F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(558, 212);
            this.Controls.Add(this.btnPage);
            this.Controls.Add(this.txtPage);
            this.Controls.Add(this.helloBoard);
            this.Name = "TestHelloSmartClient";
            this.Text = "Form1";
            this.Resize += new System.EventHandler(this.TestHelloSmartClient_Resize);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private HelloSmartClient.HelloSmartClient helloBoard;
        private System.Windows.Forms.TextBox txtPage;
        private System.Windows.Forms.Button btnPage;

    }
}

