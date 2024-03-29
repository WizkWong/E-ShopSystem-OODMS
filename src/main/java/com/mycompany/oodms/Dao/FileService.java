package com.mycompany.oodms.Dao;

import com.mycompany.oodms.customer.CartItemDao;
import com.mycompany.oodms.item.ItemDao;
import com.mycompany.oodms.user.UserDao;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public interface FileService {

    String FILE_DIRECTORY = "src\\file\\";

    char ID_COLUMN = 0;

    // allow the data remain as deleted status
    List<String> ALLOWED_REMOVE = List.of(ItemDao.ITEM_FILENAME, UserDao.FILENAME);

    // allow to delete the data
    List<String> ALLOWED_DELETE = List.of(CartItemDao.FILENAME, ItemDao.CATEGORY_FILENAME);

    // create the txt file. If file directory does not exist, create new directory
    static void createFile(String filename) {
        String textFile = String.format("%s%s.txt", FILE_DIRECTORY, filename);
        File file = new File(textFile);
        // check file exist
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

    // read all the data then split ";" into array then convert it to ArrayList
    static List<List<String>> readFile(String filename, boolean includeDeleted) {
        String textFile = String.format("%s%s.txt", FILE_DIRECTORY, filename);
        File file = new File(textFile);
        List<List<String>> array = new ArrayList<>();
        // check file exist or not
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
            String[] tempArray;

            if (!includeDeleted && ALLOWED_REMOVE.contains(filename)) {
                while ((line = br.readLine()) != null) {
                    if (line.length() > 0) {
                        // split ";" into array then add into array
                        tempArray = line.split(";");
                        if (tempArray[tempArray.length - 1].equals("D")) {
                            continue;
                        }
                        array.add(new ArrayList<>(Arrays.asList(tempArray)));
                    }
                }

            } else {
                while ((line = br.readLine()) != null) {
                    if (line.length() > 0) {
                        // split ";" into array then add into array
                        tempArray = line.split(";");
                        array.add(new ArrayList<>(Arrays.asList(tempArray)));
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

    // read all the data excluding the data in deleted status
    static List<List<String>> readFile(String filename) {
        return readFile(filename, false);
    }

    // get the id from file then increment, use for insert new data
    static Long getNewId(String filename) {
        String textFile = String.format("%s%s.txt", FILE_DIRECTORY, filename);
        File file = new File(textFile);
        // check file exist or not
        if (!file.exists()) {
            System.out.printf("Cannot find the \"%s\" file\n", textFile);
            return null;
        }
        // get the id from file
        FileReader fr;
        BufferedReader br = null;
        String lastLine = null;
        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                if (line.length() > 0) {
                    lastLine = line;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeFile(br);
        }
        // if empty file, means no id will return 0
        if (lastLine == null) {
            return 0L;
        }
        long id;
        // check is number or not, if not return -1L. Note that in GUI will prompt system error message if is -1L
        try {
            id = Long.parseLong(lastLine.split(";")[0]);
        } catch (Exception e) {
            e.printStackTrace();
            return -1L;
        }
        // increment id by 1
        return id + 1;
    }

    // rewrite the file with new content
    static boolean modifyFile(String filename, String content, Boolean append) {
        String textFile = String.format("%s%s.txt", FILE_DIRECTORY, filename);
        File file = new File(textFile);
        // check file exist or not
        if (!file.exists()) {
            System.out.printf("Cannot find the \"%s\" file\n", textFile);
            return false;
        }
        FileWriter fw;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter(textFile, append);
            bw = new BufferedWriter(fw);
            // rewrite the file
            bw.write(content);

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeFile(bw);
        }
        return true;
    }

    // close file, use for read and write file
    static void closeFile(Closeable file) {
        // close the file
        if (file != null) {
            try {
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // insert a new data into the file
    static boolean insertData(String filename, List<String> data) {
        String content = ALLOWED_REMOVE.contains(filename) ?
                String.join(";", data) + ";E\n" : String.join(";", data) + "\n";
        return modifyFile(filename, content, true);
    }

    // insert multiple new data into the file
    static boolean insertMultipleData(String filename, List<List<String>> dataList) {
        StringBuilder content = new StringBuilder();
        for (List<String> data : dataList) {
            content.append(ALLOWED_REMOVE.contains(filename) ? String.join(";", data) + ";E\n" : String.join(";", data) + "\n");
        }
        return modifyFile(filename, content.toString(), true);
    }

    // get all the match data by specific column
    static List<List<String>> getMultipleSpecificData(String filename, int column, String matchData) {
        String textFile = String.format("%s%s.txt", FILE_DIRECTORY, filename);
        File file = new File(textFile);
        List<List<String>> array = new ArrayList<>();
        // check file exist or not
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
                    // if found, add into array
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
        // return all found data
        return array;
    }

    // get one match data by specific column, only use for unique id
    static List<String> getOneSpecificData(String filename, int column, String matchData) {

        String textFile = String.format("%s%s.txt", FILE_DIRECTORY, filename);
        File file = new File(textFile);
        List<String> array = new ArrayList<>();
        // check file exist or not
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
                    // if found, stop loop
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
        // return first found data
        return array;
    }

    private static String convertToContent(String filename, List<String> data) {
        int lastIndex = data.size() - 1;
        // if filename does not allow remove
        if (!ALLOWED_REMOVE.contains(filename)) {
            return String.join(";", data) + "\n";
        }
        // note after this line, all the data are allow to be remove
        // if data does not contain "E" and "D" at last index, add "E" in last index
        if (!data.get(lastIndex).equals("E") && !data.get(lastIndex).equals("D")) {
            return String.join(";", data) + ";E\n";
        }
        return String.join(";", data) + "\n";
    }

    /* NEVER USE THIS METHOD WHEN THERE ARE MULTIPLE SAME ID IN MORE THAN ONE COLUMN
       check duplication exist, if true then stop execute */
    // get all the data by one column
    static boolean updateMultipleRows(String filename, List<List<String>> newArray, int column1) {
        List<String> check = newArray.stream().map(array -> array.get(0)).toList();
        if (duplicationExist(check)) {
            System.out.println("Cannot update the file cause duplication exist");
            return false;
        }
        // read file to get all the data
        List<List<String>> oldArray = readFile(filename, true);
        StringBuilder content = new StringBuilder();
        int i = 0;
        for (List<String> oldRow : oldArray) {
            for (List<String> newRow : newArray) {
                // if old data match with new data get replace
                if (oldRow.get(column1).equals(newRow.get(column1))) {
                    oldArray.set(i, newRow);
                    newArray.remove(newRow);
                    break;
                }
            }
            content.append(convertToContent(filename, oldArray.get(i)));
            i++;
        }

        if (!newArray.isEmpty()) {
            System.out.printf("These are the data that is not found in %s\n", filename);
            System.out.println(newArray);
        }
        return modifyFile(filename, content.toString(), false);
    }

    // get all the data by two column
    static boolean updateMultipleRows(String filename, List<List<String>> newArray, int column1, int column2) {
        List<List<String>> oldArray = readFile(filename, true);
        StringBuilder content = new StringBuilder();
        int i = 0;
        for (List<String> oldRow : oldArray) {
            for (List<String> newRow : newArray) {
                // if old data match with new data get replace
                if (oldRow.get(column1).equals(newRow.get(column1)) && oldRow.get(column2).equals(newRow.get(column2))) {
                    oldArray.set(i, newRow);
                    newArray.remove(newRow);
                    break;
                }
            }
            content.append(convertToContent(filename, oldArray.get(i)));
            i++;
        }

        if (!newArray.isEmpty()) {
            System.out.printf("These are the data that is not found in %s\n", filename);
            System.out.println(newArray);
        }
        return modifyFile(filename, content.toString(), false);
    }

    // only update single row by matching one column
    static boolean updateSingleRow(String filename, List<String> newData, int column1) {
        List<List<String>> oldArray = readFile(filename, true);
        StringBuilder content = new StringBuilder();
        boolean found = false;
        int i = 0;
        for (List<String> oldRow : oldArray) {
            // if data get match then replace
            if (!found && oldRow.get(column1).equals(newData.get(column1))) {
                oldArray.set(i, newData);
                found = true;
            }
            content.append(convertToContent(filename, oldArray.get(i)));
            i++;
        }
        // if data got replace then execute to replace the file
        if (found) {
            return modifyFile(filename, content.toString(), false);
        }

        System.out.printf("Data {%s} of column{%d} is not found in %s\n", newData, column1, filename);
        return true;
    }

    // only update single row by matching two column
    static boolean updateSingleRow(String filename, List<String> newData, int column1, int column2) {
        List<List<String>> oldArray = readFile(filename, true);
        StringBuilder content = new StringBuilder();
        boolean found = false;
        int i = 0;
        for (List<String> oldRow : oldArray) {
            // if data get match then replace
            if (!found && oldRow.get(column1).equals(newData.get(column1)) && oldRow.get(column2).equals(newData.get(column2))) {
                oldArray.set(i, newData);
                found = true;
            }
            content.append(convertToContent(filename, oldArray.get(i)));
            i++;
        }
        // if data got replace then execute to replace the file
        if (found) {
            return modifyFile(filename, content.toString(), false);
        }

        System.out.printf("Data {%s} of column{%d} is not found in %s\n", newData, column1, filename);
        return true;
    }

    // check any duplication, if exist return true else false
    static boolean duplicationExist(List<String> array) {
        Set<String> set = Set.copyOf(array); // copy into set array to remove duplication
        return set.size() != array.size(); // check size
    }

    // change the data status to deleted, only allow some file
    static boolean removeById(String filename, List<List<String>> arrayData) {
        // check the file have right to execute this method or not
        if (!ALLOWED_REMOVE.contains(filename)) {
            System.out.printf("%s file does not allow remove the data!\n", filename);
            return false;
        }
        // get all id and convert into set array to remove duplication
        Set<String> setId = arrayData.stream().map(array -> array.get(0)).collect(Collectors.toSet());
        List<List<String>> array = readFile(filename, true);
        StringBuilder content = new StringBuilder();
        int lastIndex = array.get(0).toArray().length - 1;
        int i = 0;
        for (List<String> row : array) {
            for (String id : setId) {
                if (row.get(0).equals(id)) {
                    array.get(i).set(lastIndex, "D");
                    break;
                }
            }
            content.append(String.join(";", array.get(i))).append("\n");
            i++;
        }
        return modifyFile(filename, content.toString(), false);
    }
    
    // delete the data completely, only allow some file
    static boolean deleteById(String filename, List<List<String>> arrayData, int columnId) {
        // check the file have right to execute this method or not
        if (!ALLOWED_DELETE.contains(filename)) {
            System.out.printf("%s file does not allow to delete the data!\n", filename);
            return false;
        }
        // get all id and convert into set array to remove duplication
        Set<List<String>> setId = arrayData.stream().map(array -> List.of(array.get(columnId))).collect(Collectors.toSet());
        List<List<String>> array = readFile(filename);
        StringBuilder content = new StringBuilder();
        boolean remain;
        int i = 0;
        for (List<String> row : array) {
            remain = true;
            for (List<String> listId : setId) {
                if (row.get(columnId).equals(listId.get(0))) {
                    remain = false;
                    break;
                }
            }
            // if id match then will not add into content
            if (remain) {
                content.append(String.join(";", array.get(i))).append("\n");
            }
            i++;
        }
        return modifyFile(filename, content.toString(), false);
    }
    
    // delete the data completely, only allow some file
    static boolean deleteByTwoId(String filename, List<List<String>> arrayData, int firstColumnId, int secondColumnId) {
        // check the file have right to execute this method or not
        if (!ALLOWED_DELETE.contains(filename)) {
            System.out.printf("%s file does not allow to delete the data!\n", filename);
            return false;
        }
        // get all id and convert into set array to remove duplication
        Set<List<String>> setId = arrayData.stream().map(array -> List.of(array.get(firstColumnId), array.get(secondColumnId))).collect(Collectors.toSet());
        List<List<String>> array = readFile(filename);
        StringBuilder content = new StringBuilder();
        boolean remain;
        int i = 0;
        for (List<String> row : array) {
            remain = true;
            for (List<String> listId : setId) {
                if (row.get(firstColumnId).equals(listId.get(0)) && row.get(secondColumnId).equals(listId.get(1))) {
                    remain = false;
                    break;
                }
            }
            // if id match then will not add into content
            if (remain) {
                content.append(String.join(";", array.get(i))).append("\n");
            }
            i++;
        }
        return modifyFile(filename, content.toString(), false);
    }
}
