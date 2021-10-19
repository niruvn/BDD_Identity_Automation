package com.helpers;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

//Reads the comma separated (csv style) output text file and
// returns the HashMap with car details for each registration number

public class GetCarDetailsFromFile {
    public static String outFilePath;
    private static Map<String, HashMap<String, String>> outputMap = new HashMap<String, HashMap<String, String>>();

    public static Map<String, HashMap<String, String>> read(String outputFilePath) {
        File file = new File(outputFilePath);
        try (Scanner scan = new Scanner(file, StandardCharsets.UTF_8.name())) {
            String[] headers = new String[10];
            if (scan.hasNextLine()) {
                headers = scan.nextLine().split(",");
            }
            while (scan.hasNextLine()) {
                String[] values = scan.nextLine().split(",");
                String key = values[0];
                HashMap<String, String> innerMap = new HashMap<String, String>();
                for (int i = 1; i < values.length; i++) {
                    innerMap.put(headers[i].toLowerCase(), values[i]);
                }
                outputMap.put(key, innerMap);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outputMap;

    }
}
