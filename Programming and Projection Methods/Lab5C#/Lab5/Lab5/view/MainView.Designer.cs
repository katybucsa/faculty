namespace Lab5
{
    partial class MainView
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
            this.table = new System.Windows.Forms.DataGridView();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.namebox = new System.Windows.Forms.TextBox();
            this.comboplaces = new System.Windows.Forms.ComboBox();
            this.bookingbtn = new System.Windows.Forms.Button();
            this.label3 = new System.Windows.Forms.Label();
            this.label4 = new System.Windows.Forms.Label();
            this.label5 = new System.Windows.Forms.Label();
            this.label6 = new System.Windows.Forms.Label();
            this.destbox = new System.Windows.Forms.TextBox();
            this.datebox = new System.Windows.Forms.TextBox();
            this.hourbox = new System.Windows.Forms.TextBox();
            this.searchbtn = new System.Windows.Forms.Button();
            this.logout = new System.Windows.Forms.Button();
            ((System.ComponentModel.ISupportInitialize)(this.table)).BeginInit();
            this.SuspendLayout();
            // 
            // table
            // 
            this.table.AllowUserToAddRows = false;
            this.table.AllowUserToDeleteRows = false;
            this.table.AllowUserToOrderColumns = true;
            this.table.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.table.Location = new System.Drawing.Point(1, 138);
            this.table.Name = "table";
            this.table.ReadOnly = true;
            this.table.RowTemplate.Height = 24;
            this.table.SelectionMode = System.Windows.Forms.DataGridViewSelectionMode.FullRowSelect;
            this.table.Size = new System.Drawing.Size(590, 615);
            this.table.TabIndex = 0;
            this.table.CellClick += new System.Windows.Forms.DataGridViewCellEventHandler(this.ShowDetailes);
            this.table.MouseClick += new System.Windows.Forms.MouseEventHandler(this.MouseClick);
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(64, 48);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(82, 17);
            this.label1.TabIndex = 1;
            this.label1.Text = "Nume client";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(271, 48);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(65, 17);
            this.label2.TabIndex = 2;
            this.label2.Text = "Nr. locuri";
            // 
            // namebox
            // 
            this.namebox.Location = new System.Drawing.Point(33, 76);
            this.namebox.Name = "namebox";
            this.namebox.Size = new System.Drawing.Size(156, 22);
            this.namebox.TabIndex = 3;
            // 
            // comboplaces
            // 
            this.comboplaces.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.comboplaces.FormattingEnabled = true;
            this.comboplaces.Location = new System.Drawing.Point(246, 74);
            this.comboplaces.Name = "comboplaces";
            this.comboplaces.Size = new System.Drawing.Size(121, 24);
            this.comboplaces.TabIndex = 4;
            // 
            // bookingbtn
            // 
            this.bookingbtn.Location = new System.Drawing.Point(431, 75);
            this.bookingbtn.Name = "bookingbtn";
            this.bookingbtn.Size = new System.Drawing.Size(75, 23);
            this.bookingbtn.TabIndex = 5;
            this.bookingbtn.Text = "Rezervă";
            this.bookingbtn.UseVisualStyleBackColor = true;
            this.bookingbtn.Click += new System.EventHandler(this.bookingbtn_Click);
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(720, 138);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(45, 17);
            this.label3.TabIndex = 6;
            this.label3.Text = "Curse";
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(646, 214);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(71, 17);
            this.label4.TabIndex = 7;
            this.label4.Text = "Destinația";
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(679, 276);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(38, 17);
            this.label5.TabIndex = 8;
            this.label5.Text = "Data";
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Location = new System.Drawing.Point(685, 344);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(32, 17);
            this.label6.TabIndex = 9;
            this.label6.Text = "Ora";
            // 
            // destbox
            // 
            this.destbox.Location = new System.Drawing.Point(744, 209);
            this.destbox.Name = "destbox";
            this.destbox.Size = new System.Drawing.Size(139, 22);
            this.destbox.TabIndex = 10;
            // 
            // datebox
            // 
            this.datebox.Location = new System.Drawing.Point(744, 276);
            this.datebox.Name = "datebox";
            this.datebox.Size = new System.Drawing.Size(139, 22);
            this.datebox.TabIndex = 11;
            // 
            // hourbox
            // 
            this.hourbox.Location = new System.Drawing.Point(744, 344);
            this.hourbox.Name = "hourbox";
            this.hourbox.Size = new System.Drawing.Size(139, 22);
            this.hourbox.TabIndex = 12;
            // 
            // searchbtn
            // 
            this.searchbtn.Location = new System.Drawing.Point(708, 441);
            this.searchbtn.Name = "searchbtn";
            this.searchbtn.Size = new System.Drawing.Size(75, 23);
            this.searchbtn.TabIndex = 13;
            this.searchbtn.Text = "Caută";
            this.searchbtn.UseVisualStyleBackColor = true;
            this.searchbtn.Click += new System.EventHandler(this.searchbtn_Click);
            // 
            // logout
            // 
            this.logout.Location = new System.Drawing.Point(808, 713);
            this.logout.Name = "logout";
            this.logout.Size = new System.Drawing.Size(115, 40);
            this.logout.TabIndex = 14;
            this.logout.Text = "Deconectare";
            this.logout.UseVisualStyleBackColor = true;
            this.logout.Click += new System.EventHandler(this.logout_Click);
            // 
            // MainView
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(924, 755);
            this.Controls.Add(this.logout);
            this.Controls.Add(this.searchbtn);
            this.Controls.Add(this.hourbox);
            this.Controls.Add(this.datebox);
            this.Controls.Add(this.destbox);
            this.Controls.Add(this.label6);
            this.Controls.Add(this.label5);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.bookingbtn);
            this.Controls.Add(this.comboplaces);
            this.Controls.Add(this.namebox);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.table);
            this.Name = "MainView";
            this.Text = "MainView";
            this.Click += new System.EventHandler(this.SelectionChanged);
            ((System.ComponentModel.ISupportInitialize)(this.table)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.DataGridView table;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.TextBox namebox;
        private System.Windows.Forms.Button bookingbtn;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.Label label6;
        private System.Windows.Forms.TextBox destbox;
        private System.Windows.Forms.TextBox datebox;
        private System.Windows.Forms.TextBox hourbox;
        private System.Windows.Forms.Button searchbtn;
        private System.Windows.Forms.ComboBox comboplaces;
        private System.Windows.Forms.Button logout;
    }
}