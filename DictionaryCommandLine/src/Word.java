public class Word {
  private String word_target;

  private String word_explain;

  public Word(String wt, String we) {
    this.word_target = wt;
    this.word_explain = we;
  }

  public Word() {
  }

  public String getWordTarget() {
    return this.word_target;
  }

  public void setWordTarget(String wt) {
    this.word_target = wt;
  }

  public String getWordExplain() {
    return this.word_explain;
  }

  public void setWordExplain(String we) {
    this.word_explain = we;
  }
}
