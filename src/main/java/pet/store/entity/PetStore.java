package pet.store.entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Data
@Entity
public class PetStore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pet_store_id")
    private Long petStoreId;
    
    @Column(name = "pet_store_name")
    private String petStoreName;
    
    @Column(name = "pet_store_address")
    private String petStoreAddress;
    
    @Column(name = "pet_store_city")
    private String petStoreCity;
    
    @Column(name = "pet_store_state")
    private String petStoreState;
    
    @Column(name = "pet_store_zip")
    private String petStoreZip;
    
    @Column(name = "pet_store_phone")
    private String petStorePhone;
    
        
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(cascade = CascadeType.PERSIST)
    
    @JoinTable(name = "pet_store_customer", joinColumns = @JoinColumn(name = "pet_store_id"), inverseJoinColumns = @JoinColumn(name = "customer_id"))
    Set<Customer> customers;
    
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    
    @OneToMany(mappedBy = "petStore", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<Employee> employees;

	
	public Object getId() {
		// TODO Auto-generated method stub
		return null;
	}


	

	public Object getName() {
		// TODO Auto-generated method stub
		return null;
	}




	public Object getAddress() {
		// TODO Auto-generated method stub
		return null;
	}

	
	}

		
    
    
}
   
