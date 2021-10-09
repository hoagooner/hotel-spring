package fa.training.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "BookingServiceDetails")
public class BookingServiceDetailEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "booking_detail_id")
	@JsonIgnore
	private BookingDetailEntity bookingDetail;
	
	
	@ManyToOne
	@JoinColumn(name = "service_id")
	private ServiceEntity service;
	
	private int quantity;
	
	private double price;
	
	private boolean deleteFlag;
	
    private Date createdDate;
    
    private Date modifiedDate;
}
