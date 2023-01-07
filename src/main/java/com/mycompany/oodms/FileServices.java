package com.mycompany.oodms;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class FileServices {
    public static final String fileDirectory = "src\\file\\";

    public static void createFile(String filename) {
        String textFile = String.format("%s%s.txt", fileDirectory, filename);
        File file = new File(textFile);
        if (file.exists()) {
            return;
        }
        File directory = file.getParentFile();
        // if directory exist in filepath
        if (directory != null) {
            // if directory is not exist
            if (!(directory).exists()) {
                // create a directory of the file
                if (!directory.mkdir()) {
                    throw new RuntimeException(String.format("Fail to create a new file \"%s\"", file.getName()));
                }
            }
        }

        try {
            // create a new file
            if (!file.createNewFile()) {
                throw new RuntimeException(String.format("Fail to create a new file \"%s\"", file.getName()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<List<String>> readFile(String filename) {
        String textFile = String.format("%s%s.txt", fileDirectory, filename);
        File file = new File(textFile);
        List<List<String>> array = new ArrayList<>();
        if (!file.exists()) {
            System.out.printf("Cannot find the \"%s\" file\n", textFile);
            return array;
        }
        FileReader fr;
        BufferedReader br = null;
        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                if (line.length() > 0) {
                    // split ";" into array then add into array
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

    public static void modifyFile(String filename, String content, Boolean append) {
        // replace the file
        String textFile = String.format("%s%s.txt", fileDirectory, filename);
        File file = new File(textFile);
        if (!file.exists()) {
            System.out.printf("Cannot find the \"%s\" file\n", textFile);
            return;
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

    public static void closeFile(Closeable file) {
        // close the file
        if (file != null) {
            try {
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static List<List<String>> getMultipleSpecificData(String filename, int column, String matchData) {
        // get all the match data
        String textFile = String.format("%s%s.txt", fileDirectory, filename);
        File file = new File(textFile);
        List<List<String>> array = new ArrayList<>();
        if (!file.exists()) {
            System.out.printf("Cannot find the \"%s\" file\n", textFile);
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
                        // split ";" into array then add into array
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

    public static List<String> getOneSpecificData(String filename, int column, String matchData) {
        // get one match data
        // only use for unique data
        String textFile = String.format("%s%s.txt", fileDirectory, filename);
        File file = new File(textFile);
        List<String> array = new ArrayList<>();
        if (!file.exists()) {
            System.out.printf("Cannot find the \"%s\" file\n", textFile);
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
                        array.addAll(List.of(line.split(";")));
                        break;
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

    public static void updateRows(String filename, List<List<String>> newArray, int columnNum) {
        /* NEVER USE THIS METHOD WHEN THERE ARE MULTIPLE SAME ID
           check duplication exist, if true then stop execute */
        List<String> check = newArray.stream().map(array -> array.get(0)).toList();
        if (duplicationExist(check)) {
            System.out.println("Cannot update the file cause duplication ID exist");
            return;
        }
        // read file to get all the data
        List<List<String>> oldArray = readFile(filename);
        String content = "";
        int i = 0;
        for (List<String> oldRow : oldArray) {
            for (List<String> newRow : newArray) {
                // if old data match with new data get replace
                if (oldRow.get(columnNum).equals(newRow.get(columnNum))) {
                    oldArray.set(i, newRow);
                    newArray.remove(newRow);
                    break;
                }
            }
            content += String.join(";", oldArray.get(i)) + "\n";
            i++;
        }
        modifyFile(filename, content, false);

        if (!newArray.isEmpty()) {
            System.out.printf("These are the data that is not found in %s\n", filename);
            System.out.println(newArray);
        }
    }

    public static void updateRowsById(String filename, List<List<String>> newArray) {
        updateRows(filename, newArray, 0);
    }

    public static void updateSingleRow(String filename, List<String> newData, int columnNum) {
        /* NEVER USE THIS METHOD WHEN THERE ARE MULTIPLE SAME ID
           It will replace all the data that match */
        List<List<String>> oldArray = readFile(filename);
        String content = "";
        boolean found = false;
        int i = 0;
        for (List<String> oldRow : oldArray) {
            // if data get match then replace
            if (oldRow.get(columnNum).equals(newData.get(columnNum))) {
                oldArray.set(i, newData);
                found = true;
            }
            content += String.join(";", oldArray.get(i)) + "\n";
            i++;
        }
        // if data got replace then execute to replace the file
        if (found) {
            modifyFile(filename, content, false);
        } else {
            System.out.printf("Data {%s} of column{%d} is not found in %s\n", newData, columnNum, filename);
        }
    }

    public static void updateSingleRowById(String filename, List<String> newData) {
        updateSingleRow(filename, newData, 0);
    }

    public static boolean duplicationExist(List<String> array) {
        Set<String> set = Set.copyOf(array); // copy into set array to remove duplication
        return set.size() != array.size(); // check size
    }

    public static void deleteRowsById(String filename, List<List<String>> arrayData) {
        // get all id and convert into set array to remove duplication
        Set<String> arrayId = arrayData.stream().map(array -> array.get(0)).collect(Collectors.toSet());
        List<List<String>> array = readFile(filename);
        String content = "";
        boolean remain;
        int i = 0;
        for (List<String> row : array) {
            remain = true;
            for (String id : arrayId) {
                if (row.get(0).equals(id)) {
                    remain = false;
                    break;
                }
            }
            // if id match then will not add into content
            if (remain) {
                content += String.join(";", array.get(i)) + "\n";
            }
            i++;
        }
        modifyFile(filename, content, false);
    }
}
