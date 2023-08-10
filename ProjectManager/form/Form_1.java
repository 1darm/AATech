/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.kevin.form;

import java.awt.Color;
import com.kevin.main.Main;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;

/**
 *
 * @author engineeringuser
 */
public class Form_1 extends javax.swing.JPanel {

    Main m1;
    final String projectListPath;
    final String directoryPath;
    String line;
    ArrayList<String> folders;

    /**
     * Creates new form Form_1
     */
    public Form_1(Main m1) {
        this.m1 = m1;
        folders = new ArrayList<>(Arrays.asList("Overview", "Requirements", "Feedback", "Actions", "Emails"));
        projectListPath = "////fs//data//_Dropbox//Kevin M-Dropbox//ProjectManagerDatabase//Projects.txt";
        directoryPath = "////fs//data//_Dropbox//Kevin M-Dropbox//ProjectManagerDatabase";
        initComponents();
        setOpaque(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        projectNameText = new javax.swing.JTextField();
        projectStartText = new javax.swing.JTextField();
        projectDueText = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        submitButton = new javax.swing.JButton();
        projectUserText = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        statusList = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        projectNameText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                projectNameTextActionPerformed(evt);
            }
        });

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Project Name :");

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Project Start Date :");

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Project Due Date :");

        submitButton.setForeground(new java.awt.Color(255, 255, 255));
        submitButton.setText("SUBMIT");
        submitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitButtonActionPerformed(evt);
            }
        });

        projectUserText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                projectUserTextActionPerformed(evt);
            }
        });

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("User :");

        statusList.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "APPROVED", "PENDING", "REJECTED" }));
        statusList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statusListActionPerformed(evt);
            }
        });

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Status");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(200, 200, 200)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(78, 78, 78)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(submitButton)
                                .addGap(21, 21, 21)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(projectDueText, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                            .addComponent(projectStartText, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(projectNameText, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(projectUserText, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                        .addGap(149, 149, 149))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(projectUserText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(projectNameText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(projectStartText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(projectDueText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(15, 15, 15)
                .addComponent(submitButton)
                .addContainerGap(85, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void projectNameTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_projectNameTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_projectNameTextActionPerformed

    private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitButtonActionPerformed
        boolean nameExist = false;

        if (projectNameText.getText() != null && !projectNameText.getText().isEmpty() && !projectDueText.getText().isEmpty() && projectDueText.getText() != null && !projectStartText.getText().isEmpty() && projectStartText.getText() != null) {
            String folderPath = projectNameText.getText();
            try {
                FileReader fileReader = new FileReader(projectListPath);
                BufferedReader bufferedReader = new BufferedReader(fileReader);

                while ((line = bufferedReader.readLine()) != null) {
                    int first = 0;
                    while (line.charAt(first) != ':') {
                        first++;
                    }
                    int second = first;
                    while (line.charAt(second + 1) != ':') {
                        second++;
                    }
                    String projectName = line.substring(first + 1, second + 1);
                    if (projectName.equals(projectNameText.getText())) {
                        nameExist = true;
                        JOptionPane.showMessageDialog(this, "THIS PROJECT NAME ALREADY EXISTS", "Alert", JOptionPane.WARNING_MESSAGE);
                    }
                }
                fileReader.close();
                bufferedReader.close();
            } catch (IOException ie) {
                ie.printStackTrace();
            }

            if (nameExist == false) {
                File folder = new File(directoryPath + File.separator + folderPath);
                folder.mkdir();

                for (int x = 0; x < folders.size(); x++) { // for loop that runs through the arraylist and makes the
                    File newFile = new File(
                            directoryPath + File.separator + folderPath + File.separator + folders.get(x));
                    newFile.mkdir();

                    File file = new File(directoryPath + File.separator + folderPath + File.separator + folders.get(x) + File.separator + folders.get(x) + ".txt");
                    if (!(file.exists()) && !folders.get(x).equals("Emails")) {
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
                }

                try { // writes it inside the projects.txt file, used so when it opens again it uses
                    // that file to display previously made projects
                    FileWriter fileWriter = new FileWriter(projectListPath, true);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                    bufferedWriter.write(projectUserText.getText() + ":" + projectNameText.getText() + ":" + projectStartText.getText() + ":" + projectDueText.getText() + ":" + "StatusType." + statusList.getSelectedItem() + ":");
                    bufferedWriter.newLine();
                    bufferedWriter.close();
                    fileWriter.close();
                    JOptionPane.showMessageDialog(this, "Project added succesfully", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
                    m1.setVisible(false);
                    Main m2 = new Main();
                    m2.setVisible(true);
                } catch (IOException a) {
                    a.printStackTrace();
                }
            }
        }
    }//GEN-LAST:event_submitButtonActionPerformed

    private void projectUserTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_projectUserTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_projectUserTextActionPerformed

    private void statusListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statusListActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_statusListActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField projectDueText;
    private javax.swing.JTextField projectNameText;
    private javax.swing.JTextField projectStartText;
    private javax.swing.JTextField projectUserText;
    private javax.swing.JComboBox<String> statusList;
    private javax.swing.JButton submitButton;
    // End of variables declaration//GEN-END:variables
}