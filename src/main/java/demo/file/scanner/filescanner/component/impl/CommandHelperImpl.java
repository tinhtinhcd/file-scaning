package demo.file.scanner.filescanner.component.impl;

import demo.file.scanner.filescanner.component.CommandHelper;
import org.springframework.stereotype.Component;

@Component
public class CommandHelperImpl implements CommandHelper {

    @Override
    public void listCommand() {
        System.out.println("\nApplication commands:");
        System.out.format( "%-30s%-25s \n",  "exit, -e","Exit application");
        System.out.format( "%-30s%-25s \n\n","upload, -u [file-path]","Upload and scan file.");
    }
}
