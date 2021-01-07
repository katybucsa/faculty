﻿namespace CompanyClient
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
            this.table.Location = new System.Drawing.Point(1, 172);
            this.table.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.table.Name = "table";
            this.table.ReadOnly = true;
            this.table.RowTemplate.Height = 24;
            this.table.SelectionMode = System.Windows.Forms.DataGridViewSelectionMode.FullRowSelect;
            this.table.Size = new System.Drawing.Size(664, 769);
            this.table.TabIndex = 0;
            this.table.CellClick += new System.Windows.Forms.DataGridViewCellEventHandler(this.ShowDetailes);
            this.table.MouseClick += new System.Windows.Forms.MouseEventHandler(this.MouseClick);
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(72, 60);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(92, 20);
            this.label1.TabIndex = 1;
            this.label1.Text = "Nume client";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(305, 60);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(70, 20);
            this.label2.TabIndex = 2;
            this.label2.Text = "Nr. locuri";
            // 
            // namebox
            // 
            this.namebox.Location = new System.Drawing.Point(37, 95);
            this.namebox.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.namebox.Name = "namebox";
            this.namebox.Size = new System.Drawing.Size(175, 26);
            this.namebox.TabIndex = 3;
            // 
            // comboplaces
            // 
            this.comboplaces.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.comboplaces.FormattingEnabled = true;
            this.comboplaces.Location = new System.Drawing.Point(277, 92);
            this.comboplaces.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.comboplaces.Name = "comboplaces";
            this.comboplaces.Size = new System.Drawing.Size(136, 28);
            this.comboplaces.TabIndex = 4;
            // 
            // bookingbtn
            // 
            this.bookingbtn.Location = new System.Drawing.Point(485, 94);
            this.bookingbtn.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.bookingbtn.Name = "bookingbtn";
            this.bookingbtn.Size = new System.Drawing.Size(84, 29);
            this.bookingbtn.TabIndex = 5;
            this.bookingbtn.Text = "Rezervă";
            this.bookingbtn.UseVisualStyleBackColor = true;
            this.bookingbtn.Click += new System.EventHandler(this.Bookingbtn_Click);
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(810, 172);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(51, 20);
            this.label3.TabIndex = 6;
            this.label3.Text = "Curse";
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(727, 268);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(81, 20);
            this.label4.TabIndex = 7;
            this.label4.Text = "Destinația";
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(764, 345);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(44, 20);
            this.label5.TabIndex = 8;
            this.label5.Text = "Data";
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Location = new System.Drawing.Point(771, 430);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(35, 20);
            this.label6.TabIndex = 9;
            this.label6.Text = "Ora";
            // 
            // destbox
            // 
            this.destbox.Location = new System.Drawing.Point(837, 261);
            this.destbox.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.destbox.Name = "destbox";
            this.destbox.Size = new System.Drawing.Size(156, 26);
            this.destbox.TabIndex = 10;
            // 
            // datebox
            // 
            this.datebox.Location = new System.Drawing.Point(837, 345);
            this.datebox.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.datebox.Name = "datebox";
            this.datebox.Size = new System.Drawing.Size(156, 26);
            this.datebox.TabIndex = 11;
            // 
            // hourbox
            // 
            this.hourbox.Location = new System.Drawing.Point(837, 430);
            this.hourbox.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.hourbox.Name = "hourbox";
            this.hourbox.Size = new System.Drawing.Size(156, 26);
            this.hourbox.TabIndex = 12;
            // 
            // searchbtn
            // 
            this.searchbtn.Location = new System.Drawing.Point(796, 551);
            this.searchbtn.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.searchbtn.Name = "searchbtn";
            this.searchbtn.Size = new System.Drawing.Size(84, 29);
            this.searchbtn.TabIndex = 13;
            this.searchbtn.Text = "Caută";
            this.searchbtn.UseVisualStyleBackColor = true;
            this.searchbtn.Click += new System.EventHandler(this.Searchbtn_Click);
            // 
            // logout
            // 
            this.logout.Location = new System.Drawing.Point(1, -3);
            this.logout.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.logout.Name = "logout";
            this.logout.Size = new System.Drawing.Size(129, 50);
            this.logout.TabIndex = 14;
            this.logout.Text = "Deconectare";
            this.logout.UseVisualStyleBackColor = true;
            this.logout.Click += new System.EventHandler(this.Logout_Click);
            // 
            // MainView
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(9F, 20F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1040, 669);
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
            this.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
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