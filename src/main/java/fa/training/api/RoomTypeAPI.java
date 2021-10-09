package fa.training.api;

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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import fa.training.dto.RoomTypeDTO;
import fa.training.entities.RoomTypeEntity;
import fa.training.services.FilesStorageService;
import fa.training.services.RoomTypeService;

@RestController
public class RoomTypeAPI {

	@Autowired
	private RoomTypeService roomTypeService;

	@Autowired
	FilesStorageService storageService;

	/**
	 * @param query
	 * @param pageNumber
	 * @param pageSize
	 * @return Page<RoomTypeEntity>
	 */
	@GetMapping("/api/room-types")
	public Object getRoomTypes(@RequestParam(name = "query", defaultValue = "", required = false) String query,
			@RequestParam(name = "sortBy", defaultValue = "id", required = false) String sortBy,
			@RequestParam(name = "sortDirection", defaultValue = "desc", required = false) String sortDirection,
			@RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber,
			@RequestParam(name = "pageSize", defaultValue = "5") int pageSize) {
		pageNumber = pageNumber != 0 ? pageNumber - 1 : pageNumber;
		Page<RoomTypeEntity> roomTypes;
		if (query.isEmpty()) {
			roomTypes = roomTypeService.getAll(pageNumber, pageSize, sortBy, sortDirection);
		} else {
			roomTypes = roomTypeService.findByName(query, pageNumber, pageSize, sortBy, sortDirection);
		}
		if (roomTypes.isEmpty()) {
			return new ResponseEntity<>(Page.empty(), HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(roomTypes, HttpStatus.OK);
		}
	}

	/**
	 * 
	 * @param facility
	 * @return ResponseEntity<RoomTypeEntity>
	 */
	@PostMapping(value = "/api/room-types", consumes = { "multipart/form-data"})
	public Object addRoomType(@Valid @RequestPart("roomType") RoomTypeDTO roomType,
			@RequestParam(name = "file", required = false) MultipartFile file) {
			try {
				if(file!=null) {
					String filename =  storageService.save(file);
					roomType.setImage(filename);
				}
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.OK).body("Could not upload the file");
			}
		return ResponseEntity.ok().body(roomTypeService.save(roomType));
	}

	@DeleteMapping("/api/room-types/{id}")
	public Object deleteFacility(@PathVariable("id") int id) {
		RoomTypeDTO roomType = roomTypeService.getById(id);
		if (roomType != null) {
			roomTypeService.delete(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/api/room-types/{id}")
	public Object updateFacility(@PathVariable("id") int id,@RequestBody RoomTypeDTO roomTypeDTO) {
		RoomTypeDTO roomType = roomTypeService.getById(id);
		if (roomType != null) {
			roomTypeService.save(roomTypeDTO);
			return new ResponseEntity<>(roomTypeDTO, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/api/room-types/{id}")
	public Object getFacility(@PathVariable("id") int id) {
		RoomTypeDTO roomType = roomTypeService.getById(id);
		if (roomType != null) {
			return new ResponseEntity<>(roomType, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/api/room-types/search")
	public Object getFacilityByName(@RequestParam("name") String name) {
		RoomTypeEntity obj =  roomTypeService.checkNameExists(name);
		if(obj != null ) {
			return ResponseEntity.ok().body(obj);
		}
		return  ResponseEntity.notFound().build();
	}
	
	@GetMapping("/api/room-types/list")
	public Object getAllRoomTypes() {
		return roomTypeService.getAll();
	}
}
