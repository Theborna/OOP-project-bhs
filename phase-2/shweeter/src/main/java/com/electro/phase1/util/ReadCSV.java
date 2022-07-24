package com.electro.phase1.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReadCSV {
    private static ReadCSV instance;
    private static String path;
    private static final String COMMA_DELIMITER = ",";
    private static final int TEST_SIZE = 700;
    private List<List<String>> data = new ArrayList<>();
    private List<String> test = new ArrayList<>();

    private ReadCSV(String path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path));
                PrintWriter pw = new PrintWriter("answers.txt");) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(COMMA_DELIMITER);
                System.out.println(values.length);
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < values.length - 1; j++)
                    sb.append(values[j].toLowerCase().replaceAll("[^\\w \\d]", "").trim()).trimToSize();
                String[] parsed = { sb.toString().trim(), values[values.length - 1] };
                if (i < TEST_SIZE)
                    data.add(Arrays.asList(parsed));
                else {
                    test.add(parsed[0]);
                    pw.println(parsed[1] + ", " + parsed[0]);
                }
                i++;
                if (i == 1000)
                    break;
            }
        } catch (Exception e) {
            System.out.println(path);
            e.printStackTrace();
        }
    }

    public static ReadCSV getInstance() {
        if (instance == null)
            instance = new ReadCSV(path);
        return instance;
    }

    public static void setPath(String path) {
        ReadCSV.path = path;
    }

    public List<List<String>> getData() {
        return data;
    }

    public List<String> getTest() {
        return test;
    }

    public static void main(String[] args) throws Exception {
        ReadCSV.setPath("data/data.csv");
        ReadCSV.getInstance(); // initializing the csv reader
        Dictionary.getInstance();
        try (PrintWriter pw = new PrintWriter("our.txt")) {
            for (String review : ReadCSV.getInstance().getTest())
                pw.println(((Dictionary.getInstance().goodProbability(review) > Dictionary.getInstance()
                        .badProbability(review)) ? 1 : 0) + ", " + review);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(measureDifference("our.txt", "answers.txt"));
    }

    public static double measureDifference(String file1, String file2) {
        try (BufferedReader br1 = new BufferedReader(new FileReader(file1));
                BufferedReader br2 = new BufferedReader(new FileReader(file2))) {
            int notSame = 0, all = 0;
            String line1, line2;
            while ((line1 = br1.readLine()) != null && (line2 = br2.readLine()) != null) {
                all++;
                if (!line1.equals(line2))
                    notSame++;
            }
            return (1 - notSame / (double) all);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0; // should never reach here
    }
}
