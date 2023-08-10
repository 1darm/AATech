package com.kevin.frame;

import com.kevin.main.Main;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
import javax.swing.*;

public class Form_2 {

    Main m1;
    JFrame frame;
    JPanel panel;
    JPanel headerPanel;
    JPanel mainPanel;
    JScrollPane scrollPane;
    final String projectListPath;
    final String directoryPath;
    String line;
    ArrayList<String> deleteList;
    ArrayList<String> keepList;
    ArrayList<String> folders;

    public Form_2(Main m1) {
        this.m1 = m1;
        frame = new JFrame("Delete Project");
        panel = new JPanel();
        deleteList = new ArrayList<String>();
        keepList = new ArrayList<String>();
        projectListPath = "////fs//data//_Dropbox//Kevin M-Dropbox//ProjectManagerDatabase//Projects.txt";
        directoryPath = "////fs//data//_Dropbox//Kevin M-Dropbox//ProjectManagerDatabase//";
        folders = new ArrayList<String>(
                Arrays.asList("Overview", "Requirements", "Feedback", "Actions", "Emails"));

        frame.setLayout(new BorderLayout());
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        try {
            FileReader fileReader = new FileReader(projectListPath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                final String projectLine = line;
                int first = projectLine.indexOf(":");
                int second = projectLine.indexOf(":", first + 1);
                JCheckBox c1 = new JCheckBox(projectLine.substring(first + 1, second));
                c1.addItemListener(new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        if (e.getStateChange() == ItemEvent.SELECTED) {
                            deleteList.add(projectLine);
                        } else {
                            deleteList.remove(projectLine);
                        }
                    }

                });
                panel.add(c1);

            }
            bufferedReader.close();
            fileReader.close();
        } catch (IOException ie) {
            ie.printStackTrace();
        }

        scrollPane = new JScrollPane(panel);
        scrollPane.setViewportView(panel);

        headerPanel = new JPanel();
        headerPanel.setLayout(new GridLayout(0, 2));
        JButton deleteButton = new JButton("DELETE");

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);

                try {
                    FileReader fileReader = new FileReader(projectListPath);
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    StringBuffer content = new StringBuffer();

                    while ((line = bufferedReader.readLine()) != null) {
                        boolean bool = true;
                        for (int x = 0; x < deleteList.size(); x++) {
                            if (line.equals(deleteList.get(x))) {
                                bool = false;
                            }
                        }
                        if (bool == false) {
                            line = line.replace(line, "");
                            content.append(line).append(System.lineSeparator());
                        } else {
                            line = line.replace(line, line);
                            content.append(line).append(System.lineSeparator());
                        }
                    }

                    bufferedReader.close();
                    fileReader.close();

                    FileWriter fileWriter = new FileWriter(projectListPath);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                    bufferedWriter.write(content.toString());
                    bufferedWriter.close();
                    fileWriter.close();

                } catch (IOException io) {
                    io.printStackTrace();
                }

                try {
                    File newFile = new File(directoryPath + "projectlisttemp.txt");
                    if (newFile.createNewFile()) {
                        System.out.println("New action file created");
                    } else {
                        System.out.println("Big boo boo");
                    }
                } catch (IOException io) {
                    io.printStackTrace();
                }

                Scanner file;
                PrintWriter writer;
                try {
                    file = new Scanner(new File(projectListPath)); //sourcefile
                    writer = new PrintWriter(new File(directoryPath + "projectlisttemp.txt")); //destinationfile
                    while (file.hasNext()) {
                        String line = file.nextLine();
                        if (!line.isEmpty()) {
                            writer.write(line);
                            writer.write("\n");
                        }
                    }
                    file.close();
                    writer.close();
                } catch (IOException io) {
                    io.printStackTrace();
                }
                File newFile = new File(projectListPath);
                File oldFile = new File(directoryPath + "projectlisttemp.txt");

                newFile.delete();
                oldFile.renameTo(newFile);

                m1.setVisible(false);
                Main m2 = new Main();
                m2.setVisible(true);
            }
        });

        JButton backButton = new JButton("BACK");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                //new ProjectListFrame();
            }
        });

        headerPanel.add(backButton);
        headerPanel.add(deleteButton);
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        frame.add(mainPanel);
        frame.setSize(800, 600);
        frame.setVisible(true);
    }

    public static void deleteFile(File element) {
        if (element.isDirectory()) {
            for (File sub : element.listFiles()) {
                deleteFile(sub);
            }
        }
        element.delete();
    }
}
