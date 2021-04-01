package demo.file.scanner.service.impl;

import demo.file.scanner.service.Hashing;

import java.io.File;

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
