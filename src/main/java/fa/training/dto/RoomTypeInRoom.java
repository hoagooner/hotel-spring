package fa.training.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RoomTypeInRoom {

	private int id;
	
	private String name;
	
	private double price;
	
	private double size;

	private int numberOfAdults;

	private int numberOfChilds;

	private int numberOfBeds;

}
