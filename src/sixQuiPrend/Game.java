package sixQuiPrend;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Game {
	private ArrayList<Player> players;
	private ArrayList<Integer> deck;
	private Series series;
	private int currentCardIndex = 0;
	private static final int NB_CARDS = 104;
	private static final int NB_SERIES = 4;
	private static final int NB_MAX_CARD_SERIES = 6;
	private static final int NB_CARDS_IN_HAND = 10;

	public Game(String configFileName) throws Exception {
		ArrayList<String> playersName = Game.getPlayersNameFromFile(configFileName);
		boolean correctNumberPlayers = 2 <= playersName.size() && playersName.size() <= 10;
		if (!correctNumberPlayers) {
			throw new Exception("Le nombre de joueurs doit être entre 2 et 10");
		}
		this.initDeck();
		this.initPlayer(playersName);
		this.initSeries();
	}

	private void initDeck() {
		this.deck = new ArrayList<>();
		for (int i = 1; i <= NB_CARDS; i++) {
			this.deck.add(i);
		}
		Collections.shuffle(this.deck);
	}

	private void initPlayer(ArrayList<String> playersName) {
		this.players = new ArrayList<>();
		ArrayList<ArrayList<Card>> hands = getHandsFromDeck(playersName.size());
		for (int i = 0; i < playersName.size(); ++i) {
			String playerName = playersName.get(i);
			ArrayList<Card> hand = hands.get(i);
			this.players.add(new Player(playerName, hand));
		}
	}

	private void initSeries() {
		int[] initialCards = new int[Game.NB_SERIES];
		for (int i = 0; i < Game.NB_SERIES; i++) {
			initialCards[i] = this.deck.get(this.currentCardIndex + i + 1);
		}
		this.series = new Series(initialCards, NB_MAX_CARD_SERIES);
	}

	private static ArrayList<String> getPlayersNameFromFile(String configFileName) {
		String parentDirectory = "";
		ArrayList<String> playersName = new ArrayList<>();
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(parentDirectory + configFileName));
			String line;
			while (true) {
				line = reader.readLine();
				if (line != null)
					playersName.add(line);
				else
					break;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return playersName;
	}

	private ArrayList<ArrayList<Card>> getHandsFromDeck(int nbPlayers) {
		ArrayList<ArrayList<Card>> hands = new ArrayList<>();
		this.currentCardIndex = 0;
		for (int i = 0; i < nbPlayers; i++) {
			ArrayList<Card> hand = new ArrayList<>();
			for (int j = 0; j < NB_CARDS_IN_HAND; j++) {
				hand.add(new Card(this.deck.get(this.currentCardIndex)));
				this.currentCardIndex++;
			}
			hands.add(hand);
		}
		return hands;
	}

	public Game() throws Exception {
		this("config.txt");
	}

	public boolean isEndGame() {
		for (Player player : this.players) {
			if (player.getHand().size() != 0) {
				return false;
			}
		}
		return true;
	}

	public ArrayList<Player> getPlayers() {
		return this.players;
	}

	public Series getSeries() {
		return this.series;
	}
}
