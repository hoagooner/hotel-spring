package fa.training.entities;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "Bookings")
@Entity
public class BookingEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	@OneToMany(mappedBy = "booking", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	private Set<BookingDetailEntity> bookingDetails;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private UserEntity customer;

	@ManyToOne
	@JoinColumn(name = "reservation_staff_id")
	private UserEntity reservationStaff;
	
	private boolean deleteFlag;
	
    private Date createdDate;
    
    private Date modifiedDate;
}
