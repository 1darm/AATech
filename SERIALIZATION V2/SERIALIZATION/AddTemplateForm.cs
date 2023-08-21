using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Data.SqlClient;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using static System.Windows.Forms.VisualStyles.VisualStyleElement;

namespace SERIALIZATION
{
    public partial class AddTemplateForm : Form
    {
        System.Windows.Forms.ComboBox c1;
        public AddTemplateForm(System.Windows.Forms.ComboBox c1)
        {
            this.c1 = c1;
            InitializeComponent();
        }
        SqlConnection conn = new SqlConnection(@"Data Source=SQLSERVER;Initial Catalog=SERIALIZATION;Persist Security Info=True;User ID=sa;Password=AATech#101");
        private void submitButton_Click(object sender, EventArgs e)
        {
            //inserts the template into table
            conn.Open();
            string query = "INSERT INTO templates ([Company], [Format]) VALUES (@Company, @Format)";
            SqlCommand command = new SqlCommand(query, conn);
            command.Parameters.AddWithValue("@Company", companyText.Text);
            command.Parameters.AddWithValue("@Format", formatText.Text);
            command.ExecuteNonQuery();
            conn.Close();

            c1.Items.Clear();
            loadComboBox();
            //reloads combo box
            this.Hide();
        }
        private void loadComboBox()
        {
            //combo box loada
            conn.Open();
            string query = "SELECT [Company] FROM templates";
            SqlCommand command = new SqlCommand(query, conn);

            using (SqlDataReader reader = command.ExecuteReader())
            {
                while (reader.Read())
                {
                    c1.Items.Add(reader.GetString(0));
                }
            }
            conn.Close();
        }
    }
}
