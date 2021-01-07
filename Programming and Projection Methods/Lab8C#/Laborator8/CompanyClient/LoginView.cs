using CompanyModel;
using CompanyServices;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace CompanyClient
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
                User user=controller.FindUser(username, password);
                MainView mainView = new MainView();
                mainView.SetAttributes(controller,user);
                this.ClearFields();
                this.Hide();
                mainView.ShowDialog();
                this.Show();
            }catch(AppException re)
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
