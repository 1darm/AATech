
package com.kevin.form;

import com.kevin.main.Main;
import com.kevin.swing.ScrollBar;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import com.kevin.model.Model_Card;
import com.kevin.model.StatusType;
import java.awt.Color;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Form_Home extends javax.swing.JPanel {
    String directoryPath;
    Main m1;
    String line;
    public int which;
    ArrayList<String> statusStringArray;
    ArrayList<StatusType> statusArray;
    /**
     * Creates new form Form_Home
     */
    public Form_Home(Main m1) {
        this.m1 = m1;
        directoryPath = "////fs//data//_Dropbox//Kevin M-Dropbox//ProjectManagerDatabase//Projects.txt";
        statusStringArray = new ArrayList<String>(Arrays.asList("StatusType.APPROVED", "StatusType.PENDING", "StatusType.REJECTED"));
        statusArray = new ArrayList<StatusType>(Arrays.asList(StatusType.APPROVED, StatusType.PENDING, StatusType.REJECTED));
        
        initComponents();
        infoScroll.setVerticalScrollBar(new ScrollBar());
        spTable.setVerticalScrollBar(new ScrollBar());
        spTable.getVerticalScrollBar().setBackground(Color.WHITE);
        spTable.getViewport().setBackground(Color.WHITE);
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        spTable.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                which = row;
                System.out.println(table.getValueAt(row, 1));
                //infoArea.setText(table.getValueAt(row, 1).toString());
                if(table.getSelectedColumn() == 4) {
                    try {
                        FileReader fileReader = new FileReader(directoryPath);
                        BufferedReader bufferedReader = new BufferedReader(fileReader);
                        StringBuffer content = new StringBuffer();
                        while((line = bufferedReader.readLine()) != null) {
                            int first = line.indexOf(":");
                            int second = line.indexOf(":", first + 1);
                            
                            System.out.println("FIRST : " + first);
                            System.out.println("SECOND : " + second);
                            System.out.println(line.substring(first + 1, second));
                            
                            if(table.getValueAt(table.getSelectedRow(), 1).toString().equals(line.substring(first + 1, second))) {
                                int third = line.indexOf(":", second + 1);
                                int fourth = line.indexOf(":", third + 1);
                                int fifth = line.indexOf(":", fourth + 1);
                                
                                int firstOne = 0;
                                int secondOne = 0;
                                for(int y = 0; y < statusStringArray.size(); y++) {
                                    if(line.substring(fourth + 1, fifth).equals(statusStringArray.get(y))) {
                                        if(y == 2) {
                                            firstOne = 2;
                                            secondOne = 0;
                                        }
                                        else {
                                            firstOne = y;
                                            secondOne = y + 1;
                                        }
                                    }
                                }
                                line = line.replace(line.substring(fourth + 1, fifth), statusStringArray.get(secondOne));
                                content.append(line).append(System.lineSeparator());
                            }
                            else {
                                line = line.replace(line, line);
                                content.append(line).append(System.lineSeparator());
                            }
                            
                        }
                        fileReader.close();
                        bufferedReader.close();
                        
                        FileWriter fileWriter = new FileWriter(directoryPath);
                        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                        
                        bufferedWriter.write(content.toString());
                        bufferedWriter.close();
                        fileWriter.close();
                        
                    }
                    catch (IOException io) {
                        io.printStackTrace();
                    }
                    m1.setVisible(false);
                    Main m2 = new Main();
                    m2.setVisible(true);
                }
                
            }
        });
        try {
            FileReader fileReader = new FileReader(directoryPath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            
            while ((line = bufferedReader.readLine()) != null) {
                int first = 0;
                ArrayList<String> tempList = new ArrayList<String>();
                for (int x = 0; x < line.length(); x++) {
                    if (line.charAt(x) == (':')) {
                        int second = x;
                        tempList.add(line.substring(first, second));
                        first = second + 1;
                    }
                }
                StatusType st = null;
                for (int x = 0; x < statusStringArray.size(); x++) {
                    if (tempList.get(tempList.size() - 1).equals(statusStringArray.get(x))) {
                        st = statusArray.get(x);
                    }
                }
                table.addRow(new Object[] {tempList.get(0), tempList.get(1), tempList.get(2), tempList.get(3), st});
            }
            bufferedReader.close();
            fileReader.close();
        }                                                                                                                                                                                                                           
        catch (IOException ie){
            ie.printStackTrace();
        }
        
        
    }
    
    public void setInfo(String text) {
        infoArea.setText("asasasa");
    }
    public String getTextT() {
        return infoArea.getText();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        panel = new javax.swing.JLayeredPane();
        infoScroll = new javax.swing.JScrollPane();
        infoArea = new javax.swing.JTextArea();
        panelBorder1 = new com.kevin.swing.PanelBorder();
        spTable = new javax.swing.JScrollPane();
        table = new com.kevin.swing.Table();

        jMenuItem1.setText("jMenuItem1");

        panel.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        infoArea.setColumns(20);
        infoArea.setLineWrap(true);
        infoArea.setRows(5);
        infoArea.setBorder(null);
        infoScroll.setViewportView(infoArea);
        infoArea.getAccessibleContext().setAccessibleDescription("");

        panel.add(infoScroll);

        panelBorder1.setBackground(new java.awt.Color(255, 255, 255));

        spTable.setBorder(null);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "User", "Project", "Start Date", "End Date", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        spTable.setViewportView(table);

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addComponent(spTable, javax.swing.GroupLayout.DEFAULT_SIZE, 913, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(spTable, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel)
                    .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked

    }//GEN-LAST:event_tableMouseClicked

    public int getWhich() {
        return which;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTextArea infoArea;
    public javax.swing.JScrollPane infoScroll;
    public javax.swing.JMenuItem jMenuItem1;
    public javax.swing.JLayeredPane panel;
    public com.kevin.swing.PanelBorder panelBorder1;
    public javax.swing.JScrollPane spTable;
    public com.kevin.swing.Table table;
    // End of variables declaration//GEN-END:variables
}
