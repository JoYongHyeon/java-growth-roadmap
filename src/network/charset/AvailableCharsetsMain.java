package network.charset;

import java.nio.charset.Charset;
import java.util.SortedMap;

public class AvailableCharsetsMain {

    public static void main(String[] args) {

        // 자바의 이용가능한 모든 Charset
        SortedMap<String, Charset> charsets = Charset.availableCharsets();
        for (String charsetName : charsets.keySet()) {
            System.out.println("charsetName = " + charsetName);
        }
    }
}
