package network.io_1;

import java.io.FileInputStream;
import java.io.IOException;

import static network.io_1.BufferedConst.FILE_NAME;
import static network.io_1.BufferedConst.FILE_SIZE;

public class _8_Read_File_Main {

    public static void main(String[] args) throws IOException {

        FileInputStream fis = new FileInputStream(FILE_NAME);
        long startTime = System.currentTimeMillis();

        int fileSize = 0;
        int data;
        while ((data = fis.read()) != -1) {
            fileSize++;
        }
        fis.close();

        long endTime = System.currentTimeMillis();
        System.out.println("File created: " + FILE_NAME);
        System.out.println("File Size: " + FILE_SIZE / 1024 / 1024 + "MB");
        System.out.println("Time taken: " + (endTime - startTime) + "ms");
    }
}
