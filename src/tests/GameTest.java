package tests;

import sixQuiPrend.Card;
import sixQuiPrend.Game;
import sixQuiPrend.Player;
import sixQuiPrend.Serie;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;

public class GameTest {
	@Test
	public void failWithLessThanTwoPlayers() throws Exception {
		// V�rifie qu'une partie ne peut pas se lancer si le config.txt contient moins de 2 joueurs.
		Exception thrown = Assertions.assertThrows(Exception.class, () -> new Game("configOnePlayer.txt"));
		Assertions.assertEquals("Le nombre de joueurs doit �tre entre 2 et 10", thrown.getMessage());
	}

	@Test
	public void failWithMoreThanTenPlayers() throws Exception {
		// V�rifie qu'une partie ne peut pas se lancer si le config.txt contient plus de 10 joueurs.
		Exception thrown = Assertions.assertThrows(Exception.class, () -> new Game("configElevenPlayer.txt"));
		Assertions.assertEquals("Le nombre de joueurs doit �tre entre 2 et 10", thrown.getMessage());
	}

	@Test
	public void testGetPlayersName() throws Exception {
		// V�rifie le bon fonctionnement de la r�cup�ration du nom des joueurs.
		Game game = new Game();
		Assert.assertEquals("Paul", game.getPlayers().get(0).getName());
		Assert.assertEquals("Aba", game.getPlayers().get(1).getName());
		Assert.assertEquals("Paris", game.getPlayers().get(2).getName());
		Assert.assertEquals("Japon", game.getPlayers().get(3).getName());
	}

	@Test
	public void testGetPlayersHandAndSeries() throws Exception {
		//V�rifie le bon fonctionnement de la distribution des cartes aux joueurs et des s�ries
		Game game = new Game();
		for (Player player : game.getPlayers()) {
			Assert.assertEquals(10, player.getHand().size());
		}

		int[] nbEncountered = new int[105];
		Arrays.fill(nbEncountered, 0);
		for (Player player : game.getPlayers()) {
			for (Card card : player.getHand()) {
				nbEncountered[card.getValue()] += 1;
			}
		}

		for (int i = 0; i < 105; i++) {
			Assert.assertTrue(nbEncountered[i] <= 1);
		}

		for (Serie serie : game.getSeries().getSerieList()) {
			for (Card card : serie.getCards()) {
				nbEncountered[card.getValue()] += 1;
			}
		}
		for (int i = 0; i < 105; i++) {
			Assert.assertTrue(nbEncountered[i] <= 1);
		}

	}
}
