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

    }
}
