package fa.training.services.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import fa.training.converter.RoomTypeConverter;
import fa.training.dto.RoomTypeDTO;
import fa.training.dto.RoomTypeInRoom;
import fa.training.entities.PriceEntity;
import fa.training.entities.RoomTypeEntity;
import fa.training.repositories.RoomTypeRepository;
import fa.training.services.PriceService;
import fa.training.services.RoomTypeService;

@Service
public class RoomTypeServiceImpl implements RoomTypeService {

	@Autowired
	private RoomTypeRepository roomTypeRepository;

	@Autowired
	private PriceService priceService;
	
	@Autowired
	private RoomTypeConverter roomTypeConverter;

	public Page<RoomTypeEntity> getAll(int pageNumber, int pageSize, String sortBy, String sortDirection) {
		pageNumber = pageNumber != 0 ? pageNumber - 1 : pageNumber;
		Pageable pageable = PageRequest.of(pageNumber, pageSize,
				Sort.Direction.fromString(sortDirection.toUpperCase()), sortBy);
		Page<RoomTypeEntity> page = roomTypeRepository.findAllByDeleteFlagFalse(pageable);
		return page;
	}

	public Page<RoomTypeEntity> findByName(String name, int pageNumber, int pageSize, String sortBy,
			String sortDirection) {
		pageNumber = pageNumber != 0 ? pageNumber - 1 : pageNumber;
		Pageable pageable = PageRequest.of(pageNumber, pageSize,
				Sort.Direction.fromString(sortDirection.toUpperCase()), sortBy);
		Page<RoomTypeEntity> page = roomTypeRepository.findByNameContainingAndDeleteFlagFalse(name, pageable);
		return page;
	}

	@Override
	public RoomTypeDTO save(RoomTypeDTO roomTypeDTO) {
		
		RoomTypeEntity roomType = roomTypeConverter.toEntiy(roomTypeDTO);
		roomTypeRepository.save(roomType);
		// add new price if price updated
		PriceEntity price = new PriceEntity(roomType.getPrice(), new Date(), roomType);
		if(priceService.checkPriceAvailable(roomType.getId())) {
			if(roomType.getPrice() != priceService.getLatestPriceOfRoomType(roomType.getId())) {
				priceService.save(price);	
			}
		}else {
			priceService.save(price);
		}
		return roomTypeDTO;
	}

	@Override
	public RoomTypeDTO getById(int id) {
		Optional<RoomTypeEntity> roomType = roomTypeRepository.findById(id);
		if (roomType.isPresent()) {
			RoomTypeEntity roomTypeEntity = roomType.get();
			Collections.sort(roomTypeEntity.getPrices());
			RoomTypeDTO roomTypeDTO  = roomTypeConverter.toDTO(roomTypeEntity);
			return roomTypeDTO;
		}
		return null;
	}

	@Override
	public void delete(int id) {
		if(roomTypeRepository.existsById(id)) {
			RoomTypeEntity roomType =  roomTypeRepository.findById(id).get();
			roomType.setDeleteFlag(true);
			roomTypeRepository.save(roomType);
		}
	}

	@Override
	public RoomTypeEntity checkNameExists(String name) {
		return roomTypeRepository.findByNameAndDeleteFlagFalse(name);
	}

	@Override
	public List<RoomTypeInRoom> getAll() {
		List<RoomTypeEntity> roomTypes = roomTypeRepository.findAllByDeleteFlagFalse();
		return roomTypes.stream().map(roomType -> roomTypeConverter.toRoomTypeInRoom(roomType)).collect(Collectors.toList());
	}

	@Override
	public Object[] findRoomTypeHasFacility(int facilityId) {
		return roomTypeRepository.findRoomTypeHasFacility(facilityId);
	}

}
