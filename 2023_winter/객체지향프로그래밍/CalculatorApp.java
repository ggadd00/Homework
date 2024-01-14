package homework;

import java.io.*;
import java.util.*; // 필요한 클래스 가져옴

// 3. 인터페이스구현
interface Calculator { // 인터페이스 정의
    double add(double a, double b);
    double subtract(double a, double b);
    double multiply(double a, double b);
    double divide(double a, double b) throws ArithmeticException;
}

// 2. 클래스 상속
class AdvancedCalculator implements Calculator {
    
    public double add(double a, double b) {
        return a + b;
    }

    
    public double subtract(double a, double b) {
        return a - b;
    }

   
    public double multiply(double a, double b) {
        return a * b;
    }

    
    public double divide(double a, double b) throws ArithmeticException {
        if (b == 0) {
            throw new ArithmeticException("0으로 나눌 수 없음.");
        }
        return a / b;
    }
}

public class CalculatorApp { //메인 클래스
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // 6. 참조 타입 (배열 / 열거 타입)
        String[] operators = {"addition", "subtraction", "multiplication", "division"};

        // 7. 컬렉션 프레임워크
        List<Double> results = new ArrayList<>();

        // 8. 파일 입/출력 스트림 사용
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("calculator_results.ser"))) {
            // 5. 다형성 (상속/인터페이스)
            Calculator calculator = new AdvancedCalculator();

            System.out.println("계산기");
            System.out.print("첫번째 숫자를 입력하시오 : ");
            double num1 = scanner.nextDouble();

            System.out.print("두번째 숫자를 입력하시오 : ");
            double num2 = scanner.nextDouble();

            for (String operator : operators) { // 연산자 반복하고 계산을 수행하여 저장
                System.out.println("\n" + operator.toUpperCase() + ":");
                double result = performOperation(calculator, num1, num2, operator);
                System.out.println("Result: " + result);

                // 7. 컬렉션 프레임워크
                results.add(result);
            }

            // 8. 파일 입/출력 스트림 사용
            oos.writeObject(results);
            System.out.println("Results serialized and saved to file.");

        } catch (IOException e) {
            e.printStackTrace(); //예외처리
        }

        scanner.close();
    }

    // 5. 다형성 (상속/인터페이스)
    private static double performOperation(Calculator calculator, double num1, double num2, String operator) {
        switch (operator) {
            case "addition":
                return calculator.add(num1, num2);
            case "subtraction":
                return calculator.subtract(num1, num2);
            case "multiplication":
                return calculator.multiply(num1, num2);
            case "division":
                try {
                    return calculator.divide(num1, num2);
                } catch (ArithmeticException e) {
                    System.out.println("Error: " + e.getMessage());
                    return Double.NaN; // Not a Number
                }
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }
}
