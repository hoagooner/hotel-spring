package fa.training.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fa.training.dto.BookingDetailDTO;
import fa.training.entities.BookingDetailEntity;

@Component
public class BookingDetailConverter {

	@Autowired
	private ModelMapper modelMapper;
	
	public BookingDetailDTO toDTO(BookingDetailEntity booking) {
		return modelMapper.map(booking, BookingDetailDTO.class);
	}
	
	public BookingDetailEntity toEntity(BookingDetailDTO booking) {
		return modelMapper.map(booking, BookingDetailEntity.class);
	}
}
