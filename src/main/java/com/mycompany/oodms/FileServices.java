package com.mycompany.oodms;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileServices {
    public static final String fileDirectory = "src\\file\\";

    public static List<List<String>> readFile(String filename) {
        String textFile = String.format("%s%s.txt", fileDirectory, filename);
        File file = new File(textFile);
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
                    array.add(new ArrayList<>(Arrays.asList(line.split(";"))));
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
        // replace the file
        String textFile = String.format("%s%s.txt", fileDirectory, filename);
        File file = new File(textFile);
        if (!file.exists()) {
            createFile(file);
        }
        FileWriter fw;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter(textFile, append);
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

    public static List<List<String>> findSpecificData(String filename, int column, String matchData) {
        String textFile = String.format("%s%s.txt", fileDirectory, filename);
        File file = new File(textFile);
        List<List<String>> array = new ArrayList<>();
        if (!file.exists()) {
            System.out.printf("Cannot find the specific file call \"%s\"", textFile);
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
                        array.add(new ArrayList<>(Arrays.asList(line.split(";"))));
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

    public static void updateRowsById(String filename, List<List<String>> newData) {
        // NEVER USE THIS METHOD WHEN THERE ARE MULTIPLE SAME ID

        List<String> check = newData.stream().map(array -> array.get(0)).toList();
        if (duplicationExist(check)) {
            System.out.println("Cannot update the file cause duplication ID exist");
            return;
        }

        List<List<String>> array = readFile(filename);
        String content = "";
        int i = 0;
        for (List<String> oldRow : array) {
            for (List<String> newRow : newData) {
                if (oldRow.get(0).equals(newRow.get(0))) {
                    newData.remove(array.set(i, newRow));
                    break;
                }
            }
            content += String.join(";", array.get(i)) + "\n";
            i++;
        }
        modifyFile(filename, content, false);

        if (!newData.isEmpty()) {
            System.out.printf("These are the data that is not found in %s", filename);
            System.out.println(newData);
        }
    }

    public static boolean duplicationExist(List<String> array) {
        for (String a : array) {
            for (String b : array) {
                if (a.equals(b)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void deleteRowsById(String filename, List<String> arrayId) {
        List<List<String>> array = readFile(filename);
        String content = "";
        boolean remain;
        int i = 0;
        for (List<String> row : array) {
            remain = true;
            for (String id : arrayId) {
                if (row.get(0).equals(id)) {
                    arrayId.remove(id);
                    remain = false;
                    break;
                }
            }
            if (remain) {
                content += String.join(";", array.get(i)) + "\n";
            }
            i++;
        }
        modifyFile(filename, content, false);

        if (!arrayId.isEmpty()) {
            System.out.printf("These are the data that is not found in %s", filename);
            System.out.println(arrayId);
        }
    }
}
