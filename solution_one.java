import java.util.Scanner;

public class solution_one {
    public solution_one() {
//      추상 클래스 및 인터페이스 구성
        boolean _break = true;

        int max_people = 25;  // 최대 승객수
        int busin_people = 10;  // 현재 승객수
        int price = 1300;  // 요금
        int bus_number = (int) (Math.random() * 100);  // 버스번호 >> 버스 객체 생성시 번호는 고유값으로 생성되어야 합니다.
        int fuel_val = 20;  // 주유량
        int speed = 30;  // 속도버스에 더이상 승객을 탑승시킬수 없습니다.
        boolean status = true; // 상태 : 운행 - 1, 차고지행 - 0 > 시작시 운행 상태
        int riding = 0; // 승객 탑승운행
        int change_speed = 0;

        while (_break == true) {
            Scanner sc = new Scanner(System.in);

            System.out.println("===== bus info =====");
            if (status == true) {
                System.out.println("** Bus is running ** ");
            } else if (status == false) {
                System.out.println("** to the garage **");
            }
            System.out.println("Bus Numbers : " + bus_number);
            System.out.println("Max People : " + max_people);
            System.out.println("Bus fare : " + price);
            System.out.println("===== bus status =====");
            System.out.println("Bus fuel value : " + fuel_val);
            System.out.println("Into Bus People : " + busin_people);
            System.out.println("Bus Speed : " + speed);
            System.out.println("=================================");

            if (speed != 0) {
                System.out.print("How many people are on the bus? : ");
                riding = sc.nextInt();
            } else {
                System.out.println("The bus is stopped! change speed up!");
            }
            busin_people = bus_riding(max_people, busin_people, riding);
            System.out.print("How would you like to adjust the speed? : ");
            speed = bus_speed_change(speed, sc.nextInt());

            System.out.println("busin_people = " + busin_people);
            fuel_val = bus_status(fuel_val);

            if (fuel_val == 0) {
                status = false;
                break;
            }

            System.out.print("Would you ride bus? (0 : Stop) : ");
            if (sc.nextInt() == 0) {
                status = false;
                System.out.println("Go To Garage!");
                _break = false;
            }
        }
    }
//  기능
//  운행 기능
    public void bus_ride() {

    }
//  버스 상태 변경
//  - 버스 객체 생성시 최초 상태는 **‘운행’** 상태가 되며
//  - 주유량이 떨어지거나, 운행을 종료할 때 **‘차고지행’** 상태로 변경 합니다.
//  - 10미만일 경우 ‘주유가 필요하다’는 메시지를 출력해 주세요
    public int bus_status(int fuel_val) {
        int use_fuel = fuel_val - (int)((Math.random()*20)+2);
        if (use_fuel < 0) {
            System.out.println("fuel val is ZERO!");
            System.out.println("go to garage");
            return 0;
        } else if (use_fuel < 10) {
            System.out.println("low fuel");
            System.out.println("Bus fuel value : " + use_fuel);
            return use_fuel;
        }
        System.out.println("Bus fuel value : " + use_fuel);
        return use_fuel;
    }
//  승객 탑승
//  - 승객 탑승은 **‘최대 승객수’** 이하까지 가능하며 **‘운행 중’**인 상태의 버스만 가능합니다.
//  - 탑승시 현재 승객수가 증가되어야 합니다.
    public int bus_riding(int max_people,int busin_people, int riding) {
        int result = busin_people + riding;
        if (busin_people < 0) {
            System.out.println("The bus was empty!");
            return 0;
        }
        if (result >= max_people) {
            System.out.println("The bus can no longer carry passengers.");
            result = max_people;
        }
        return result;
    }
//  속도 변경
//  - 주유 상태를 체크하고 주유량이 10 이상이어야 운행할 수 있습니다.
//   - 경고메시지
//        - 주유량을 확인해 주세요.
//        - print문으로 출력
//  - 변경할 속도를 입력 받아 현재 속도에 추가 하거나 뺄 수 있어야 합니다.
    public int bus_speed_change(int speed, int change_speed) {
        if (speed + change_speed < 0) {
            System.out.println("Bus STOP!! - speed : 0");
            return 0;
        }
        if (change_speed < 0) {
            System.out.println("Speed Down : "+change_speed);
        } else if (change_speed == 0) {
            System.out.println("Do not change speed!");
        } else {
            System.out.println("Speed Up : "+speed);
        }
        return speed + change_speed;
    }
}
