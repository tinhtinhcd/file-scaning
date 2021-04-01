package demo.file.scanner.filescanner.component.impl;

import demo.file.scanner.filescanner.component.FileLookup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FileLookupImpl implements FileLookup {

    private RestTemplate template;

    @Autowired
    public FileLookupImpl(RestTemplate template) {
        this.template = template;
    }

    @Override
    public String lookup(String fileHashing) {
        System.out.println("searching ...");

        Object ob = template.getForEntity("https://api.metadefender.com/v4/hash/", Object.class);
        System.out.println(ob.toString());

        return null;
    }
}
