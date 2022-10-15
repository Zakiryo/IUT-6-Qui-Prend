package tests;

import sixQuiPrend.Card;
import sixQuiPrend.Serie;
import sixQuiPrend.Series;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class SeriesTest {
	@Test
	public void testConstructor() {
		// Teste l'initialisation des 4 séries
		int[] cards = { 1, 2, 3, 4 };
		Series series = new Series(cards, 4);
		Assert.assertEquals(4, series.getSerieList().size());
		for (Serie serie : series.getSerieList()) {
			Assert.assertEquals(1, serie.getCards().size());
		}
	}

	@Test
	public void testGetSerieOfCard() {
		// Teste la récupération d'une série à partir d'une carte
		int[] valueCards = { 1, 2, 3, 4 };
		Series series = new Series(valueCards, 4);

		ArrayList<Card> cardsInSerie2 = new ArrayList<>();
		cardsInSerie2.add(new Card(3));
		cardsInSerie2.add(new Card(89));
		series.getSerieList().get(2).setCards(cardsInSerie2);

		ArrayList<Card> cardsInSerie3 = new ArrayList<>();
		cardsInSerie3.add(new Card(3));
		cardsInSerie3.add(new Card(90));
		series.getSerieList().get(2).setCards(cardsInSerie3);

		ArrayList<Card> cardsInSerie1 = new ArrayList<>();
		cardsInSerie1.add(new Card(4));
		cardsInSerie1.add(new Card(92));
		series.getSerieList().get(0).setCards(cardsInSerie1);

		int indexOfSerieToAddCard = series.getIndexOfSerieToAddCard(91);
		Assert.assertEquals(2, indexOfSerieToAddCard);
	}

	@Test
	public void testRestartSerieAndGetPenalty() {
		// Vérifie que pour une carte invalide placée, la série redémarre
		int[] valueCards = { 1, 2, 3, 4 };
		Series series = new Series(valueCards, 4);

		ArrayList<Card> cardsInSerie2 = new ArrayList<>();
		cardsInSerie2.add(new Card(3));
		cardsInSerie2.add(new Card(89));
		series.getSerieList().get(2).setCards(cardsInSerie2);
		Assert.assertEquals(2, series.getSerieList().get(2).getCards().size());

		series.restartSerieAndGetPenalty(2, new Card(3));
		Assert.assertEquals(1, series.getSerieList().get(2).getCards().size());
	}
}
