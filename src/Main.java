import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean play = true;
        boolean winner = false;
        int min = 1;
        int max = 100;
        int randomNumber = random.nextInt(max - min + 1) + min;
        int maxAttempts = 10;
        int attempts = 0;
        int bestScore = Integer.MAX_VALUE;//create best score at the highest value

        File highScoreFile = new File("highscore.txt");
        //Create File
        try {
            if (highScoreFile.createNewFile()) {
                System.out.println("High score file created.");
            }
        } catch (IOException e) {
            System.out.println("Error creating high score file.");
        }

        if (highScoreFile.exists()) {
            try (Scanner fileScanner = new Scanner(highScoreFile)) {
                if (fileScanner.hasNextInt()) {
                    bestScore = fileScanner.nextInt();
                }
            } catch (IOException e) {
                System.out.println("Error reading high score file.");
            }
        }

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


                if (randomNumber == userNumber) {
                    winner = true;
                    break;
                } else if (userNumber > randomNumber) {
                    System.out.println("Number too High try again");
                }else{
                    System.out.println("Number to Low try again");
                }

                System.out.println("You still have: "+(maxAttempts-attempts)+" attempts");

            }

            if(winner){
                System.out.println("You Win in the attempt: "+attempts);
                if (attempts < bestScore) {
                    bestScore = attempts;
                    System.out.println("New high score! Fewest attempts so far.");

                    try (FileWriter writer = new FileWriter(highScoreFile)) {
                        writer.write(String.valueOf(bestScore));
                    } catch (IOException e) {
                        System.out.println("Error saving high score.");
                    }
                }
            }else {
                System.out.println("Sorry, you lose. The correct number was: " + randomNumber);
            }
            if (bestScore != Integer.MAX_VALUE) {
                System.out.println("Current high score (fewest attempts): " + bestScore);
            } else {
                System.out.println("No high score yet.");
            }

            attempts = 0;

            System.out.println("Play again (yes/no)");
            String response = scanner.next();

            if(response.equals("no")) {
                play = false;
            }
        }

        System.out.println("Thanks for playing");
        scanner.close();

    }
}
