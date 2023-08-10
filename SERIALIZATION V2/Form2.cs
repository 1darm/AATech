using System;
using System.CodeDom;
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
    public partial class Form2 : Form   
    {
        public Form2()
        {
            InitializeComponent();
        }
        String connString = @"Data Source=SQLSERVER;Initial Catalog=SERIALIZATION;Persist Security Info=True;User ID=sa;Password=AATech#101";
        SqlConnection conn = new SqlConnection(@"Data Source=SQLSERVER;Initial Catalog=SERIALIZATION;Persist Security Info=True;User ID=sa;Password=AATech#101");
        private void button1_Click(object sender, EventArgs e)
        {
            string query = "INSERT INTO jobs ([JOB #], [PO #], [PART #], [REV], [QTY BUILD], [DATE CODE])";
            query += " VALUES (@JobNumber, @PONumber, @PartNumber, @Rev, @QtyBuild, @DateCode)";

            conn.Open();

            SqlCommand myCommand = new SqlCommand(query, conn);
            myCommand.Parameters.AddWithValue("@JobNumber", jobText.Text);
            myCommand.Parameters.AddWithValue("@PONumber", poText.Text);
            myCommand.Parameters.AddWithValue("@PartNumber", partText.Text);
            myCommand.Parameters.AddWithValue("@Rev", revText.Text);
            myCommand.Parameters.AddWithValue("@QtyBuild", qtyText.Text);
            myCommand.Parameters.AddWithValue("@DateCode", dateText.Text);
            myCommand.ExecuteNonQuery();

            conn.Close();

            jobText.Clear();
            poText.Clear();
            partText.Clear();
            revText.Clear();
            qtyText.Clear();
            dateText.Clear();

        }
    }
}
