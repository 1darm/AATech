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
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }
        SqlConnection conn = new SqlConnection(@"Data Source=SQLSERVER;Initial Catalog=SERIALIZATION;Persist Security Info=True;User ID=sa;Password=AATech#101");

        private void textBox1_TextChanged(object sender, EventArgs e)
        {

        }

        private void label1_Click(object sender, EventArgs e)
        {

        }

        private void loginButton_Click(object sender, EventArgs e)
        {
            String username, password;

            username = usernameText.Text;
            password = passwordText.Text;

            try
            {
                String querry = "SELECT * FROM login WHERE username = '" + usernameText.Text + "' AND password = '" + passwordText.Text + "'";
                SqlDataAdapter sda = new SqlDataAdapter(querry, conn);

                DataTable dtable = new DataTable();
                sda.Fill(dtable);

                if (dtable.Rows.Count > 0)
                {
                    username = usernameText.Text;
                    password = passwordText.Text;

                    MessageBox.Show("LOGIN SUCCESS", "WOOOO", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
                    this.Hide();
                    Form2 form2 = new Form2();
                    form2.Show();
                    Form form3 = new Form3();
                    form3.Show();
                }
                else
                {
                    MessageBox.Show("INVALID LOGIN", "ERROR", MessageBoxButtons.OK, MessageBoxIcon.Error);
                    usernameText.Clear();
                    passwordText.Clear();
                }
            }
            catch
            {
                MessageBox.Show("UH OH STINKY!");
            }
            finally
            {
                conn.Close();
            }
        }
    }
}
