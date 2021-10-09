package fa.training.services;

import java.util.List;

import org.springframework.data.domain.Page;

import fa.training.dto.RoomTypeDTO;
import fa.training.dto.RoomTypeInRoom;
import fa.training.entities.RoomTypeEntity;

public interface RoomTypeService {
	
	public Page<RoomTypeEntity> getAll(int pageNumber, int pageSize, String sortBy, String sortDirection);
	
	public List<RoomTypeInRoom> getAll();

	public Page<RoomTypeEntity> findByName(String name, int pageNumber, int pageSize, String sortBy, String sortDirection);

	public RoomTypeDTO save(RoomTypeDTO roomType);

	public RoomTypeDTO getById(int id);

	public void delete(int id);

	public RoomTypeEntity checkNameExists(String name);
	
	public Object[] findRoomTypeHasFacility(int facilityId);

}
