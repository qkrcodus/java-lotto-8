package money;

import lotto.model.money.Money;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MoneyTest {

    @Test
    void 금액을_생성한다() {
        Money money = Money.from(5000);
        assertThat(money.getAmount()).isEqualTo(5000);
    }

    @Test
    void 금액이_0원_이하면_예외가_발생한다() {
        assertThatThrownBy(() -> Money.from(0))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 금액이_음수면_예외가_발생한다() {
        assertThatThrownBy(() -> Money.from(-1000))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 금액이_1000원_단위가_아니면_예외가_발생한다() {
        assertThatThrownBy(() -> Money.from(1500))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 로또_구매_가능_개수를_계산한다() {
        Money money = Money.from(8000);
        int count = money.getLottoCount();
        assertThat(count).isEqualTo(8);
    }

}