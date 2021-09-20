package fa.training.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fa.training.entities.PriceEntity;

public interface PriceRepository extends JpaRepository<PriceEntity, Integer> {

}
