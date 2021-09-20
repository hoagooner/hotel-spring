package fa.training.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import fa.training.entities.FacilityEntity;
import fa.training.repositories.FacilityRepository;
import fa.training.services.FacilityService;

@Service
public class FacilityServiceImpl implements FacilityService{
	
	@Autowired
	private FacilityRepository facilityRepository;

	public Page<FacilityEntity> getAll(int pageNumber, int pageSize) {
		Page<FacilityEntity> page = facilityRepository
				.findAll(PageRequest.of(pageNumber, pageSize, Sort.by("id").descending()));
		return page;
	}

	public Page<FacilityEntity> findByName(String name, int pageNumber, int pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("id").descending());
		Page<FacilityEntity> page = facilityRepository.findByNameContaining(name, pageable);
		return page;
	}

	public void save(FacilityEntity facility) {
		facilityRepository.save(facility);
	}

	public FacilityEntity getById(int id) {
		Optional<FacilityEntity> facility = facilityRepository.findById(id);
		if (facility.isPresent()) {
			return facility.get();
		}
		return null;
	}

	public void delete(int id) {
		facilityRepository.deleteById(id);
	}

	@Override
	public Object checkNameExists(String name) {
		return facilityRepository.findByName(name);
	}
}
