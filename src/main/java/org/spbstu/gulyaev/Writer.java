package org.spbstu.gulyaev;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Writer {
    public Writer() {
    }

    public static void write(List<String> text, String outputName, String inputName, BufferedWriter writer)
            throws IOException {
        if (!text.equals(new ArrayList<String>())) {
            if (outputName != null) {
                if (inputName != null) {
                    writer.write(inputName);
                    writer.newLine();
                    writer.flush();
                }
                for (int i = text.size() - 1; i >= 0; i--) {
                    writer.write(text.get(i));
                    writer.newLine();
                    writer.flush();
                }
            } else {
                if (inputName != null) System.out.println(inputName);
                for (String aText : text) {
                    System.out.println(aText);
                }
            }
        }
    }
}
