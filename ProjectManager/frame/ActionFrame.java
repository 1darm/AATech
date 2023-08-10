package com.kevin.frame;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ActionFrame {

    ArrayList<String> deleteList;
    final String directoryPath;
    String projectLine;
    String line;
    String line3;
    String line4;
    JFrame frame;

    public ActionFrame(String projectLine) {
        this.projectLine = projectLine;
        deleteList = new ArrayList<>();
        directoryPath = "////fs//data//_Dropbox//Kevin M-Dropbox//ProjectManagerDatabase//";
        frame = new JFrame();
        frame.setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        JPanel headerPanel = new JPanel();
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        File folder = new File(directoryPath + File.separator + projectLine + File.separator + "Actions");

        try {
            FileReader fileReader = new FileReader(directoryPath + projectLine + File.separator + "Actions" + File.separator + "Actions.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                int x = line.indexOf(":");
                boolean bool;

                if (line.substring(x + 1, line.length()).equals("true")) {
                    bool = true;
                } else {
                    bool = false;
                }
                JCheckBox newCheck = new JCheckBox(line.substring(0, x), bool);
                newCheck.addItemListener(new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        if (e.getStateChange() == ItemEvent.SELECTED) {
                            try {
                                FileReader fileReader = new FileReader(directoryPath + projectLine + File.separator + "Actions" + File.separator + "Actions.txt");
                                BufferedReader bufferedReader = new BufferedReader(fileReader);
                                StringBuffer content = new StringBuffer();

                                while ((line = bufferedReader.readLine()) != null) {
                                    if (line.contains(newCheck.getText())) {
                                        int first = line.indexOf(":");
                                        line = line.replace(line.substring(first + 1, line.length()), "true");
                                        content.append(line).append(System.lineSeparator());
                                    } else {
                                        line = line.replace(line, line);
                                        content.append(line).append(System.lineSeparator());
                                    }
                                }

                                bufferedReader.close();
                                fileReader.close();

                                FileWriter fileWriter = new FileWriter(directoryPath + projectLine + File.separator + "Actions" + File.separator + "Actions.txt");
                                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                                bufferedWriter.write(content.toString());
                                bufferedWriter.close();
                                fileReader.close();
                            } catch (IOException io) {
                                io.printStackTrace();
                            }
                        } else {
                            try {
                                FileReader fileReader = new FileReader(directoryPath + projectLine + File.separator + "Actions" + File.separator + "Actions.txt");
                                BufferedReader bufferedReader = new BufferedReader(fileReader);
                                StringBuffer content = new StringBuffer();

                                while ((line = bufferedReader.readLine()) != null) {
                                    if (line.contains(newCheck.getText())) {
                                        int first = line.indexOf(":");
                                        line = line.replace(line.substring(first + 1, line.length()), "false");
                                        content.append(line).append(System.lineSeparator());
                                    } else {
                                        line = line.replace(line, line);
                                        content.append(line).append(System.lineSeparator());
                                    }
                                }

                                bufferedReader.close();
                                fileReader.close();

                                FileWriter fileWriter = new FileWriter(directoryPath + projectLine + File.separator + "Actions" + File.separator + "Actions.txt");
                                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                                bufferedWriter.write(content.toString());
                                bufferedWriter.close();
                                fileReader.close();
                            } catch (IOException io) {
                                io.printStackTrace();
                            }
                        }
                    }
                });
                contentPanel.add(newCheck);
            }
            fileReader.close();
            bufferedReader.close();
        } catch (IOException ie) {
            ie.printStackTrace();
        }

        JButton addFile = new JButton("ADD ACTION");

        addFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String check = JOptionPane.showInputDialog("What action would you like to add?");
                String dueDate = JOptionPane.showInputDialog("What is the due date?");
                JCheckBox secondCheck = new JCheckBox(check);
                contentPanel.add(secondCheck);
                try {
                    FileWriter fileWriter = new FileWriter(directoryPath + projectLine + File.separator + "Actions" + File.separator + "Actions.txt", true);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                    bufferedWriter.write("(" + dueDate + ") " + check + ":false");
                    bufferedWriter.newLine();
                    bufferedWriter.close();
                    fileWriter.close();
                } catch (IOException io) {
                    io.printStackTrace();
                }
                frame.setVisible(false);
                new ActionFrame(projectLine);
            }
        });

        JButton delFile = new JButton("DELETE ACTION");

        delFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                JFrame delFrame = new JFrame();
                delFrame.setSize(800, 600);
                JPanel delPanel = new JPanel();
                delPanel.setLayout(new BoxLayout(delPanel, BoxLayout.Y_AXIS));

                File file = new File(directoryPath + projectLine + File.separator + "Actions" + File.separator + "Actions.txt");

                try {
                    FileReader fileReader = new FileReader(directoryPath + projectLine + File.separator + "Actions" + File.separator + "Actions.txt");
                    BufferedReader bufferedReader = new BufferedReader(fileReader);

                    while ((line = bufferedReader.readLine()) != null) {
                        JCheckBox j = new JCheckBox(line.substring(0, line.indexOf(":")));

                        j.addItemListener(new ItemListener() {
                            @Override
                            public void itemStateChanged(ItemEvent e) {
                                if (e.getStateChange() == ItemEvent.SELECTED) {
                                    deleteList.add(j.getText());
                                    System.out.println(j.getText());
                                }
                            }
                        });
                        delPanel.add(j);
                    }

                    fileReader.close();
                    bufferedReader.close();
                } catch (IOException ie) {
                    ie.printStackTrace();
                }
                // Iterate over the files and print their names

                JScrollPane scrollPane = new JScrollPane(delPanel);
                scrollPane.setViewportView(delPanel);

                JPanel headerPanel = new JPanel();
                headerPanel.setLayout(new GridLayout(1, 2));

                JButton deleteButton = new JButton("DELETE");

                deleteButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            FileReader fileReader = new FileReader(directoryPath + projectLine + File.separator + "Actions" + File.separator + "Actions.txt");
                            BufferedReader bufferedReader = new BufferedReader(fileReader);
                            StringBuffer content = new StringBuffer();

                            String lineToRemove = null;

                            while ((line = bufferedReader.readLine()) != null) {
                                boolean bool = false;
                                for (int x = 0; x < deleteList.size(); x++) {
                                    if (line.contains(deleteList.get(x))) {
                                        bool = true;
                                    }
                                }
                                if (bool == true) {
                                    line = line.replace(line, "");
                                    content.append(line).append(System.lineSeparator());
                                } else {
                                    line = line.replace(line, line);
                                    content.append(line).append(System.lineSeparator());
                                }
                            }
                            bufferedReader.close();
                            fileReader.close();

                            FileWriter fileWriter = new FileWriter(directoryPath + projectLine + File.separator + "Actions" + File.separator + "Actions.txt");
                            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                            bufferedWriter.write(content.toString());
                            bufferedWriter.close();
                            fileWriter.close();
                        } catch (IOException ie) {
                            ie.printStackTrace();
                        }

                        try {
                            File newFile = new File(directoryPath + projectLine + File.separator + "Actions" + File.separator + "ActionTEMP.txt");
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
                            file = new Scanner(new File(directoryPath + projectLine + File.separator + "Actions" + File.separator + "Actions.txt")); //sourcefile
                            writer = new PrintWriter(new File(directoryPath + projectLine + File.separator + "Actions" + File.separator + "ActionTEMP.txt")); //destinationfile
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
                        File newFile = new File(directoryPath + projectLine + File.separator + "Actions" + File.separator + "Actions.txt");
                        File oldFile = new File(directoryPath + projectLine + File.separator + "Actions" + File.separator + "ActionTEMP.txt");

                        newFile.delete();
                        oldFile.renameTo(newFile);
                        delFrame.setVisible(false);
                        new ActionFrame(projectLine);
                    }
                });

                JButton backButton = new JButton("BACK");

                headerPanel.add(deleteButton);
                headerPanel.add(backButton);

                JPanel mainPanel = new JPanel();
                mainPanel.setLayout(new BorderLayout());
                mainPanel.add(headerPanel, BorderLayout.NORTH);
                mainPanel.add(scrollPane, BorderLayout.CENTER);

                delFrame.add(mainPanel);
                delFrame.setVisible(true);
            }
        });

        JButton editFile = new JButton("EDIT ACTION");

        editFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame editFrame = new JFrame();
                editFrame.setSize(800, 600);
                JPanel editPanel = new JPanel();
                editPanel.setLayout(new BoxLayout(editPanel, BoxLayout.Y_AXIS));

                File file = new File(directoryPath + projectLine + File.separator + "Actions" + File.separator + "Actions.txt");

                try {
                    FileReader fileReader = new FileReader(directoryPath + projectLine + File.separator + "Actions" + File.separator + "Actions.txt");
                    BufferedReader bufferedReader = new BufferedReader(fileReader);

                    while ((line = bufferedReader.readLine()) != null) {
                        String line2 = line;
                        JButton j = new JButton(line.substring(line.indexOf(")") + 1, line.indexOf(":")));
                        JButton j2 = new JButton(line2.substring(1, line2.indexOf(")")));

                        j.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                String check = JOptionPane.showInputDialog("EDIT : " + line2.substring(line2.indexOf(")") + 1, line2.indexOf(":")));
                                
                                try {
                                    FileReader fileReader2 = new FileReader(directoryPath + File.separator + projectLine + File.separator + "Actions" + File.separator + "Actions.txt");
                                    BufferedReader bufferedReader2 = new BufferedReader(fileReader2);
                                    StringBuffer content2 = new StringBuffer();

                                    while ((line3 = bufferedReader2.readLine()) != null) {
                                        System.out.println(line2);
                                        if(line3.equals(line2)) {
                                            if(!check.equals("")) {
                                                line3 = line3.replace(line3.substring(line3.indexOf(")") + 1, line3.indexOf(":")), check);
                                                content2.append(line3).append(System.lineSeparator());
                                            }
                                            else {
                                                line3 = line3.replace(line3, "");
                                                content2.append(line3).append(System.lineSeparator());
                                            }
                                        }
                                        else {
                                            line3 = line3.replace(line3, "");
                                            content2.append(line3).append(System.lineSeparator());
                                        }
                                    }

                                    bufferedReader2.close();
                                    fileReader2.close();

                                    FileWriter fileWriter2 = new FileWriter(directoryPath + projectLine + File.separator + "Actions" + File.separator + "Actions.txt");
                                    BufferedWriter bufferedWriter2 = new BufferedWriter(fileWriter2);

                                    bufferedWriter2.write(content2.toString());
                                    bufferedWriter2.close();
                                    fileWriter2.close();
                                    
                                    frame.setVisible(false);
                                    editFrame.setVisible(false);
                                    new ActionFrame(projectLine);
                                } catch (IOException io) {
                                    io.printStackTrace();
                                }
                            }
                        });
                        j2.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                String dueDate = JOptionPane.showInputDialog("EDIT : " + line2.substring(1, line2.indexOf(")")));
                                
                                try {
                                    FileReader fileReader = new FileReader(directoryPath + File.separator + projectLine + File.separator + "Actions" + File.separator + "Actions.txt");
                                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                                    StringBuffer content = new StringBuffer();

                                    while ((line4 = bufferedReader.readLine()) != null) {
                                        System.out.println(line2);
                                        if(line4.equals(line2)) {
                                            if(!dueDate.equals("")) {
                                                line4 = line4.replace(line4.substring(1, line4.indexOf(")")), dueDate);
                                                content.append(line4).append(System.lineSeparator());
                                            }
                                            else {
                                                line4 = line4.replace(line4, "");
                                                content.append(line4).append(System.lineSeparator());
                                            }
                                        }
                                        else {
                                            line4 = line4.replace(line4, "");
                                            content.append(line4).append(System.lineSeparator());
                                        }
                                    }

                                    bufferedReader.close();
                                    fileReader.close();

                                    FileWriter fileWriter = new FileWriter(directoryPath + projectLine + File.separator + "Actions" + File.separator + "Actions.txt");
                                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                                    bufferedWriter.write(content.toString());
                                    bufferedWriter.close();
                                    fileWriter.close();
                                    
                                    frame.setVisible(false);
                                    editFrame.setVisible(false);
                                    new ActionFrame(projectLine);
                                } catch (IOException io) {
                                    io.printStackTrace();
                                }
                            }
                        });
                        editPanel.setLayout(new GridLayout(0, 2));
                        editPanel.add(j);
                        editPanel.add(j2);
                    }

                    fileReader.close();
                    bufferedReader.close();
                } catch (IOException ie) {
                    ie.printStackTrace();
                }
                JScrollPane scrollPane = new JScrollPane(editPanel);
                scrollPane.setViewportView(editPanel);
                editFrame.add(editPanel);
                editFrame.setVisible(true);
            }
        });
        headerPanel.setLayout(new GridLayout(1, 3));
        headerPanel.add(editFile);
        headerPanel.add(addFile);
        headerPanel.add(delFile);

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setViewportView(contentPanel);

        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        frame.setSize(800, 600);
        frame.add(mainPanel);
        frame.setVisible(true);

    }
}
