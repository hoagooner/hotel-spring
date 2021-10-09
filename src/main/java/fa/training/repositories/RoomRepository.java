package fa.training.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fa.training.entities.RoomEntity;

public interface RoomRepository extends JpaRepository<RoomEntity, Integer> {

	Page<RoomEntity> findByDeleteFlagFalseAndRoomNumber(int roomNumber, Pageable pageable);

	Page<RoomEntity> findAllByDeleteFlagFalse(Pageable pageable);

	List<RoomEntity> findAllByDeleteFlagFalse();

	public RoomEntity findByRoomNumberAndDeleteFlagFalse(int name);

	public RoomEntity findByIdAndDeleteFlagFalse(int id);
	
	@Query(value="select a.id from RoomEntity a join a.bookingDetails b where b.checkout between :checkin  and  :checkout or b.checkin between :checkin and :checkout")
    public List<Integer> getBookedRoom(@Param("checkin") Date checkIn,@Param("checkout")  Date checkout);

}