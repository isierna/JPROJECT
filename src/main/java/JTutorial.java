import java.util.Scanner;

/**
 * Created by Ira on 11/7/16.
 */
public class JTutorial {
    public static void main(String args[]) {
        // primitive types

        int integerNumber; // 32-bit
        long longNumber; // 64-bit
        short shortNumber; // 16-bit (value of -32,768 and a maximum value of 32,767 (inclusive))

        double doubleNumber; //64-bit
        float number;

        char character; //16-bit
        byte byteNumber;  // 8-bit (-128; 127)

        boolean booleanNumber;


        integerNumber = 10;
        longNumber = 100;
        shortNumber = 1;

        doubleNumber = 1.111d;
        number = 1.1f;

        character = 'a';
        byteNumber = 1;

        booleanNumber = true;


        System.out.println(integerNumber);
        System.out.println(longNumber);
        System.out.println(shortNumber);
        System.out.println(doubleNumber);
        System.out.println(number);
        System.out.println(character);
        System.out.println(byteNumber);
        System.out.println(booleanNumber);

        //while loop

        int myInteger = 5;

        while (myInteger < 10) {

            System.out.println("while "+myInteger);

                myInteger = myInteger + 1;

        }

        //for loop

        for (myInteger = 5; myInteger <= 10; myInteger++) {
            System.out.println("for "+myInteger);
        }

        //user input

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter any text : ");

        String userInput = scanner.nextLine();

        System.out.println("Thank You");
        System.out.println("You have entered => " + userInput);

        //switch
        System.out.println("Please enter your favourite number");

        int myInt = scanner.nextInt();
        String answer = "";

        switch (myInt) {
            case 1: answer = "you are lucky";
                    break;
            case 2: answer = "you are very happy";
                    break;
            case 3: answer = "you are extremely healthy";
                    break;
            case 4: answer = "don't change anything";
                    break;
            case 5: answer = "be yourself";
                    break;
            default: answer = "Number should be from 1 to 10";
                    break;
        }
        System.out.println(answer);

        //do while
        int i = 0;


        do {
            Scanner scanner1 = new Scanner(System.in);
            System.out.println("Please enter number 1 ");
            i = scanner1.nextInt();
            System.out.println("Your number is : " + i);
        }while (i!=1);
        System.out.println("Thank you for the correct number");

        //array

        int[] arrayOfIntegers = new int[10];
        //arrayOfIntegers[0]=0;



        for (int arrayIndex : arrayOfIntegers) {
            arrayOfIntegers[arrayIndex]=arrayIndex;
            System.out.println(arrayOfIntegers[arrayIndex]);
        }

        for (int a=0; a< arrayOfIntegers.length; a++) {
            arrayOfIntegers[a] = a;
            System.out.println(arrayOfIntegers[a]);
            System.out.print("");
        }


    }
}
