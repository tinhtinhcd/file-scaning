package demo.file.scanner.filescanner.component.impl;

import demo.file.scanner.filescanner.component.FileHelper;
import demo.file.scanner.filescanner.component.FileLookup;
import demo.file.scanner.filescanner.component.Hashing;
import demo.file.scanner.filescanner.component.Uploader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;

@Component
public class UploadImpl implements Uploader {

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
        }catch (Exception ex){
            System.out.println(ex.getMessage() +"\n");
        }
    }

    private String hash(File input) {
        System.out.println("hashing ...");
        return hashing.hash(input);
    }

    private void lookup(String fileHashing){
        System.out.println("lookup file: " + fileHashing);
        fileLookup.lookup(fileHashing);
    }

    private void uploadFile(File f){
        System.out.println("uploading ...");
    }
}
