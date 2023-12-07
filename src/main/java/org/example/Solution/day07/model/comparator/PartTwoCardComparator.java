package org.example.Solution.day07.model.comparator;

import org.example.Solution.day07.model.Card;

import java.util.Comparator;

public class PartTwoCardComparator implements Comparator<Card> {
    @Override
    public int compare(Card card1, Card card2) {
        return Integer.compare(getValue(card1), getValue(card2));
    }

    private int getValue(Card card) {
        if (card == Card.J) {
            return Integer.MAX_VALUE;
        }
        return card.ordinal();
    }
}
