package network.io_1;

import java.io.IOException;
import java.io.PrintStream;

import static java.nio.charset.StandardCharsets.UTF_8;

public class _6_PrintStream_Main {

    public static void main(String[] args) throws IOException {

        PrintStream printStream = System.out;

        // test :
        byte[] bytes = "Hello!\n".getBytes(UTF_8);
        printStream.write(bytes);
        printStream.println("Print!");

    }
}
