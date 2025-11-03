package lotto.model.lotto;

import java.util.EnumMap;
import java.util.Map;
import lotto.model.money.Money;

public class LottoResult {
    private final Map<Rank, Integer> rankCounts;
    private final Money purchaseAmount;

    public LottoResult(Money purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
        this.rankCounts = initializeRankCounts();
    }

    private Map<Rank, Integer> initializeRankCounts() {
        Map<Rank, Integer> counts = new EnumMap<>(Rank.class);
        for (Rank rank : Rank.values()) {
            if (rank != Rank.NONE) {
                counts.put(rank, 0);
            }
        }
        return counts;
    }

    public void addRank(Rank rank) {
        if (rank == Rank.NONE) {
            return;
        }
        rankCounts.put(rank, rankCounts.get(rank) + 1);
    }

    public int getCountByRank(Rank rank) {
        return rankCounts.getOrDefault(rank, 0);
    }

    public int getTotalPrizeMoney() {
        return rankCounts.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getPrizeMoney() * entry.getValue())
                .sum();
    }

    public double getReturnRate() {
        if (getTotalPrizeMoney() == 0) {
            return 0.0;
        }
        return (double) getTotalPrizeMoney() / purchaseAmount.getAmount() * 100;
    }
}