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

namespace SERIALIZATION
{
    public partial class LoginForm : Form
    {
        public LoginForm()
        {
            InitializeComponent();
            //designer.cs
        }
        SqlConnection conn = new SqlConnection(@"Data Source=SQLSERVER;Initial Catalog=SERIALIZATION;Persist Security Info=True;User ID=sa;Password=AATech#101");

        private void loginButton_Click(object sender, EventArgs e)
        {
            string username = usernameText.Text;
            string password = passwordText.Text;

            try
            {
                using (conn)
                {
                    conn.Open();

                    string query = "SELECT adminperm FROM login WHERE username = @username AND password = @password";
                    //looks through login table and gets the adminperm col
                    using (SqlCommand command = new SqlCommand(query, conn))
                    {
                        command.Parameters.AddWithValue("@username", username);
                        command.Parameters.AddWithValue("@password", password);

                        bool adminperm = false;
                        using (SqlDataReader reader = command.ExecuteReader())
                        {
                            //reads the query result
                            if (reader.Read())
                            {
                                //makes adminperm the result
                                adminperm = reader.GetBoolean(0);
                            }
                        }

                        if (adminperm)
                        {
                            //if adminperm = true goes through program with admin access
                            this.Hide();
                            Form form3 = new PartForm();
                            form3.Show();
                        }
                        else
                        {
                            //if adminperm = false goes through program w/o admin access
                            JobForm j1 = new JobForm(false);
                            this.Hide();
                            j1.Show();
                        }
                    }
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show("An error occurred: " + ex.Message, "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }
    }
}
