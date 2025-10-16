package network.io_1;

import java.io.FileInputStream;
import java.io.IOException;

import static network.io_1.BufferedConst.*;

public class _10_Read_File2_Main {

    public static void main(String[] args) throws IOException {

        FileInputStream fis = new FileInputStream(FILE_NAME);
        long startTime = System.currentTimeMillis();

        byte[] buffer = new byte[BUFFER_SIZE];
        int fileSize = 0;
        int size;

        while((size = fis.read(buffer)) != -1) {
            fileSize += size;
        }

        fis.close();
        long endTime = System.currentTimeMillis();
        System.out.println("File created: " + FILE_NAME);
        System.out.println("File Size: " + FILE_SIZE / 1024 / 1024 + "MB");
        System.out.println("Time taken: " + (endTime - startTime) + "ms");
    }
}
