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
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class EmailFrame {

    ArrayList<String> deleteList;
    final String directoryPath;
    String projectLine;
    JFrame frame;

    public EmailFrame(String projectLine) {
        this.projectLine = projectLine;
        deleteList = new ArrayList<>();
        directoryPath = "////fs//data//_Dropbox//Kevin M-Dropbox//ProjectManagerDatabase//";
        frame = new JFrame();
        frame.setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        JPanel headerPanel = new JPanel();
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        File folder = new File(directoryPath + File.separator + projectLine + File.separator + "Emails");

        if (folder.exists() && folder.isDirectory()) {
            // Get the list of files in the folder
            File[] files = folder.listFiles();

            // Iterate over the files and print their names
            for (File file : files) {
                if (file.isFile()) {
                    String fileName = file.getName();
                    String fileNameWithoutExtension = fileName.substring(0, fileName.lastIndexOf('.'));

                    JButton newButton = new JButton(fileNameWithoutExtension);

                    newButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (Desktop.isDesktopSupported()) {
                                Desktop desktop = Desktop.getDesktop();

                                // Check if the file exists
                                if (file.exists()) {
                                    try {
                                        // Open the file with the default associated application
                                        desktop.open(file);
                                    } catch (IOException b) {
                                        b.printStackTrace();
                                    }
                                } else {
                                    System.out.println("File does not exist.");
                                }
                            } else {
                                System.out.println("Desktop is not supported on this platform.");
                            }
                        }
                    });
                    contentPanel.add(newButton);

                }
            }
        } else {
            System.out.println("Folder does not exist or is not a directory.");
        }

        JButton addFile = new JButton("ADD FILE");

        addFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();

                int response = fileChooser.showOpenDialog(null);

                if (response == JFileChooser.APPROVE_OPTION) {
                    File file = new File(fileChooser.getSelectedFile().getAbsolutePath());

                    try {
                        InputStream inputStream = new FileInputStream(file);
                        OutputStream outputStream = new FileOutputStream(directoryPath + File.separator + projectLine + File.separator + "Emails" + File.separator + file.getName());

                        // Buffer size for reading and writing data
                        byte[] buffer = new byte[1024];
                        int bytesRead;

                        // Read from the source file and write to the destination file
                        while ((bytesRead = inputStream.read(buffer)) != -1) {
                            outputStream.write(buffer, 0, bytesRead);
                        }

                        System.out.println("File copied successfully.");
                        
                        inputStream.close();
                        outputStream.close();
                    } catch (IOException a) {
                        a.printStackTrace();
                    }
                }
                frame.setVisible(false);
                new EmailFrame(projectLine);
            }
        });

        JButton delFile = new JButton("DELETE FILE");

        delFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                JFrame delFrame = new JFrame();
                delFrame.setSize(800, 600);
                JPanel delPanel = new JPanel();
                delPanel.setLayout(new BoxLayout(delPanel, BoxLayout.Y_AXIS));

                File file = new File(directoryPath + projectLine + File.separator + "Emails");

                if (file.exists() && file.isDirectory()) {
                    // Get the list of files in the folder
                    File[] files = folder.listFiles();

                    // Iterate over the files and print their names
                    for (File f1 : files) {
                        if (f1.isFile()) {
                            String fileName = f1.getName();
                            String fileNameWithoutExtension = fileName.substring(0, fileName.lastIndexOf('.'));

                            JCheckBox j1 = new JCheckBox(fileNameWithoutExtension);

                            j1.addItemListener(new ItemListener() {
                                @Override
                                public void itemStateChanged(ItemEvent e) {
                                    if (e.getStateChange() == ItemEvent.SELECTED) {
                                        deleteList.add(fileName);
                                    }
                                }
                            });
                            delPanel.add(j1);

                        }
                    }
                } else {
                    System.out.println("Folder does not exist or is not a directory.");
                    System.out.println(file);
                }

                JScrollPane scrollPane = new JScrollPane(delPanel);
                scrollPane.setViewportView(delPanel);

                JPanel headerPanel = new JPanel();
                headerPanel.setLayout(new GridLayout(1, 2));

                JButton deleteButton = new JButton("DELETE");

                deleteButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        for(int x = 0; x < deleteList.size(); x++) {
                            File file = new File(directoryPath + projectLine + File.separator + "Emails" + File.separator + deleteList.get(x));
                            System.out.println(file);
                            file.delete();
                            delFrame.setVisible(false);
                            new EmailFrame(projectLine); 
                        }
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

        headerPanel.setLayout(new GridLayout(1, 2));
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
