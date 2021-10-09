package fa.training.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fa.training.entities.RoomTypeEntity;

public interface RoomTypeRepository extends JpaRepository<RoomTypeEntity, Integer> {

	RoomTypeEntity findByNameAndDeleteFlagFalse(String False);
	
	Page<RoomTypeEntity> findByNameContainingAndDeleteFlagFalse(String name, Pageable pageable);
	
	Page<RoomTypeEntity> findAllByDeleteFlagFalse(Pageable pageable);
	
	List<RoomTypeEntity> findAllByDeleteFlagFalse();

	public RoomTypeEntity findByIdAndDeleteFlagFalse(int id);
	
	@Query("select r.id, r.name  from RoomTypeEntity r where :facilityId in (select f.id from r.facilities f)")
	public Object[] findRoomTypeHasFacility(@Param("facilityId") int facilityId);
}
