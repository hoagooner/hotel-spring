package fa.training.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RoomDTO {

	private int id;

	@Min(value = 1, message = "{room.roomNumber.size}")
	@Max(value = 1000, message = "{room.roomNumber.size}")
	private int roomNumber;

	private RoomTypeInRoom roomType;

	private String floor;
	
	private boolean deleteFlag;
	
}