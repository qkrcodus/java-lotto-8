package lotto.model.lotto;

public class WinningLotto {
    private final Lotto winningLotto;
    private final int bonusNumber;

    public WinningLotto(Lotto winningLotto, int bonusNumber) {
        validateBonusNumber(bonusNumber);
        validateBonusNotInWinningNumbers(winningLotto, bonusNumber);
        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
    }

    private void validateBonusNumber(int bonusNumber) {
        LottoNumber.from(bonusNumber);
    }

    private void validateBonusNotInWinningNumbers(Lotto winningLotto, int bonusNumber) {
        if (winningLotto.contains(bonusNumber)) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }

    public Rank match(Lotto purchasedLotto) {
        int matchCount = countMatches(purchasedLotto);
        boolean bonusMatch = checkBonusMatch(purchasedLotto);
        return Rank.valueOf(matchCount, bonusMatch);
    }

    private int countMatches(Lotto purchasedLotto) {
        return (int) purchasedLotto.getNumbers().stream()
                .filter(winningLotto::contains)
                .count();
    }

    private boolean checkBonusMatch(Lotto purchasedLotto) {
        return purchasedLotto.contains(bonusNumber);
    }
}