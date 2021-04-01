package demo.file.scanner.service.impl;

import demo.file.scanner.service.FileHelper;
import demo.file.scanner.service.FileLookup;
import demo.file.scanner.service.Hashing;
import demo.file.scanner.service.Upload;

import java.io.File;
import java.io.FileNotFoundException;

public class UploadImpl implements Upload {

    private FileHelper fileHelper;

    public UploadImpl(FileHelper fileHelper) {
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
        Hashing hashing = new HashingImpl();
        return hashing.hash(input);
    }

    private void lookup(String fileHashing){
        FileLookup lookup = new FileLookupImpl();
        lookup.lookup(fileHashing);
    }

    private void uploadFile(File f){
        System.out.println("uploading ...");
    }
}
