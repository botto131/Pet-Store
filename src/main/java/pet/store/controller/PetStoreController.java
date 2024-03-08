package pet.store.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pet.store.controller.model.PetStoreData;
import pet.store.service.PetStoreService;

@RestController
@RequestMapping("/pet_store")
@Slf4j

public class PetStoreController {

	@Autowired
	private PetStoreService petStoreService;

	@PostMapping
	public PetStoreData createPetStore(@RequestBody PetStoreData petStoreData) {
		log.info("Received request to create pet store: {}", petStoreData);
		return petStoreService.savePetStore(petStoreData);
	}
}
