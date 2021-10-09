package fa.training.dto;

import java.util.Date;
import java.util.Set;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import fa.training.entities.BookingDetailEntity;
import fa.training.entities.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookingDTO {
	
	private int id;

	private String reservationCode;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date bookingDate;

	private String fullName;

	private String email;

	private String phone;

	private String status;

	private String bookingType;

	private double totalPrice;

	private Set<BookingDetailEntity> bookingDetails;
	
	private UserEntity customer;

	private UserEntity reservationStaff;
	
	private boolean deleteFlag;
	
    private Date createdDate;
    
    private Date modifiedDate;
}
