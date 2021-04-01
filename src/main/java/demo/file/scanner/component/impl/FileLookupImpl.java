package demo.file.scanner.component.impl;

import demo.file.scanner.component.FileLookup;
import org.springframework.stereotype.Component;

@Component
public class FileLookupImpl implements FileLookup {

    @Override
    public String lookup(String fileHashing) {
        System.out.println("searching ...");
        return null;
    }
}
