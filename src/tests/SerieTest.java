package tests;

import sixQuiPrend.Card;
import sixQuiPrend.Serie;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class SerieTest {
    @Test
    public void testConstrutor() {
    	//Teste l'initialisation d'une s�rie.
        Serie serie = new Serie(2);
        Assert.assertEquals(2, serie.getCards().get(0).getValue());
    }

    @Test
    public void testTotalPenalty() {
    	//V�rifie le bon cumul des p�nalit�s.
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Card(55));
        cards.add(new Card(11));
        cards.add(new Card(10));
        Serie serie = new Serie(3);
        serie.setCards(cards);
        Assert.assertEquals(15, serie.getTotalPenalty());
    }
}
