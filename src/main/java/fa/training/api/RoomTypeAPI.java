package fa.training.api;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fa.training.entities.RoomTypeEntity;
import fa.training.services.RoomTypeService;

@RestController
public class RoomTypeAPI {
	@Autowired
	private RoomTypeService roomTypeService;

	/**
	 * @param query
	 * @param pageNumber
	 * @param pageSize
	 * @return Page<RoomTypeEntity>
	 */
//	@GetMapping("/api/room-types")
	public Object getroomTypes(@RequestParam(name = "query", defaultValue = "", required = false) String query,
			@RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber,
			@RequestParam(name = "pageSize", defaultValue = "5") int pageSize) {
		pageNumber = pageNumber != 0 ? pageNumber - 1 : pageNumber;
		Page<RoomTypeEntity> roomTypes;
		if (query.isEmpty()) {
			roomTypes = roomTypeService.getAll(pageNumber, pageSize);
		} else {
			roomTypes = roomTypeService.findByName(query, pageNumber, pageSize);
		}
		if (roomTypes.isEmpty()) {
			return new ResponseEntity<>(Page.empty(), HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(roomTypes, HttpStatus.OK);
		}
	}

	@GetMapping("/api/room-types")
	public Object getroomTypes() {
		List<RoomTypeEntity> roomTypes = roomTypeService.getAll();
		if (roomTypes.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			Collections.reverse(roomTypes);
			return new ResponseEntity<>(roomTypes, HttpStatus.OK);
		}
	}

	/**
	 * 
	 * @param facility
	 * @return ResponseEntity<RoomTypeEntity>
	 */
	@PostMapping("/api/room-types")
	public ResponseEntity<RoomTypeEntity> addFacility(@Valid @RequestBody RoomTypeEntity facility) {
		try {
			roomTypeService.save(facility);
			return ResponseEntity.ok().body(facility);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().build();
		}
	}

	@DeleteMapping("/api/room-types/{id}")
	public Object deleteFacility(@PathVariable("id") int id) {
		RoomTypeEntity facility = roomTypeService.getById(id);
		if (facility != null) {
			roomTypeService.delete(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/api/room-types/{id}")
	public Object updateFacility(@PathVariable("id") int id, @RequestBody RoomTypeEntity roomType) {
		RoomTypeEntity _roomType = roomTypeService.getById(id);
		if (_roomType != null) {
			roomTypeService.save(roomType);
			return new ResponseEntity<>(roomType, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/api/room-types/{id}")
	public Object getFacility(@PathVariable("id") int id) {
		RoomTypeEntity roomType = roomTypeService.getById(id);
		if (roomType != null) {
			return new ResponseEntity<>(roomType, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/api/room-types/search")
	public Object getFacilityByName(@RequestParam("name") String name) {
		Object roomType = roomTypeService.checkNameExists(name);
		if (roomType != null) {
			return new ResponseEntity<>(roomType, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
