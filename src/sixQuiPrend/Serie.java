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
	 * Retourne la totalité des têtes de boeufs d'une série
	 * 
	 * @return le cumul des têtes de boeufs
	 */
	public int getTotalPenalty() {
		int total = 0;
		for (Card card : this.cards) {
			total += card.getBullHeads();
		}
		return total;
	}
}
