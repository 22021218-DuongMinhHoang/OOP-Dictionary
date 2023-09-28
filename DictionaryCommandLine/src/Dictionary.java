import java.util.ArrayList;

public class Dictionary {
  private ArrayList<Word> listWord = new ArrayList<>();

  public Dictionary(ArrayList<Word> lw) {
    this.listWord = lw;
  }

  public Dictionary() {
  }

  public ArrayList<Word> getListWord() {
    return this.listWord;
  }

  public void setListWord(ArrayList<Word> lw) {
    this.listWord = lw;
  }

  public void addWord(String wordTarget, String wordExplain) {
    listWord.add(new Word(wordTarget, wordExplain));
    listWord = mergeSort(0, listWord.size() - 1);
  }

  private ArrayList<Word> mergeSort(int left, int right) {
    ArrayList<Word> nlist = new ArrayList<>();
    for (int i = left; i <= right; i++) {
      nlist.add(listWord.get(i));
    }
    if (left < right) {
      ArrayList<Word> l1 = new ArrayList<>();
      ArrayList<Word> l2 = new ArrayList<>();
      l1 = mergeSort(left, (left + right) / 2); // 0 1 2 3
      l2 = mergeSort((left + right) / 2 + 1, right);
      nlist = mergeList(l1, l2);
    }
    return nlist;
  }

  private ArrayList<Word> mergeList(ArrayList<Word> l1, ArrayList<Word> l2) {
    ArrayList<Word> list = new ArrayList<>();
    int i1 = 0;
    int i2 = 0;
    while (i1 < l1.size() || i2 < l2.size()) {
      if (i1 < l1.size() && i2 < l2.size()) {
        if (compareString(l1.get(i1).getWordTarget(), l2.get(i2).getWordTarget())) {
          list.add(l1.get(i1));
          i1++;
        } else {
          list.add(l2.get(i2));
          i2++;
        }
      } else if (i1 >= l1.size() && i2 < l2.size()) {
        list.add(l2.get(i2));
        i2++;
      } else if (i1 < l1.size() && i2 >= l2.size()) {
        list.add(l1.get(i1));
        i1++;
      }

    }
    return list;
  }

  /** So sanh 2 xau, xau 1 dung truoc xau 2 tra ve true */
  public static boolean compareString(String w1, String w2) {
    int i1 = 0, i2 = 0;
    while (i1 < w1.length() || i2 < w2.length()) {
      if ((int) w1.charAt(i1) > (int) w2.charAt(i2)) {
        return false;
      } else if ((int) w1.charAt(i1) < (int) w2.charAt(i2)) {
        return true;
      } else {
        i1++;
        i2++;
        if (i1 >= w1.length()) {
          return true;
        }
        if (i2 >= w2.length()) {
          return false;
        }
      }
    }
    return false;
  }

  public int findWord(String w, int left, int right) {
    if (left <= right) {
      int mid = (left + right) / 2;
      if (listWord.get(left).getWordTarget().equals(w)) {
        return left;
      }
      if (compareString(w, listWord.get(mid).getWordTarget())) {
        return findWord(w, left, mid);
      } else {
        return findWord(w, mid + 1, right);
      }
    }
    return -1;
  }

  public void erase(int n) {
    listWord.remove(n);
  }
}
