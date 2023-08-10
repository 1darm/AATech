
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.kevin.main;

import com.kevin.event.EventMenuSelected;
import com.kevin.frame.EmailFrame;
import com.kevin.form.Form_Home;
import com.kevin.form.Form_1;
import com.kevin.frame.Form_2;
import com.kevin.form.Form_3;
import com.kevin.frame.ActionFrame;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;
import java.io.IOException;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import javax.swing.JComponent;
import javax.swing.JScrollBar;

/**
 *
 * @author engineeringuser
 */
public class Main extends javax.swing.JFrame {

    int whichOne;
    int need;
    String directoryPath;
    ArrayList<String> folders;

    /**
     * Creates new form Main
     */
    public Main() {
        whichOne = -1;
        folders = new ArrayList<String>(Arrays.asList("Overview", "Requirements", "Feedback", "Actions", "Emails"));
        directoryPath = "////fs//data//_Dropbox//Kevin M-Dropbox//ProjectManagerDatabase";
        System.out.println("FOWKDWD");
        initComponents();
        setBackground(new Color(0, 0, 0, 0));
        menu.initMoving(Main.this);
        Form_Home fh = new Form_Home(this);
        setForm(fh);
        sp.setVerticalScrollBar(new JScrollBar());
        menu.addEventMenuSelected(new EventMenuSelected() {
            @Override
            public void selected(int index) {
                if (index == 0) {
                    setForm(fh);
                } else if (index == 1) {
                    setForm(new Form_1(Main.this));
                } else if (index == 2) {
                    new Form_2(Main.this);
                }
                if (fh.table.getSelectedRow() != -1) {
                    if (index == 4) {
                        setForm(fh);
                        fh.infoArea.setVisible(true);
                        whichOne = 0;
                        try {
                            fh.infoArea.read(new BufferedReader(new FileReader(directoryPath + File.separator + fh.table.getValueAt(fh.table.getSelectedRow(), 1) + File.separator + "Overview" + File.separator + "Overview.txt")), null);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    } else if (index == 5) {
                        setForm(fh);
                        fh.infoArea.setVisible(true);
                        whichOne = 1;
                        try {
                            fh.infoArea.read(new BufferedReader(new FileReader(directoryPath + File.separator + fh.table.getValueAt(fh.table.getSelectedRow(), 1) + File.separator + "Requirements" + File.separator + "Requirements.txt")), null);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    } else if (index == 6) {
                        setForm(fh);
                        fh.infoArea.setVisible(true);
                        whichOne = 2;
                        try {
                            fh.infoArea.read(new BufferedReader(new FileReader(directoryPath + File.separator + fh.table.getValueAt(fh.table.getSelectedRow(), 1) + File.separator + "Feedback" + File.separator + "Feedback.txt")), null);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    } else if (index == 7) {
                        setForm(fh);
                        fh.infoArea.setVisible(false);
                        whichOne = 3;
                        new ActionFrame(fh.table.getValueAt(fh.table.getSelectedRow(), 1).toString());
                    } else if (index == 8) {
                        setForm(fh);
                        fh.infoArea.setVisible(false);
                        whichOne = 4; 
                        new EmailFrame(fh.table.getValueAt(fh.table.getSelectedRow(), 1).toString());
   
                    } else if (index == 10) {
                        if(whichOne != 3 && whichOne != 4) {
                            setForm(fh);
                            fh.infoArea.setVisible(true);
                            fh.infoArea.setEditable(true);
                        }
                    } else if (index == 11) {
                        if(whichOne != 3 && whichOne != 4) {
                            setForm(fh); 
                            fh.infoArea.setEditable(false);
                            File file = new File(directoryPath + File.separator + fh.table.getValueAt(fh.table.getSelectedRow(), 1) + File.separator + folders.get(whichOne) + File.separator + folders.get(whichOne) + ".txt");
                        
                            try {
                                PrintWriter printWriter = new PrintWriter(file);
                                printWriter.print("");
                                printWriter.close();
                            }
                            catch(IOException io) {
                                io.printStackTrace();
                            }
                        
                            try {
                                BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
                                fh.infoArea.write(writer);
                                writer.close();
                            } catch (IOException e1) {
                               e1.printStackTrace();
                            }
                        }
                    }
                } else {
                    if (index == 4) {
                        setForm(fh);
                        fh.infoArea.setVisible(true);
                        whichOne = 0;
                    } else if (index == 5) {
                        setForm(fh);
                        fh.infoArea.setVisible(true);
                        whichOne = 1;
                    } else if (index == 6) {
                        setForm(fh);
                        fh.infoArea.setVisible(true);
                        whichOne = 2;
                    } else if (index == 7) {
                        setForm(fh);
                        fh.infoArea.setVisible(false);
                        whichOne = 3;
                    } else if (index == 8) {
                        setForm(fh);
                        fh.infoArea.setVisible(false);
                        whichOne = 4;
                    } else if (index == 10) {
                        setForm(fh);
                        fh.infoArea.setVisible(true);
                        fh.infoArea.setEditable(true);
                    } else if (index == 11) {
                        setForm(fh);
                        fh.infoArea.setVisible(true);
                        fh.infoArea.setEditable(false);
                    }
                }
            }
        });
        //setForm(new Form_Home());

        fh.table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (fh.table.getSelectedRow() != -1 && whichOne != -1) {
                    try {
                        fh.infoArea.read(new BufferedReader(new FileReader(directoryPath + File.separator + fh.table.getValueAt(fh.table.getSelectedRow(), 1) + File.separator + folders.get(whichOne) + File.separator + folders.get(whichOne) + ".txt")), null);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
    }

    public void setForm(JComponent com) {
        mainPanel.removeAll();
        mainPanel.add(com);
        mainPanel.repaint();
        mainPanel.revalidate();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        model_Menu1 = new com.kevin.model.Model_Menu();
        panelBorder1 = new com.kevin.swing.PanelBorder();
        menu = new com.kevin.component.Menu();
        header2 = new com.kevin.component.Header();
        sp = new javax.swing.JScrollPane();
        mainPanel = new javax.swing.JPanel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        header2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        sp.setBorder(null);
        sp.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        mainPanel.setOpaque(false);
        mainPanel.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(header2, javax.swing.GroupLayout.DEFAULT_SIZE, 934, Short.MAX_VALUE)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 922, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0)
                .addComponent(sp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addComponent(header2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGap(0, 238, Short.MAX_VALUE)
                        .addComponent(sp, javax.swing.GroupLayout.PREFERRED_SIZE, 479, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
            .addComponent(menu, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        String projectListPath = "////fs//data//_Dropbox//Kevin M-Dropbox//ProjectManagerDatabase//Projects.txt";
        File file = new File(projectListPath);

        if (!(file.exists())) {
            try {
                boolean result = file.createNewFile();

                if (result) {
                    System.out.println("Projects.txt was created successfully");
                } else {
                    System.out.println("Projects.txt took a shit on itself");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Projects.txt exists already");
        }
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.kevin.component.Header header2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JPanel mainPanel;
    private com.kevin.component.Menu menu;
    private com.kevin.model.Model_Menu model_Menu1;
    private com.kevin.swing.PanelBorder panelBorder1;
    private javax.swing.JScrollPane sp;
    // End of variables declaration//GEN-END:variables

}
