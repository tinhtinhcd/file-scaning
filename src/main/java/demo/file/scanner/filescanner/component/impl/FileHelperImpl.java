package demo.file.scanner.filescanner.component.impl;

import demo.file.scanner.filescanner.component.FileHelper;
import org.springframework.stereotype.Component;
import org.springframework.util.InvalidMimeTypeException;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;
import java.io.FileNotFoundException;

@Component
public class FileHelperImpl implements FileHelper {

    /**
     * validate if file exist in local
     * if not throw a FileNotFoundException
     * or else return the file
     * @param file is the path to local file
     * */
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

    /**
     * validate file type
     * currently only accept file extension as txt
     * */
    private void validFileType(File file){
        System.out.println("validation ...");
        MimetypesFileTypeMap fileTypeMap = new MimetypesFileTypeMap();
        String type = fileTypeMap.getContentType(file);
        if(!type.equals("text/plain"))
            throw new InvalidMimeTypeException(type,"only accept file type as \"text/plain\"");
    }
}
