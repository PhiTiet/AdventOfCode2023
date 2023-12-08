package org.example.Solution.day07.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.Solution.utils.AdventPart;

import java.util.List;
import java.util.Map;

import static java.util.Collections.max;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static org.example.Solution.day07.model.HandType.*;
import static org.example.Solution.utils.AdventPart.PART_ONE;
import static org.example.Solution.utils.StringUtils.toCharacterList;

@Getter
@AllArgsConstructor
public class Hand implements Comparable<Hand> {
    private static final Card JOKER_CARD = Card.J;

    private final List<Card> cards;
    private final HandType handType;
    private final Long bet;

    public static Hand getHandForPart(String cardString, String betString, AdventPart part) {
        var bet = Long.parseLong(betString);
        var cards = toCharacterList(cardString).stream()
                .map(Card::fromString)
                .toList();

        var countMap = part == PART_ONE ? getCountMap(cards) : jokerSubstituteCountMap(cards);
        var handType = determineHandType(countMap);

        return new Hand(cards, handType, bet);
    }

    private static Map<Card, Long> jokerSubstituteCountMap(List<Card> cards) {
        var countMap = getCountMap(cards);

        if (!countMap.containsKey(JOKER_CARD) || countMap.get(JOKER_CARD) == cards.size()) {
            return countMap;
        }
        var numJokers = countMap.remove(JOKER_CARD);
        Card mostCopiesOfCard = max(countMap.entrySet(), Map.Entry.comparingByValue()).getKey();
        Long mostCopiesOfCardAmount = countMap.get(mostCopiesOfCard);
        countMap.put(mostCopiesOfCard, mostCopiesOfCardAmount + numJokers);
        return countMap;
    }

    private static HandType determineHandType(Map<Card, Long> countMap) {
        int mapSize = countMap.size();
        if (mapSize == 1) {
            return FIVE_OF_A_KIND;
        }
        if (mapSize == 2) {
            if (countMap.containsValue(4L)) {
                return FOUR_OF_A_KIND;
            }
            return FULL_HOUSE;
        }
        if (mapSize == 3) {
            if (countMap.containsValue(3L)) {
                return THREE_OF_A_KIND;
            }
            return TWO_PAIR;
        }
        if (mapSize == 4) {
            return ONE_PAIR;
        }

        if (mapSize == 5) {
            return HIGH_CARD;
        }
        throw new IllegalStateException("wtf");
    }

    private static Map<Card, Long> getCountMap(List<Card> cards) {
        return cards.stream().collect(groupingBy(c -> c, counting()));
    }

    @Override
    public int compareTo(Hand other) {
        var handTypeResult = this.handType.compareTo(other.handType);
        if (handTypeResult == 0) {
            for (int i = 0; i < cards.size(); i++) {
                var cardTypeResult = cards.get(i).compareTo(other.cards.get(i));
                if (cardTypeResult != 0) {
                    return cardTypeResult;
                }
            }
        }
        return handTypeResult;
    }

}
