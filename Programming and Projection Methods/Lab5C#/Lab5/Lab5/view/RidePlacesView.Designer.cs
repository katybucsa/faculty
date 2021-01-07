namespace Lab5.view
{
    partial class RidePlacesView
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
            this.tabel = new System.Windows.Forms.DataGridView();
            ((System.ComponentModel.ISupportInitialize)(this.tabel)).BeginInit();
            this.SuspendLayout();
            // 
            // tabel
            // 
            this.tabel.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.tabel.Location = new System.Drawing.Point(0, 0);
            this.tabel.Name = "tabel";
            this.tabel.RowTemplate.Height = 24;
            this.tabel.Size = new System.Drawing.Size(320, 563);
            this.tabel.TabIndex = 0;
            this.tabel.CellContentClick += new System.Windows.Forms.DataGridViewCellEventHandler(this.tabel_CellContentClick);
            // 
            // RidePlacesView
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(320, 560);
            this.Controls.Add(this.tabel);
            this.Name = "RidePlacesView";
            this.Text = "RidePlacesView";
            this.Load += new System.EventHandler(this.RidePlacesView_Load);
            ((System.ComponentModel.ISupportInitialize)(this.tabel)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.DataGridView tabel;
    }
}