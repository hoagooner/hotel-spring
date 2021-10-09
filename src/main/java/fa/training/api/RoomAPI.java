package fa.training.api;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fa.training.dto.RoomDTO;
import fa.training.entities.RoomEntity;
import fa.training.services.RoomService;

@CrossOrigin(maxAge = 3600)
@RestController
public class RoomAPI {

	@Autowired
	private RoomService roomServiceImpl;

	/**
	 * 
	 * @param query
	 * @param sortBy
	 * @param sortDirection
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	@GetMapping("/api/rooms")
	public Object getFacilities(@RequestParam(name = "query", required = false) String query,
			@RequestParam(name = "sortBy", defaultValue = "id", required = false) String sortBy,
			@RequestParam(name = "sortDirection", defaultValue = "desc", required = false) String sortDirection,
			@RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber,
			@RequestParam(name = "pageSize", defaultValue = "5") int pageSize) {
		
		Page<RoomEntity> rooms;
		if (query == null || query.isEmpty()) {
			rooms = roomServiceImpl.getAll(pageNumber, pageSize, sortBy, sortDirection);
		} else {
			rooms = roomServiceImpl.findByName(query, pageNumber, pageSize, sortBy, sortDirection);
		}
		return rooms.isEmpty() ? new ResponseEntity<>(Page.empty(), HttpStatus.NO_CONTENT)
				: new ResponseEntity<>(rooms, HttpStatus.OK);
	}

	@GetMapping("/api/rooms/{id}")
	public Object getRoom(@PathVariable("id") int id) {
		Optional<RoomDTO> roomOptional = roomServiceImpl.getById(id);
		return roomOptional.map(room -> ResponseEntity.ok().body(room))
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@GetMapping("/api/rooms/search")
	public Object getRoomByName(@RequestParam("name") int name) {
		RoomEntity obj =  roomServiceImpl.checkNameExists(name);
		if(obj != null) {
			return ResponseEntity.ok().body(obj);
		}
		return  ResponseEntity.notFound().build();
	}

	@GetMapping("/api/rooms/list")
	public Object getRooms() {
		Object rooms = roomServiceImpl.getAll();
		return (rooms != null) ? ResponseEntity.ok().body(rooms) : ResponseEntity.noContent();
	}

	/**
	 * 
	 * @param Room
	 * @return ResponseEntity<RoomEntity>
	 */
	@PostMapping("/api/rooms")
	public ResponseEntity<RoomDTO> addRoom(@Valid @RequestBody RoomDTO room) {
		return ResponseEntity.ok().body(roomServiceImpl.save(room));
	}

	@DeleteMapping(value = "/api/rooms/{id}")
	public Object deleteRoom(@PathVariable("id") int id) {
		Optional<RoomDTO> RoomOptional = roomServiceImpl.getById(id);
		return RoomOptional.map(room -> {
			roomServiceImpl.delete(id);
			return ResponseEntity.ok().build();
		}).orElseGet(() -> ResponseEntity.internalServerError().build());
	}

	@PutMapping("/api/rooms/{id}")
	public Object updateRoom(@PathVariable("id") int id, @RequestBody RoomDTO roomDTO) {
		Optional<RoomDTO> RoomOptional = roomServiceImpl.getById(id);
		return RoomOptional.map(room -> {
			roomDTO.setId(room.getId());
			return new ResponseEntity<>(roomServiceImpl.save(roomDTO), HttpStatus.OK);
		}).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

	}

}
