package appli;

import sixQuiPrend.*;

import static util.Console.clearScreen;
import static util.Console.pause;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Application {

	public static void main(String[] args) throws Exception {
		Game game = new Game();
		displayWelcomeMessage(game);
		while (!game.isEndGame()) {
			ArrayList<Choice> choices = getPlayerChoices(game);
			Collections.sort(choices);
			for (Choice choice : choices) {
				Player player = choice.player;
				Card card = choice.card;
				choice.setPreviousPenalty(choice.player.getPenalty());
				player.playCardInSerie(game.getSeries(), card, game, choices);
			}
			displayChoices(choices, game);
			System.out.println(game.getSeries().toString());
			displayRecapRound(choices);
		}
		displayEndGameResult(game);
	}

	/**
	 * Affiche les r�sultats d'un tour
	 * 
	 * @param choices les choix des joueurs
	 */
	private static void displayRecapRound(ArrayList<Choice> choices) {
		String res = "";
		for (Choice choice : choices) {
			int scoreDiff = choice.player.getPenalty() - choice.getPreviousPenalty();
			if (scoreDiff != 0) {
				res += choice.player.getName() + " a ramass� " + scoreDiff + " t�tes de boeufs";
			}
		}
		if (res.equals("")) {
			res = "Aucun joueur ne ramasse de t�te de boeufs.";
		}
		System.out.println(res);
	}

	/**
	 * Affiche les cartes ayant �t� choisies par les joueurs
	 * 
	 * @param choices les choix des joueurs
	 * @param game    la partie en cours
	 */
	public static void displayChoices(ArrayList<Choice> choices, Game game) {
		String res = "Les cartes ";
		for (int i = 0; i < choices.size(); i++) {
			Choice choice = choices.get(i);
			res += choice.card.getValue() + " (" + choice.player.getName() + ") ";
			if (i < choices.size() - 2) {
				res += ", ";
			} else if (i == choices.size() - 2) {
				res += "et ";
			}
		}
		if (game.getSeries().getSerieValide()) {
			res += "ont �t� pos�es.";
		} else {
			res += "vont �tre pos�es.";
		}
		System.out.println(res);
	}

	/**
	 * Affiche le score final et le nombre de t�tes de boeufs par joueurs
	 * 
	 * @param game la partie en cours
	 */
	private static void displayEndGameResult(Game game) {
		ArrayList<Player> players = game.getPlayers();
		Collections.sort(players);
		System.out.println("** Score final");
		for (Player player : players) {
			System.out.println(player.getName() + " a ramass� " + player.getPenalty() + " t�te de boeufs");
		}
	}

	/**
	 * Affiche le message de bienvenue
	 * 
	 * @param game la partie qui va commencer
	 */
	private static void displayWelcomeMessage(Game game) {
		String concatedNames = "";
		ArrayList<Player> players = game.getPlayers();
		for (int i = 0; i < players.size(); i++) {
			concatedNames += players.get(i).getName();
			if (i < players.size() - 2) {
				concatedNames += ", ";
			} else if (i == players.size() - 2) {
				concatedNames += " et ";
			}
		}
		String welcomeMessage = "Les " + players.size() + " joueurs sont " + concatedNames
				+ ". Merci de jouer � 6 qui prend !";
		System.out.println(welcomeMessage);
	}

	/**
	 * Affiche un message d'appel puis la main d'un joueur
	 * 
	 * @param player le joueur appel�
	 * @param game   la partie en cours
	 * @throws IOException
	 */
	private static void displayTableAndPlayersHand(Player player, Game game) throws IOException {
		String startTurnString = "A " + player.getName() + " de jouer.";
		System.out.println(startTurnString);
		pause();
		Series series = game.getSeries();
		System.out.println(series);
		String playersCards = "- Vos cartes : " + Card.concatenateCards(player.getHand());
		System.out.println(playersCards);
	}

	/**
	 * Demande aux joueurs de saisir un choix puis l'ajoute � la liste des cartes
	 * qui vont �tre pos�es
	 * 
	 * @param game la partie en cours
	 * @return la liste compl�te des cartes qui vont �tre pos�es
	 * @throws IOException
	 */
	private static ArrayList<Choice> getPlayerChoices(Game game) throws IOException {
		ArrayList<Player> players = game.getPlayers();
		ArrayList<Choice> choices = new ArrayList<>();
		for (Player player : players) {
			Choice choice = new Choice(player);
			displayTableAndPlayersHand(player, game);
			choice.chooseCard(player);
			choices.add(choice);
			clearScreen();
		}
		return choices;
	}
}
