package fa.training;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fa.training.services.FilesStorageService;

@SpringBootApplication
public class HotelApplication implements CommandLineRunner {

	@Resource
	FilesStorageService storageService;

	public static void main(String[] args) {
		SpringApplication.run(HotelApplication.class, args);
	}

	@Override
	public void run(String... arg) throws Exception {
	}

}
