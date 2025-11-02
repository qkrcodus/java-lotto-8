package money;

public class Money {
    private static final int LOTTO_PRICE = 1000;
    private static final int MIN_AMOUNT = 1;

    private final int amount;

    private Money(int amount) {
        validate(amount);
        this.amount = amount;
    }

    public static Money from(int amount) {
        return new Money(amount);
    }

    private void validate(int amount) {
        validatePositive(amount);
        validateUnit(amount);
    }

    private void validatePositive(int amount) {
        if (amount < MIN_AMOUNT) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 " + MIN_AMOUNT + "원 이상이어야 합니다.");
        }
    }

    private void validateUnit(int amount) {
        if (amount % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 " + LOTTO_PRICE + "원 단위여야 합니다.");
        }
    }

    public int getLottoCount() {
        return amount / LOTTO_PRICE;
    }

    public int getAmount() {
        return amount;
    }
}