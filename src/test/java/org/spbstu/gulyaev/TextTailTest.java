package org.spbstu.gulyaev;

import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;

public class TextTailTest {

    @Test
    public void testingTextTailOnCharWithOneStringFile() throws IOException {
        TextTail tail = new TextTail(5, 0);
        BufferedWriter writer = new BufferedWriter(new FileWriter("input.txt"));
        writer.write("asd asd asdasd asdasd asd asdfg");
        writer.flush();
        tail.tail("input.txt", "output.txt");
        String test = "";
        BufferedReader reader = new BufferedReader(new FileReader("output.txt"));
        String line = reader.readLine();
        while (line != null) {
            test += line;
            line = reader.readLine();
        }
        assertEquals("asdfg", test);
    }

    @Test
    public void testingTextTailOnCharWithManyStringFile() throws IOException {
        TextTail tail = new TextTail(12, 0);
        BufferedWriter writer = new BufferedWriter(new FileWriter("input.txt"));
        writer.write("qwertyqwerty");
        writer.write("qwertyqwertyqwerty");
        writer.write("qwerty");
        writer.flush();
        tail.tail("input.txt", "output.txt");
        String test = "";
        BufferedReader reader = new BufferedReader(new FileReader("output.txt"));
        String line = reader.readLine();
        while (line != null) {
            test += line;
            line = reader.readLine();
        }
        assertEquals("qwertyqwerty", test);
    }

    @Test
    public void testingTextTailOnOneStringFile() throws IOException {
        TextTail tail = new TextTail(0, 1);
        BufferedWriter writer = new BufferedWriter(new FileWriter("input.txt"));
        writer.write("asd asdfg");
        writer.flush();
        tail.tail("input.txt", "output.txt");
        String test = "";
        BufferedReader reader = new BufferedReader(new FileReader("output.txt"));
        String line = reader.readLine();
        while (line != null) {
            test += line;
            line = reader.readLine();
        }
        assertEquals("asd asdfg", test);
    }

    @Test
    public void testingTextTailOnManyStringsFile() throws IOException {
        TextTail tail = new TextTail(0, 2);
        BufferedWriter writer = new BufferedWriter(new FileWriter("input.txt"));
        writer.write("asd asdfg");
        writer.newLine();
        writer.write("asd asdfgasdfgh");
        writer.newLine();
        writer.write("bombom");
        writer.newLine();
        writer.write("qwerty");
        writer.flush();
        tail.tail("input.txt", "output.txt");
        String test = "";
        BufferedReader reader = new BufferedReader(new FileReader("output.txt"));
        String line = reader.readLine();
        while (line != null) {
            test += line;
            line = reader.readLine();
        }
        assertEquals("bombomqwerty", test);
    }
}
