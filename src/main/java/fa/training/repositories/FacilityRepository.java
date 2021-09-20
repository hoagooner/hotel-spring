package fa.training.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import fa.training.entities.FacilityEntity;

public interface FacilityRepository extends JpaRepository<FacilityEntity, Integer> {
	
	Page<FacilityEntity> findByNameContaining(String name, Pageable pageable);
	
	public FacilityEntity findByName(String name);

}
