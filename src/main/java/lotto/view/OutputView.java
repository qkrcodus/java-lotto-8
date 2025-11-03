package lotto.view;

import java.util.List;
import lotto.model.lotto.Lotto;
import lotto.model.lotto.LottoResult;
import lotto.model.lotto.Rank;

public class OutputView {

    public static void printPurchaseResult(int count) {
        System.out.println();
        System.out.println(count + "개를 구매했습니다.");
    }

    public static void printLottos(List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            System.out.println(lotto.getNumbers());
        }
    }

    public static void printStatistics(LottoResult result) {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---");

        printRankResult(Rank.FIFTH, result);
        printRankResult(Rank.FOURTH, result);
        printRankResult(Rank.THIRD, result);
        printRankResult(Rank.SECOND, result);
        printRankResult(Rank.FIRST, result);

        printReturnRate(result);
    }

    private static void printRankResult(Rank rank, LottoResult result) {
        int count = result.getCountByRank(rank);
        String message = getRankMessage(rank);
        System.out.println(message + " - " + count + "개");
    }

    private static String getRankMessage(Rank rank) {
        if (rank == Rank.SECOND) {
            return "5개 일치, 보너스 볼 일치 (" + formatMoney(rank.getPrizeMoney()) + "원)";
        }
        return getMatchCount(rank) + "개 일치 (" + formatMoney(rank.getPrizeMoney()) + "원)";
    }

    private static int getMatchCount(Rank rank) {
        if (rank == Rank.FIFTH) {
            return 3;
        }
        if (rank == Rank.FOURTH) {
            return 4;
        }
        if (rank == Rank.THIRD) {
            return 5;
        }
        if (rank == Rank.FIRST) {
            return 6;
        }
        return 0;
    }

    private static String formatMoney(int money) {
        return String.format("%,d", money);
    }

    private static void printReturnRate(LottoResult result) {
        double returnRate = result.getReturnRate();
        System.out.printf("총 수익률은 %.1f%%입니다.%n", returnRate);
    }
}
