package fa.training.services;

import java.util.List;

import org.springframework.data.domain.Page;

import fa.training.entities.RoomTypeEntity;

public interface RoomTypeService {
	
	public Page<RoomTypeEntity> getAll(int pageNumber, int pageSize);
	
	public List<RoomTypeEntity> getAll();

	public Page<RoomTypeEntity> findByName(String name, int pageNumber, int pageSize);

	public void save(RoomTypeEntity roomType);

	public RoomTypeEntity getById(int id);

	public void delete(int id);

	public Object checkNameExists(String name);
	
}
