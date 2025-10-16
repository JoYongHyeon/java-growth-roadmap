package network.io_1;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static network.io_1.BufferedConst.FILE_NAME;
import static network.io_1.BufferedConst.FILE_SIZE;

public class _7_Create_File_Main {

    public static void main(String[] args) throws IOException {

        FileOutputStream fos = new FileOutputStream(FILE_NAME);
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < FILE_SIZE; i++) {
            fos.write(1);
        }
        fos.close();

        long endTime = System.currentTimeMillis();

        System.out.println("File created: " + FILE_NAME);
        System.out.println("File Size: " + FILE_SIZE / 1024 / 1024 + "MB");
        System.out.println("Time taken: " + (endTime - startTime) + "ms");


    }
}
