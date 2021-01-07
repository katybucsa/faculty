using CompanyModel;
using CompanyServices;
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
    public partial class MainView : Form
    {
        private Controller controller;
        private User user;
        private DataTable datatable = new DataTable();

        public MainView()
        {

            InitializeComponent();

        }

        public void SetAttributes(Controller c, User user)
        {
            this.controller = c;
            this.user = user;

            controller.UpdateEvent += UserUpdate;
            FillTable();
        }

        public void UserUpdate(object sender, UserEventArgs e)
        {
            table.BeginInvoke(new UpdateTableCallback(FillTable));
        }


        public delegate void UpdateTableCallback();

        private void FillTable()
        {
            table.DataSource = null;
            table.DataSource = controller.GetRModel();
        }

        private void Bookingbtn_Click(object sender, EventArgs e)
        {
            String name = namebox.Text;
            if (name.Equals(""))
            {
                MessageBox.Show("Nume client invalid!");
                return;
            }
            if (comboplaces.SelectedItem == null)
            {
                MessageBox.Show("Selectati numarul de locuri dorite!");
            }
            int nrplaces = int.Parse(comboplaces.Text);
            try
            {

                Ride r = new Ride(0, table.SelectedRows[0].Cells["Destinatia"].Value.ToString(),
                    table.SelectedRows[0].Cells["Data"].Value.ToString(), table.SelectedRows[0].Cells["Ora"].Value.ToString());
                String msg = controller.BookPlaces(r, name, nrplaces);
                MessageBox.Show(msg, "Informare", MessageBoxButtons.OK, MessageBoxIcon.Information);
                ClearFields();
            }
            catch (AppException err)
            {
                MessageBox.Show(err.Message, "Eroare", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }

        }

        private void ShowDetailes(object sender, DataGridViewCellEventArgs e)
        {
            if (e.RowIndex >= 0)
            {
                DataGridViewRow row = this.table.Rows[e.RowIndex];
                destbox.Text = row.Cells["Destinatia"].Value.ToString();
                datebox.Text = row.Cells["Data"].Value.ToString();
                hourbox.Text = row.Cells["Ora"].Value.ToString();
                var combosource = new List<int>();
                for (int i = 1; i <= int.Parse(row.Cells["NrLocuriDisponibile"].Value.ToString()); i++)
                    combosource.Add(i);
                comboplaces.DataSource = combosource;
                comboplaces.SelectedItem = null;
                return;
            }
            table.ClearSelection();
            this.ClearFields();
            comboplaces.DataSource = null;

        }

        private void SelectionChanged(object sender, EventArgs e)
        {
            table.ClearSelection();
            this.ClearFields();
            comboplaces.DataSource = null;
        }

        private void ClearFields()
        {
            destbox.Text = "";
            datebox.Text = "";
            hourbox.Text = "";
        }

        private void MouseClick(object sender, MouseEventArgs e)
        {
            if (e.Location.IsEmpty)
                ClearFields();
        }

        private void Searchbtn_Click(object sender, EventArgs e)
        {
            try
            {
                string dest = destbox.Text;
                string date = datebox.Text;
                string hour = hourbox.Text;

                RidePlacesView ridePlacesView = new RidePlacesView();
                ridePlacesView.SetAttributes(controller.GetRBModel(dest, date, hour), controller, dest, date, hour);
                ridePlacesView.Show();
            }
            catch (AppException err)
            {
                MessageBox.Show(err.Message);
            }
        }

        private void Logout_Click(object sender, EventArgs e)
        {
            controller.Logout(user);
            this.Close();
        }
    }
}
