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

@Entity
@Table(name = "prices")
@Getter
@Setter
@NoArgsConstructor
public class PriceEntity  implements Comparable<PriceEntity>{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private double price;

	private Date modifiedDate;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "room_type_id")
	private RoomTypeEntity roomType;
	
	private boolean deleteFlag;
	
	public PriceEntity(double price, Date modifiedDate, RoomTypeEntity roomType) {
		super();
		this.price = price;
		this.modifiedDate = modifiedDate;
		this.roomType = roomType;
	}

	public void setDeleteFlag(boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	@Override
	public int compareTo(PriceEntity obj) {
		return obj.getModifiedDate().compareTo(this.getModifiedDate());
	}
	
}
