package demo.file.scanner.service.impl;

import demo.file.scanner.service.FileHelper;

import java.io.File;
import java.io.FileNotFoundException;

public class FileHelperImpl implements FileHelper {

    @Override
    public File getFile(String file) throws FileNotFoundException {
        System.out.println("validation ...");
        String fileName = file.substring(file.indexOf(' ')+1);
        fileName = fileName.replace("\\", "");
        if(fileName.length() < 1)
            throw new FileNotFoundException();

        File f = new File(fileName.trim());
        if(!f.exists()){
            throw new FileNotFoundException(fileName);
        }
        return f;
    }
}
