package demo.file.scanner.component.impl;

import demo.file.scanner.component.FileHelper;
import demo.file.scanner.component.FileLookup;
import demo.file.scanner.component.Hashing;
import demo.file.scanner.component.Upload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;

@Component
public class UploadImpl implements Upload {

    private FileLookup fileLookup;
    private Hashing hashing;
    private FileHelper fileHelper;

    @Autowired
    public UploadImpl(FileLookup fileLookup, Hashing hashing, FileHelper fileHelper) {
        this.fileLookup = fileLookup;
        this.hashing = hashing;
        this.fileHelper = fileHelper;
    }

    @Override
    public void upload(String file){
        File f;
        try {
            f = fileHelper.getFile(file);
            String fileHashing = hash(f);
            lookup(fileHashing);
            uploadFile(f);
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage() +"\n");
        }
    }

    private String hash(File input) {
        System.out.println("hashing ...");

        return hashing.hash(input);
    }

    private void lookup(String fileHashing){
        fileLookup.lookup(fileHashing);
    }

    private void uploadFile(File f){
        System.out.println("uploading ...");
    }
}
