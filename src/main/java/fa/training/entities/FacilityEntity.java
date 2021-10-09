package fa.training.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import fa.training.validator.UniqueFacility;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Facilities")
@Getter
@Setter
@NoArgsConstructor
public class FacilityEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(columnDefinition = "nvarchar(50)")
	@NotEmpty(message = "{facility.name.notempty}")
	@Size(max  = 50, message = "{facility.name.invalid_length}")
	@UniqueFacility(message = "{facility.name.unique}")
	private String name;

	@Column(columnDefinition = "nvarchar(255)")
	@Size(max  = 255, message = "{facility.description.invalid_length}")
	private String description;

	@JsonIgnore
	@ManyToMany(mappedBy = "facilities")
	private Set<RoomTypeEntity> roomTypes;

	private String icon;
	
	private boolean deleteFlag;

    private Date createdDate;
    
    private Date modifiedDate;

}
