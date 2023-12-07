package org.example.Solution.day07.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.Solution.utils.AdventPart;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
@Getter
@AllArgsConstructor
public class Hand implements Comparable<Hand>{
    private static final Card JOKER_CARD = Card.J;

    private final List<Card> cards;
    private final HandType handType;
    private final Long bet;

    public static Hand handPart(String cardString, String betString, AdventPart part) {
        var bet = getBet(betString);
        var cards = getCards(cardString);
        var countMap = part == AdventPart.PART_ONE ? getCountMap(cards) : jokerSubstituteCountMap(cards);
        var handType = determineHandType(countMap);

        return new Hand(cards, handType, bet);
    }

    private static Map<Card, Long> jokerSubstituteCountMap(List<Card> cards) {
        var countMap = getCountMap(cards);

        if (!countMap.containsKey(JOKER_CARD) || countMap.get(JOKER_CARD) == 5){
            return getCountMap(cards);
        }
        var numJokers = countMap.get(JOKER_CARD);
        countMap.remove(JOKER_CARD);
        Card mostCopiesOfCard = Collections.max(countMap.entrySet(), Map.Entry.comparingByValue()).getKey();
        Long mostCopiesOfCardAmount = countMap.get(mostCopiesOfCard);
        countMap.put(mostCopiesOfCard, mostCopiesOfCardAmount + numJokers);
        return countMap;
    }

    private static long getBet(String betString) {
        return Long.parseLong(betString);
    }

    private static List<Card> getCards(String cardString) {
        return cardString.chars()
                .mapToObj(c -> String.valueOf((char) c))
                .map(Card::fromString)
                .toList();
    }

    private static HandType determineHandType(Map<Card, Long> countMap) {
        int mapSize = countMap.size();
        if (mapSize == 1){
            return HandType.FIVE_OF_A_KIND;
        }
        if (mapSize == 2){
            if (countMap.containsValue(4L)){
                return HandType.FOUR_OF_A_KIND;
            }
            return HandType.FULL_HOUSE;
        }
        if(mapSize == 3){
            if (countMap.containsValue(3L)){
                return HandType.THREE_OF_A_KIND;
            }
            return HandType.TWO_PAIR;
        }
        if(mapSize == 4){
            return HandType.ONE_PAIR;
        }

        if (mapSize == 5){
            return HandType.HIGH_CARD;
        }
        throw new IllegalStateException("wtf");
    }

    private static Map<Card, Long> getCountMap(List<Card> cards) {
        return cards.stream().collect(groupingBy(c -> c, counting()));
    }

    @Override
    public int compareTo(Hand other) {
        var handTypeResult = this.handType.compareTo(other.handType);
        if (handTypeResult == 0){
            for (int i = 0; i < cards.size(); i++){
                var cardTypeResult = cards.get(i).compareTo(other.cards.get(i));
                if (cardTypeResult != 0){
                    return cardTypeResult;
                }
            }
        }
        return handTypeResult;
    }

}
