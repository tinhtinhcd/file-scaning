package demo.file.scanner;

import demo.file.scanner.service.CommandHelper;
import demo.file.scanner.service.Upload;
import demo.file.scanner.service.impl.CommandHelperImpl;
import demo.file.scanner.service.impl.FileHelperImpl;
import demo.file.scanner.service.impl.UploadImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class FileScannerApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(FileScannerApplication.class, args);
	}

	@Override
	public void run(String... args) {
		CommandHelper commandHelper = new CommandHelperImpl();
		commandHelper.listCommand();
		Scanner in = new Scanner(System.in);

		while (in.hasNext()){
			String s = in.nextLine();
			if(s.equalsIgnoreCase("exit") || s.equals("-e"))
				System.exit(0);

			else if(s.startsWith("upload")||s.startsWith("Upload")||s.startsWith("-u")){
				Upload upload = new UploadImpl(new FileHelperImpl());
				upload.upload(s);
			}

			else {
				System.out.printf("command not found: '" +s+ "'");
				commandHelper.listCommand();
			}

		}
	}
}
