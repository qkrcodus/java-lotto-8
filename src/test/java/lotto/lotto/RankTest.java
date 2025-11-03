package lotto;

import static org.assertj.core.api.Assertions.assertThat;

import lotto.model.lotto.Rank;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class RankTest {
    @Test
    void 일치_개수_3개는_5등이다() {
        Rank rank = Rank.valueOf(3, false);
        assertThat(rank).isEqualTo(Rank.FIFTH);
        assertThat(rank.getPrizeMoney()).isEqualTo(5000);
    }

    @Test
    void 일치_개수_4개는_4등이다() {
        Rank rank = Rank.valueOf(4, false);
        assertThat(rank).isEqualTo(Rank.FOURTH);
        assertThat(rank.getPrizeMoney()).isEqualTo(50000);
    }

    @Test
    void 일치_개수_5개_보너스_불일치는_3등이다() {
        Rank rank = Rank.valueOf(5, false);
        assertThat(rank).isEqualTo(Rank.THIRD);
        assertThat(rank.getPrizeMoney()).isEqualTo(1500000);
    }

    @Test
    void 일치_개수_5개_보너스_일치는_2등이다() {
        Rank rank = Rank.valueOf(5, true);
        assertThat(rank).isEqualTo(Rank.SECOND);
        assertThat(rank.getPrizeMoney()).isEqualTo(30000000);
    }

    @Test
    void 일치_개수_6개는_1등이다() {
        Rank rank = Rank.valueOf(6, false);
        assertThat(rank).isEqualTo(Rank.FIRST);
        assertThat(rank.getPrizeMoney()).isEqualTo(2000000000);
    }

    @ParameterizedTest
    @CsvSource({"2,false", "1,false", "0,false"})
    void 일치_개수_2개_이하는_낙첨이다(int matchCount, boolean bonus) {
        Rank rank = Rank.valueOf(matchCount, bonus);
        assertThat(rank).isEqualTo(Rank.NONE);
        assertThat(rank.getPrizeMoney()).isEqualTo(0);
    }

    @ParameterizedTest
    @CsvSource({"2,true", "1,true", "0,true"})
    void 보너스가_있어도_2개_이하는_낙첨(int matchCount, boolean bonus) {
        Rank rank = Rank.valueOf(matchCount, bonus);
        assertThat(rank).isEqualTo(Rank.NONE);
        assertThat(rank.getPrizeMoney()).isEqualTo(0);
    }

}
