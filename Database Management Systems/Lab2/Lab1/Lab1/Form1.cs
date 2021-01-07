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
using System.Configuration;

namespace Lab1
{
    public partial class Form1 : Form
    {
        SqlConnection connection;
        SqlDataAdapter adapter1 = new SqlDataAdapter();
        SqlDataAdapter adapter2 = new SqlDataAdapter();
        DataSet dataset1 = new DataSet();
        DataSet dataset2 = new DataSet();
        IEnumerable<TextBox> boxes;

        public Form1()
        {
            InitializeComponent();
        }

        private void InitializeTextBoxes()
        {
            boxes = myPanel.Controls.OfType<TextBox>();
        }

        private void LoadTable(object sender, EventArgs e)
        {

            string str = ConfigurationManager.ConnectionStrings["connection"].ConnectionString;
            connection = new SqlConnection(str);
            this.Text = ConfigurationManager.AppSettings["tablesName"];
            string selectCmd = ConfigurationManager.AppSettings["selectParent"];
            adapter1.SelectCommand = new SqlCommand(selectCmd, connection);
            IList<String> boxesStrings = new List<string>(ConfigurationManager.AppSettings["textBoxes"].Split(','));

            int top = 130, left = 100;
            foreach (string box in boxesStrings)
            {
                Label label = new Label { Text = box.ToUpper(), Left = 0, Top = top };
                TextBox textBox = new TextBox { Name = box, Top = top, Left = left, Width = 140, Height = 50 };
                myPanel.Controls.Add(label);
                myPanel.Controls.Add(textBox);
                top += 50;
            }

            dataset1.Clear();
            adapter1.Fill(dataset1);
            dataGridView1.DataSource = dataset1.Tables[0];
            dataGridView1.AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.Fill;
            dataGridView1.ClearSelection();
            addBtn.Enabled = false;
            deleteBtn.Enabled = false;
            updateBtn.Enabled = false;
            InitializeTextBoxes();
        }

        private void ShowChildren(object sender, DataGridViewCellEventArgs e)
        {
            if (e.RowIndex >= 0)
            {
                DataGridViewRow row = this.dataGridView1.Rows[e.RowIndex];
                foreach (var box in boxes)
                {
                    box.Clear();
                }

                boxes.ElementAt(boxes.Count() - 1).Text = row.Cells[0].Value.ToString();
                boxes.ElementAt(boxes.Count() - 1).ReadOnly = true;
                boxes.ElementAt(0).ReadOnly = false;

                int id = Int32.Parse(row.Cells[0].Value.ToString());
                string cmd = ConfigurationManager.AppSettings["selectChild"] + id;
                adapter2.SelectCommand = new SqlCommand(cmd, connection);

                dataset2.Clear();
                adapter2.Fill(dataset2);
                dataGridView2.DataSource = dataset2.Tables[0];
                dataGridView2.AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.Fill;
                dataGridView2.ClearSelection();


                addBtn.Enabled = true;
                deleteBtn.Enabled = false;
                updateBtn.Enabled = false;
                return;
            }
            boxes.ElementAt(0).ReadOnly = true;
            addBtn.Enabled = false;
        }

        private void ShowDetailes(object sender, DataGridViewCellEventArgs e)
        {
            if (e.RowIndex >= 0)
            {
                DataGridViewRow row = this.dataGridView2.Rows[e.RowIndex];
                int i = 0;
                for (i = 0; i < boxes.Count(); i++)
                {
                    boxes.ElementAt(i).Text = row.Cells[i].Value.ToString();
                }

                boxes.ElementAt(0).ReadOnly = true;
                boxes.ElementAt(boxes.Count() - 1).ReadOnly = false;
                addBtn.Enabled = false;
                deleteBtn.Enabled = true;
                updateBtn.Enabled = true;
                return;
            }
            foreach (var box in boxes)
            {
                box.Clear();
            }

            boxes.ElementAt(0).ReadOnly = false;
            deleteBtn.Enabled = false;
            updateBtn.Enabled = false;
        }


        private void addBtn_Click(object sender, EventArgs e)
        {
            try
            {
                IList<string> columns = ConfigurationManager.AppSettings["ChildColumnsInsertParameters"].Split(',');
                string cmd = ConfigurationManager.AppSettings["insert"] + ConfigurationManager.AppSettings["ChildColumnsInsertParameters"] + ")";
                adapter2.InsertCommand = new SqlCommand(cmd, connection);
                for (int i = 0; i < boxes.Count(); i++)
                {
                    adapter2.InsertCommand.Parameters.AddWithValue(columns.ElementAt(i), boxes.ElementAt(i).Text.ToString());
                }
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
            foreach (var box in boxes)
            {
                box.Clear();
            }
        }

        private void deleteBtn_Click(object sender, EventArgs e)
        {
            try
            {
                string cmd = ConfigurationManager.AppSettings["delete"] + boxes.ElementAt(0).Text.ToString();
                adapter2.DeleteCommand = new SqlCommand(cmd, connection);
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
                IList<string> columns = ConfigurationManager.AppSettings["ChildColumnsInsertParameters"].Split(',');
                string cmd = ConfigurationManager.AppSettings["update"];

                adapter2.UpdateCommand = new SqlCommand(cmd, connection);

                for (int i = 1; i < boxes.Count(); i++)
                {
                    adapter2.UpdateCommand.Parameters.AddWithValue(columns.ElementAt(i), boxes.ElementAt(i).Text.ToString());
                }
                adapter2.UpdateCommand.Parameters.AddWithValue(columns.ElementAt(0), boxes.ElementAt(0).Text.ToString());
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
            foreach (var box in boxes)
            {
                box.Clear();
            }
            addBtn.Enabled = false;
            deleteBtn.Enabled = false;
            updateBtn.Enabled = false;
            dataGridView1.ClearSelection();
            dataGridView2.ClearSelection();
        }

        private void dataGridView1_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {

        }

        private void ClearFields(object sender, EventArgs e)
        {
            ClearFields();
        }
    }
}