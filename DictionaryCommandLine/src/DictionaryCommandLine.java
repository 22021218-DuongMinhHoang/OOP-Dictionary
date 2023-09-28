import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryCommandLine {
  public DictionaryManagement dicManager = new DictionaryManagement();

  public void showAllWords() {
    ArrayList<Word> lw = dicManager.getDictionary().getListWord();
    System.out.println("No\t|\tEnglish\t\t|\tVietnamese");
    for (int i = 0; i < lw.size(); i++) {
      System.out.println((i + 1) + "\t" + "|\t" + lw.get(i).getWordTarget()
          + "\t\t|\t" + lw.get(i).getWordExplain());
    }
  }

  public void dictionaryBasic() {
    boolean open = true;
    System.out.println("Welcome to My Application!");
    Scanner sc = new Scanner(System.in);
    System.out.println("Please import data first");
    while (dicManager.getDictionary().getListWord().isEmpty()) {
      dicManager.insertFile();
    }
    while (open) {
      System.out.println(
          "[0] Exit\n[1] Add\n[2] Remove\n[3] Update\n[4] Display\n[5] Lookup\n[6] Search\n[7] Game\n[8] Import from file\n[9] Export to file");
      System.out.print("Your action: ");
      int action = sc.nextInt();
      sc.nextLine();
      System.out.println();
      switch (action) {
        case 0:
          open = false;
          break;
        case 1:
          dicManager.insertFromCommandline();
          break;
        case 2:
          dicManager.eraseWord();
          break;
        case 3:
          dicManager.changeWordExplain();
          break;
        case 4:
          showAllWords();
          break;
        case 5:
          dicManager.dictionaryLookup();
          break;
        case 6:
          dicManager.dictionarySearcher();
          break;
        case 7:
          Game.playGame(dicManager.getDictionary());
          break;
        case 8:
          dicManager.insertFile();
          break;
        case 9:
          dicManager.exportFile();
          break;
        default:
          System.out.println("Action not supported");
          break;
      }
      System.out.println();
    }
  }
}
