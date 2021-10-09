package fa.training.services.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import fa.training.services.FilesStorageService;

@Service
public class FilesStorageServiceImpl implements FilesStorageService {

	private final Path root = Paths.get("src/main/resources/static/upload");

	@Override
	public void init() {
		if (!Files.exists(root)) {
			try {
				Files.createDirectory(root);
			} catch (IOException e) {
				throw new RuntimeException("Could not initialize folder for upload!");
			}
		}
	}

	@Override
	public String save(MultipartFile file) {
		this.init();
		try {
//			Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
			String filename = new Date().getTime() + "_" + file.getOriginalFilename();
			byte[] bytes = file.getBytes();
			String insPath = this.root.toString() +"/"+ filename;
			 Files.write(Paths.get(insPath), bytes);
			return filename;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	@Override
	public void deleteFile(String fileName) {
		Path dir = Paths.get(this.root.toString(), "/" + fileName);
		FileSystemUtils.deleteRecursively(dir.toFile());
	}
}