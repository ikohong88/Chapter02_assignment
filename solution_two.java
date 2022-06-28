import java.util.Scanner;

public class solution_two {
    public solution_two() {
        int taxi_num = (int)(Math.random()*100 + 1);  // 택시 번호
        int fuel_val = 100;  // 주유량
        int speed = 0;  // 현재속도
        int base_price = 3300;  // 기본 요금
        String destination = "";  // 목적지
        int destination_distance = 0;  // 목적지까지 거리
        boolean taxi_status = false;  // 상태 (운전 중, 일반)
        String operating_condition = "";
        Scanner sc = new Scanner(System.in);

//      - 운행 시작전 주유상태를 체크 하고 주유량이 10 이상이어야 운행 가능
        while (fuel_val >= 10) {
            operating_condition = (taxi_status == true) ? "Running" : "base";

            System.out.println("** taxi status **");
            System.out.println("taxi number : "+taxi_num);
            System.out.println("Check fuel value : " + fuel_val);
            System.out.println("base prive : "+base_price);
            System.out.println("======================================");
            System.out.println("taxi status : " + operating_condition);
            start_riding(fuel_val);
            if (taxi_status == false) {
                System.out.print("Serving guests? [1 : yes | 2 : no] : ");
                try {
                    taxi_status = into_taxi(sc.nextInt());
                } catch (Exception e) {
                    System.out.println("You entered an incorrect number.");
                }
            }
//          손님이 택시에 탑승해있을경우
            if (taxi_status == true) {
//              상태 - 운전중으로 변경
                taxi_status = true;
                System.out.print("where are the guests going? : ");
                destination = sc.next();
                System.out.print("How far do guests go? [KM] : ");
                destination_distance = sc.nextInt();
                speed = change_speed(speed, destination_distance, fuel_val);
                fuel_val -= change_fuel_val()*destination_distance;
                taxi_status = payment(destination_distance);
            }
        }
        if (fuel_val < 10) {
            System.out.println("Refueling is less than 10.");
            System.out.println("Stop Taxi!");
        }
    }
    // 기능 - 운행 시작
    public boolean start_riding(int fuel_val) {
        if (fuel_val < 10) {
            System.out.println("fuel_val = " + fuel_val);
            System.out.println("fuel value is low. Stop running");
            return false;
        } else {
            System.out.println("Running");
            return true;
        }
    }
    // 기능 - 승객 탑승
    // - 승객탑승
    // - 승객 탑승은 택시 상태가 ‘일반'일 때만 가능합니다.
    // - 그 외 택시는 ‘탑승 불가’ 처리를 해주세요.
    // - ‘일반’ 상태의 택시가 승객을 태우면 ‘운행 중’ 상태로 변경해 주세요
    public boolean into_taxi(int user_answer) {
        if (user_answer == 2) {
            return false;
        }
        System.out.println("Taxis are running.");
        return true;
    }

    // 기능 - 속도 변경
    // - 변경할 속도를 입력 받아 현재 속도에 추가 하거나 뺄 수 있어야 합니다.
    // 재귀함수 사용
    public int change_speed(int speed, int destination_distance, int fuel_val) {
        Scanner sc = new Scanner(System.in);
        if (fuel_val/3 < destination_distance) {
            System.out.println("Because of the lack of oil, the distance cannot be reached.");
            System.out.println("Change destination distance? or get off? [change : 1 | get off : 2] : ");
            if (sc.nextInt() == 1) {
                System.out.println("distance to go : "+(fuel_val/3));
                System.out.print("How far do guests go? [KM] : ");
                change_speed(speed,sc.nextInt(),fuel_val);
            } else if (sc.nextInt() == 2) {
                System.out.println("Guest has choice get off.");
                speed = 0;
                return speed;
            }
        }
        if (destination_distance < 1) {
            System.out.println("Guest has arrived.");
            speed = 0;
            return speed;
        }
        System.out.print("Change Speed [KM/h] : ");
        int change_speed = sc.nextInt();
        speed += change_speed;
        if (speed <= 0 && change_speed < 0) {
            System.out.println("speed is ZERO!! speed do not change!");
            speed = 0;
//          재귀함수 사용
            change_speed(speed,destination_distance,fuel_val);
        } else {
            destination_distance -= speed;
//          남은 거리가 음수가 될 경우 0으로 반환
            if (destination_distance < 0) {
                destination_distance = 0;
            }
            System.out.println("speed = " + speed + " | destination distance = " + destination_distance);
            change_speed(speed,destination_distance,fuel_val);
        }
        return speed;
    }
    // 기름량 제어
    public int change_fuel_val() {
        int change_Val = (int)(Math.random()*3)+1;
        return change_Val;
    }

    // 기능 - 거리당 요금 추가
    // - 기본 거리보다 먼 곳은 추가 요금이 붙습니다.
    // - 기본 거리와 추가 요금은 자유롭게 산정해 주세요
    // 기능 - 요금 결제
    // - 최종 요금을 출력하는 것으로 합니다.
    public boolean payment(int destination_distance) {
        int base_distance = 2;  // 기본거리
        int base_price = 3300;  // 기본 요금
        int distance_prive = 100;  // 거리당 요금
        int taxi_fare = 0;
        if (destination_distance < base_distance) {
            taxi_fare = base_price;
        } else {
            taxi_fare = base_price + (destination_distance - base_distance)*distance_prive;
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("taxi fare is " + taxi_fare);
        System.out.println("Do you want to keep riding? [yes : 1 | no : anykey]");
        if (sc.nextInt() == 1) {
            return true;
        } else {
            return false;
        }
    }
}

