package org.example.Solution.day07.model;

import lombok.Getter;

@Getter
public enum Card implements Comparable<Card> {
    A("A"),
    K("K"),
    Q("Q"),
    J("J"),
    T("T"),
    NINE("9"),
    EIGHT("8"),
    SEVEN("7"),
    SIX("6"),
    FIVE("5"),
    FOUR("4"),
    THREE("3"),
    TWO("2");

    private final String symbol;

    Card(String symbol) {
        this.symbol = symbol;
    }

    public static Card fromString(String symbol) {
        for (Card card : Card.values()) {
            if (card.symbol.equals(symbol)) {
                return card;
            }
        }
        throw new IllegalArgumentException("Invalid card symbol: " + symbol);
    }

}
