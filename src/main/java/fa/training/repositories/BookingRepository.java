package fa.training.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fa.training.entities.BookingEntity;

public interface BookingRepository extends JpaRepository<BookingEntity, Integer>{

}
