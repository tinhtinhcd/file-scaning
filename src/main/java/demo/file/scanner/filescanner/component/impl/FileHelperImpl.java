package demo.file.scanner.filescanner.component.impl;

import demo.file.scanner.filescanner.component.FileHelper;
import org.springframework.stereotype.Component;
import org.springframework.util.InvalidMimeTypeException;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;
import java.io.FileNotFoundException;

@Component
public class FileHelperImpl implements FileHelper {

    @Override
    public File getFile(String file) throws FileNotFoundException {

        System.out.println("get file ...");
        String fileName = file.substring(file.indexOf(' ')+1);
        fileName = fileName.replace("\\", "");
        if(fileName.length() < 1)
            throw new FileNotFoundException();

        File f = new File(fileName.trim());
        if(!f.exists()){
            throw new FileNotFoundException(fileName);
        }
        validFileType(f);

        return f;
    }

    private void validFileType(File file){
        System.out.println("validation ...");
        MimetypesFileTypeMap fileTypeMap = new MimetypesFileTypeMap();
        String type = fileTypeMap.getContentType(file);
        if(!type.equals("text/plain"))
            throw new InvalidMimeTypeException(type,"only accept file type as \"text/plain\"");
    }
}
