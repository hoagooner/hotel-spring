package fa.training.entities;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "BookingDetails")
@Entity
public class BookingDetailEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double price; // price of room
    
	private String fullName;

	private String email;

	private String phone;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private RoomEntity room;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "booking_id")
    @JsonIgnore
    private BookingEntity booking;
    
    private Date checkin;
    
    private Date checkout;

    @OneToMany(mappedBy = "bookingDetail", cascade = CascadeType.PERSIST)
    private Set<BookingServiceDetailEntity> bookingServiceDetails;
    
    private boolean deleteFlag;
    
    private Date createdDate;
    
    private Date modifiedDate;
}
