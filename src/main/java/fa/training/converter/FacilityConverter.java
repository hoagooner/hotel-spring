package fa.training.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fa.training.dto.FacilityDTO;
import fa.training.entities.FacilityEntity;

@Component
public class FacilityConverter {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public FacilityEntity toEntity(FacilityDTO facilityDTO) {
		return modelMapper.map(facilityDTO, FacilityEntity.class);
	}
	
	public FacilityDTO toDTO(FacilityEntity facilityEntity) {
		return modelMapper.map(facilityEntity, FacilityDTO.class);
	}
}
