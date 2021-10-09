package fa.training.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import fa.training.entities.BookingEntity;
import fa.training.entities.RoomEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookingDetailDTO {

	private int id;

	private double price; // price of room

	private RoomEntity room;
	
	private String fullName;

	private String email;

	private String phone;

	@JsonIgnore
	private BookingEntity booking;

	private Date checkin;

	private Date checkout;

	private boolean deleteFlag;

	private Date createdDate;

	private Date modifiedDate;
}
