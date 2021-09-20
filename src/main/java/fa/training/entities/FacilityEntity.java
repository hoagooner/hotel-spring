package fa.training.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import fa.training.constraint.UniqueFacility;

@Entity
@Table(name = "Facility")
public class FacilityEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(columnDefinition = "nvarchar")
	@NotEmpty(message = "{facility.name.notempty}")
	@Size(max  = 50, message = "{facility.name.invalid_length}")
	@UniqueFacility(message = "{facility.name.unique}")
	private String name;

	@Column(columnDefinition = "nvarchar")
	@NotEmpty(message = "{facility.description.notempty}")
	@Size(max  = 255, message = "{facility.description.invalid_length}")
	private String description;

	@JsonIgnore
	@ManyToMany(mappedBy = "facilities")
	private Set<RoomTypeEntity> roomTypes;

	private String icon;

	public FacilityEntity() {
	}

	public FacilityEntity(int id) {
		this.id = id;
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

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

}
