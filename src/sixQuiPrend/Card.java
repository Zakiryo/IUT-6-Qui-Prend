package sixQuiPrend;

import java.util.ArrayList;

public class Card {
	private final int value;
	private final int bullHeads;

	public Card(int value) {
		this.value = value;
		this.bullHeads = computeBullHeads(value);
	}

	/**
	 * Calcule et d�finit le nombre de t�tes de boeufs selon la valeur d'une carte
	 * 
	 * @param value la valeur de la carte
	 * @return le nombre de t�tes de boeufs associ� � cette carte
	 */
	private int computeBullHeads(int value) {
		if (value == 55) {
			return 7;
		} else if (value % 11 == 0) {
			return 5;
		} else if (value % 5 == 0 && value % 10 != 0) {
			return 2;
		} else if (value % 10 == 0) {
			return 3;
		}
		return 1;
	}

	public int getValue() {
		return this.value;
	}

	public int getBullHeads() {
		return this.bullHeads;
	}

	/**
	 * Affiche les donn�es d'une carte selon le mod�le "valeur (nbT�tesDeBoeufs)"
	 */
	public String toString() {
		return "" + this.value + ((this.bullHeads != 1) ? " (" + this.bullHeads + ")" : "");
	}

	/**
	 * G�re l'affichage des virgules dans l'affichage d'une liste de cartes
	 * 
	 * @param cards la liste de cartes
	 * @return l'affichage final
	 */
	public static String concatenateCards(ArrayList<Card> cards) {
		String res = "";
		for (int i = 0; i < cards.size(); i++) {
			res += cards.get(i);
			if (i != cards.size() - 1) {
				res += ", ";
			}
		}
		return res;
	}
}
