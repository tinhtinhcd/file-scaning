package demo.file.scanner.filescanner.component.impl;

import com.google.gson.*;
import demo.file.scanner.filescanner.component.FileLookup;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


@Component
public class FileLookupImpl implements FileLookup {

    private RestTemplate template;
    private boolean fileExist = false;
    private String fileName;

    @Value("${scan-url}")
    private String url;

    @Value("${opswat-apikey-demo-scan-file}")
    private String apikey;

    @Autowired
    public FileLookupImpl(RestTemplate template) {
        this.template = template;
    }

    /**
     * hashing lookup with opswat api
     * @param fileHashing the file hash string
     * */
    @Override
    public void lookup(String fileHashing) {
        System.out.println("searching ...");
        HttpHeaders headers = new HttpHeaders();
        headers.set("apikey", apikey);
        HttpEntity entity = new HttpEntity(headers);
        try {
            HttpEntity<JSONObject> ob = template.exchange(url+fileHashing,
                    HttpMethod.GET,
                    entity ,
                    JSONObject.class);
            handleResponse((new JsonParser()).parse(ob.getBody().toString()).getAsJsonObject());
        }catch (HttpClientErrorException.NotFound ex){
            fileExist = false;
        }
    }

    /**
     * handle the response from opswat hash lookup api
     * */
    private void handleResponse(JsonObject json){

        Object error = json.get("error");
        fileExist = (error == null);

        if(error != null){
            handleError(json);
        }else {
            handleResource(json);
        }
    }

    /**
     * handle the successful response from opswat hash lookup api
     * */
    private void handleResource(JsonObject json){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println("file name: "+ fileName);
        Set<Map.Entry<String, JsonElement>> map = json.getAsJsonObject("scan_results")
                .getAsJsonObject("scan_details").entrySet();
        map = map.stream().limit(6).collect(Collectors.toSet());
        map.forEach(e->{
            System.out.println("engine: "+e.getKey());
            System.out.println(gson.toJson(e.getValue()));
            System.out.println("_______");
        });
    }

    /**
     * handle the error response from opswat hash lookup api
     * */
    private void handleError(JsonObject object){
        JSONParser parser = new JSONParser();
        try {
            JSONObject eObj = (JSONObject) parser.parse(object.get("error").toString());
            System.out.println(eObj.get("messages"));
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public boolean isFileExist() {
        return fileExist;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
