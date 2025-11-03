package lotto;

import lotto.model.lotto.Lotto;
import lotto.model.lotto.LottoMachine;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoMachineTest {

    @Test
    void 로또를_1개_발행한다() {
        LottoMachine lottoMachine = LottoMachine.getInstance();
        int count = 1;
        List<Lotto> lottos = lottoMachine.generate(count);
        assertThat(lottos).hasSize(1);
    }

    @Test
    void 로또를_여러개_발행한다() {
        LottoMachine lottoMachine = LottoMachine.getInstance();
        int count = 5;
        List<Lotto> lottos = lottoMachine.generate(count);
        assertThat(lottos).hasSize(5);
    }

    @Test
    void 발행된_로또는_6개의_번호를_가진다() {
        LottoMachine lottoMachine = LottoMachine.getInstance();
        List<Lotto> lottos = lottoMachine.generate(1);
        Lotto lotto = lottos.get(0);
        assertThat(lotto.getNumbers()).hasSize(6);
    }

    @Test
    void 발행된_로또_번호는_1부터_45_사이다() {
        LottoMachine lottoMachine = LottoMachine.getInstance();
        List<Lotto> lottos = lottoMachine.generate(1);
        Lotto lotto = lottos.get(0);
        assertThat(lotto.getNumbers())
                .allMatch(number -> number >= 1 && number <= 45);
    }

    @Test
    void 발행된_로또_번호는_중복되지_않는다() {
        LottoMachine lottoMachine = LottoMachine.getInstance();
        List<Lotto> lottos = lottoMachine.generate(1);
        Lotto lotto = lottos.get(0);
        List<Integer> numbers = lotto.getNumbers();
        assertThat(numbers).doesNotHaveDuplicates();
    }
}
