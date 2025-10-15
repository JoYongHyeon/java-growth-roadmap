package network.io_1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class _4_StreamStart_Main {

    public static void main(String[] args) throws IOException {

        FileOutputStream fos = new FileOutputStream("src/network/temp/hello.dat");
        byte[] input = {65, 66, 67};
        fos.write(input);
        fos.close();

        FileInputStream fis = new FileInputStream("src/network/temp/hello.dat");
        // 스트림이 끝날 때 까지 모든 데이터를 한번에 읽어옴
        byte[] readBytes = fis.readAllBytes();
        System.out.println(Arrays.toString(readBytes));
        fis.close();
    }
}
