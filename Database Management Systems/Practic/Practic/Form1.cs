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

namespace Practic
{
    public partial class Form1 : Form
    {
        SqlConnection connection = new SqlConnection("Data Source=DESKTOP-094779K\\SQLEXPRESS;Initial Catalog=TraficRutier;Integrated Security=True");
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
                adapter2.InsertCommand = new SqlCommand("insert into Masini (NrInmatriculare,Marca,Model,DataInmatriculare,ItpValid,Pid,Sid) values (@nrinmat, @marca, @model, @datainmat,@itp,@pid,@sid)", connection);
                adapter2.InsertCommand.Parameters.Add("@nrinmat", SqlDbType.VarChar).Value = nrinmatbox.Text.ToString();
                adapter2.InsertCommand.Parameters.Add("@marca", SqlDbType.VarChar).Value = marcabox.Text.ToString();
                adapter2.InsertCommand.Parameters.Add("@model", SqlDbType.VarChar).Value = modelbox.Text.ToString();
                adapter2.InsertCommand.Parameters.Add("@datainmat", SqlDbType.Date).Value = DateTime.Parse(datainmatbox.Text.ToString()).Date;
                adapter2.InsertCommand.Parameters.Add("@itp", SqlDbType.Bit).Value = Byte.Parse(itpbox.Text.ToString());
                adapter2.InsertCommand.Parameters.Add("@pid", SqlDbType.Int).Value = Int32.Parse(pidbox.Text.ToString());
                adapter2.InsertCommand.Parameters.Add("@sid", SqlDbType.Int).Value = Int32.Parse(sidbox.Text.ToString());
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
            this.Text = "EchipajePolitie_Masini";
            adapter1.SelectCommand = new SqlCommand("SELECT * FROM EchipajePolitie", connection);
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
                nrinmatbox.Clear();
                marcabox.Clear();
                modelbox.Clear();
                datainmatbox.Clear();
                itpbox.Clear();
                sidbox.Clear();
                pidbox.Text = row.Cells["Pid"].Value.ToString();
                int id = Int32.Parse(row.Cells["Pid"].Value.ToString());
                adapter2.SelectCommand = new SqlCommand("SELECT * from Masini where Pid=@pid", connection);
                adapter2.SelectCommand.Parameters.Add("@pid", SqlDbType.Int).Value = id;
                dataset2.Clear();
                adapter2.Fill(dataset2);
                dataGridView2.DataSource = dataset2.Tables[0];
                dataGridView2.AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.Fill;
                dataGridView2.ClearSelection();
                pidbox.ReadOnly = false;
                addBtn.Enabled = true;
                deleteBtn.Enabled = false;
                updateBtn.Enabled = false;
                return;
            }
            pidbox.ReadOnly = true;
            addBtn.Enabled = false;
        }


        private void ShowDetailes(object sender, DataGridViewCellEventArgs e)
        {
            if (e.RowIndex >= 0)
            {
                DataGridViewRow row = this.dataGridView2.Rows[e.RowIndex];
                nrinmatbox.Text = row.Cells["NrInmatriculare"].Value.ToString();
                marcabox.Text = row.Cells["Marca"].Value.ToString();
                modelbox.Text = row.Cells["Model"].Value.ToString();
                datainmatbox.Text = row.Cells["DataInmatriculare"].Value.ToString().Split(' ')[0];
                itpbox.Text = (Boolean.Parse(row.Cells["ItpValid"].Value.ToString()) ? 1 : 0).ToString();
                pidbox.Text = row.Cells["Pid"].Value.ToString();
                sidbox.Text = row.Cells["Sid"].Value.ToString();
                pidbox.ReadOnly = true;
                addBtn.Enabled = false;
                deleteBtn.Enabled = true;
                updateBtn.Enabled = true;
                return;
            }
            pidbox.Clear();
            marcabox.Clear();
            modelbox.Clear();
            datainmatbox.Clear();
            itpbox.Clear();
            sidbox.Clear();
            pidbox.ReadOnly = false;
            deleteBtn.Enabled = false;
            updateBtn.Enabled = false;
        }

        private void deleteBtn_Click(object sender, EventArgs e)
        {
            try
            {
                adapter2.DeleteCommand = new SqlCommand("delete from Masini where Mid=@mid", connection);
                adapter2.DeleteCommand.Parameters.Add("@mid", SqlDbType.Int).Value = Int32.Parse(dataGridView2.SelectedRows[0].Cells[0].Value.ToString());
                connection.Open();
                adapter2.DeleteCommand.ExecuteNonQuery();
                connection.Close();
                dataset2.Clear();
                adapter2.Fill(dataset2);
                MessageBox.Show("Ștergere realizată cu succes!", "Informare");
            }
            catch (Exception err)
            {
                MessageBox.Show(err.Message);
            }
        }

        private void updateBtn_Click(object sender, EventArgs e)
        {
            try
            {
                adapter2.UpdateCommand = new SqlCommand("update Masini set NrInmatriculare=@nrinmat, Marca=@marca, Model=@model, DataInmatriculare=@datainmat, ItpValid=@itp, Sid=@sid where Mid=@mid", connection);
                adapter2.UpdateCommand.Parameters.Add("@nrinmat", SqlDbType.VarChar).Value = nrinmatbox.Text.ToString();
                adapter2.UpdateCommand.Parameters.Add("@marca", SqlDbType.VarChar).Value = marcabox.Text.ToString();
                adapter2.UpdateCommand.Parameters.Add("@model", SqlDbType.VarChar).Value = modelbox.Text.ToString();
                adapter2.UpdateCommand.Parameters.Add("@datainmat", SqlDbType.Date).Value = DateTime.Parse(datainmatbox.Text.ToString());
                adapter2.UpdateCommand.Parameters.Add("@itp", SqlDbType.Bit).Value = Byte.Parse(itpbox.Text.ToString());
                adapter2.UpdateCommand.Parameters.Add("@sid", SqlDbType.Int).Value = Int32.Parse(sidbox.Text.ToString());
                adapter2.UpdateCommand.Parameters.Add("@mid", SqlDbType.Int).Value = Int32.Parse(dataGridView2.SelectedRows[0].Cells[0].Value.ToString());
                connection.Open();
                adapter2.UpdateCommand.ExecuteNonQuery();
                connection.Close();
                dataset2.Clear();
                adapter2.Fill(dataset2);
                MessageBox.Show("Modificare realizată cu succes!", "Informare");
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
            nrinmatbox.Clear();
            marcabox.Clear();
            modelbox.Clear();
            datainmatbox.Clear();
            itpbox.Clear();
            pidbox.Clear();
            sidbox.Clear();
            addBtn.Enabled = false;
            deleteBtn.Enabled = false;
            updateBtn.Enabled = false;
        }
    }
}