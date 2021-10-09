package fa.training.dto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import fa.training.validator.UniqueRoomType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoomTypeDTO {

	private int id;

	@NotEmpty(message = "{roomType.name.notempty}")
	@UniqueRoomType(message = "{roomType.name.unique}")
	private String name;

	private String description;

	@Min(value = 1, message = "{roomType.size.size}")
	@Max(value = 200, message = "{roomType.size.size}")
	private double size;

	@Min(value = 1, message = "{roomType.numberOfAdults.size}")
	@Max(value = 50, message = "{roomType.numberOfAdults.size}")
	private int numberOfAdults;

	@Min(value = 1, message = "{roomType.numberOfChilds.size}")
	@Max(value = 50, message = "{roomType.numberOfChilds.size}")
	private int numberOfChilds;

	@Min(value = 1, message = "{roomType.numberOfBeds.size}")
	@Max(value = 20, message = "{roomType.numberOfBeds.size}")
	private int numberOfBeds;

	private boolean deleteFlag;

	private List<PriceDTO> prices;

	private double price;

	private String image;

	private Set<RoomOfRoomType> rooms;

	private Set<FacilityDTO> facilities = new HashSet<FacilityDTO>();

	@Override
	public String toString() {
		return "RoomTypeDTO [id=" + id + ", name=" + name + ", description=" + description + ", size=" + size
				+ ", numberOfAdults=" + numberOfAdults + ", numberOfChilds=" + numberOfChilds + ", numberOfBeds="
				+ numberOfBeds + ", deleteFlag=" + deleteFlag + ", prices=" + prices + ", price=" + price + ", image="
				+ image + ", rooms=" + rooms + ", facilities=" + facilities + "]";
	}

}
