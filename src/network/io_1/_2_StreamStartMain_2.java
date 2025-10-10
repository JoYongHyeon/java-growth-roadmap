package network.io_1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class _2_StreamStartMain_2 {

    public static void main(String[] args) throws IOException {

        FileOutputStream fos = new FileOutputStream("src/network/temp/hello.dat");
        fos.write(65);
        fos.write(66);
        fos.write(67);
        fos.close();

        FileInputStream fis = new FileInputStream("src/network/temp/hello.dat");
        int data;
        // read() 메서드는 파일의 끝에 도달하면 -1 반환 -> -1 반환까지 반복
        while ((data = fis.read()) != -1) {
            System.out.println(data);
        }
        fis.close();
    }
}
