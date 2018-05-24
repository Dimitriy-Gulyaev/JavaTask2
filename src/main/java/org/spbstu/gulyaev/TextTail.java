package org.spbstu.gulyaev;

import java.util.ArrayList;
import java.util.List;

public class TextTail {

    Integer numberOfChars;
    Integer numberOfStrings;

    public TextTail(Integer numberOfChars, Integer numberOfStrings) {
        this.numberOfChars = numberOfChars;
        this.numberOfStrings = numberOfStrings;
    }

    public List<String> tail(ArrayList<String> text) {
        if (!text.equals(new ArrayList<String>())) {
            if (numberOfChars == null && numberOfStrings == null) numberOfStrings = 10;
            if (numberOfStrings != null) {
                if (numberOfStrings > text.size()) throw new IllegalArgumentException("required number of strings" +
                        " is bigger than number of strings in the original text");
                return text.subList(text.size() - numberOfStrings, text.size());
            } else {
                int k = numberOfChars;
                List<String> result = new ArrayList<>();
                int i = 1;
                while (text.get(text.size() - i).length() <= k) {
                    result.add(text.get(text.size() - i));
                    k -= text.get(text.size() - i).length();
                    i++;
                    if (i > text.size()) throw new IllegalArgumentException("required number of chars" +
                            " is bigger than number of chars in the original text");
                }
                if (k != 0) {
                    if (k > text.get(text.size() - i).substring(text.get(text.size() - i).length() - k).length())
                        throw new IllegalArgumentException("required number of chars" +
                                " is bigger than number of chars in the original text");
                    result.add(text.get(text.size() - i).substring(text.get(text.size() - i).length() - k));
                }
                return result;
            }
        }
        return new ArrayList<>();
    }
}
