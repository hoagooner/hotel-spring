package fa.training.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import fa.training.dto.FacilityDTO;
import fa.training.entities.FacilityEntity;
import fa.training.exception.ConflictedSqlException;

public interface FacilityService {

	/**
	 * This function return facility list
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @return Page<FacilityEntity>
	 */
	public Page<FacilityEntity> getAll(int pageNumber, int pageSize, String sortBy, String sortDirection);
	
	public List<FacilityEntity> getAll();

	public Page<FacilityEntity> findByName(String name, int pageNumber, int pageSize, String sortBy, String sortDirection);

	public FacilityDTO save(FacilityDTO facilityDTO);

	public Optional<FacilityEntity> getById(int id);

	public void delete(int id) ;
	
	public FacilityEntity checkNameExists(String name);
	
}
