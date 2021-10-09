package fa.training.services;

import org.springframework.web.multipart.MultipartFile;

public interface FilesStorageService {
	
	public void init();

	public String save(MultipartFile file);

	public void deleteFile(String fileName);
	
}
