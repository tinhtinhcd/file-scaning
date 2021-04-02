package demo.file.scanner.filescanner;

import demo.file.scanner.filescanner.component.CommandHelper;
import demo.file.scanner.filescanner.component.Uploader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Scanner;

@SpringBootApplication
public class FileScannerApplication implements CommandLineRunner {

	@Autowired
	CommandHelper commandHelper;

	@Autowired
	Uploader uploader;

	public static void main(String[] args) {
		SpringApplication.run(FileScannerApplication.class, args);
	}


	/**
	 * application listen to the input from command line.
	 * if command start with exit or -e then terminate the application
	 * if command start with upload or -u then validate, lookup and upload.
	 * or else show the command helper.
	 */
	@Override
	public void run(String... args) {
		commandHelper.listCommand();
		Scanner in = new Scanner(System.in);

		while (in.hasNext()){
			String s = in.nextLine();
			if(s.equalsIgnoreCase("exit") || s.equals("-e"))
				System.exit(0);

			else if(s.startsWith("upload")||s.startsWith("Upload")||s.startsWith("-u")){
				uploader.upload(s);
			}

			else {
				System.out.printf("command not found: '" +s+ "'");
				commandHelper.listCommand();
			}

		}
	}

	/**
	 * create bean for restTemplate that using by lookup and upload
	 * */
	@Bean
	RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
		return restTemplate;
	}

}
