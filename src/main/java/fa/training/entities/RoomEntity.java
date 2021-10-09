package fa.training.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "rooms")
public class RoomEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int roomNumber;

	@ManyToOne
	@JoinColumn(name = "room_type_id")
	private RoomTypeEntity roomType;

	private String floor;

	@OneToMany(mappedBy = "room")
	@JsonIgnore
	private List<BookingDetailEntity> bookingDetails;

	private boolean deleteFlag;

    private Date createdDate;
    
    private Date modifiedDate;
}