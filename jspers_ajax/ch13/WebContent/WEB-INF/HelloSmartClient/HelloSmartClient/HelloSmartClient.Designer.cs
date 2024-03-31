namespace HelloSmartClient
{
    partial class HelloSmartClient
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

        #region 구성 요소 디자이너에서 생성한 코드

        /// <summary> 
        /// 디자이너 지원에 필요한 메서드입니다. 
        /// 이 메서드의 내용을 코드 편집기로 수정하지 마십시오.
        /// </summary>
        private void InitializeComponent()
        {
            this.components = new System.ComponentModel.Container();
            this.helloSmartBoard = new System.Windows.Forms.DataGridView();
            this.idDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.writtentimeDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.writerDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.subcountDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.readsDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.subjectDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.aJAXBoardBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.helloDataSet = new HelloDataSet();
            this.aJAXBoardTableAdapter = new HelloDataSetTableAdapters.AJAXBoardTableAdapter();
            ((System.ComponentModel.ISupportInitialize)(this.helloSmartBoard)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.aJAXBoardBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.helloDataSet)).BeginInit();
            this.SuspendLayout();
            // 
            // helloSmartBoard
            // 
            this.helloSmartBoard.AllowUserToAddRows = false;
            this.helloSmartBoard.AllowUserToDeleteRows = false;
            this.helloSmartBoard.AutoGenerateColumns = false;
            this.helloSmartBoard.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.helloSmartBoard.Columns.AddRange(new System.Windows.Forms.DataGridViewColumn[] {
            this.idDataGridViewTextBoxColumn,
            this.writtentimeDataGridViewTextBoxColumn,
            this.writerDataGridViewTextBoxColumn,
            this.subcountDataGridViewTextBoxColumn,
            this.readsDataGridViewTextBoxColumn,
            this.subjectDataGridViewTextBoxColumn});
            this.helloSmartBoard.DataSource = this.aJAXBoardBindingSource;
            this.helloSmartBoard.Location = new System.Drawing.Point(3, 3);
            this.helloSmartBoard.Name = "helloSmartBoard";
            this.helloSmartBoard.ReadOnly = true;
            this.helloSmartBoard.RowTemplate.Height = 23;
            this.helloSmartBoard.Size = new System.Drawing.Size(526, 150);
            this.helloSmartBoard.TabIndex = 0;
            // 
            // idDataGridViewTextBoxColumn
            // 
            this.idDataGridViewTextBoxColumn.DataPropertyName = "id";
            this.idDataGridViewTextBoxColumn.HeaderText = "id";
            this.idDataGridViewTextBoxColumn.Name = "idDataGridViewTextBoxColumn";
            this.idDataGridViewTextBoxColumn.ReadOnly = true;
            // 
            // writtentimeDataGridViewTextBoxColumn
            // 
            this.writtentimeDataGridViewTextBoxColumn.DataPropertyName = "writtentime";
            this.writtentimeDataGridViewTextBoxColumn.HeaderText = "writtentime";
            this.writtentimeDataGridViewTextBoxColumn.Name = "writtentimeDataGridViewTextBoxColumn";
            this.writtentimeDataGridViewTextBoxColumn.ReadOnly = true;
            // 
            // writerDataGridViewTextBoxColumn
            // 
            this.writerDataGridViewTextBoxColumn.DataPropertyName = "writer";
            this.writerDataGridViewTextBoxColumn.HeaderText = "writer";
            this.writerDataGridViewTextBoxColumn.Name = "writerDataGridViewTextBoxColumn";
            this.writerDataGridViewTextBoxColumn.ReadOnly = true;
            // 
            // subcountDataGridViewTextBoxColumn
            // 
            this.subcountDataGridViewTextBoxColumn.DataPropertyName = "subcount";
            this.subcountDataGridViewTextBoxColumn.HeaderText = "subcount";
            this.subcountDataGridViewTextBoxColumn.Name = "subcountDataGridViewTextBoxColumn";
            this.subcountDataGridViewTextBoxColumn.ReadOnly = true;
            // 
            // readsDataGridViewTextBoxColumn
            // 
            this.readsDataGridViewTextBoxColumn.DataPropertyName = "reads";
            this.readsDataGridViewTextBoxColumn.HeaderText = "reads";
            this.readsDataGridViewTextBoxColumn.Name = "readsDataGridViewTextBoxColumn";
            this.readsDataGridViewTextBoxColumn.ReadOnly = true;
            // 
            // subjectDataGridViewTextBoxColumn
            // 
            this.subjectDataGridViewTextBoxColumn.DataPropertyName = "subject";
            this.subjectDataGridViewTextBoxColumn.HeaderText = "subject";
            this.subjectDataGridViewTextBoxColumn.Name = "subjectDataGridViewTextBoxColumn";
            this.subjectDataGridViewTextBoxColumn.ReadOnly = true;
            // 
            // aJAXBoardBindingSource
            // 
            this.aJAXBoardBindingSource.DataMember = "AJAXBoard";
            this.aJAXBoardBindingSource.DataSource = this.helloDataSet;
            // 
            // helloDataSet
            // 
            this.helloDataSet.DataSetName = "HelloDataSet";
            this.helloDataSet.SchemaSerializationMode = System.Data.SchemaSerializationMode.IncludeSchema;
            // 
            // aJAXBoardTableAdapter
            // 
            this.aJAXBoardTableAdapter.ClearBeforeFill = true;
            // 
            // HelloSmartClient
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(7F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.Controls.Add(this.helloSmartBoard);
            this.Name = "HelloSmartClient";
            this.Size = new System.Drawing.Size(532, 156);
            this.Resize += new System.EventHandler(this.HelloSmartClient_Resize);
            ((System.ComponentModel.ISupportInitialize)(this.helloSmartBoard)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.aJAXBoardBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.helloDataSet)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.DataGridView helloSmartBoard;
        private System.Windows.Forms.BindingSource aJAXBoardBindingSource;
        private HelloDataSet helloDataSet;
        private global::HelloSmartClient.HelloDataSetTableAdapters.AJAXBoardTableAdapter aJAXBoardTableAdapter;
        private System.Windows.Forms.DataGridViewTextBoxColumn idDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn writtentimeDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn writerDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn subcountDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn readsDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewCheckBoxColumn deletedDataGridViewCheckBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn subjectDataGridViewTextBoxColumn;
    }
}
