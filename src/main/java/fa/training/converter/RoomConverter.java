package fa.training.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fa.training.dto.RoomDTO;
import fa.training.dto.RoomOfRoomType;
import fa.training.entities.RoomEntity;

@Component
public class RoomConverter {
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	public RoomEntity toEntity(RoomDTO roomDTO) {
		return modelMapper.map(roomDTO, RoomEntity.class);
	}
	
	public RoomDTO toDTO(RoomEntity room) {
		return modelMapper.map(room, RoomDTO.class);
	}
	
	public RoomOfRoomType toRoomOfRoomType(RoomEntity room) {
		return modelMapper.map(room, RoomOfRoomType.class);
	}
}
