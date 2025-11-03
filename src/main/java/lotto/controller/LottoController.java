package lotto.controller;

import java.util.List;
import lotto.model.lotto.Lotto;
import lotto.model.lotto.LottoMachine;
import lotto.model.lotto.LottoResult;
import lotto.model.lotto.Rank;
import lotto.model.lotto.WinningLotto;
import lotto.model.money.Money;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private final LottoMachine lottoMachine;

    public LottoController() {
        this.lottoMachine = LottoMachine.getInstance();
    }

    public void run() {
        Money purchaseAmount = inputPurchaseAmountWithRetry();
        List<Lotto> lottos = generateLottos(purchaseAmount);
        WinningLotto winningLotto = inputWinningLottoWithRetry();
        LottoResult result = calculateResult(lottos, winningLotto, purchaseAmount);
        OutputView.printStatistics(result);
    }

    private Money inputPurchaseAmountWithRetry() {
        while (true) {
            try {
                return InputView.readPurchaseAmount();
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    private List<Lotto> generateLottos(Money purchaseAmount) {
        int count = purchaseAmount.getLottoCount();
        List<Lotto> lottos = lottoMachine.generate(count);
        OutputView.printPurchaseResult(count);
        OutputView.printLottos(lottos);
        return lottos;
    }

    private WinningLotto inputWinningLottoWithRetry() {
        while (true) {
            try {
                List<Integer> winningNumbers = InputView.readWinningNumbers();
                Lotto winningLotto = new Lotto(winningNumbers);
                int bonusNumber = inputBonusNumberWithRetry(winningLotto);
                return new WinningLotto(winningLotto, bonusNumber);
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    private int inputBonusNumberWithRetry(Lotto winningLotto) {
        while (true) {
            try {
                int bonusNumber = InputView.readBonusNumber();
                new WinningLotto(winningLotto, bonusNumber);
                return bonusNumber;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private LottoResult calculateResult(List<Lotto> lottos, WinningLotto winningLotto, Money purchaseAmount) {
        LottoResult result = new LottoResult(purchaseAmount);
        for (Lotto lotto : lottos) {
            Rank rank = winningLotto.match(lotto);
            result.addRank(rank);
        }
        return result;
    }


}
