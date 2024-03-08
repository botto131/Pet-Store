package pet.store.service;

import java.util.NoSuchElementException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pet.store.controller.model.PetStoreData;
import pet.store.dao.PetStoreDao;
import pet.store.entity.PetStore;

@Service
public class PetStoreService {
	private final PetStoreDao petStoreDao;

	@Autowired
	public PetStoreService(PetStoreDao petStoreDao) {
		this.petStoreDao = petStoreDao;
	}

	public PetStoreData savePetStore(PetStoreData petStoreData) {
		PetStore petStore = findOrCreatePetStore(petStoreData.getId());
		copyPetStoreFields(petStore, petStoreData);
		PetStore savedPetStore = petStoreDao.save(petStore);
		return new PetStoreData(savedPetStore);
	}

	private PetStore findOrCreatePetStore(Long petStoreId) {
		if (petStoreId == null) {
			return new PetStore(); // Create a new PetStore object if ID is null
		} else {
			return petStoreDao.findById(petStoreId)
					.orElseThrow(() -> new NoSuchElementException("PetStore with ID " + petStoreId + " not found"));
		}
	}

	private void copyPetStoreFields(PetStore petStore, PetStoreData petStoreData) {
		BeanUtils.copyProperties(petStoreData, petStore);
		
	}

}