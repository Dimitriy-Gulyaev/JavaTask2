package org.spbstu.gulyaev;

import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TextTailTest {

    @Test
    public void testingTextTailOnCharWithOneStringFile() throws IOException {
        TextTail tail = new TextTail(5, null,
                "input.txt", "output.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(tail.inputName));
        writer.write("asd asd asdasd asdasd asd asdfg");
        writer.flush();
        tail.tail(tail.inputName, tail.outputName);
        String test = "";
        BufferedReader reader = new BufferedReader(new FileReader(tail.outputName));
        String line = reader.readLine();
        while (line != null) {
            test += line;
            line = reader.readLine();
        }
        assertEquals("asdfg", test);
    }

    @Test
    public void testingTextTailOnCharWithManyStringFile() throws IOException {
        TextTail tail = new TextTail(12, null,
                "input.txt", "output.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(tail.inputName));
        writer.write("qwertyqwerty");
        writer.newLine();
        writer.write("qwertyqwertyasdasd");
        writer.newLine();
        writer.write("qwerty");
        writer.newLine();
        writer.flush();
        tail.tail(tail.inputName, tail.outputName);
        List<String> test = Files.readAllLines(Paths.get(tail.outputName));
        assertEquals("asdasd" + '\n' + "qwerty", test.get(0) + '\n' + test.get(1));
    }

    @Test
    public void testingTextTailOnOneStringFile() throws IOException {
        TextTail tail = new TextTail(null, 1,
                "input.txt", "output.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(tail.outputName));
        writer.write("asd asdfg");
        writer.flush();
        tail.tail(tail.inputName, tail.inputName);
        String test = "";
        BufferedReader reader = new BufferedReader(new FileReader(tail.outputName));
        String line = reader.readLine();
        while (line != null) {
            test += line;
            line = reader.readLine();
        }
        assertEquals("asd asdfg", test);
    }

    @Test
    public void testingTextTailOnManyStringsFile() throws IOException {
        TextTail tail = new TextTail(null, 2,
                "input.txt", "output.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(tail.inputName));
        writer.write("asd asdfg");
        writer.newLine();
        writer.write("asd asdfgasdfgh");
        writer.newLine();
        writer.write("bombom");
        writer.newLine();
        writer.write("qwerty");
        writer.flush();
        writer.close();
        tail.tail(tail.inputName, tail.outputName);
        BufferedWriter writer2 = new BufferedWriter(new FileWriter("test.txt"));
        writer2.write("bombom" + '\n' + "qwerty");
        writer2.flush();
        assertEquals(Files.readAllLines(Paths.get("test.txt")), Files.readAllLines(Paths.get(tail.outputName)));
    }

    @Test
    public void testingNullNumberOfCharsAndString() throws IOException {
        TextTail tail = new TextTail(null, null,
                "input.txt", "output.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(tail.inputName));
        for (int i = 0; i < 12; i++) {
            writer.write("asd" + '\n');
        }
        writer.flush();
        writer.close();
        tail.tail(tail.inputName, tail.outputName);
        BufferedWriter writer2 = new BufferedWriter((new FileWriter("test.txt")));
        for (int i = 0; i < 10; i++) {
            writer2.write("asd" + '\n');
        }
        writer2.flush();
        assertEquals(Files.readAllLines(Paths.get("test.txt")), Files.readAllLines(Paths.get(tail.outputName)));
    }
}
