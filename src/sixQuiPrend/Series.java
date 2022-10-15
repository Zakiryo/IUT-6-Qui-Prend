package sixQuiPrend;

import java.util.ArrayList;
import java.util.Scanner;

import appli.Application;

public class Series {
	private final ArrayList<Serie> serieList;
	private final int nbMaxCardInSerie;
	private boolean serieValide;

	public Series(int[] cards, int nbMaxCardInSerie) {
		this.serieList = new ArrayList<>();
		this.nbMaxCardInSerie = nbMaxCardInSerie;
		for (int i = 0; i < cards.length; ++i) {
			this.serieList.add(new Serie(cards[i]));
		}
	}

	public ArrayList<Serie> getSerieList() {
		return this.serieList;
	}

	public boolean getSerieValide() {
		return this.serieValide;
	}

	/**
	 * Vérfie qu'une carte choisie peut être jouée dans une série. Si oui, elle sera
	 * placée dans la série correspondante. Si non, demandera au joueur de choisir
	 * une série dont il récupèrera les pénalités pour placer sa carte.
	 * 
	 * @param card    la carte jouée
	 * @param player  le joueur concerné
	 * @param game    la partie en cours
	 * @param choices la liste de choix
	 * @return une nouvelle série si la carte choisie ne peut être placée.
	 */
	public int addCardAndGetPenalty(Card card, Player player, Game game, ArrayList<Choice> choices) {
		int indexOfSerieToAddCard = this.getIndexOfSerieToAddCard(card.getValue());
		if (indexOfSerieToAddCard != -1) {
			Serie serie = this.serieList.get(indexOfSerieToAddCard);
			if (serie.getCards().size() == this.nbMaxCardInSerie - 1) {
				return restartSerieAndGetPenalty(indexOfSerieToAddCard, card);
			}
			this.serieList.get(indexOfSerieToAddCard).getCards().add(card);
			serieValide = true;
			return 0;
		} else {
			serieValide = false;
			@SuppressWarnings("resource")
			Scanner scan = new Scanner(System.in);
			if (!game.getSeries().getSerieValide()) {
				Application.displayChoices(choices, game);
			}
			System.out.println("Pour poser la carte " + card.getValue() + ", " + player.getName()
					+ " doit choisir la série qu'il va ramasser.");
			System.out.println(this);
			System.out.print("Saisissez votre choix : ");
			String choice = scan.nextLine();
			while (!(Helper.isNumeric(choice) && 1 <= Integer.parseInt(choice)
					&& Integer.parseInt(choice) <= this.serieList.size())) {
				System.out.print("Ce n’est pas une série valide, saisissez votre choix : ");
				choice = scan.nextLine();
			}
			int indexSerie = Integer.parseInt(choice) - 1;
			return this.restartSerieAndGetPenalty(indexSerie, card);
		}
	}

	/**
	 * Recherche et récupère l'index d'une série valide pour une valeur donnée.
	 * 
	 * @param valueCard la valeur d'une carte
	 * @return l'index de la série correspondante
	 */
	public int getIndexOfSerieToAddCard(int valueCard) {
		int minDist = Integer.MAX_VALUE;
		int resIndex = -1;
		for (int i = 0; i < this.serieList.size(); ++i) {
			Serie serie = this.serieList.get(i);
			ArrayList<Card> cards = serie.getCards();
			int topCard = cards.get(cards.size() - 1).getValue();
			if (valueCard > topCard && valueCard - topCard < minDist) {
				resIndex = i;
				minDist = valueCard - topCard;
			}
		}
		return resIndex;

	}

	/**
	 * Vide une série, y ajoute la carte jouée et retourne le cumul des pénalités
	 * associées.
	 * 
	 * @param indexSerie l'index de la série à vider
	 * @param card       la carte à remettre en première position
	 * @return cumul des pénalités de la série
	 */
	public int restartSerieAndGetPenalty(int indexSerie, Card card) {
		Serie serieToRetrieve = this.serieList.get(indexSerie);
		int totalPenalty = serieToRetrieve.getTotalPenalty();
		serieToRetrieve.getCards().clear();
		serieToRetrieve.getCards().add(card);
		return totalPenalty;
	}

	/**
	 * Affiche toutes les séries selon le modèle "série n° n : valeur (pénalité)".
	 */
	public String toString() {
		String res = "";
		for (int i = 0; i < this.serieList.size(); i++) {
			Serie serie = this.serieList.get(i);
			String message = "- série n° " + (i + 1) + " : " + Card.concatenateCards(serie.getCards());
			res += message;

			if (i != this.serieList.size() - 1) {
				res += '\n';
			}
		}
		return res;
	}
}
