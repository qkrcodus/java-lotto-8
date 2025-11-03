package lotto.lotto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import lotto.model.lotto.Lotto;
import lotto.model.lotto.Rank;
import lotto.model.lotto.WinningLotto;
import org.junit.jupiter.api.Test;

public class WinningLottoTest {
    @Test
    void 당첨_로또를_생성한다() {
        WinningLotto winningNumbers = new WinningLotto(new Lotto(List.of(1, 2, 3, 4, 5, 6)), 7);
        assertThat(winningNumbers).isNotNull();
    }

    @Test
    void 보너스_번호가_1보다_작으면_예외가_발생한다() {
        assertThatThrownBy(() -> new WinningLotto(new Lotto(List.of(1, 2, 3, 4, 5, 6)), 0))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 보너스_번호가_45보다_크면_예외가_발생한다() {
        assertThatThrownBy(() -> new WinningLotto(new Lotto(List.of(1, 2, 3, 4, 5, 6)), 46))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 보너스_번호가_당첨_번호와_중복되면_예외가_발생한다() {
        assertThatThrownBy(() -> new WinningLotto(new Lotto(List.of(1, 2, 3, 4, 5, 6)), 6))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 구매한_로또의_당첨_등수를_판정한다_1등() {
        WinningLotto winningNumbers = new WinningLotto(new Lotto(List.of(1, 2, 3, 4, 5, 6)), 7);
        Lotto purchasedLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Rank rank = winningNumbers.match(purchasedLotto);
        assertThat(rank).isEqualTo(Rank.FIRST);
    }

    @Test
    void 구매한_로또의_당첨_등수를_판정한다_2등() {
        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        WinningLotto winningNumbers = new WinningLotto(winningLotto, 7);
        Lotto purchasedLotto = new Lotto(List.of(1, 2, 3, 4, 5, 7));
        Rank rank = winningNumbers.match(purchasedLotto);
        assertThat(rank).isEqualTo(Rank.SECOND);
    }

    @Test
    void 구매한_로또의_당첨_등수를_판정한다_3등() {
        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        WinningLotto winningNumbers = new WinningLotto(winningLotto, 7);
        Lotto purchasedLotto = new Lotto(List.of(1, 2, 3, 4, 5, 8));
        Rank rank = winningNumbers.match(purchasedLotto);
        assertThat(rank).isEqualTo(Rank.THIRD);
    }

    @Test
    void 구매한_로또의_당첨_등수를_판정한다_4등() {
        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        WinningLotto winningNumbers = new WinningLotto(winningLotto, 7);
        Lotto purchasedLotto = new Lotto(List.of(1, 2, 3, 4, 8, 9));
        Rank rank = winningNumbers.match(purchasedLotto);
        assertThat(rank).isEqualTo(Rank.FOURTH);
    }

    @Test
    void 구매한_로또의_당첨_등수를_판정한다_5등() {
        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        WinningLotto winningNumbers = new WinningLotto(winningLotto, 7);
        Lotto purchasedLotto = new Lotto(List.of(1, 2, 3, 8, 9, 10));
        Rank rank = winningNumbers.match(purchasedLotto);
        assertThat(rank).isEqualTo(Rank.FIFTH);
    }

    @Test
    void 구매한_로또의_당첨_등수를_판정한다_낙첨() {
        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        WinningLotto winningNumbers = new WinningLotto(winningLotto, 7);
        Lotto purchasedLotto = new Lotto(List.of(8, 9, 10, 11, 12, 13));
        Rank rank = winningNumbers.match(purchasedLotto);
        assertThat(rank).isEqualTo(Rank.NONE);
    }
}
