package fa.training.entities;

import java.io.Serializable;
import java.util.Date;
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

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "RoomTypes")
@Getter
@Setter
@NoArgsConstructor
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

	private double size;

	@Column(name = "number_of_adults")
	private int numberOfAdults;

	@Column(name = "number_of_childs")
	private int numberOfChilds;

	@Column(name = "number_of_beds")
	private int numberOfBeds;
	
	private boolean deleteFlag;

	@OneToMany(mappedBy = "roomType", cascade = { CascadeType.REMOVE })
//	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	private List<PriceEntity> prices;
	
	private double price;
	
	private String image;

	@JsonIgnore
	@OneToMany(mappedBy = "roomType", fetch = FetchType.LAZY, cascade = { CascadeType.REMOVE })
	private Set<RoomEntity> rooms;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "room_type_facility_relationship", joinColumns = @JoinColumn(name = "room_type_id", referencedColumnName = "id"), 
	inverseJoinColumns = @JoinColumn(name = "facility_id", referencedColumnName = "id"))
	@JsonIgnore
	private Set<FacilityEntity> facilities = new HashSet<FacilityEntity>();

    private Date createdDate;
    
    private Date modifiedDate;

}
