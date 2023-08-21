using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Data.SqlClient;
using System.Diagnostics.Eventing.Reader;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Linq.Expressions;
using System.Net.Mail;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using Microsoft.VisualBasic;

namespace SERIALIZATION
{
    public partial class JobForm : Form
    {
        bool admin;
        string part;
        public JobForm(string part)
        {
            //admin perm constructor
            InitializeComponent();
            admin = true;
            this.Focus();
            this.part = part;
            conn.Open();
            string query = "SELECT * FROM jobs WHERE [PART #] = @Part";
            SqlCommand command = new SqlCommand(query, conn);
            command.Parameters.AddWithValue("@Part", part);

            SqlDataReader reader = command.ExecuteReader();
            DataTable dt = new DataTable();
            dt.Load(reader);
            partText.Hide();
            partBox.Hide();
            table.DataSource = dt;
            table.Columns[7].AutoSizeMode = DataGridViewAutoSizeColumnMode.Fill;
            table.Refresh();
            reader.Close();
            conn.Close();
        }
        public JobForm(bool admin)
        {
            //no admin perm constructor
            InitializeComponent();
            this.admin = admin;
            conn.Open();
            string query = "SELECT * FROM jobs";
            SqlCommand command = new SqlCommand(query, conn);

            SqlDataReader reader = command.ExecuteReader();
            DataTable dt = new DataTable();
            dt.Load(reader);

            table.DataSource = dt;
            table.Columns[7].AutoSizeMode = DataGridViewAutoSizeColumnMode.Fill;
            table.Refresh();
            reader.Close();
            conn.Close();
        }
        String connString = @"Data Source=SQLSERVER;Initial Catalog=SERIALIZATION;Persist Security Info=True;User ID=sa;Password=AATech#101";
        SqlConnection conn = new SqlConnection(@"Data Source=SQLSERVER;Initial Catalog=SERIALIZATION;Persist Security Info=True;User ID=sa;Password=AATech#101");
        private void table_CellClick(object sender, DataGridViewCellEventArgs e)
        {
            try
            {
                if (table.CurrentCell.ColumnIndex.Equals(6) && e.RowIndex != -1 && admin)
                {
                    //gose to create serial form and intakes row
                    CreateSerial f6 = new CreateSerial(table[0, e.RowIndex].Value.ToString(),
                        table[1, e.RowIndex].Value.ToString(),
                        table[2, e.RowIndex].Value.ToString(),
                        table[3, e.RowIndex].Value.ToString(),
                        table[4, e.RowIndex].Value.ToString(),
                        table[5, e.RowIndex].Value.ToString(),
                        table[6, e.RowIndex].Value.ToString(),
                        table);
                    f6.Show();
                }
                else if (table.CurrentCell.ColumnIndex.Equals(0) && e.RowIndex != -1)
                {
                    if (table[6, e.RowIndex].Value.ToString().Equals(""))
                    {
                        //shows all shipping / inspection information form if it is the main row, (no range in serial range)
                        ShipInspectForm f5 = new ShipInspectForm(table[2, e.RowIndex].Value.ToString(), table[1, e.RowIndex].Value.ToString());
                        f5.Show();
                    }
                    else
                    {
                        //shows all shipping / inspection information for the serial range
                        ShipInspectForm f5 = new ShipInspectForm(table[2, e.RowIndex].Value.ToString(), table[1, e.RowIndex].Value.ToString(),
                            int.Parse(table[6, e.RowIndex].Value.ToString().Substring(0, table[6, e.RowIndex].Value.ToString().IndexOf("-"))),
                            int.Parse(table[6, e.RowIndex].Value.ToString().Substring(table[6, e.RowIndex].Value.ToString().IndexOf("-") + 1)));
                        f5.Show();
                    }
                }
            }
            catch (IOException io)
            {
                //test to find out why 'autosizing' exception happens
                Console.WriteLine("ERRMM WHATS HAPPENING");
            }
        }
        private void advancedSearchButton_Click(object sender, EventArgs e)
        {
            //advanced button
            conn.Open();
            string query = "SELECT * FROM jobs";

            //makes sure isnt empty, if empty shows everything from table
            if (Convert.ToBoolean(jobBox.CheckState) == true && !(jobText.Text.Replace(" ", "").Equals("")))
            {
                if (query.Length > 18)
                {
                    //checks if it is the first statement being added to original query
                    query = query + " AND [JOB #] = @jobNumber";
                }
                else
                {
                    query = query + " WHERE [JOB #] = @jobNumber";
                }
            }
            if (Convert.ToBoolean(poBox.CheckState) == true && !(poText.Text.Replace(" ", "").Equals("")))
            {
                if (query.Length > 18)
                {
                    query = query + " AND [PO #] = @poNumber";
                }
                else
                {
                    query = query + " WHERE [PO #] = @poNumber";

                }
            }
            if (Convert.ToBoolean(revBox.CheckState) == true && !(revText.Text.Replace(" ", "").Equals("")))
            {
                if (query.Length > 18)
                {
                    query = query + " AND [REV] = @revNumber";
                }
                else
                {
                    query = query + " WHERE [REV] = @revNumber";

                }
            }
            if (Convert.ToBoolean(partBox.CheckState) == true && !(partText.Text.Replace(" ", "").Equals("")))
            {
                if (query.Length > 18)
                {
                    query = query + " AND [PART #] = @partNumber";
                }
                else
                {
                    query = query + " WHERE [PART #] = @partNumber";

                }
            }
            SqlCommand command = new SqlCommand(query, conn);

            if (Convert.ToBoolean(jobBox.CheckState) == true)
            {
                if (jobText.Text.Replace(" ", "").Equals(""))
                {
                    //checks if box is empty
                    MessageBox.Show("MISSING JOB #", "ERROR", MessageBoxButtons.OK, MessageBoxIcon.Error);
                }
                else
                {
                    command.Parameters.AddWithValue("@jobNumber", jobText.Text);
                }
            }
            if (Convert.ToBoolean(poBox.CheckState) == true)
            {
                if (poText.Text.Replace(" ", "").Equals(""))
                {
                    MessageBox.Show("MISSING PO #", "ERROR", MessageBoxButtons.OK, MessageBoxIcon.Error);
                }
                else
                {
                    command.Parameters.AddWithValue("@poNumber", poText.Text);
                }
            }
            if (Convert.ToBoolean(revBox.CheckState) == true)
            {
                if (revText.Text.Replace(" ", "").Equals(""))
                {
                    MessageBox.Show("MISSING REV", "ERROR", MessageBoxButtons.OK, MessageBoxIcon.Error);
                }
                else
                {
                    command.Parameters.AddWithValue("@revNumber", revText.Text);
                }
            }
            if (Convert.ToBoolean(partBox.CheckState) == true)
            {
                if (partText.Text.Replace(" ", "").Equals(""))
                {
                    MessageBox.Show("MISSING PART", "ERROR", MessageBoxButtons.OK, MessageBoxIcon.Error);
                }
                else
                {
                    command.Parameters.AddWithValue("@partNumber", partText.Text);
                }
            }

            SqlDataReader reader = command.ExecuteReader();

            table.DataSource = null;
            table.Rows.Clear();

            DataTable dt = new DataTable();
            dt.Load(reader);

            table.DataSource = dt;
            table.Columns[7].AutoSizeMode = DataGridViewAutoSizeColumnMode.Fill;
            table.Refresh();
            reader.Close();
            conn.Close();
        }
        private void refreshButton_Click(object sender, EventArgs e)
        {
            //refresh table i do not remember what causes the need for this but doesnt hurt to keep
            conn.Open();
            string currentPart = table[2, 0].Value.ToString();
            table.DataSource = null;
            table.Rows.Clear();

            string query = "SELECT * FROM jobs WHERE [PART #] = @partNumber";
            SqlCommand command = new SqlCommand(query, conn);
            command.Parameters.AddWithValue("@partNumber", currentPart);
            SqlDataReader reader = command.ExecuteReader();
            DataTable dt = new DataTable();
            dt.Load(reader);

            table.DataSource = dt;
            table.Columns[7].AutoSizeMode = DataGridViewAutoSizeColumnMode.Fill;
            table.Refresh();
            reader.Close();
            conn.Close();
        }

        private void JobForm_Load(object sender, EventArgs e)
        {

        }

        private void table_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {

        }
    }
}
