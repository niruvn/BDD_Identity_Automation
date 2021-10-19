package com.helpers;

import java.util.HashMap;
import java.util.Map;

import static java.lang.String.format;

public class compareCarDetails {

    public static void compareCarCheck(Map<String, HashMap<String, String>> outputCarDetails, Map<String, HashMap<String, String>> actualCarDetails) {
        HashMap<String, String> outputCarDetailsMap = new HashMap<String, String>();
        HashMap<String, String> actualCarDetailsMap = new HashMap<String, String>();
        //Iterate the car details from Actual
        for (String key : actualCarDetails.keySet()) {
            System.out.println(format("Comparing the results for RegNumber %s", key));
            System.out.println("Key: " + key + ", Value: " + actualCarDetails.get(key));
            // first check outPutCarDetails has the vehicle details
            if (!outputCarDetails.containsKey(key)) {
                System.out.println(format("MIS MATCHED - No Vehicle details found for Reg: %s in OUTPUT car details", key));
                continue;
            }
            //now get the innermap for RegNumber from both maps
            outputCarDetailsMap = outputCarDetails.get(key);
            actualCarDetailsMap = actualCarDetails.get(key);
            //if the innermap of actual car details doesn't exist then report mismatch
            if (actualCarDetailsMap.size() < 1) {
                System.out.println(format("MIS MATCHED - No Vehicle details found on the website for Reg: %s ", key));
                continue;
            }
            //now iterate the innermap and compare each values
            for (String innerKey : actualCarDetailsMap.keySet()) {
                String expectedValue = actualCarDetailsMap.get(innerKey);
                String actualValue;
                if (outputCarDetailsMap.containsKey(innerKey)) {
                    actualValue = outputCarDetailsMap.get(innerKey);
                    if (expectedValue.equals(actualValue)) {
                        System.out.println(format("MATCHED - Actual - Key : %s - Value %s ", innerKey, actualValue));
                    } else {
                        System.out.println(format("MIS MATCHED - Expected %s is %s. But the actual is: %s ", innerKey, expectedValue, actualValue));
                    }
                } else {
                    System.out.println(format("No value preset in Actual for %s", innerKey));
                }
            }
        }
    }
}
