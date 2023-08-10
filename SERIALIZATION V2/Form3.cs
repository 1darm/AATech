using System;
using System.Collections;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Data.SqlClient;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace SERIALIZATION
{
    public partial class Form3 : Form
    {
        public Form3()
        {
            InitializeComponent();
            loadTable();
        }

        String connString = @"Data Source=SQLSERVER;Initial Catalog=SERIALIZATION;Persist Security Info=True;User ID=sa;Password=AATech#101";
        SqlConnection conn = new SqlConnection(@"Data Source=SQLSERVER;Initial Catalog=SERIALIZATION;Persist Security Info=True;User ID=sa;Password=AATech#101");
        private void table_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {
          
        }

        private void label1_Click(object sender, EventArgs e)
        {

        }

        private void button1_Click(object sender, EventArgs e)
        {
            conn.Open();

            string query = "SELECT * FROM jobs WHERE [JOB #] = @SearchValue";
            SqlCommand command = new SqlCommand(query, conn);
            command.Parameters.AddWithValue("@SearchValue", searchText.Text);

            SqlDataReader reader = command.ExecuteReader();
            DataTable dt = new DataTable();
            dt.Load(reader);

            table.DataSource = dt;
            table.Columns[0].AutoSizeMode = DataGridViewAutoSizeColumnMode.Fill;
            table.Refresh();
            reader.Close();
            conn.Close();
        }
        private void loadTable()
        {
            conn.Open();
            string query = "SELECT * FROM jobs";
            SqlCommand command = new SqlCommand(query, conn);

            SqlDataReader reader = command.ExecuteReader();
            DataTable dt = new DataTable();
            dt.Load(reader);

            table.DataSource = dt;
            table.Columns[0].AutoSizeMode = DataGridViewAutoSizeColumnMode.Fill;
            table.Refresh();
            reader.Close();
            conn.Close();
        }
    }
}
