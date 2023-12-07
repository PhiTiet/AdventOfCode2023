package org.example.Solution.day07.model.comparator;

import org.example.Solution.day07.model.Hand;

import java.util.Comparator;

public class PartTwoHandComparator implements Comparator<Hand> {
    private final PartTwoCardComparator partTwoCardComparator = new PartTwoCardComparator();

    @Override
    public int compare(Hand a, Hand b) {
        var handTypeResult = a.getHandType().compareTo(b.getHandType());
        if (handTypeResult == 0) {
            for (int i = 0; i < a.getCards().size(); i++) {
                var cardTypeResult = partTwoCardComparator.compare(a.getCards().get(i), b.getCards().get(i));
                if (cardTypeResult != 0) {
                    return cardTypeResult;
                }
            }
        }
        return handTypeResult;
    }
}
