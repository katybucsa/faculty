using Lab5.controller;
using Lab5.domain;
using Lab5.repository;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Lab5.view
{
    public partial class LoginView : Form
    {

        private Controller controller;
        public LoginView()
        {
            InitializeComponent();
        }



        public void setAttributes(Controller c)
        {
            this.controller = c;
        }

        private void button1_Click(object sender, EventArgs e)
        {
            String username = userbox.Text;
            String password = passbox.Text;
            try
            {
                User user = controller.FindUser(username, password);
                MainView mainView = new MainView();
                mainView.setController(controller);
                this.ClearFields();
                this.Hide();
                mainView.ShowDialog();
                this.Show();
            }catch(RepositoryException re)
            {
                MessageBox.Show(re.Message);
                this.Show();
            }
        }


        private void ClearFields()
        {
            userbox.Text = "";
            passbox.Text = "";
        }

        private void LoginView_Load(object sender, EventArgs e)
        {
            passbox.PasswordChar = '*';
        }
    }
}
