/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arithmetic;


import java.util.Scanner;
import static java.time.Clock.system;

/** This class calls the method to perform 
 * arithmetic operations based on user input
 * execute the code check the output
 * @author sivagamasrinivasan
 * 
 */




// Concrete class for addition operation
class PlusOperation extends ArithmeticOperationStrategy {
    @Override
    public double performOperation(double x, double y) {
        return x + y;
    }
}

// Concrete class for subtraction operation
class MinusOperation extends ArithmeticOperationStrategy {
    @Override
    public double performOperation(double x, double y) {
        return x - y;
    }
}

// Concrete class for multiplication operation
class TimesOperation extends ArithmeticOperationStrategy {
    @Override
    public double performOperation(double x, double y) {
        return x * y;
    }
}

// Concrete class for division operation
class DivideOperation extends ArithmeticOperationStrategy {
    @Override
    public double performOperation(double x, double y) {
        if (y == 0) {
            throw new ArithmeticException("Cannot divide by zero.");
        }
        return x / y;
    }
}

public class Arithmetic {

    public static void main(String[] args) {
        // Create an instance of Scanner for user input
        Scanner sc = new Scanner(System.in);

        // Ask the user for input values for x and y
        System.out.println("Enter the first number (x): ");
        double x = sc.nextDouble();
        System.out.println("Enter the second number (y): ");
        double y = sc.nextDouble();

        // Ask the user for the arithmetic operation
        System.out.println("Enter the operation you want to perform (PLUS, MINUS, TIMES, DIVIDE): ");
        String operationInput = sc.next().toUpperCase(); // Convert input to uppercase for consistency

        ArithmeticOperation operation = null;

        // Try to map the input string to an enum value
        try {
            operation = ArithmeticOperation.valueOf(operationInput);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid operation. Please enter a valid operation (PLUS, MINUS, TIMES, DIVIDE).");
            sc.close();
            return;
        }

        // Use the corresponding strategy based on the operation
        ArithmeticOperationStrategy operationStrategy = null;

        switch (operation) {
            case PLUS:
                operationStrategy = new PlusOperation();
                break;
            case MINUS:
                operationStrategy = new MinusOperation();
                break;
            case TIMES:
                operationStrategy = new TimesOperation();
                break;
            case DIVIDE:
                operationStrategy = new DivideOperation();
                break;
        }

        // Perform the operation and print the result
        try {
            double result = operationStrategy.performOperation(x, y);
            System.out.println("Result of " + operation + ": " + result);
        } catch (ArithmeticException e) {
            System.out.println("Error performing " + operation + ": " + e.getMessage());
        }

        // Close the scanner to avoid resource leak
        sc.close();
    }
}
