package ru.dataart.academy.java;

import java.io.*;
import java.util.Scanner;
import java.util.zip.ZipInputStream;

public class Calculator {
    /**
     * @param zipFilePath -  path to zip archive with text files
     * @param character   - character to find
     * @return - how many times character is in files
     */
    public Integer getNumberOfChar(String zipFilePath, char character) {
        int count = 0;
        try (ZipInputStream zipInput = new ZipInputStream(new FileInputStream(zipFilePath))) {
            while (zipInput.getNextEntry() != null) {
                BufferedReader entryBuffer = new BufferedReader(new InputStreamReader(zipInput));
                String[] stringArray = entryBuffer.lines().toArray(size -> new String[size]);
                for(String str : stringArray){
                    count += str.chars().filter(c -> c == character).count();
                }
            }

        } catch (IOException ioException) {
            ioException.getMessage();
        }

        return count;
    }

    /**
     * @param zipFilePath - path to zip archive with text files
     * @return - max length
     */

    public Integer getMaxWordLength(String zipFilePath) {
        int maxLengthOfString = 0;
        int lengthOfCurrentString;
        String currentString;

        try (ZipInputStream zipInput = new ZipInputStream(new FileInputStream(zipFilePath))) {
            while (zipInput.getNextEntry() != null) {
                Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(zipInput)));
                while (scanner.hasNext()) {
                    lengthOfCurrentString = scanner.next().length();
                    if(lengthOfCurrentString > maxLengthOfString){
                        maxLengthOfString = lengthOfCurrentString;
                    }
                }


            }
        } catch (IOException ioException) {
            ioException.getMessage();
        }

        return maxLengthOfString;
    }

}
