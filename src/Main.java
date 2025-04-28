import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean play = true;
        boolean winner = false;
        int min = 1;
        int max = 100;
        int ramndomNumber = random.nextInt(max - min + 1) + min;
        int maxAttempts = 10;
        int attempts = 0;

        while(play){
            System.out.println("Guest the number between "+min+" and "+max);

            while(attempts < maxAttempts){
                System.out.println("Enter your guess: ");

                //Verification of input int
                if (!scanner.hasNextInt()) {
                    System.out.println("Invalid input. \nEnter a number:");
                    scanner.next();
                    continue;
                }

                int userNumber = scanner.nextInt();

                if (userNumber < min || userNumber > max) {
                    System.out.println("Your guess is out of bounds ");
                    continue;
                }
                attempts++;


                if (ramndomNumber == userNumber) { 
                    winner = true;
                    break;
                } else if (userNumber > ramndomNumber) {
                    System.out.println("Number too High try again");
                }else{
                    System.out.println("Number to Low try again");
                }

                System.out.println("You still have: "+(maxAttempts-attempts)+" attempts");

            }

            if(winner){
                System.out.println("You Win in the attempt: "+attempts);
            }else {
                System.out.println("Sorry you loose");
            }

            attempts = 0;

            System.out.println("Play again (yes/no)");
            String response = scanner.next();

            if(response.equals("no")) {
                play = false;
            }
        }

        System.out.println("Thanks for playing! Goodbye.");
        scanner.close();

    }
}
