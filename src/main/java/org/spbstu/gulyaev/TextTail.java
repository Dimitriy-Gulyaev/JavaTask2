package org.spbstu.gulyaev;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class TextTail {

    Integer numberOfChars;
    Integer numberOfStrings;
    String inputName;
    String outputName;

    public TextTail(Integer numberOfChars, Integer numberOfStrings, String inputName, String outputName) {
        this.numberOfChars = numberOfChars;
        this.numberOfStrings = numberOfStrings;
        this.inputName = inputName;
        this.outputName = outputName;
    }

    public void tail(String inputName, String outputName) throws IOException {
        if (numberOfChars == null && numberOfStrings == null) numberOfStrings = 10;
        if (inputName == null) {
            inputName = "input.txt";
            System.out.println("Write your text manually down here, type \"close\" to end the input:");
            Scanner reader = new Scanner(System.in);
            BufferedWriter writer = new BufferedWriter(new FileWriter(inputName));
            try {
                String temp = reader.nextLine();
                while (!temp.equals("close")) {
                    writer.write(temp + '\n');
                    temp = reader.nextLine();
                    writer.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            reader.close();
        }
        boolean consoleOutput = false;
        if (outputName == null) {
            outputName = "output.txt";
            consoleOutput = true;
        }
        List<String> text = Files.readAllLines(Paths.get(inputName));
        if (numberOfStrings != null) {
            text = text.subList(text.size() - numberOfStrings, text.size());
            Files.write(Paths.get(outputName), text);
        }
        if (numberOfChars != null) {
            int k = numberOfChars;
            ArrayList<String> result = new ArrayList<>();
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputName));
            int i = 1;
            while (text.get(text.size() - i).length() <= k) {
                result.add(text.get(text.size() - i) + '\n');
                k -= text.get(text.size() - i).length();
                i++;
            }
            if (k != 0) {
                result.add(text.get(text.size() - i).substring(text.get(text.size() - i).length() - k) + '\n');
            }
            for (int j = result.size() - 1; j >= 0; j--) {
                writer.write(result.get(j));
            }
            writer.flush();
        }
        if (consoleOutput) Files.readAllLines(Paths.get(outputName)).forEach(System.out::println);
    }
}
