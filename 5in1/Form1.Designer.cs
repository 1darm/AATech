namespace _5in1
{
    partial class Form1
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.selectDatabaseButton = new System.Windows.Forms.Button();
            this.panel1 = new System.Windows.Forms.Panel();
            this.table = new System.Windows.Forms.DataGridView();
            this.backgroundWorker1 = new System.ComponentModel.BackgroundWorker();
            this.textBox1 = new System.Windows.Forms.TextBox();
            this.textBox2 = new System.Windows.Forms.TextBox();
            this.button2 = new System.Windows.Forms.Button();
            this.findAlternateButton = new System.Windows.Forms.Button();
            this.importBomButton = new System.Windows.Forms.Button();
            this.exportExcelButton = new System.Windows.Forms.Button();
            this.crossRefPCMRPButton = new System.Windows.Forms.Button();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.altCol = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.pcmrpCol = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.mfgpnCol = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.mfgpnManCol = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.descCol = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.panel1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.table)).BeginInit();
            this.SuspendLayout();
            // 
            // selectDatabaseButton
            // 
            this.selectDatabaseButton.Location = new System.Drawing.Point(13, 369);
            this.selectDatabaseButton.Name = "selectDatabaseButton";
            this.selectDatabaseButton.Size = new System.Drawing.Size(139, 42);
            this.selectDatabaseButton.TabIndex = 0;
            this.selectDatabaseButton.Text = "SELECT ALTERNATE DATABASE";
            this.selectDatabaseButton.UseVisualStyleBackColor = true;
            this.selectDatabaseButton.Click += new System.EventHandler(this.button1_Click);
            // 
            // panel1
            // 
            this.panel1.AutoScroll = true;
            this.panel1.Controls.Add(this.table);
            this.panel1.Location = new System.Drawing.Point(12, 12);
            this.panel1.Name = "panel1";
            this.panel1.Size = new System.Drawing.Size(1244, 351);
            this.panel1.TabIndex = 1;
            this.panel1.Paint += new System.Windows.Forms.PaintEventHandler(this.panel1_Paint);
            // 
            // table
            // 
            this.table.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.table.Columns.AddRange(new System.Windows.Forms.DataGridViewColumn[] {
            this.descCol,
            this.mfgpnManCol,
            this.mfgpnCol,
            this.pcmrpCol,
            this.altCol});
            this.table.Location = new System.Drawing.Point(0, 0);
            this.table.Name = "table";
            this.table.Size = new System.Drawing.Size(1244, 351);
            this.table.TabIndex = 0;
            this.table.CellContentClick += new System.Windows.Forms.DataGridViewCellEventHandler(this.dataGridView1_CellContentClick);
            // 
            // backgroundWorker1
            // 
            this.backgroundWorker1.DoWork += new System.ComponentModel.DoWorkEventHandler(this.backgroundWorker1_DoWork);
            // 
            // textBox1
            // 
            this.textBox1.Location = new System.Drawing.Point(977, 381);
            this.textBox1.Name = "textBox1";
            this.textBox1.Size = new System.Drawing.Size(279, 20);
            this.textBox1.TabIndex = 2;
            this.textBox1.TextChanged += new System.EventHandler(this.textBox1_TextChanged);
            // 
            // textBox2
            // 
            this.textBox2.Location = new System.Drawing.Point(977, 407);
            this.textBox2.Name = "textBox2";
            this.textBox2.Size = new System.Drawing.Size(279, 20);
            this.textBox2.TabIndex = 3;
            this.textBox2.TextChanged += new System.EventHandler(this.textBox2_TextChanged);
            // 
            // button2
            // 
            this.button2.Location = new System.Drawing.Point(1170, 435);
            this.button2.Name = "button2";
            this.button2.Size = new System.Drawing.Size(86, 44);
            this.button2.TabIndex = 4;
            this.button2.Text = "Add Part";
            this.button2.UseVisualStyleBackColor = true;
            this.button2.Click += new System.EventHandler(this.button2_Click);
            // 
            // findAlternateButton
            // 
            this.findAlternateButton.Location = new System.Drawing.Point(12, 414);
            this.findAlternateButton.Name = "findAlternateButton";
            this.findAlternateButton.Size = new System.Drawing.Size(140, 41);
            this.findAlternateButton.TabIndex = 5;
            this.findAlternateButton.Text = "FIND ALTERNATES";
            this.findAlternateButton.UseVisualStyleBackColor = true;
            this.findAlternateButton.Click += new System.EventHandler(this.button3_Click);
            // 
            // importBomButton
            // 
            this.importBomButton.Location = new System.Drawing.Point(158, 369);
            this.importBomButton.Name = "importBomButton";
            this.importBomButton.Size = new System.Drawing.Size(139, 42);
            this.importBomButton.TabIndex = 6;
            this.importBomButton.Text = "IMPORT BOM";
            this.importBomButton.UseVisualStyleBackColor = true;
            this.importBomButton.Click += new System.EventHandler(this.button4_Click);
            // 
            // exportExcelButton
            // 
            this.exportExcelButton.Location = new System.Drawing.Point(158, 414);
            this.exportExcelButton.Name = "exportExcelButton";
            this.exportExcelButton.Size = new System.Drawing.Size(139, 41);
            this.exportExcelButton.TabIndex = 7;
            this.exportExcelButton.Text = "EXPORT EXCEL FILE";
            this.exportExcelButton.UseVisualStyleBackColor = true;
            this.exportExcelButton.Click += new System.EventHandler(this.button5_Click);
            // 
            // crossRefPCMRPButton
            // 
            this.crossRefPCMRPButton.Location = new System.Drawing.Point(13, 461);
            this.crossRefPCMRPButton.Name = "crossRefPCMRPButton";
            this.crossRefPCMRPButton.Size = new System.Drawing.Size(139, 41);
            this.crossRefPCMRPButton.TabIndex = 8;
            this.crossRefPCMRPButton.Text = "CROSS REFERENCE PCMRP";
            this.crossRefPCMRPButton.UseVisualStyleBackColor = true;
            this.crossRefPCMRPButton.Click += new System.EventHandler(this.button6_Click);
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(918, 384);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(53, 13);
            this.label1.TabIndex = 9;
            this.label1.Text = "MFG P/N";
            this.label1.Click += new System.EventHandler(this.label1_Click);
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(849, 410);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(122, 13);
            this.label2.TabIndex = 10;
            this.label2.Text = "MFG MANAFACTURER";
            // 
            // altCol
            // 
            this.altCol.AutoSizeMode = System.Windows.Forms.DataGridViewAutoSizeColumnMode.Fill;
            this.altCol.HeaderText = "ALTERNATES";
            this.altCol.Name = "altCol";
            this.altCol.ReadOnly = true;
            // 
            // pcmrpCol
            // 
            this.pcmrpCol.HeaderText = "PC/MRP";
            this.pcmrpCol.Name = "pcmrpCol";
            this.pcmrpCol.ReadOnly = true;
            this.pcmrpCol.Width = 250;
            // 
            // mfgpnCol
            // 
            this.mfgpnCol.HeaderText = "MFG P/N";
            this.mfgpnCol.Name = "mfgpnCol";
            this.mfgpnCol.ReadOnly = true;
            this.mfgpnCol.Width = 200;
            // 
            // mfgpnManCol
            // 
            this.mfgpnManCol.HeaderText = "MFG MANAFACTURER";
            this.mfgpnManCol.Name = "mfgpnManCol";
            this.mfgpnManCol.ReadOnly = true;
            this.mfgpnManCol.Width = 150;
            // 
            // descCol
            // 
            this.descCol.HeaderText = "DESCRIPTION";
            this.descCol.Name = "descCol";
            this.descCol.ReadOnly = true;
            this.descCol.Width = 300;
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1268, 508);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.crossRefPCMRPButton);
            this.Controls.Add(this.exportExcelButton);
            this.Controls.Add(this.importBomButton);
            this.Controls.Add(this.findAlternateButton);
            this.Controls.Add(this.button2);
            this.Controls.Add(this.textBox2);
            this.Controls.Add(this.textBox1);
            this.Controls.Add(this.panel1);
            this.Controls.Add(this.selectDatabaseButton);
            this.Name = "Form1";
            this.Text = "Form1";
            this.panel1.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.table)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Button selectDatabaseButton;
        private System.Windows.Forms.Panel panel1;
        private System.ComponentModel.BackgroundWorker backgroundWorker1;
        private System.Windows.Forms.DataGridView table;
        private System.Windows.Forms.TextBox textBox1;
        private System.Windows.Forms.TextBox textBox2;
        private System.Windows.Forms.Button button2;
        private System.Windows.Forms.Button findAlternateButton;
        private System.Windows.Forms.Button importBomButton;
        private System.Windows.Forms.Button exportExcelButton;
        private System.Windows.Forms.Button crossRefPCMRPButton;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.DataGridViewTextBoxColumn descCol;
        private System.Windows.Forms.DataGridViewTextBoxColumn mfgpnManCol;
        private System.Windows.Forms.DataGridViewTextBoxColumn mfgpnCol;
        private System.Windows.Forms.DataGridViewTextBoxColumn pcmrpCol;
        private System.Windows.Forms.DataGridViewTextBoxColumn altCol;
    }
}

