package demo.file.scanner.filescanner.component.impl;

import demo.file.scanner.filescanner.component.Hashing;
import org.springframework.stereotype.Component;

import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;

@Component
public class HashingImpl implements Hashing {

    /**
     * Hashing file using MD5
     * */
    @Override
    public String hash(File input) {
        String rs = "";
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(Files.readAllBytes(Paths.get(input.getPath())));
            byte[] digest = md.digest();
            rs = DatatypeConverter.printHexBinary(digest).toUpperCase();
        } catch (Exception e) {
            System.out.printf(e.getMessage());
        }

        return rs;
    }

}
