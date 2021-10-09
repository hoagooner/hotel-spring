package fa.training.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import fa.training.validator.UniqueFacility;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FacilityDTO {

	
	private int id;

	@NotEmpty(message = "{facility.name.notempty}")
	@Size(max  = 50, message = "{facility.name.invalid_length}")
	@UniqueFacility(message = "{facility.name.unique}")
	private String name;

	@Size(max  = 255, message = "{facility.description.invalid_length}")
	private String description;

	private String icon;
	
	private boolean deleteFlag;

}
