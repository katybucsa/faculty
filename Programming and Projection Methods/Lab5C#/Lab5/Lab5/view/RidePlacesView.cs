using Lab5.controller;
using Lab5.utils;
using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Lab5.view
{
    public partial class RidePlacesView : Form
    {
        private ObservableCollection<RBooking> rbookings;
        private DataTable dataTable = new DataTable();

        public RidePlacesView()
        {
            InitializeComponent();
        }

        public void SetAttributes(ObservableCollection<RBooking> rBookings)
        {
            this.rbookings = rBookings;
        }


        private void RidePlacesView_Load(object sender, EventArgs e)
        {
            tabel.DataSource = rbookings;
        }

        private void tabel_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {

        }
    }
}
