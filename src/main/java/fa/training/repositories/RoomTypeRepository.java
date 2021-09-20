package fa.training.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fa.training.entities.RoomTypeEntity;

public interface RoomTypeRepository extends JpaRepository<RoomTypeEntity, Integer> {

	RoomTypeEntity findByName(String name);

}
