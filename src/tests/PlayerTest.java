package tests;

import sixQuiPrend.Card;
import sixQuiPrend.Player;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class PlayerTest {
	// Teste le nommage des joueurs.
	@Test
	public void testPlayerName() {
		String name = "Paul";
		Player player = new Player(name);
		Assert.assertEquals(name, player.getName());
	}

	@Test
	public void testPlayerHand() {
		// Re-vérifie le nommage et la distribution des cartes dans une main.
		String name = "Paul";
		ArrayList<Card> expectedHand = new ArrayList<Card>();
		int maxCardValue = 5;
		for (int i = 0; i < maxCardValue; i++) {
			expectedHand.add(new Card(i));
		}
		Player player = new Player(name, expectedHand);

		Assert.assertEquals(player.getName(), name);
		for (int i = 0; i < maxCardValue; i++) {
			Card expectedCard = expectedHand.get(i);
			Assert.assertEquals(expectedCard.getValue(), player.getHand().get(i).getValue());
		}
	}

	@Test
	public void testPenalty() {
		// Vérifie l'attribution des pénalités à une joueur.
		String name = "Paul";
		ArrayList<Card> receivedPenaltyCards = new ArrayList<Card>();
		receivedPenaltyCards.add(new Card(102));
		receivedPenaltyCards.add(new Card(5));
		receivedPenaltyCards.add(new Card(55));
		receivedPenaltyCards.add(new Card(20));
		receivedPenaltyCards.add(new Card(22));

		Player player = new Player(name);
		player.addPenalty(receivedPenaltyCards);
		Assert.assertEquals(18, player.getPenalty());
	}
}
