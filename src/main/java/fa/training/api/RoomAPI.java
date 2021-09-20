package fa.training.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoomAPI {

	@GetMapping("/api/rooms")
	public Object getList() {
		return "room";
	}
}
