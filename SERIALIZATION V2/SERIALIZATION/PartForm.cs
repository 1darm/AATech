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
    public partial class PartForm : Form
    {
        public PartForm()
        {
            InitializeComponent();
            loadTable();
            //table loader
        }

        String connString = @"Data Source=SQLSERVER;Initial Catalog=SERIALIZATION;Persist Security Info=True;User ID=sa;Password=AATech#101";
        SqlConnection conn = new SqlConnection(@"Data Source=SQLSERVER;Initial Catalog=SERIALIZATION;Persist Security Info=True;User ID=sa;Password=AATech#101");

        private void table_CellClick(object sender, DataGridViewCellEventArgs e)
        {
            //if cell clicked is the part #
            if (table.CurrentCell.ColumnIndex.Equals(0) && e.RowIndex != -1)
            {
                if (table.CurrentCell != null && table.CurrentCell.Value != null)
                {
                    //testing because when a cell is clicked, it causes it to highlight the text and makes that window go on top again, unsure as to why
                    table.ClearSelection();
                    JobForm f4 = new JobForm(table.CurrentCell.Value.ToString());
                    //loads all jobs under that part #
                    f4.Show(); 
                }
            }
        }
        private void button1_Click(object sender, EventArgs e)
        {
            //search button
            conn.Open();

            string query = "SELECT * FROM parts WHERE [PART #] = @SearchValue";
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
            //table loada
            conn.Open();
            string query = "SELECT [PART #] FROM parts";
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

        private void addButton_Click(object sender, EventArgs e)
        {
            //goes to add job form
            AddJobForm f2 = new AddJobForm(table);
            f2.Show();
        }

    }
}
