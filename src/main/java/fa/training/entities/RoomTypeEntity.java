package fa.training.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "RoomType")
public class RoomTypeEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	private String description;

	private int size;

	@Column(name = "number_of_adults")
	private int numberOfAdults;

	@Column(name = "number_of_childs")
	private int numberOfChilds;

	@Column(name = "number_of_beds")
	private int numberOfBeds;

	@OneToMany(mappedBy = "roomType")
	private List<PriceEntity> prices;

	@JsonIgnore
	@OneToMany(mappedBy = "roomType", fetch = FetchType.LAZY, cascade = { CascadeType.REMOVE })
	private Set<RoomEntity> rooms;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "room_type_facility_relationship", joinColumns = @JoinColumn(name = "room_type_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "facility_id", referencedColumnName = "id"))
	private Set<FacilityEntity> facilities = new HashSet<FacilityEntity>();

	public RoomTypeEntity() {
	}

	public RoomTypeEntity(int id) {
		this.id = id;
	}

	public Set<RoomEntity> getRooms() {
		return rooms;
	}

	public void setRooms(Set<RoomEntity> rooms) {
		this.rooms = rooms;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getNumberOfBeds() {
		return numberOfBeds;
	}

	public void setNumberOfBeds(int numberOfBeds) {
		this.numberOfBeds = numberOfBeds;
	}

	public int getNumberOfAdults() {
		return numberOfAdults;
	}

	public void setNumberOfAdults(int numberOfAdults) {
		this.numberOfAdults = numberOfAdults;
	}

	public int getNumberOfChilds() {
		return numberOfChilds;
	}

	public void setNumberOfChilds(int numberOfChilds) {
		this.numberOfChilds = numberOfChilds;
	}

	public Set<FacilityEntity> getFacilities() {
		return facilities;
	}

	public void setFacilities(Set<FacilityEntity> facilities) {
		this.facilities = facilities;
	}

	public List<PriceEntity> getPrices() {
		return prices;
	}

	public void setPrices(List<PriceEntity> prices) {
		this.prices = prices;
	}

}
