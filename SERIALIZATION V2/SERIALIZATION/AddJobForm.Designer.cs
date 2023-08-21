namespace SERIALIZATION
{
    partial class AddJobForm
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
            this.submitButton = new System.Windows.Forms.Button();
            this.label1 = new System.Windows.Forms.Label();
            this.jobText = new System.Windows.Forms.TextBox();
            this.poText = new System.Windows.Forms.TextBox();
            this.label2 = new System.Windows.Forms.Label();
            this.partText = new System.Windows.Forms.TextBox();
            this.partLabel = new System.Windows.Forms.Label();
            this.revText = new System.Windows.Forms.TextBox();
            this.revLabel = new System.Windows.Forms.Label();
            this.label5 = new System.Windows.Forms.Label();
            this.qtyText = new System.Windows.Forms.TextBox();
            this.dateText = new System.Windows.Forms.TextBox();
            this.label7 = new System.Windows.Forms.Label();
            this.SuspendLayout();
            // 
            // submitButton
            // 
            this.submitButton.Location = new System.Drawing.Point(238, 74);
            this.submitButton.Name = "submitButton";
            this.submitButton.Size = new System.Drawing.Size(75, 65);
            this.submitButton.TabIndex = 0;
            this.submitButton.Text = "SUBMIT";
            this.submitButton.UseVisualStyleBackColor = true;
            this.submitButton.Click += new System.EventHandler(this.button1_Click);
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(41, 15);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(37, 13);
            this.label1.TabIndex = 1;
            this.label1.Text = "JOB #";
            // 
            // jobText
            // 
            this.jobText.Location = new System.Drawing.Point(83, 12);
            this.jobText.Name = "jobText";
            this.jobText.Size = new System.Drawing.Size(100, 20);
            this.jobText.TabIndex = 2;
            // 
            // poText
            // 
            this.poText.Location = new System.Drawing.Point(83, 55);
            this.poText.Name = "poText";
            this.poText.Size = new System.Drawing.Size(100, 20);
            this.poText.TabIndex = 4;
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(45, 58);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(32, 13);
            this.label2.TabIndex = 3;
            this.label2.Text = "PO #";
            // 
            // partText
            // 
            this.partText.Location = new System.Drawing.Point(83, 97);
            this.partText.Name = "partText";
            this.partText.Size = new System.Drawing.Size(100, 20);
            this.partText.TabIndex = 6;
            // 
            // partLabel
            // 
            this.partLabel.AutoSize = true;
            this.partLabel.Location = new System.Drawing.Point(32, 100);
            this.partLabel.Name = "partLabel";
            this.partLabel.Size = new System.Drawing.Size(46, 13);
            this.partLabel.TabIndex = 5;
            this.partLabel.Text = "PART #";
            // 
            // revText
            // 
            this.revText.Location = new System.Drawing.Point(83, 140);
            this.revText.Name = "revText";
            this.revText.Size = new System.Drawing.Size(100, 20);
            this.revText.TabIndex = 8;
            // 
            // revLabel
            // 
            this.revLabel.AutoSize = true;
            this.revLabel.Location = new System.Drawing.Point(48, 143);
            this.revLabel.Name = "revLabel";
            this.revLabel.Size = new System.Drawing.Size(29, 13);
            this.revLabel.TabIndex = 7;
            this.revLabel.Text = "REV";
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(14, 181);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(64, 13);
            this.label5.TabIndex = 9;
            this.label5.Text = "QTY BUILD";
            // 
            // qtyText
            // 
            this.qtyText.Location = new System.Drawing.Point(83, 178);
            this.qtyText.Name = "qtyText";
            this.qtyText.Size = new System.Drawing.Size(100, 20);
            this.qtyText.TabIndex = 12;
            // 
            // dateText
            // 
            this.dateText.Location = new System.Drawing.Point(82, 217);
            this.dateText.Name = "dateText";
            this.dateText.Size = new System.Drawing.Size(100, 20);
            this.dateText.TabIndex = 14;
            // 
            // label7
            // 
            this.label7.AutoSize = true;
            this.label7.Location = new System.Drawing.Point(8, 220);
            this.label7.Name = "label7";
            this.label7.Size = new System.Drawing.Size(69, 13);
            this.label7.TabIndex = 13;
            this.label7.Text = "DATE CODE";
            // 
            // Form2
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(378, 254);
            this.Controls.Add(this.dateText);
            this.Controls.Add(this.label7);
            this.Controls.Add(this.qtyText);
            this.Controls.Add(this.label5);
            this.Controls.Add(this.revText);
            this.Controls.Add(this.revLabel);
            this.Controls.Add(this.partText);
            this.Controls.Add(this.partLabel);
            this.Controls.Add(this.poText);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.jobText);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.submitButton);
            this.Name = "Form2";
            this.Text = "Form2";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Button submitButton;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.TextBox jobText;
        private System.Windows.Forms.TextBox poText;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.TextBox partText;
        private System.Windows.Forms.Label partLabel;
        private System.Windows.Forms.TextBox revText;
        private System.Windows.Forms.Label revLabel;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.TextBox qtyText;
        private System.Windows.Forms.TextBox dateText;
        private System.Windows.Forms.Label label7;
    }
}