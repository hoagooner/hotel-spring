package fa.training.services;

import org.springframework.data.domain.Page;

import fa.training.entities.FacilityEntity;

public interface FacilityService {

	/**
	 * This function return facility list
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @return Page<FacilityEntity>
	 */
	public Page<FacilityEntity> getAll(int pageNumber, int pageSize);

	public Page<FacilityEntity> findByName(String name, int pageNumber, int pageSize);

	public void save(FacilityEntity facility);

	public FacilityEntity getById(int id);

	public void delete(int id);
	
	public Object checkNameExists(String name);
}
