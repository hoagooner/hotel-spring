package fa.training.api;

import java.util.Optional;

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

import fa.training.dto.FacilityDTO;
import fa.training.entities.FacilityEntity;
import fa.training.services.FacilityService;

@RestController
public class FacilityAPI {

	@Autowired
	private FacilityService facilityServiceImpl;

	/**
	 * 
	 * @param query
	 * @param sortBy
	 * @param sortDirection
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	@GetMapping("/api/facilities")
	public Object getFacilities(@RequestParam(name = "query", defaultValue = "", required = false) String query,
			@RequestParam(name = "sortBy", defaultValue = "id", required = false) String sortBy,
			@RequestParam(name = "sortDirection", defaultValue = "desc", required = false) String sortDirection,
			@RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber,
			@RequestParam(name = "pageSize", defaultValue = "5") int pageSize) {

		Page<FacilityEntity> facilities;
		if (query.isEmpty()) {
			facilities = facilityServiceImpl.getAll(pageNumber, pageSize, sortBy, sortDirection);
		} else {
			facilities = facilityServiceImpl.findByName(query, pageNumber, pageSize, sortBy, sortDirection);
		}
		return facilities.isEmpty() ? new ResponseEntity<>(Page.empty(), HttpStatus.NO_CONTENT)
				: new ResponseEntity<>(facilities, HttpStatus.OK);
	}

	@GetMapping("/api/facilities/{id}")
	public Object getFacility(@PathVariable("id") int id) {
		Optional<FacilityEntity> facilityOptional = facilityServiceImpl.getById(id);
		return facilityOptional.map(facility -> ResponseEntity.ok().body(facility))
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@GetMapping("/api/facilities/search")
	public Object getFacilityByName(@RequestParam("name") String name) {
		FacilityEntity facility =  facilityServiceImpl.checkNameExists(name);
		if(facility != null) {
			return ResponseEntity.ok().body(facility);
		}
		return  ResponseEntity.notFound().build();
	}

	@GetMapping("/api/facilities/list")
	public Object getFacilities() {
		Object facilities = facilityServiceImpl.getAll();
		return (facilities != null) ? ResponseEntity.ok().body(facilities) : ResponseEntity.noContent();
	}

	/**
	 * 
	 * @param facility
	 * @return ResponseEntity<FacilityEntity>
	 */
	@PostMapping("/api/facilities")
	public ResponseEntity<FacilityDTO> addFacility(@Valid @RequestBody FacilityDTO facility) {
		return ResponseEntity.ok().body(facilityServiceImpl.save(facility));
	}

	@DeleteMapping(value = "/api/facilities/{id}")
	public Object deleteFacility(@PathVariable("id") int id) {
		Optional<FacilityEntity> facilityOptional = facilityServiceImpl.getById(id);
		return facilityOptional.map(facility -> {
			facilityServiceImpl.delete(id);
			return ResponseEntity.ok().build();
		}).orElseGet(() -> ResponseEntity.internalServerError().build());
	}

	@PutMapping("/api/facilities/{id}")
	public Object updateFacility(@PathVariable("id") int id, @RequestBody FacilityDTO facilityDTO) {
		Optional<FacilityEntity> facilityOptional = facilityServiceImpl.getById(id);
		return facilityOptional.map(facility -> {
			facilityDTO.setId(facility.getId());
			return new ResponseEntity<>(facilityServiceImpl.save(facilityDTO), HttpStatus.OK);
		}).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

	}

}
