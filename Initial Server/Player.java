import java.util.ArrayList;
import java.io.Serializable;

public class Player implements Serializable {
  public String name;
  public ArrayList<String> words;
  public String playingWord;
  public int life;

  public Player(String name) {
    this.name = name;
    words = new ArrayList<>();
    life = 5;
  }

  public void SetPlayingWord(String word) {
    playingWord = word;
  }

  public String getPlayingWord() {
    return playingWord;
  }

 // insert code //


}
