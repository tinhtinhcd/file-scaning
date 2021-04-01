package demo.file.scanner.filescanner.component;

import java.io.File;
import java.io.FileNotFoundException;

public interface FileHelper {
    File getFile(String path) throws FileNotFoundException;
}
