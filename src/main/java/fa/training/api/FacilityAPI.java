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
import org.springframework.web.bind.annotation.RestController;

import fa.training.entities.FacilityEntity;
import fa.training.services.FacilityService;

@RestController
public class FacilityAPI {

	@Autowired
	private FacilityService facilityServiceImpl;

	/**
	 * @param query
	 * @param pageNumber
	 * @param pageSize
	 * @return Page<FacilityEntity>
	 */
	@GetMapping("/api/facilities")
	public Object getFacilities(@RequestParam(name = "query", defaultValue = "", required = false) String query,
			@RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber,
			@RequestParam(name = "pageSize", defaultValue = "5") int pageSize) {
		pageNumber = pageNumber != 0 ? pageNumber - 1 : pageNumber;
		Page<FacilityEntity> facilities;
		if (query.isEmpty()) {
			facilities = facilityServiceImpl.getAll(pageNumber, pageSize);
		} else {
			facilities = facilityServiceImpl.findByName(query, pageNumber, pageSize);
		}
		if (facilities.isEmpty()) {
			return new ResponseEntity<>(Page.empty(),HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(facilities, HttpStatus.OK);
		}
	}

	/**
	 * 
	 * @param facility
	 * @return ResponseEntity<FacilityEntity>
	 */
	@PostMapping("/api/facilities")
	public ResponseEntity<FacilityEntity> addFacility(@Valid @RequestBody FacilityEntity facility) {
		try {
			facilityServiceImpl.save(facility);
			return ResponseEntity.ok().body(facility);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().build();
		}
	}

	@DeleteMapping("/api/facilities/{id}")
	public Object deleteFacility(@PathVariable("id") int id) {
		FacilityEntity facility = facilityServiceImpl.getById(id);
		if (facility != null) {
			facilityServiceImpl.delete(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/api/facilities/{id}")
	public Object updateFacility(@PathVariable("id") int id, @RequestBody FacilityEntity facility) {
		FacilityEntity _facility = facilityServiceImpl.getById(id);
		if (_facility != null) {
			facilityServiceImpl.save(facility);
			return new ResponseEntity<>(facility, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/api/facilities/{id}")
	public Object getFacility(@PathVariable("id") int id) {
		FacilityEntity facility = facilityServiceImpl.getById(id);
		if (facility != null) {
			return new ResponseEntity<>(facility, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/api/facilities/search")
	public Object getFacilityByName(@RequestParam("name") String name) {
		Object facility = facilityServiceImpl.checkNameExists(name);
		if (facility != null) {
			return new ResponseEntity<>(facility, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	

}
