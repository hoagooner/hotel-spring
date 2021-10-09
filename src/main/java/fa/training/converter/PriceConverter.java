package fa.training.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fa.training.dto.PriceDTO;
import fa.training.entities.PriceEntity;

@Component
public class PriceConverter {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public PriceEntity toEntity(PriceDTO priceDTO) {
		return modelMapper.map(priceDTO, PriceEntity.class);
	}
	
	public PriceDTO toDTO(PriceEntity priceEntity) {
		return modelMapper.map(priceEntity, PriceDTO.class);
	}

}
