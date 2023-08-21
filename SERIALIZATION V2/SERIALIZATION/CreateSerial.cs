using Microsoft.VisualBasic;
using System;
using System.Collections;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Data.SqlClient;
using System.Drawing;
using System.Globalization;
using System.Linq;
using System.Net.Http.Headers;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace SERIALIZATION
{
    public partial class CreateSerial : Form
    {
        string jobNumber;
        string poNumber;
        string partNumber;
        string revNumber;
        string qtyNumber;
        string dateNumber;
        string serialNumber;
        DataGridView table;
        int digitPlace;

        string item;
        public bool year;
        public bool day;
        public bool month;
        public bool week;

        public ArrayList order;

        public CreateSerial(string jobNumber, string poNumber, string partNumber, string revNumber, string qtyNumber, string dateNumber, string serialNumber, DataGridView table)
        {
            this.jobNumber = jobNumber;
            this.poNumber = poNumber;
            this.partNumber = partNumber;
            this.revNumber = revNumber;
            this.qtyNumber = qtyNumber;
            this.dateNumber = dateNumber;
            this.serialNumber = serialNumber;
            this.table = table;

            digitPlace = 0;
            year = false;
            day = false;
            month = false;
            week = false;

            order = new ArrayList();
            InitializeComponent();
            fillComboBox();
            weekLabel.Hide();
            weekText.Hide();
            monthText.Hide();
            monthLabel.Hide();
            dayText.Hide();
            dayLabel.Hide();
            yearText.Hide();
            yearLabel.Hide();
            this.poNumber = poNumber;
            this.table = table;

            //gets current dates
            currentMonth.Text = DateTime.Now.ToString("MM");
            currentDay.Text = DateTime.Now.ToString("dd");
            currentYear.Text = DateTime.Now.ToString("yyyy");

            CultureInfo myCI = new CultureInfo("en-US");
            Calendar myCal = myCI.Calendar;
            CalendarWeekRule myCWR = myCI.DateTimeFormat.CalendarWeekRule;
            DayOfWeek myFirstDOW = myCI.DateTimeFormat.FirstDayOfWeek;

            currentWeek.Text = myCal.GetWeekOfYear(DateTime.Now, myCWR, myFirstDOW).ToString();
        }
        SqlConnection conn = new SqlConnection(@"Data Source=SQLSERVER;Initial Catalog=SERIALIZATION;Persist Security Info=True;User ID=sa;Password=AATech#101");
        private void templateButton_Click(object sender, EventArgs e)
        {
            //add template form
            AddTemplateForm f1 = new AddTemplateForm(comboBox1);
            f1.Show();
        }

        private void comboBox1_SelectedIndexChanged(object sender, EventArgs e)
        {
            //default hides everything and picks which to show
            weekLabel.Hide();
            weekText.Hide();
            monthText.Hide();
            monthLabel.Hide();
            dayText.Hide();
            dayLabel.Hide();
            yearText.Hide();
            yearLabel.Hide();

            item = comboBox1.SelectedItem.ToString();
            string template = "";

            conn.Open();
            string query = "SELECT [Format] FROM templates WHERE [Company] = @Company";
            SqlCommand command = new SqlCommand(query, conn);
            command.Parameters.AddWithValue("@Company", item);

            using (SqlDataReader reader = command.ExecuteReader())
            {
                if (reader.Read()) // Check if there's data to read
                {
                    template = reader.GetString(0); // Read the template format
                    Console.WriteLine(template);
                }
            }
            conn.Close();
            templateText.Text = template;

            //goes through the template string and decrypts it
            for (int x = 0; x < template.Length; x++)
            {
                if (template[x].ToString().Equals("Y"))
                {
                    if (template[x + 1].ToString().Equals("Y"))
                    {
                        x++;
                        year = true;
                        order.Add("YEAR");
                        yearLabel.Show();
                        yearText.Show();
                    }
                }
                else if (template[x].ToString().Equals("D"))
                {
                    if (template[x + 1].ToString().Equals("D"))
                    {
                        x++;
                        day = true;
                        order.Add("DAY");
                        dayLabel.Show(); 
                        dayText.Show();
                    }
                }
                else if (template[x].ToString().Equals("M"))
                {
                    if (template[x + 1].ToString().Equals("M"))
                    {
                        x++;
                        month = true;
                        order.Add("MONTH");
                        monthLabel.Show();
                        monthText.Show();
                    }
                }
                else if (template[x].ToString().Equals("W"))
                {
                    if (template[x + 1].ToString().Equals("W"))
                    {
                        x++;
                        week = true;
                        order.Add("WEEK");
                        weekLabel.Show();
                        weekText.Show();
                    }
                }
                else if (template[x].ToString().Equals("#"))
                {
                    digitPlace++;
                }
                else
                {
                    order.Add(template[x].ToString());
                }
            }
            
        }
        private void fillComboBox()
        {
            //fills combo box
            conn.Open();
            string query = "SELECT [Company] FROM templates";
            SqlCommand command = new SqlCommand(query, conn);

            using (SqlDataReader reader = command.ExecuteReader())
            {
                while (reader.Read())
                {
                    comboBox1.Items.Add(reader.GetString(0));
                }
            }
            conn.Close();
        }

        private void createButton_Click(object sender, EventArgs e)
        {
            //create serial numba button
            conn.Open();
            string query = "SELECT [QUANTITY] FROM parts WHERE [PART #] = @PartNumber";
            SqlCommand command = new SqlCommand(query, conn);
            command.Parameters.AddWithValue("@PartNumber", partNumber);

            int current = -1;
            using (SqlDataReader reader = command.ExecuteReader())
            {
                while (reader.Read())
                {
                    //gets the current quantity
                    current = reader.GetInt32(0);
                }
            }

            //creates prefix
            string output = "";
            if (item != null)
            {
                for (int x = 0; x < order.Count; x++)
                {
                    if (order[x].Equals("YEAR"))
                    {
                        output += yearText.Text;
                    }
                    else if (order[x].Equals("DAY"))
                    {
                        output += dayText.Text;
                    }
                    else if (order[x].Equals("MONTH"))
                    {
                        output += monthText.Text;
                    }
                    else if (order[x].Equals("WEEK"))
                    {
                        output += weekText.Text;
                    }
                    else
                    {
                        output += order[x];
                    }
                }
                Console.WriteLine(output);
            }

            //adds to shipping table each one in the range with the prefix before it
            for (int x = current; x <= int.Parse(numberText.Text) + current - 1; x++)
            {
                string query5 = "INSERT INTO shipping ([PART #], [PO #], [SERIAL #], [COUNT]) VALUES (@partNumber, @poNumber, @serialNumber, @count)";
                SqlCommand command5 = new SqlCommand(query5, conn);

                command5.Parameters.AddWithValue("@partNumber", partNumber);
                command5.Parameters.AddWithValue("@poNumber", poNumber);
                string temp = x.ToString();
                int z = temp.Length;
                Console.WriteLine(digitPlace - temp.Length);
                if(temp.Length < digitPlace)
                {
                    for(int y = z; y < digitPlace; y++)
                    {
                        Console.WriteLine("TEMP = " + temp);
                        temp = "0" + temp;
                    }
                }
                command5.Parameters.AddWithValue("@serialNumber", output + temp);
                command5.Parameters.AddWithValue("@count", x);
                command5.ExecuteNonQuery();
            }
           
            string query2 = "SELECT [QUANTITY] FROM parts WHERE [PART #] = @PartNumber";
            SqlCommand command2 = new SqlCommand(query2, conn);
            command2.Parameters.AddWithValue("@PartNumber", partNumber);

            using (SqlDataReader reader = command.ExecuteReader())
            {
                while (reader.Read())
                {
                    current = reader.GetInt32(0);
                }
            }
            Console.WriteLine(current);

            //adds into job table for that batch
            string query3 = "INSERT INTO jobs ([JOB #], [PO #], [PART #], [REV], [QTY BUILD], [DATE CODE], [SERIAL RANGE]) " +
                "VALUES (@jobNumber, @poNumber, @partNumber, @revNumber, @qtyNumber, @dateNumber, @serialNumber)";

            SqlCommand command3 = new SqlCommand(query3, conn);

            string query4 = "UPDATE parts SET [QUANTITY] = @quan WHERE [PART #] = @partNumber";
            SqlCommand command4 = new SqlCommand(query4, conn);

            command4.Parameters.AddWithValue("@quan", (int.Parse(numberText.Text) + current).ToString());
            command4.Parameters.AddWithValue("@partNumber", partNumber);
            command4.ExecuteNonQuery();

            command3.Parameters.AddWithValue("@jobNumber", jobNumber);
            command3.Parameters.AddWithValue("@poNumber", poNumber);
            command3.Parameters.AddWithValue("@partNumber", partNumber);
            command3.Parameters.AddWithValue("@revNumber", revNumber);
            command3.Parameters.AddWithValue("@qtyNumber", qtyNumber);
            command3.Parameters.AddWithValue("@dateNumber", output);
            command3.Parameters.AddWithValue("@serialNumber", current.ToString() + " - " + (int.Parse(numberText.Text) + current - 1).ToString());

            command3.ExecuteNonQuery();

            conn.Close();
            loadTable();

            monthText.Clear();
            dayText.Clear();
            yearText.Clear();
            weekText.Clear();
        }
        private void loadTable()
        {
            //TABLE LOADA!
            table.DataSource = null;
            table.Rows.Clear();

            conn.Open();
            string query = "SELECT * FROM jobs WHERE [PART #] = @Part";
            SqlCommand command = new SqlCommand(query, conn);
            command.Parameters.AddWithValue("@Part", partNumber);

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
