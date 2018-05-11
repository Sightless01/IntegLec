import hangman.*;
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

	// seth //
	
	

}