package fa.training.converter;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fa.training.dto.FacilityDTO;
import fa.training.dto.PriceDTO;
import fa.training.dto.RoomOfRoomType;
import fa.training.dto.RoomTypeDTO;
import fa.training.dto.RoomTypeInRoom;
import fa.training.entities.FacilityEntity;
import fa.training.entities.PriceEntity;
import fa.training.entities.RoomTypeEntity;

@Component
public class RoomTypeConverter {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private FacilityConverter facilityConverter;
	
	@Autowired
	private RoomConverter roomConverter;
	
	@Autowired
	private PriceConverter priceConverter;

	public RoomTypeEntity toEntiy(RoomTypeDTO roomTypeDTO) {
		
		RoomTypeEntity roomType = modelMapper.map(roomTypeDTO, RoomTypeEntity.class);
		if(roomTypeDTO.getFacilities()!=null) {
			Set<FacilityEntity> facilities = roomTypeDTO.getFacilities().stream()
					.map(facility -> facilityConverter.toEntity(facility)).collect(Collectors.toSet());
			roomType.setFacilities(facilities);
		}
		
		if(roomTypeDTO.getPrices()!=null) {
			List<PriceEntity> prices = roomTypeDTO.getPrices().stream()
					.map(price -> priceConverter.toEntity(price)).collect(Collectors.toList());
			roomType.setPrices(prices);
		}
		return roomType;
	}

	public RoomTypeDTO toDTO(RoomTypeEntity roomType) {
		// convert to facility DTO list
		Set<FacilityDTO> facilityDTOs = roomType.getFacilities().stream()
				.map(facility -> facilityConverter.toDTO(facility)).collect(Collectors.toSet());
		
		// convert to  price DTO list
		List<PriceDTO> prices = roomType.getPrices().stream()
				.map(price -> priceConverter.toDTO(price)).collect(Collectors.toList());
		
		// convert to  room of room type list
		Set<RoomOfRoomType> roomsOfRoomType = roomType.getRooms().stream()
				.map(room -> roomConverter.toRoomOfRoomType(room)).collect(Collectors.toSet());
		
		RoomTypeDTO roomTypeDTO = modelMapper.map(roomType, RoomTypeDTO.class);
		
		roomTypeDTO.setFacilities(facilityDTOs);
		roomTypeDTO.setRooms(roomsOfRoomType);
		roomTypeDTO.setPrices(prices);
		
		return roomTypeDTO;
	}
	
	public RoomTypeEntity toEntity(RoomTypeInRoom roomType) {
		return modelMapper.map(roomType,RoomTypeEntity.class);
	}
	
	public RoomTypeInRoom  toRoomTypeInRoom(RoomTypeEntity roomType) {
		return modelMapper.map(roomType,RoomTypeInRoom.class);
	}
	

}
