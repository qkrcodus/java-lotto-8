package lotto;

public class LottoNumber {
    private static final int LOTTO_MIN = 1;
    private static final int LOTTO_MAX = 45;
    private final int value;

    private LottoNumber(int value) {
        this.value = value;
    }

    public static LottoNumber from(int value) {
        if (value < LOTTO_MIN || value > LOTTO_MAX) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 1~45 사이여야 합니다.");
        }
        return new LottoNumber(value);
    }
}
