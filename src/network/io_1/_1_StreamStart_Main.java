package network.io_1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class _1_StreamStart_Main {

    public static void main(String[] args) throws IOException {
        // 파일에 데이터를 출력하는 스트림
        FileOutputStream fos = new FileOutputStream("src/network/temp/hello.dat");
        // byte 단위로 값을 출력
        fos.write(65);
        fos.write(66);
        fos.write(67);
        fos.close();

        // 파일에서 데이터를 읽어오는 스트림
        FileInputStream fis = new FileInputStream("src/network/temp/hello.dat");
        // 파일에서 데이터를 byte 단위로 하나씩 읽음.
        System.out.println(fis.read());
        System.out.println(fis.read());
        System.out.println(fis.read());
        // 값이 없다면(끝에 도달) -1
        System.out.println(fis.read());
        fis.close();
    }
}
