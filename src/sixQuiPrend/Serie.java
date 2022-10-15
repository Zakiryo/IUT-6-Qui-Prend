package sixQuiPrend;

import java.util.ArrayList;

public class Serie {
	private ArrayList<Card> cards;

	public Serie(int valueCard) {
		cards = new ArrayList<>();
		cards.add(new Card(valueCard));
	}

	public ArrayList<Card> getCards() {
		return cards;
	}

	public void setCards(ArrayList<Card> cards) {
		this.cards = cards;
	}

	/**
	 * Retourne la totalit� des t�tes de boeufs d'une s�rie
	 * 
	 * @return le cumul des t�tes de boeufs
	 */
	public int getTotalPenalty() {
		int total = 0;
		for (Card card : this.cards) {
			total += card.getBullHeads();
		}
		return total;
	}
}
