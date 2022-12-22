/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.oodms;



import javax.swing.JFrame;
import javax.swing.JPanel;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * @author Wong Chi Jian
 */
public class OODMS extends JFrame{
    public static final String fileDirectory = "src\\file\\";
    JPanel start;

    public OODMS() {
        start = new TestJPanel();
        this.setContentPane(start);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.pack();
        
    }

    public static List<List<String>> readFile(String name) {
        // make sure the class name does not appear twice in different package
        String filename = String.format("%s%s.txt", fileDirectory, name);
        File file = new File(filename);
        if (!file.exists()) {
            createFile(file);
        }
        FileReader fr;
        BufferedReader br = null;
        List<List<String>> array = new ArrayList<>();
        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                if (line.length() > 0) {
                    array.add(List.of(line.split(";")));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeFile(br);
        }
        return array;
    }

    public static void createFile(File file) {
        // create or replace the file
        File directory = file.getParentFile();
        if (directory != null) {
            if (!(directory).exists()) {
                if (!directory.mkdir()) {
                    throw new RuntimeException(String.format("Fail to create a new file \"%s\"", file.getName()));
                }
            }
        }

        try {
            if (!file.createNewFile()) {
                throw new RuntimeException(String.format("Fail to create a new file \"%s\"", file.getName()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void modifyFile(String filename, String content, Boolean append) {
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

    public static List<List<String>> findSpecificData(String name, int column, String matchData) {
        String filename = String.format("%s%s.txt", fileDirectory, name);
        File file = new File(filename);
        List<List<String>> array = new ArrayList<>();
        if (!file.exists()) {
            System.out.printf("Cannot find the specific file call \"%s\"", filename);
            return array;
        }
        FileReader fr;
        BufferedReader br = null;
        String[] tempArray;
        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                if (line.length() > 0) {
                    tempArray = line.split(";");
                    if (matchData.equals(tempArray[column])) {
                        array.add(List.of(tempArray));
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeFile(br);
        }
        return array;
    }

    public static void main(String[] args) {
        new OODMS();
    }
}
