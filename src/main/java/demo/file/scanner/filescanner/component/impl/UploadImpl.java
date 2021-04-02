package demo.file.scanner.filescanner.component.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import demo.file.scanner.filescanner.component.FileHelper;
import demo.file.scanner.filescanner.component.FileLookup;
import demo.file.scanner.filescanner.component.Hashing;
import demo.file.scanner.filescanner.component.Uploader;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@Component
public class UploadImpl implements Uploader {

    private FileLookup fileLookup;
    private Hashing hashing;
    private FileHelper fileHelper;
    private RestTemplate template;

    @Value("${upload-url}")
    private String url;

    @Value("${opswat-apikey-demo-scan-file}")
    private String apikey;

    @Autowired
    public UploadImpl(FileLookup fileLookup, Hashing hashing, FileHelper fileHelper, RestTemplate template) {
        this.fileLookup = fileLookup;
        this.hashing = hashing;
        this.fileHelper = fileHelper;
        this.template = template;
    }

    /**
     * execute the upload process
     * @param filePath physical path to the local file.
     * */
    @Override
    public void upload(String filePath){
        File file;
        try {
            file = fileHelper.getFile(filePath);
            String fileHashing = hash(file);
            ((FileLookupImpl) fileLookup).setFileName(file.getName());
            lookup(fileHashing);

            if(!fileLookup.isFileExist())
                uploadFile(file);

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage() +"\n");
        }catch (Exception ex){
            System.out.println(ex.getMessage() +"\n");
        }
    }

    /**
     * call hashing file
     * */
    private String hash(File input) {
        System.out.println("hashing ...");
        return hashing.hash(input);
    }

    /**
     * call lookup file using opswat api
     * */
    private void lookup(String fileHashing){
        System.out.println("lookup file: " + fileHashing);
        fileLookup.lookup(fileHashing);
    }

    /**
     * execute the upload after validate and lookup file not found
     * */
    private void uploadFile(File f) throws IOException {
        System.out.println("uploading ...");
        HttpEntity<JSONObject> ob = template.postForEntity(url, createRequest(f), JSONObject.class);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson.toJson(ob.getBody()));
    }

    /**
     * create header and body for upload request
     * */
    private HttpEntity<MultiValueMap<String, Object>> createRequest(File f) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("apikey", apikey);
        headers.set("filename", f.getName());

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", convertToBinary(f));
        body.add("filename", f.getName());
        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(body, headers);
        return request;
    }

    /**
     * convert file to binary for upload request
     * */
    private byte[] convertToBinary(File f) throws IOException {
        byte[] fileData = new byte[(int) f.length()];
        try {
            FileInputStream in = new FileInputStream(f);
            in.read(fileData);
            in.close();
        } catch (IOException e) {
            throw e;
        }
        return fileData;
    }
}
