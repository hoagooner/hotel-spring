package fa.training.entities;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Setter
@Getter
@NoArgsConstructor
public class UserEntity {


	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	private String username;

    private String password;
    
	private String fullName;

    private String email;

    private String phone;

    private String address;

    @Column(name = "birth_day")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birhtDay;

    private String status;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    @JoinTable(name = "user_role_relationship",
            joinColumns = @JoinColumn(name = "user_id",
                    referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id",
                    referencedColumnName = "id"))
    private Set<RoleEntity> roles;

    @OneToMany(mappedBy = "reservationStaff", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<BookingEntity> bookingsOfStaff;
    
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<BookingEntity> bookingsOfCustomer;
    
    private Date createdDate;
    
    private Date modifiedDate;
}
