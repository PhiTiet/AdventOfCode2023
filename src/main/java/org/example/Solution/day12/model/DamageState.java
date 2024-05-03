package org.example.Solution.day12.model;

public enum DamageState {
    OPERATIONAL("."),
    DAMAGED("#"),
    UNKNOWN("?");

    private final String symbol;

    DamageState(String symbol) {
        this.symbol = symbol;
    }

    public static DamageState fromString(String symbol) {
        for (DamageState damageState : DamageState.values()) {
            if (damageState.symbol.equals(symbol)) {
                return damageState;
            }
        }
        throw new IllegalArgumentException("Invalid damageState symbol: " + symbol);
    }
}
