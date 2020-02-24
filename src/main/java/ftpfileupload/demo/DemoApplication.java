package ftpfileupload.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.apache.commons.net.ftp.FTPClient;

import java.io.FileInputStream;
import java.io.IOException;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {

        SpringApplication.run(DemoApplication.class, args);

        FTPClient client = new FTPClient();

        FileInputStream fis = null;

        try {


            client.connect("testftp.beatcatalog.com");
            client.login("testftp2@testftp.beatcatalog.com", "P455w0rd!");

            String filename = ".\\test-upload-from-specd-file.txt";
            fis = new FileInputStream(filename);

            client.storeFile(filename, fis);
            client.logout();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                client.disconnect();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
