package fa.training.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import fa.training.models.ResponseMessage;
import fa.training.services.FilesStorageService;

@Controller
public class UploadFileAPI {
	
	@Autowired
	FilesStorageService storageService;

	@PostMapping("/api/images")
	public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
		try {
			String filename = storageService.save(file);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(filename));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage("Error"));
		}
	}

	@DeleteMapping("/api/images/{filename}")
	public Object deleteImage(@PathVariable("filename") String fileName) {
		String message = "";
		try {
			storageService.deleteFile(fileName);
			message = "Deleted the file successfully";
			return ResponseEntity.status(HttpStatus.OK).body(message);
		} catch (Exception e) {
			e.printStackTrace();
			message = "Could not delete file";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
		}
		
	}

}