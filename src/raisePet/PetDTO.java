package raisePet;

import java.util.*;

public class PetDTO {
    private int type; // 동물 종류
    private String name; // 이름
    private int hunger; // 허기
    private int cleanliness; // 청결 상태
    private int affection; // 호감도
	
    public PetDTO() {
       this.hunger = 50; // 허기 초기값 설정
       this.cleanliness = 50; // 청결 상태 초기값 설정
       this.affection = 50; // 호감도 초기값 설정
    }
    
    public int getType() {
		return type;
	}
	public void setType(int petType) {
		this.type = petType;
	}
	 public String getTypeName() { // 종류 이름 반환 메서드 추가
	        if (type == 1) {
	            return "강아지";
	        } else if (type == 2) {
	            return "고양이";
	        } else {
	            return "알 수 없음";
	        }
	    }
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getHunger() {
		return hunger;
	}
	public void setHunger(int hunger) {
		this.hunger = hunger;
	}
	public int getCleanliness() {
		return cleanliness;
	}
	public void setCleanliness(int cleanliness) {
		this.cleanliness = cleanliness;
	}
	public int getAffection() {
		return affection;
	}
	public void setAffection(int affection) {
		this.affection = affection;
	}
	public void print() {
		System.out.printf("%s\t%s\t%d\t%d\t%d\n",name,getTypeName(),hunger,cleanliness,affection);
	}
	
	@Override
	public boolean equals(Object obj) {
	    if (obj == null) return false;
	    if (getClass() != obj.getClass()) return false;
	    PetDTO other = (PetDTO) obj;
	    return Objects.equals(this.name, other.name);
	}

	@Override
	public int hashCode() {
	    return Objects.hash(name);
	}

    
}
