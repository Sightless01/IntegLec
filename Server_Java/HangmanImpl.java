import HangmanGame.*;
import java.util.*;
import java.io.*;

public class HangmanImpl extends HangmanPOA {
	ArrayList<Player> players = new ArrayList<>();
	ArrayList<String> words;

	public HangmanImpl() throws Exception {
			getWords();
	  }

	@Override
	public String startGame(String name) {
		String word = "";
		if(!containsByName(name)) {
			players.add(new Player(name));
			word = selectWord(name);
		} else {
			return "Player Already exists.";
		}
		System.out.println(word);
		return word;
	}

	@Override
	public int getCurrentLife(String name) {
		return getPlayerByName(name).getLife();
	}

	@Override
	public boolean letterGuess(String name, char letter) {
		String word = getPlayerByName(name).getPlayingWord();
		for(int i = 0; i < word.length(); i++) {
			if (letter == word.charAt(i)) {
				return true;
			}
		}
		getPlayerByName(name).reduceLife();
		return false;
	}

	@Override
	public boolean wordGuess(String name, String guess) {
		String word = getPlayerByName(name).getPlayingWord();
		return word.equalsIgnoreCase(guess);
	}

	@Override
	public String newGame(String name) {
		String word = selectWord(name);
		if(getPlayerByName(name).getLife() == 0) {
			getPlayerByName(name).reset();
			return word;
		}
		return word;
	}

	@Override
	public String endGame(String name) {
		players.remove(getPlayerByName(name));
		return "Thank You for Playing the game!";
	}

	private Player getPlayerByName(String name) {
		return players.get(players.indexOf(new Player(name)));
	}

	
	private void getWords() throws Exception {
		File file = new File("words.txt");
		Scanner read = new Scanner(file);
		words = new ArrayList<>();
		do {
			words.add(read.nextLine());
		} while(read.hasNext());
	}

	private boolean containsByName(String name) {
		return players.contains(new Player(name));
	}

	private String selectWord(String name) {
		Random random = new Random();
		String word = "";
		do {
			int num = random.nextInt(words.size());
			word = words.get(num);
		} while(getPlayerByName(name).words.contains(word));
		getPlayerByName(name).words.add(word);
		getPlayerByName(name).SetPlayingWord(word);
		return word;
	}

}
