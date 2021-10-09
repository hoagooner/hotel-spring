package fa.training.dto;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PriceDTO {

	private int id;

	private double price;

	private Date modifiedDate;
	
}
