package fa.training.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fa.training.dto.BookingDTO;
import fa.training.entities.BookingEntity;

@Component
public class BookingConverter {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public BookingDTO toDTO(BookingEntity booking) {
		return modelMapper.map(booking, BookingDTO.class);
	}
	
	public BookingEntity toEntity(BookingDTO booking) {
		return modelMapper.map(booking, BookingEntity.class);
	}

}
