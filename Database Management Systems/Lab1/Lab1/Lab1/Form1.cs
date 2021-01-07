using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Data.SqlClient;

namespace Lab1
{
    public partial class Form1 : Form
    {
        SqlConnection connection = new SqlConnection("Data Source=DESKTOP-FDH8JHJ\\SQLEXPRESS;Initial Catalog=Primarie;Integrated Security=True");
        SqlDataAdapter adapter1 = new SqlDataAdapter();
        SqlDataAdapter adapter2 = new SqlDataAdapter();
        DataSet dataset1 = new DataSet();
        DataSet dataset2 = new DataSet();

        public Form1()
        {
            InitializeComponent();
        }


        private void addBtn_Click(object sender, EventArgs e)
        {
            try
            {
                adapter2.InsertCommand = new SqlCommand("insert into Locuitori (Lid, Nume,Prenume,Serviciu,Adid) values (@id, @nume, @prenume, @serviciu, @adid)", connection);
                adapter2.InsertCommand.Parameters.Add("@id", SqlDbType.Int).Value = Int32.Parse(idbox.Text.ToString());
                adapter2.InsertCommand.Parameters.Add("@nume", SqlDbType.VarChar).Value = numebox.Text.ToString();
                adapter2.InsertCommand.Parameters.Add("@prenume", SqlDbType.VarChar).Value = prenumebox.Text.ToString();
                adapter2.InsertCommand.Parameters.Add("@serviciu", SqlDbType.VarChar).Value = serviciubox.Text.ToString();
                adapter2.InsertCommand.Parameters.Add("@adid", SqlDbType.Int).Value = Int32.Parse(adresabox.Text.ToString());
                connection.Open();
                adapter2.InsertCommand.ExecuteNonQuery();
                connection.Close();
                dataset2.Clear();
                adapter2.Fill(dataset2);
                MessageBox.Show("Adăugare realizată cu succes!", "Informare");
            }
            catch (Exception err)
            {
                MessageBox.Show(err.Message);
            }
        }

        private void LoadTable(object sender, EventArgs e)
        {
            this.Text = "Adrese_Locuitori";
            adapter1.SelectCommand = new SqlCommand("SELECT * FROM Adrese", connection);
            dataset1.Clear();
            adapter1.Fill(dataset1);
            dataGridView1.DataSource = dataset1.Tables[0];
            dataGridView1.AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.Fill;
            dataGridView1.ClearSelection();
            addBtn.Enabled = false;
            deleteBtn.Enabled = false;
            updateBtn.Enabled = false;
        }

        private void ShowChildren(object sender, DataGridViewCellEventArgs e)
        {
            if (e.RowIndex >= 0)
            {
                DataGridViewRow row = this.dataGridView1.Rows[e.RowIndex];
                idbox.Clear();
                numebox.Clear();
                prenumebox.Clear();
                serviciubox.Clear();
                adresabox.Text = row.Cells["Adid"].Value.ToString();
                int id = Int32.Parse(row.Cells["Adid"].Value.ToString());
                adapter2.SelectCommand = new SqlCommand("SELECT * from Locuitori where Adid=@id", connection);
                adapter2.SelectCommand.Parameters.Add("@id", SqlDbType.Int).Value = id;
                dataset2.Clear();
                adapter2.Fill(dataset2);
                dataGridView2.DataSource = dataset2.Tables[0];
                dataGridView2.AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.Fill;
                dataGridView2.ClearSelection();
                idbox.ReadOnly = false;
                adresabox.ReadOnly = true;
                addBtn.Enabled = true;
                deleteBtn.Enabled = false;
                updateBtn.Enabled = false;
                return;
            }
            idbox.ReadOnly = true;
            addBtn.Enabled = false;
        }

        private void ShowDetailes(object sender, DataGridViewCellEventArgs e)
        {
            if (e.RowIndex >= 0)
            {
                DataGridViewRow row = this.dataGridView2.Rows[e.RowIndex];
                idbox.Text = row.Cells["Lid"].Value.ToString();
                numebox.Text = row.Cells["Nume"].Value.ToString();
                prenumebox.Text = row.Cells["Prenume"].Value.ToString();
                serviciubox.Text = row.Cells["Serviciu"].Value.ToString();
                adresabox.Text = row.Cells["Adid"].Value.ToString();
                idbox.ReadOnly = true;
                adresabox.ReadOnly = false;
                addBtn.Enabled = false;
                deleteBtn.Enabled = true;
                updateBtn.Enabled = true;
                return;
            }
            idbox.Clear();
            numebox.Clear();
            prenumebox.Clear();
            serviciubox.Clear();
            adresabox.Clear();
            idbox.ReadOnly = false;
            deleteBtn.Enabled = false;
            updateBtn.Enabled = false;
        }

        private void deleteBtn_Click(object sender, EventArgs e)
        {
            try
            {
                adapter2.DeleteCommand = new SqlCommand("delete from Locuitori where Lid=@id and Nume=@nume and Prenume=@prenume and Serviciu=@serviciu and Adid=@adid", connection);
                adapter2.DeleteCommand.Parameters.Add("@id", SqlDbType.Int).Value = Int32.Parse(idbox.Text.ToString());
                adapter2.DeleteCommand.Parameters.Add("@nume", SqlDbType.VarChar).Value = numebox.Text.ToString();
                adapter2.DeleteCommand.Parameters.Add("@prenume", SqlDbType.VarChar).Value = prenumebox.Text.ToString();
                adapter2.DeleteCommand.Parameters.Add("@serviciu", SqlDbType.VarChar).Value = serviciubox.Text.ToString();
                adapter2.DeleteCommand.Parameters.Add("@adid", SqlDbType.Int).Value = Int32.Parse(adresabox.Text.ToString());
                connection.Open();
                adapter2.DeleteCommand.ExecuteNonQuery();
                connection.Close();
                dataset2.Clear();
                adapter2.Fill(dataset2);
                MessageBox.Show("Ștergere realizată cu succes!", "Informare");
            }
            catch(Exception err)
            {
                MessageBox.Show(err.Message);
            }
        }

        private void updateBtn_Click(object sender, EventArgs e)
        {
            try
            {
                adapter2.UpdateCommand = new SqlCommand("update Locuitori set Nume=@nume, Prenume=@prenume, Serviciu=@serviciu, Adid=@adid where Lid=@id", connection);
                adapter2.UpdateCommand.Parameters.Add("@nume", SqlDbType.VarChar).Value = numebox.Text.ToString();
                adapter2.UpdateCommand.Parameters.Add("@prenume", SqlDbType.VarChar).Value = prenumebox.Text.ToString();
                adapter2.UpdateCommand.Parameters.Add("@serviciu", SqlDbType.VarChar).Value = serviciubox.Text.ToString();
                adapter2.UpdateCommand.Parameters.Add("@adid", SqlDbType.Int).Value = Int32.Parse(adresabox.Text.ToString());
                adapter2.UpdateCommand.Parameters.Add("@id", SqlDbType.Int).Value = Int32.Parse(idbox.Text.ToString());
                connection.Open();
                adapter2.UpdateCommand.ExecuteNonQuery();
                connection.Close();
                dataset2.Clear();
                adapter2.Fill(dataset2);
                MessageBox.Show("Modificare realizată cu succes!","Informare");
            }
            catch (Exception err)
            {
                MessageBox.Show(err.Message);
            }
        }

        private void ClearSelections(object sender, EventArgs e)
        {
            dataGridView1.ClearSelection();
            dataGridView2.ClearSelection();
            ClearFields();
        }

        private void ClearFields()
        {
            idbox.Clear();
            numebox.Clear();
            prenumebox.Clear();
            serviciubox.Clear();
            adresabox.Clear();
            addBtn.Enabled = false;
            deleteBtn.Enabled = false;
            updateBtn.Enabled = false;
        }
    }
}