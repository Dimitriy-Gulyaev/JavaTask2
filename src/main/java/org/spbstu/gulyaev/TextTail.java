package org.spbstu.gulyaev;

import java.io.*;
import java.util.ArrayList;

public class TextTail {

    int numberOfChars;
    int numberOfStrings;

    public TextTail(int numberOfChars, int numberOfStrings) {
        this.numberOfChars = numberOfChars;
        this.numberOfStrings = numberOfStrings;
    }

    public void tail(String inputName, String outputName) throws IOException {
        ArrayList<String> text = new ArrayList<String>();
        BufferedReader reader = new BufferedReader(new FileReader(inputName));
        String line = reader.readLine();
        while (line != null) {
            text.add(line);
            line = reader.readLine();
        }

        if (numberOfStrings != 0) {
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputName));
            for (int i = text.size() - numberOfStrings; i < text.size(); i++)
                writer.write(text.get(i));
            writer.flush();
        }

        if (numberOfChars != 0) {
            int i = 0;
            int j = 1;
            int k = 0;
            while (k != numberOfChars) {
                if (i < text.get(text.size() - j).length()) {
                    i++;
                    k++;
                } else {
                    j++;
                    i = 0;
                }
            }
            int count = text.get(text.size() - j).length() - i;
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputName));
            while (j >= 1) {
                if (count > text.get(text.size() - j).length() - 1) {
                    if (j == 1) break;
                    else {
                        j--;
                        count = 0;
                    }
                }
                writer.write(text.get(text.size() - j).charAt(count));
                count++;
            }
            writer.flush();
        }

    }
}
