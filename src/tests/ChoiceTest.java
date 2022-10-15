package tests;

import sixQuiPrend.Card;
import sixQuiPrend.Choice;
import sixQuiPrend.Player;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

public class ChoiceTest {
	@Test
	public void testConstructor() {
		// Vérifie l'initialisation d'un joueur et son nombre de pénalité
		Player player = new Player("Alice");
		Choice choice = new Choice(player);
		Assert.assertEquals(player, choice.player);
		Assert.assertEquals(0, choice.getPreviousPenalty());
	}

	@Test
	public void testCardIndex() {
		// Teste le bon indexage d'une liste de carte
		ArrayList<Card> cards = new ArrayList<>();
		for (int i = 0; i < 4; i++) {
			cards.add(new Card(i + 1));
		}
		Player player = new Player("Alice", cards);
		Choice choice = new Choice(player);
		int existingCardIndex = choice.getCardIndex(2);
		Assert.assertEquals(1, existingCardIndex);
		int nonExistingCardIndex = choice.getCardIndex(45);
		Assert.assertEquals(-1, nonExistingCardIndex);
	}

	@Test
	public void testChoiceWithLowestCardShouldStart() {
		Card card1 = new Card(1);
		Choice choice1 = new Choice(new Player("Police"));
		choice1.setCard(card1);

		Card card2 = new Card(2);
		Choice choice2 = new Choice(new Player("Police"));
		choice2.setCard(card2);

		Card card3 = new Card(3);
		Choice choice3 = new Choice(new Player("Police"));
		choice3.setCard(card3);

		ArrayList<Choice> choices = new ArrayList<>();
		choices.add(choice2);
		choices.add(choice3);
		choices.add(choice1);

		Collections.sort(choices);

		Assert.assertEquals(choice1, choices.get(0));
		Assert.assertEquals(choice2, choices.get(1));
		Assert.assertEquals(choice3, choices.get(2));
	}
}
