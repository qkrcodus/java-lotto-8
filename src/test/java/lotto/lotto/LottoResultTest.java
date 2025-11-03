package lotto;

import static org.assertj.core.api.Assertions.assertThat;

import lotto.model.lotto.LottoResult;
import lotto.model.lotto.Rank;
import lotto.model.money.Money;
import org.junit.jupiter.api.Test;

public class LottoResultTest {
    @Test
    void 당첨_결과를_생성한다() {
        LottoResult result = new LottoResult(Money.from(8000));
        assertThat(result).isNotNull();
    }

    @Test
    void 당첨_등수를_추가한다() {
        LottoResult result = new LottoResult(Money.from(8000));
        result.addRank(Rank.FIFTH);
        result.addRank(Rank.FIFTH);
        result.addRank(Rank.FOURTH);
        assertThat(result.getCountByRank(Rank.FIFTH)).isEqualTo(2);
        assertThat(result.getCountByRank(Rank.FOURTH)).isEqualTo(1);
    }

    @Test
    void 당첨되지_않은_등수는_0개다() {
        LottoResult result = new LottoResult(Money.from(8000));
        result.addRank(Rank.FIFTH);
        assertThat(result.getCountByRank(Rank.FIRST)).isEqualTo(0);
        assertThat(result.getCountByRank(Rank.SECOND)).isEqualTo(0);
    }

    @Test
    void 총_당첨_금액을_계산한다() {
        LottoResult result = new LottoResult(Money.from(8000));
        result.addRank(Rank.FIFTH);
        result.addRank(Rank.FOURTH);
        assertThat(result.getTotalPrizeMoney()).isEqualTo(55000);
    }

    @Test
    void 수익률을_계산한다() {
        LottoResult result = new LottoResult(Money.from(8000));
        result.addRank(Rank.FIFTH);
        // 수익률 = (5,000 / 8,000) * 100 = 62.5%
        assertThat(result.getReturnRate()).isEqualTo(62.5);
    }

}
