package org.spbstu.gulyaev;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class TextTailTest {

    @Test
    public void testingTextTailOnCharWithOneString() {
        TextTail tail = new TextTail(5, null);
        ArrayList<String> test = new ArrayList<>();
        test.add("asd asd asdasd asdasd asd asdfg");
        ArrayList<String> expect = new ArrayList<>();
        expect.add("asdfg");
        assertEquals(expect, tail.tail(test));
    }

    @Test
    public void testingTextTailOnCharWithManyStrings() {
        TextTail tail = new TextTail(12, null);
        ArrayList<String> test = new ArrayList<>();
        test.add("asd asd asdasd asdasd asd asdfga");
        test.add("bombom");
        ArrayList<String> expect = new ArrayList<>();
        expect.add("bombom");
        expect.add("asdfga");
        assertEquals(expect, tail.tail(test));
    }

    @Test
    public void testingTextTailOnOneString() {
        TextTail tail = new TextTail(null, 1);
        ArrayList<String> test = new ArrayList<>();
        test.add("asd asd asdasd asdasd asd asdfg");
        ArrayList<String> expect = new ArrayList<>();
        expect.add("asd asd asdasd asdasd asd asdfg");
        assertEquals(expect, tail.tail(test));
    }

    @Test
    public void testingTextTailOnManyStrings() {
        TextTail tail = new TextTail(null, 2);
        ArrayList<String> test = new ArrayList<>();
        test.add("grrrrrrr");
        test.add("asd asd asdasd asdasd asd asdfg");
        test.add("bombom");
        ArrayList<String> expect = new ArrayList<>();
        expect.add("asd asd asdasd asdasd asd asdfg");
        expect.add("bombom");
        assertEquals(expect, tail.tail(test));
    }

    @Test
    public void testingNullNumberOfCharsAndString() {
        TextTail tail = new TextTail(null, null);
        ArrayList<String> test = new ArrayList<>();
        for (int i = 0; i <= 12; i++)
            test.add("bom");
        ArrayList<String> expect = new ArrayList<>();
        for (int i = 0; i < 10; i++)
            expect.add("bom");
        assertEquals(expect, tail.tail(test));
    }

    @Test
    public void numberOfStringBiggerThanText() {
        TextTail tail = new TextTail(null, 3);
        ArrayList<String> test = new ArrayList<>();
        test.add("bom");
        test.add("bom");
        try {
            tail.tail(test);
        } catch (IllegalArgumentException e) {
            assertEquals("required number of strings" +
                    " is bigger than number of strings in the original text", e.getMessage());
        }
    }

    @Test
    public void numberOfCharsBiggerThanText() {
        TextTail tail = new TextTail(7, null);
        ArrayList<String> test = new ArrayList<>();
        test.add("bom");
        test.add("bom");
        try {
            tail.tail(test);
        } catch (IllegalArgumentException e) {
            assertEquals("required number of chars" +
                    " is bigger than number of chars in the original text", e.getMessage());
        }
    }
}
