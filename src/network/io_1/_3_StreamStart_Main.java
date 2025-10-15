package network.io_1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class _3_StreamStart_Main {

    public static void main(String[] args) throws IOException {

        FileOutputStream fos = new FileOutputStream("src/network/temp/hello.dat");
        byte[] input = {65, 66, 67};
        // 원하는 데이터를 담고 write() 에 전달 -> 한번에 출력 가능
        fos.write(input);
        fos.close();

        FileInputStream fis = new FileInputStream("src/network/temp/hello.dat");
        byte[] buffer = new byte[10];
        int readCount = fis.read(buffer, 0, 10);
        System.out.println("readCount = " + readCount);
        System.out.println(Arrays.toString(buffer));
        fis.close();
    }
}
