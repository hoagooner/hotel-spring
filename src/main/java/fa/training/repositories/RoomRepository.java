package fa.training.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fa.training.entities.RoomEntity;

public interface RoomRepository extends JpaRepository<RoomEntity, Integer> {

}
