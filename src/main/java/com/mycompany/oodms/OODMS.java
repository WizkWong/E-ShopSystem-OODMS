/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.oodms;

import com.mycompany.oodms.customer.Customer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 *
 * @author Wong Chi Jian
 */
public class OODMS extends JFrame{
    static String fileDirectory = "src\\file\\";
    JPanel start;

    public OODMS() {
        start = new TestJPanel();
        this.setContentPane(start);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.pack();
        
    }

    public static List<List<String>> readFile(Object object) {
        // make sure the class name does not appear twice in different package
        String filename = String.format("%s%s.txt", fileDirectory, object.getClass().getSimpleName());
        File file = new File(filename);
        checkFileExist(file);

        FileReader fr;
        BufferedReader br = null;
        List<List<String>> array = new ArrayList<>();
        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                if (line.length() > 0) {
                    array.add(Arrays.stream(line.split(";")).map(String::trim).collect(Collectors.toList()));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeFile(br);
        }
        return array;
    }

    public static void checkFileExist(File file) {
        // create or replace the file
        File directory;
        if ((directory = file.getParentFile()) != null) {
            directory.mkdir();
        }

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void createOrModifyFile(String filename, String content, Boolean append) {
        // create or replace the file
        FileWriter fw;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter(filename, append);
            bw = new BufferedWriter(fw);
            bw.write(content);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeFile(bw);
        }
    }

    public static void closeFile(Closeable file) { // close the file
        if (file != null) {
            try {
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new OODMS();
    }
}
