import java.util.ArrayList;
import java.util.Scanner;

public class Game {
  public static void playGame(Dictionary d) {
    boolean play = true;
    Scanner sc = new Scanner(System.in);
    ArrayList<Integer> answered = new ArrayList<>();
    while (play) {
      int guess = 5;
      int rand = (int) (Math.random() * 10);
      while ((rand < 0 || rand >= d.getListWord().size()) || answered.contains(rand)) {
        rand = (int) (Math.random() * 10);
      }
      while (guess > 0) {
        System.out.println("Guess the English word that has this Vietnamese explain: "
            + d.getListWord().get(rand).getWordExplain());
        System.out.print("Your answer: ");
        String guessWord = sc.nextLine();
        if (guessWord.equals(d.getListWord().get(rand).getWordTarget())) {
          System.out.println("Correct. Congratulation!!!");
          answered.add(rand);
          if (answered.size() == d.getListWord().size()) {
            System.out.println("You have guessed all word!!!\nGood job!");
            play = false;
            break;
          }
          String ans = "";
          while (!ans.equals("Y") && !ans.equals("N")) {
            System.out.print("Want to play again? [Y/N]: ");
            ans = sc.nextLine();
          }
          if (ans.equals("Y")) {
            play = true;
          } else {
            play = false;
          }
          break;
        } else {
          System.out.println("Wrong.");
          guess--;
          System.out.println("Guess left: " + guess);
          if (guess == 0) {
            play = false;
            System.out.println("Game Over");
            break;
          } else if (guess > 0) {
            System.out.println("Try again.");
          }
        }
      }
      System.out.println();
    }
  }
}
