package pet.store.controller;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pet.store.controller.model.PetStoreData;
import pet.store.controller.model.PetStoreEmployee;
import pet.store.service.PetStoreService;

@RestController
@RequestMapping("/pet_store")
@Slf4j

public class PetStoreController {

	@Autowired
	private PetStoreService petStoreService;

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	
	public PetStoreData createPetStore(@RequestBody PetStoreData petStoreData) {
		log.info("Received request to create pet store: {}", petStoreData);
		return petStoreService.savePetStore(petStoreData);
	}
	
	@PutMapping("/{petStoreId}")
	public PetStoreData updatePetStore(@PathVariable Long petStoreId, @RequestBody PetStoreData petStoreData) {
		petStoreData.setPetStoreId(petStoreId);
		log.info("Updating pet store {}", petStoreData);
		return petStoreService.savePetStore(petStoreData);
		
		
	}
	
	@PostMapping("/{petStoreId}/employee")
	@ResponseStatus(code = HttpStatus.CREATED)
	public PetStoreEmployee addEmployeeToPetStore(@PathVariable Long petStoreId, @RequestBody PetStoreEmployee employee) {
		
		Log.info("Adding an employee to pet store with ID: " + petStoreId);
		Object petStoreEmployee;
		return petStoreService.saveEmployee(petStoreEmployee, employee);
		
		
		}
	 @Autowired

	    @GetMapping("/pet_store")
	    public List<PetStoreData> getAllPetStores() {
	        return petStoreService.retrieveAllPetStores();
	    }
	 @DeleteMapping("/{id}")
	    public Map<String, String> deletePetStoreById(@PathVariable Long id) {
	        System.out.println("Deleting pet store with ID: " + id);

	        petStoreService.deletePetStoreById(id);

	        Map<String, String> response = new HashMap<>();
	        response.put("message", "Pet store with ID " + id + " deleted successfully.");
	        return response;
	    }
	}
	
	

