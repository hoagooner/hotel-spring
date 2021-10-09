package fa.training.repositories;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import fa.training.entities.FacilityEntity;

public interface FacilityRepository extends JpaRepository<FacilityEntity, Integer> {
	
	Page<FacilityEntity> findByNameContainingAndDeleteFlagFalse(String name, Pageable pageable);
	
	Page<FacilityEntity> findAllByDeleteFlagFalse(Pageable pageable);
	
	List<FacilityEntity> findAllByDeleteFlagFalse();
	
	public FacilityEntity findByNameAndDeleteFlagFalse(String name);
	
	public FacilityEntity findByIdAndDeleteFlagFalse(int id);

}
