package raisePet;

import java.util.*;

public class PetService {
	private PetRepository petRepository = new PetRepository();
	private Scanner sc = new Scanner(System.in);

	public void adoptPet(PetDTO pet) {
		petRepository.save(pet);
		petRepository.showStatus(pet);
	}

	public void feed(String name) {
		PetDTO pet = petRepository.findByName(name);
//        pet.setHunger(pet.getHunger() + 30);
		if (pet.getHunger() >= 80) {
			System.out.println(pet.getName() + "은(는) 이미 배가 부릅니다!");
		} else {
			pet.setHunger(pet.getHunger() + 30);
		}
		if (pet.getHunger() > 100) {
			pet.setHunger(100);
		}
		pet.setCleanliness(pet.getCleanliness() - 10);
		pet.setAffection(pet.getAffection() + 10);
		petRepository.update(pet);
		System.out.println(pet.getName() + "에게 밥을 주었습니다");
		petRepository.showStatus(pet);
	}

	public void play(String name) {
		PetDTO pet = petRepository.findByName(name);
		pet.setHunger(pet.getHunger() - 20);
		if (pet.getHunger() < 0) {
			pet.setHunger(0);
		}
		pet.setCleanliness(pet.getCleanliness() - 20);
		if (pet.getCleanliness() < 0) {
			pet.setCleanliness(0);
		}
		pet.setAffection(pet.getAffection() + 20);
		if (pet.getAffection() > 100) {
			pet.setAffection(100);
		}
		petRepository.update(pet);
		System.out.println(pet.getName() + "을(를) 놀아주었습니다");
		petRepository.showStatus(pet);
	}

	public void wash(String name) {
		PetDTO pet = petRepository.findByName(name);
		pet.setCleanliness(pet.getCleanliness() + 30);
		pet.setAffection(pet.getAffection() - 10);
		if (pet.getAffection() < 0) {
			pet.setAffection(0);
		}
		petRepository.update(pet);
		System.out.println(pet.getName() + "을(를) 씻겼습니다");
		petRepository.showStatus(pet);
	}

	public boolean isAlive(String name) {
	    PetDTO pet = petRepository.findByName(name);
	    if(pet != null) {
	        if(pet.getHunger() <= 0 || pet.getCleanliness() <= 0) {
	            petRepository.delete(name);
	            return false;
	        } else {
	            return true;
	        }
	    } else {
	        return false;
	    }
	}

	
	public boolean isCompleted(String name) {
		PetDTO pet = petRepository.findByName(name);
		return pet.getAffection() >= 100;
	}

	public void findAll() {
		Map<String, PetDTO> petMap = petRepository.findAll();
		System.out.println("이름\t종류\t허기\t청결도\t호감도");
		System.out.println("---------------------------------");
		for (PetDTO pet : petMap.values()) {
			pet.print();
		}
	}
	
	public void update() {
	    System.out.print("이름을 변경할 펫의 이름을 입력해주세요: ");
	    String changeName = sc.next();
	    PetDTO changePetName = petRepository.findByName(changeName);
	    if(changePetName != null) {
	        System.out.print("새로운 이름을 입력해주세요: ");
	        String newName = sc.next();
	        changePetName.setName(newName);
	        petRepository.update(changePetName);
	        petRepository.delete(changeName);
	        System.out.println(changeName + "의 이름이 " + newName + "으로 변경되었습니다.");
	    } else {
	        System.out.println("해당 이름의 펫이 존재하지 않습니다.");
	    }
	}
	
	public void delete() {
	    System.out.print("방출할 펫의 이름을 입력해주세요: ");
	    String name = sc.next();
	    PetDTO pet = petRepository.findByName(name);
	    if(pet != null) {
	        petRepository.delete(name);
	        System.out.println(pet.getName() + "이(가) 방출되었습니다.");
	    } else {
	        System.out.println("해당 이름의 펫이 존재하지 않습니다.");
	    }
	}


	public void continueGame() {
		System.out.print("이어서 키울 애완동물의 이름을 입력하세요: ");
		String searchName = sc.next();
		PetDTO searchPet = petRepository.findByName(searchName);
//		if(searchPet.getAffection() >=100) {
//			System.out.println("이미 육성완료된 펫입니다");
//		}
		if (searchPet != null && searchPet.getAffection() < 100) {
			petRepository.showStatus(searchPet);
			 while (true) {			        	
		        	System.out.println("무엇을 하시겠습니까?");				
		            System.out.println("1. 밥 주기");
		            System.out.println("2. 놀아주기");
		            System.out.println("3. 씻기기");
		            System.out.println("0. 뒤로 가기");
		            System.out.print("입력> ");
		            int choice = sc.nextInt();

		            if (choice == 1) {
		                feed(searchName);
		            } else if (choice == 2) {
		                play(searchName);
		            } else if (choice == 3) {
		                wash(searchName);
		            } else if (choice == 0) {
		                break;
		            }
		            else {
		                System.out.println("잘못된 선택입니다. 다시 선택해주세요.");
		            }
		            if (!isAlive(searchName)) {
		                System.out.println(searchPet.getName() + "이(가) 죽었습니다.");
		                break;
		            }
		            if (isCompleted(searchName)) {
		                System.out.println(searchPet.getName() + "의 육성 완료");
		                break;
		            }
		        }
		}else if(searchPet != null && searchPet.getAffection() >= 100) {
			System.out.println("이미 육성완료된 펫입니다");
		}
		else {
			System.out.println(searchName+"이(가) 없습니다");
		}
	}
}
