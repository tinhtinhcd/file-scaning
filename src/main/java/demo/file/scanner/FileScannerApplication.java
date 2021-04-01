package demo.file.scanner;

import demo.file.scanner.component.CommandHelper;
import demo.file.scanner.component.Upload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class FileScannerApplication implements CommandLineRunner {

	@Autowired
	CommandHelper commandHelper;

	@Autowired
	Upload upload;

	public static void main(String[] args) {
		SpringApplication.run(FileScannerApplication.class, args);
	}

	@Override
	public void run(String... args) {
		commandHelper.listCommand();
		Scanner in = new Scanner(System.in);

		while (in.hasNext()){
			String s = in.nextLine();
			if(s.equalsIgnoreCase("exit") || s.equals("-e"))
				System.exit(0);

			else if(s.startsWith("upload")||s.startsWith("Upload")||s.startsWith("-u")){
				upload.upload(s);
			}

			else {
				System.out.printf("command not found: '" +s+ "'");
				commandHelper.listCommand();
			}

		}
	}
}
