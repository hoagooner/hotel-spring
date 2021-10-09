package fa.training.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fa.training.entities.PriceEntity;

public interface PriceRepository extends JpaRepository<PriceEntity, Integer> {

	@Query(value = "Select TOP 1 price from prices where room_type_id = :roomTypeId ORDER BY modified_date DESC",nativeQuery = true)
	double getLatestPriceOfRoomType(@Param("roomTypeId") int roomTypeId);

	Object[] findByRoomTypeId(int roomTypeId);

}
