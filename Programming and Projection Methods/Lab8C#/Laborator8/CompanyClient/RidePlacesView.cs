using CompanyModel;
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

namespace CompanyClient
{
    public partial class RidePlacesView : Form
    {
        private IList<RBooking> rbookings;
        private Controller controller;
        private String destination;
        private String date;
        private String hour;
        private DataTable dataTable = new DataTable();

        public RidePlacesView()
        {
            InitializeComponent();
        }

        public void SetAttributes(IList<RBooking> rBookings,Controller c,String dest,String date,String hour)
        {
            this.rbookings = rBookings;
            this.controller = c;
            this.destination = dest;
            this.date = date;
            this.hour = hour;
            controller.UpdateEvent += UserUpdate;
        }

        private void FillList()
        {
            tabel.DataSource = null;
            tabel.DataSource = controller.GetRBModel(destination,date,hour);
        }

        public void UserUpdate(object sender, UserEventArgs e)
        {
            tabel.BeginInvoke(new UpdateTableCallback(FillList));
        }

        public delegate void UpdateTableCallback();

        private void RidePlacesView_Load(object sender, EventArgs e)
        {
            tabel.DataSource = rbookings;
        }

    }
}
