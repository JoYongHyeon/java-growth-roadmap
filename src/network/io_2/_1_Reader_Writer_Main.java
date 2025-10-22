package network.io_2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

import static java.nio.charset.StandardCharsets.UTF_8;
import static network.io_2.TextConst.FILE_NAME;

public class _1_Reader_Writer_Main {

    public static void main(String[] args) throws IOException {

        String writeString = "ABC";
        // 문자 -> byte UTF-8 인코딩

        byte[] writeBytes = writeString.getBytes(UTF_8);
        System.out.println("write String: " + writeString);
        System.out.println("write bytes: " + Arrays.toString(writeBytes));

        // 파일에 쓰기
        FileOutputStream fos = new FileOutputStream(FILE_NAME);
        fos.write(writeBytes);
        fos.close();

        // 파일에서 읽기
        FileInputStream fis = new FileInputStream(FILE_NAME);
        byte[] readBytes = fis.readAllBytes();
        fis.close();

        // byte -> String UTF-8 디코딩
        String readString = new String(readBytes, UTF_8);

        System.out.println("read bytes: " + Arrays.toString(readBytes));
        System.out.println("read String: " + readString);
    }

}
