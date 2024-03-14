package pet.store.service;

import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pet.store.controller.model.PetStoreData;
import pet.store.controller.model.PetStoreData.PetStoreCustomer;
import pet.store.controller.model.PetStoreEmployee;
import pet.store.dao.CustomerDao;
import pet.store.dao.EmployeeDao;
import pet.store.dao.PetStoreDao;
import pet.store.entity.Customer;
import pet.store.entity.Employee;
import pet.store.entity.PetStore;

@Service

public class PetStoreService {
	@Autowired
	private PetStoreDao petStoreDao;

	
	@Transactional(readOnly = false)
	public PetStoreData savePetStore(PetStoreData petStoreData) {
		PetStore petStore = findOrCreatePetStore(petStoreData.getPetStoreId());
		copyPetStoreFields(petStore, petStoreData);
		return new PetStoreData(petStoreDao.save(petStore));
	}

	private void copyPetStoreFields(PetStore petStore, PetStoreData petStoreData) {
		petStore.setPetStoreAddress(petStoreData.getPetStoreAddress());
		petStore.setPetStoreCity(petStoreData.getPetStoreCity());
		petStore.setPetStoreId(petStoreData.getPetStoreId());
		petStore.setPetStoreName(petStoreData.getPetStoreName());
		petStore.setPetStorePhone(petStoreData.getPetStorePhone());
		petStore.setPetStoreState(petStoreData.getPetStoreState());
		petStore.setPetStoreZip(petStoreData.getPetStoreZip());
	}

	private PetStore findOrCreatePetStore(Long petStoreId) {
		if (petStoreId == null) {
			return new PetStore(); 
		} else {
			return findPetStoreById(petStoreId);
			
		}
	}

	private PetStore findPetStoreById(Long petStoreId) {
		return petStoreDao.findById(petStoreId)
				.orElseThrow(() -> new NoSuchElementException("Pet Store with ID=" + petStoreId + " was not found."));

	}

	public Employee saveEmployee(Long petStoreId, PetStoreEmployee petStoreEmployee) {
		PetStore petStore = findPetStoreById(petStoreId);
		Employee employee = findOrCreateEmployee(petStoreId, petStoreEmployee.getEmployeeId());
		copyEmployeeFields(employee, petStoreEmployee);
		employee.setPetStore(petStore);
		petStore.getEmployees().add(employee);
		employeeDao.save(employee);
		return employee;

	}

	@Autowired
	private EmployeeDao employeeDao;

	@Transactional(readOnly = false)
	public Employee findEmployeeById(Long petStoreId, Long employeeId) {
		Employee employee = employeeDao.findById(employeeId).orElseThrow(NoSuchElementException::new);
		if (!employee.getPetStore().getId().equals(petStoreId)) {
			throw new IllegalArgumentException("Employee does not belong to the given pet store.");
		}
		return employee;
	}

	public Employee findOrCreateEmployee(Long petStoreId, Long employeeId) {
		if (employeeId == null) {
			return new Employee();
		} else {
			return findEmployeeById(petStoreId, employeeId);
		}
	}

	public void copyEmployeeFields(Employee employee, PetStoreEmployee petStoreEmployee) {
		employee.setEmployeeFirstName(petStoreEmployee.getEmployeeFirstName());
		employee.setEmployeeLastName(petStoreEmployee.getEmployeeLastName());
		employee.setEmployeeId(petStoreEmployee.getEmployeeId());
		employee.setEmployeeJobTitle(petStoreEmployee.getEmployeeJobTitle());
		employee.setEmployeePhone(petStoreEmployee.getEmployeePhone());
		
	}


	@Autowired
	private CustomerDao customerDao;

	@Transactional(readOnly = false)
	    public Customer findCustomerById(Long petStoreId, Long customerId) {
	    	Customer customer = customerDao.findById(customerId).orElseThrow(() -> new NoSuchElementException("No Customer Found!"));
	        boolean found = false;
	        for (PetStore petStore : customer.getPetStores()) {
	            if (petStore.getId().equals(petStoreId)) {
	                found = true;
	                break;
	            }
	        }
	        if (!found) {
	            throw new IllegalArgumentException("Customer is not associated with the given pet store.");
	        }
	        return customer;
	    }

	public Customer findOrCreateCustomer(Long petStoreId, Long customerId) {
		if (customerId == null) {
			return new Customer();
		} else {
			return findCustomerById(petStoreId, customerId);
		}
	}

	public void copyCustomerFields(Customer customer, PetStoreCustomer petStoreCustomer) {
		customer.setName(petStoreCustomer.getCustomerEmail());
		customer.setCustomerFirstName(petStoreCustomer.getCustomerFirstName());
		customer.setCustomerLastName(petStoreCustomer.getCustomerLastName());
		customer.setCustomerId(petStoreCustomer.getCustomerId());
	}

	public Customer saveCustomer(Long petStoreId, PetStoreCustomer petStoreCustomer) {
		PetStore petStore = findPetStoreById(petStoreId);
		Customer customer = findOrCreateCustomer(petStoreId, petStoreCustomer.getCustomerId());
		copyCustomerFields(customer, petStoreCustomer);
		customer.getPetStores().add(petStore);
		petStore.getCustomers().add(customer);
		customerDao.save(customer);
		return customer;
	}

	

		public void deletePetStoreById(Long id) {
			 PetStore petStore = findPetStoreById(id);
		        
		        petStoreDao.delete(petStore);
		    }

		public List<PetStoreData> retrieveAllPetStores() {
			// TODO Auto-generated method stub
			return null;
		}
		    
		}
	
	

	
		
		
	

