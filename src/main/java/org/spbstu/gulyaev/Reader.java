package org.spbstu.gulyaev;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Reader {
    public Reader() {
    }

    public static ArrayList<String> read(String inputName) throws IOException {
        ArrayList<String> text = new ArrayList<>();
        if (inputName == null) {
            System.out.println("Write your text manually down here, type \"close\" to end the input:");
            Scanner reader = new Scanner(System.in);
            String temp = reader.nextLine();
            while (!temp.equals("close")) {
                text.add(temp);
                temp = reader.nextLine();
            }
            reader.close();
        } else {
            Scanner reader = new Scanner(Paths.get(inputName));
            while (reader.hasNextLine()) {
                text.add(reader.nextLine());
            }
        }
        return text;
    }
}
