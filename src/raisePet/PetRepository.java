package raisePet;

import java.util.*;


public class PetRepository {
	private Map<String, PetDTO> petMap = new HashMap<>();

	public void save(PetDTO pet) {
		petMap.put(pet.getName(), pet);
	}

	
	public Map<String, PetDTO> findAll() {
		return petMap;
	}
	
    public PetDTO findByName(String name) {
        return petMap.get(name);
    }
    
    public void update(PetDTO pet) {
        petMap.put(pet.getName(), pet);
    } 
    
	public void delete(String name) {
    	petMap.remove(name);
    }
    
    public void showStatus(PetDTO pet) {
    	System.out.println("현재 " + pet.getName() + "의 상태");
		System.out.println("허기: " + pet.getHunger() + " / 100");
		System.out.println("청결 상태: " + pet.getCleanliness() + " / 100");
		System.out.println("호감도: " + pet.getAffection() + " / 100");
    }
}
