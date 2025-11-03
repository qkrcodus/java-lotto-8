package lotto;

import java.util.Arrays;

public enum Rank {
    FIRST(6, false, 2000000000),
    SECOND(5, true, 30000000),
    THIRD(5, false, 1500000),
    FOURTH(4, false, 50000),
    FIFTH(3, false, 5000),
    NONE(0, false, 0);

    private final int matchCount;
    private final boolean requireBonus;
    private final int prizeMoney;
    private static final Rank[] CACHED_VALUES = Rank.values();

    Rank(int matchCount, boolean requireBonus, int prizeMoney) {
        this.matchCount = matchCount;
        this.requireBonus = requireBonus;
        this.prizeMoney = prizeMoney;
    }

    public static Rank valueOf(int matchCount, boolean bonusMatch) {
        return Arrays.stream(CACHED_VALUES)
                .filter(rank -> rank.matches(matchCount, bonusMatch))
                .findFirst()
                .orElse(NONE);
    }

    private boolean matches(int matchCount, boolean bonusMatch) {
        if (this == NONE) {
            return matchCount < FIFTH.matchCount;
        }
        if (matchCount == FIRST.matchCount) {
            return this == FIRST;
        }
        if (matchCount == SECOND.matchCount) {
            return this.matchCount == 5 && this.requireBonus == bonusMatch;
        }

        return matchCount == this.matchCount && !this.requireBonus;
    }


    public int getPrizeMoney() {
        return prizeMoney;
    }
}
