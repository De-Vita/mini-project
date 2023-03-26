package raisePet;

import java.util.List;
import java.util.Scanner;

public class PetMain {
    public static void main(String[] args) {
        PetService petService = new PetService();

        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.println("=====애완동물 키우기=====");
            System.out.println("1.새로운 펫 키우기 2.이어서 키우기 3.펫 리스트 4.펫 이름 변경 5.펫 방출하기 0.종료");
            System.out.print("입력> ");
            int menu = sc.nextInt();
            if(menu == 1) {
                PetDTO petDTO = new PetDTO();
                System.out.println("동물 종류를 선택해주세요");
                System.out.println("1.강아지 2.고양이 0.뒤로가기 ");
                System.out.print("선택: ");
                int type = sc.nextInt();
                petDTO.setType(type);
                if (type == 0) {
                    continue;
                }
                System.out.print("동물 이름을 입력해주세요: ");
                petDTO.setName(sc.next());
                petService.adoptPet(petDTO);
                while(true) {                  
                    System.out.println("무엇을 하시겠습니까?");
                    System.out.println("1.밥주기 2.놀아주기 3.씻기기 0.뒤로가기");
                    System.out.print("입력> ");
                    int choice = sc.nextInt();
                    if (choice == 1) {
                        petService.feed(petDTO.getName());
                    }else if(choice == 2) {
                        petService.play(petDTO.getName());
                    }else if(choice == 3) {
                        petService.wash(petDTO.getName());
                    }else if (choice == 0) {
                        break;
                    }
                     if (!petService.isAlive(petDTO.getName())) {
                            System.out.println(petDTO.getName() + "이(가) 죽었습니다.");
                            break;
                        }
                     if (petService.isCompleted(petDTO.getName())) {
                            System.out.println(petDTO.getName() + "의 육성 완료");
                            break;
                        }               
                }
            }else if(menu == 2) {              
                petService.continueGame();
            }else if(menu == 3) {
                petService.findAll();
            }
            else if(menu == 4) {
                petService.update();
            }
            else if(menu == 5) {
                petService.delete();
            }
            else if(menu == 0) {
                break;
            }else {
                System.out.println("다시 입력");
            }
        }
        System.out.println("프로그램 종료");
    }
}
