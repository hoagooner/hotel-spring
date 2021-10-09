package fa.training.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fa.training.entities.BookingDetailEntity;

public interface BookingDetailRepository  extends JpaRepository<BookingDetailEntity, Integer>{

}
