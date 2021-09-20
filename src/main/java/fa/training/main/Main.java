package fa.training.main;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.util.FileSystemUtils;

public class Main {
	public static void main(String[] args) {
		Path dir = Paths.get("src/main/resources/static/upload");
		dir = Paths.get(dir.toString(), "/facilities.png");
		System.out.println(dir);
		FileSystemUtils.deleteRecursively(dir.toFile());
	}
}
