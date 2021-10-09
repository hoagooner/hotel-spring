package fa.training.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import fa.training.dto.RoomDTO;
import fa.training.entities.RoomEntity;

public interface RoomService {

	public Page<RoomEntity> getAll(int pageNumber, int pageSize, String sortBy, String sortDirection);
	
	public List<RoomEntity> getAll();

	public Page<RoomEntity> findByName(String number, int pageNumber, int pageSize, String sortBy, String sortDirection);

	public RoomDTO save(RoomDTO roomDTO);

	public Optional<RoomDTO> getById(int id);

	public void delete(int id) ;
	
	public RoomEntity checkNameExists(int name);
	
	public List<Integer> getBookedRoom(Date checkin, Date checkout);
}
