package demo.file.scanner.service.impl;

import demo.file.scanner.service.CommandHelper;

public class CommandHelperImpl implements CommandHelper {

    @Override
    public void listCommand() {
        System.out.println("\nApplication commands:");
        System.out.format( "%-30s%-25s \n",  "exit, -e","Exit application");
        System.out.format( "%-30s%-25s \n\n","upload, -u [file-path]","Upload and scan file.");
    }
}
