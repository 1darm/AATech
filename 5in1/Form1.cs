using System;
using System.IO;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Collections;
using Excel = ClosedXML.Excel;
using ClosedXML.Excel;
using DocumentFormat.OpenXml.Drawing.Charts;
using Microsoft.VisualBasic;
using DocumentFormat.OpenXml.Spreadsheet;
using System.Security.Policy;

namespace _5in1
{
    public partial class Form1 : Form
    {
        string desktopPath;
        Boolean bool1;
        Boolean bool2;
        ArrayList partList;
        ArrayList searchList;
        ArrayList resultList;
        ArrayList altList;
        ArrayList pcmrpList;
        ArrayList descList;
        string[] names;
        String excelFile;
        XLWorkbook wb;
        public Form1()
        {
            desktopPath = Environment.GetFolderPath(Environment.SpecialFolder.Desktop);
            altList = new ArrayList();
            partList = new ArrayList();
            searchList = new ArrayList();
            resultList = new ArrayList();
            pcmrpList = new ArrayList();
            descList = new ArrayList();
            names = new string[] {
                "Capacitors", "Resistors", "Diodes", "ICs", "Transistors & MOSFET",
                "Inductors", "LEDs", "Varistors", "Mechanical", "Connectors", "Crystals", "Wires", "Misc"};
            InitializeComponent();
        }
        //SELECT ALTERNATE BUTTON
        private void button1_Click(object sender, EventArgs e)
        {
            partList.Clear();
            OpenFileDialog openDialog = new OpenFileDialog();
            openDialog.Title = "Select a file";

            if (openDialog.ShowDialog() == DialogResult.OK)
            {
                excelFile = openDialog.FileName;
                wb = new Excel.XLWorkbook(excelFile);
            }

            int totalCount = 0;
            for (int x = 0; x < names.Length; x++)
            {
                int count;
                if (names[x] == "Wires" || names[x] == "Misc")
                {
                    count = 5;
                }
                else
                {
                    count = 6;
                }
                var ws = wb.Worksheets.Worksheet(names[x]);

                while (!ws.Cell(count, 7).IsEmpty())
                {
                    if (ws.Cell(count, 4).IsEmpty())
                    {
                        partList.Add(partList[totalCount - 4].ToString());
                        partList.Add(partList[totalCount - 3].ToString());
                    }
                    else
                    {
                        partList.Add(ws.Cell(count, 3).Value.ToString());
                        partList.Add(ws.Cell(count, 4).Value.ToString());
                    }
                    partList.Add(ws.Cell(count, 7).Value.ToString());
                    partList.Add(ws.Cell(count, 8).Value.ToString());
                    count++;
                    totalCount = totalCount + 4;
                }
            }
        }

        //MANUALLY ADD PART BUTTON
        private void button2_Click(object sender, EventArgs e)
        {
            string[] tempList = {" ", textBox1.Text, textBox2.Text, " ", " " };
            table.Rows.Add(tempList);
            table.Refresh();
        }

        //FIND ALTERNATE BUTTON
        private void button3_Click(object sender, EventArgs e)
        {
            altList.Clear();
            resultList.Clear();
            searchList.Clear();
            foreach (DataGridViewRow row in table.Rows)
            {
                foreach (DataGridViewCell cell in row.Cells)
                {
                    if (cell.Value != null && !cell.Value.ToString().Equals(" "))
                    {
                        searchList.Add(cell.Value.ToString());

                    }
                }
            }

            for (int x = 0; x < searchList.Count; x += 2)
            {
                for (int y = 0; y < partList.Count; y += 4)
                {
                    if (partList[y].ToString().Replace(" ", "").Equals(searchList[x].ToString().Replace(" ", "")) && (partList[y + 1].ToString().Replace(" ", "").ToLower().Equals(searchList[x + 1].ToString().Replace(" ", "").ToLower()) 
                        || partList[y + 1].ToString().Replace(" ", "").ToLower().IndexOf(searchList[x + 1].ToString().Replace(" ", "").ToLower()) != -1 
                        || searchList[x + 1].ToString().ToLower().Replace(" ", "").IndexOf(partList[y + 1].ToString().Replace(" ", "").ToLower()) != -1))
                    {
                        resultList.Add(partList[y + 2].ToString());
                        resultList.Add(partList[y + 3].ToString());

                        if (!(partList[y].ToString().Replace(" ", "").Equals(partList[y + 2].ToString().Replace(" ", ""))) && partList[y + 2].ToString().IndexOf(partList[y].ToString()) != -1)
                        {
                            for (int c = 0; c < partList.Count; c += 4)
                            {
                                if (partList[c].ToString().Replace(" ", "").Equals(partList[y + 2].ToString().Replace(" ", "")) && partList[y + 3].ToString().Replace(" ", "").Equals(partList[c + 1].ToString().Replace(" ", "")))
                                {
                                    resultList.Add(partList[c + 2].ToString());
                                    resultList.Add(partList[c + 3].ToString());
                                }
                            }
                        }
                    }
                }
                resultList.Add("AAA999");
            }
            Boolean bool3 = false;
            int count = 0;
            int secondCount = 0;
            
            for (int x = 0; x < resultList.Count; x++)
            {
                bool3 = false;
                if (resultList[x].ToString().Equals("AAA999"))
                {
                    secondCount = x;
                    bool3 = true;
                }
                if (bool3 == true)
                {
                    for (int a = count; a < secondCount; a+=2)
                    {
                        for (int b = count; b < secondCount; b+=2)
                        {
                            if (resultList[a].ToString().Replace(" ", "").Equals(resultList[b].ToString().Replace(" ", "")) && a != b)
                            {
                                resultList[a] = "";
                                resultList[a + 1] = "";
                            }
                        }
                    }
                    ArrayList tempList = new ArrayList();
                    for(int a = count; a < secondCount; a+=2)
                    {
                        if (!resultList[a].ToString().Equals("") && !resultList[a].ToString().Equals("AAA999"))
                        {
                            tempList.Add(resultList[a]);
                        }
                    }
                    altList.Add(tempList);
                    for(int a = secondCount; a < resultList.Count; a++)
                    {
                        if(a == resultList.Count - 1)
                        {
                            //do nothing
                        }
                        else if (!resultList[a].ToString().Equals("AAA999"))
                        {
                            count = a;
                            x = count + 1;
                            break;
                        }
                    }
                }
            }
            
            int searchCount = 0;
            for(int x = 0; x < resultList.Count; x++)
            {
                if (resultList[x].ToString().Equals("AAA999"))
                {
                    searchCount++;
                }
                else
                {

                    //table(col, row)
                    if (table[4, searchCount].Value == null && !resultList[x].ToString().Equals(""))
                    {
                        table[4, searchCount].Style.WrapMode = DataGridViewTriState.True;
                        table[4, searchCount].Value = resultList[x].ToString().Replace(" ", "") + " " + resultList[x + 1].ToString();
                        x++;
                    }
                    else
                    {
                        if (!resultList[x].ToString().Equals(""))
                        {
                            table[4, searchCount].Style.WrapMode = DataGridViewTriState.True;
                            table[4, searchCount].Value = table[4, searchCount].Value.ToString() + "\n" + resultList[x].ToString().Replace(" ", "") + " " + resultList[x + 1].ToString();
                            x++;
                        }
                    }
                }
            }
            
            for(int x = 0; x < resultList.Count; x++)
            {
                Console.WriteLine(resultList[x].ToString());
            }
            table.AutoResizeColumns(DataGridViewAutoSizeColumnsMode.DisplayedCells);
            table.AutoResizeRows(DataGridViewAutoSizeRowsMode.DisplayedCells);
            table.Refresh();
        }

        //IMPORT BOM BUTTON
        private void button4_Click(object sender, EventArgs e)
        {
            OpenFileDialog openDialog = new OpenFileDialog();
            openDialog.Title = "Select a file";
            IXLWorksheet ws = null;

            if (openDialog.ShowDialog() == DialogResult.OK)
            {
                excelFile = openDialog.FileName;
                wb = new Excel.XLWorkbook(excelFile);
                ws = wb.Worksheets.Worksheet("Sheet1");
            }


            int row = 1;
      
            while (!ws.Cell(row, 1).IsEmpty())
            {
                string[] tempList = {" ", ws.Cell(row, 1).Value.ToString(), ws.Cell(row, 2).Value.ToString()};
                table.Rows.Add(tempList);
                table.Refresh();
                row++;
            }
        }

        //EXPORT EXCEL BUTTON
        private void button5_Click(object sender, EventArgs e)
        {
            desktopPath = Environment.GetFolderPath(Environment.SpecialFolder.Desktop);
            
            if (File.Exists(desktopPath + @"\Book1.xlsx"))
            {
                File.Delete(desktopPath + @"\Book1.xlsx");
            }
            File.Copy(@"\\fs\data\_Dropbox\Kevin M-Dropbox\5in1 Example Files\BOMR w PULL TEMPLATE V2023 COPY.xlsx", desktopPath + @"\Book1.xlsx");

            var workbook = new XLWorkbook(desktopPath + @"\Book1.xlsx");
            var ws = workbook.Worksheets.Worksheet("BOMR");

            
            int col = 7;
            int rows = 5;

            for (int x = 0; x < searchList.Count; x += 2)
            {
                ws.Cell(rows, 11).Value = "ALTERNATE : ";
                ws.Cell(rows, col).Value = (searchList[x + 1].ToString());
                ws.Cell(rows, col + 1).Value = (searchList[x].ToString());
                rows++;
            }

            col = 4;
            rows = 5;
            for (int a = 0; a < resultList.Count; a++)
            {
                if (resultList[a].ToString().Equals("AAA999"))
                {
                    rows = rows + 1;
                }
                else
                {
                    if (!resultList[a].ToString().Equals(""))
                    {
                        ws.Cell(rows, 11).Value = ws.Cell(rows, 11).Value.ToString() + " " + resultList[a].ToString();
                        ws.Cell(rows, 11).Value = ws.Cell(rows, 11).Value.ToString() + " " + resultList[a + 1].ToString() + Environment.NewLine;
                        ws.Column(11).AdjustToContents();
                        ws.Row(rows).AdjustToContents();
                        a++;
                    }
                }
            }
            
            for(int x = 0; x < table.RowCount; x++)
            {
                if(table[0, x].Value != null)
                {
                    ws.Cell(x + 5, 6).Value = table[0, x].Value.ToString();
                }
                if (table[3, x].Value != null)
                {
                    ws.Cell(x + 5, 9).Value = table[3, x].Value.ToString();
                }
            }
            workbook.Save();
        }

        //CROSS REFERENCE PCMRP BUTTON
        private void button6_Click(object sender, EventArgs e)
        {
            /*
            var workbook = new XLWorkbook(desktopPath + @"\Book1.xlsx");
            var ws = workbook.Worksheets.Worksheet("BOMR");
            */
            var pcmrpWB = new XLWorkbook(@"\\fs\data\_Dropbox\Kevin M-Dropbox\5in1 Example Files\PCMRP SEARCHER (OPTIMIZED).xlsm");
            var pcmrpWS = pcmrpWB.Worksheets.Worksheet("PART LIST");
            for (int x = 0; x < altList.Count; x++)
            {
                Boolean found = false;
                ArrayList firstList = (ArrayList)altList[x];
                List<string> tempList = firstList.Cast<string>().ToList();
                tempList.Add(searchList[x * 2].ToString());

                for (int a = 2; a < 26432; a++)
                {
                    List<string> tempListTwo = new List<string>();
                    int col = 3;
                    while (!pcmrpWS.Cell(a, col).IsEmpty())
                    {
                        tempListTwo.Add(pcmrpWS.Cell(a, col).Value.ToString());
                        col++;
                    }
                    if (Enumerable.SequenceEqual(tempList.OrderBy(z => z), tempListTwo.OrderBy(z => z)))
                    {
                        found = true;
                        pcmrpList.Add(pcmrpWS.Cell(a, 1).Value.ToString());
                        descList.Add(pcmrpWS.Cell(a, 2).Value.ToString());
                    }
                }
                if (found == false)
                {
                    pcmrpList.Add(" ");
                    descList.Add(" ");
                }

            }
            //workbook.Save();
            for(int x = 0; x < pcmrpList.Count; x++)
            {
                table[0, x].Value = descList[x];
                table[3, x].Value = pcmrpList[x];
            }
            table.AutoResizeColumns(DataGridViewAutoSizeColumnsMode.DisplayedCells);
            table.AutoResizeRows(DataGridViewAutoSizeRowsMode.DisplayedCells);
            table.Refresh();
        }

        
        ///////////////////////////////////////////////////////////////////////////// DONT TOUCH OR BREAK EVERYTHING! /////////////////////////////////////////////////////////////////

        private void label1_Click(object sender, EventArgs e)
        {

        }
        private void backgroundWorker1_DoWork(object sender, DoWorkEventArgs e)
        {

        }

        private void panel1_Paint(object sender, PaintEventArgs e)
        {

        }

        private void dataGridView1_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {

        }
        private void textBox1_TextChanged(object sender, EventArgs e)
        {

        }

        private void textBox2_TextChanged(object sender, EventArgs e)
        {

        }
    }
}
