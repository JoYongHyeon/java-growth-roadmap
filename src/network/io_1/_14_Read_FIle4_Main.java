package network.io_1;

import java.io.FileInputStream;
import java.io.IOException;

import static network.io_1.BufferedConst.FILE_NAME;
import static network.io_1.BufferedConst.FILE_SIZE;

public class _14_Read_FIle4_Main {

    public static void main(String[] args) throws IOException {

        FileInputStream fis = new FileInputStream(FILE_NAME);
        long startTime = System.currentTimeMillis();

        byte[] bytes = fis.readAllBytes();
        fis.close();

        long endTime = System.currentTimeMillis();
        System.out.println("File created: " + FILE_NAME);
        System.out.println("File Size: " + FILE_SIZE / 1024 / 1024 + "MB");
        System.out.println("Time taken: " + (endTime - startTime) + "ms");

    }
}
