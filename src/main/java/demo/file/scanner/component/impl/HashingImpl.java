package demo.file.scanner.component.impl;

import demo.file.scanner.component.Hashing;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class HashingImpl implements Hashing {

    @Override
    public String hash(File input) {
        String rs = "";
//        try (InputStream is = Files.newInputStream(Paths.get(input.getPath()))) {
//            rs = DigestUtils.md5DigestAsHex(is);
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        }
        return rs;
    }

}
