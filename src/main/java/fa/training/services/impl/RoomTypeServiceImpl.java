package fa.training.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import fa.training.entities.FacilityEntity;
import fa.training.entities.RoomTypeEntity;
import fa.training.repositories.RoomTypeRepository;
import fa.training.services.FacilityService;
import fa.training.services.RoomTypeService;

@Service
public class RoomTypeServiceImpl implements RoomTypeService{
	
	@Autowired
	private RoomTypeRepository roomTypeRepository;

	@Override
	public Page<RoomTypeEntity> getAll(int pageNumber, int pageSize) {
		Page<RoomTypeEntity> page = roomTypeRepository
				.findAll(PageRequest.of(pageNumber, pageSize, Sort.by("id").descending()));
		return page;
	}

	@Override
	public Page<RoomTypeEntity> findByName(String name, int pageNumber, int pageSize) {
		return null;
	}

	@Override
	public void save(RoomTypeEntity roomType) {
		roomTypeRepository.save(roomType);
	}

	@Override
	public RoomTypeEntity getById(int id) {
		Optional<RoomTypeEntity> roomType = roomTypeRepository.findById(id);
		if (roomType.isPresent()) {
			return roomType.get();
		}
		return null;
	}

	@Override
	public void delete(int id) {
		roomTypeRepository.deleteById(id);
	}

	@Override
	public Object checkNameExists(String name) {
		return roomTypeRepository.findByName(name);
	}

	@Override
	public List<RoomTypeEntity> getAll() {
		return roomTypeRepository.findAll();
	}

}
