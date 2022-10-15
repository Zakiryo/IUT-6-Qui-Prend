package sixQuiPrend;

import java.util.ArrayList;
import java.util.Scanner;

public class Choice implements Comparable<Choice> {
	public Player player;
	public Card card;
	public int previousPenalty;

	public Choice(Player player) {
		this.player = player;
		this.previousPenalty = 0;
	}

	/**
	 * Compare la main d'un joueur avec la valeur d'une carte saisie et retourne son
	 * index
	 * 
	 * @param cardValue la valeur de la carte recherchée
	 * @return l'index dans la main du joueur si elle s'y trouve
	 */
	public int getCardIndex(int cardValue) {
		ArrayList<Card> hand = this.player.getHand();
		for (int i = 0; i < hand.size(); ++i) {
			if (hand.get(i).getValue() == cardValue) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Demande à un joueur de saisir une carte de sa main et vérifie que celle-ci
	 * sois valide
	 * 
	 * @param player le joueur
	 */
	public void chooseCard(Player player) {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		System.out.print("Saisissez votre choix : ");
		String valueOfChosenCard = scan.nextLine();
		while (!(Helper.isNumeric(valueOfChosenCard) && this.getCardIndex(Integer.parseInt(valueOfChosenCard)) != -1)) {
			System.out.print("Vous n'avez pas cette carte, saisissez votre choix :");
			valueOfChosenCard = scan.nextLine();
		}
		int indexOfChosenCard = this.getCardIndex(Integer.parseInt(valueOfChosenCard));
		this.card = player.getHand().get(indexOfChosenCard);
	}

	public void setPreviousPenalty(int previousPenalty) {
		this.previousPenalty = previousPenalty;
	}
	
    public void setCard(Card card) {
        this.card = card;
    }

	public int getPreviousPenalty() {
		return previousPenalty;
	}

	@Override
	public int compareTo(Choice choice2) {
		return this.card.getValue() - choice2.card.getValue();
	}
}
