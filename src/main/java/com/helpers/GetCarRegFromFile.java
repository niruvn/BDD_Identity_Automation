package com.helpers;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetCarRegFromFile {

    public static String inputFilePath;
    private static final Pattern regNumberPattern = Pattern.compile("[A-Z]{2}[0-9]{2}[\\s]?[A-Z]{3}");

    public static ArrayList read(String inputFilePath) {
        ArrayList<String> regNumberList = new ArrayList();
        File file = new File(inputFilePath);

        try (Scanner scan = new Scanner(file, StandardCharsets.UTF_8.name())) {
            while (scan.hasNextLine()) {
                String nextLine = scan.nextLine();
                Matcher matcher = regNumberPattern.matcher(nextLine);
                while (matcher.find()) {
                    String regNumber = matcher.group();
                    regNumberList.add(regNumber.replaceAll("\\s", ""));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return regNumberList;
    }
}
