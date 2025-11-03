package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.ArrayList;
import java.util.List;
import lotto.model.lotto.LottoNumber;
import lotto.model.money.Money;

public class InputView {
    private static final String DELIMITER = ",";
    private static final int WINNING_NUMBERS_SIZE = 6;

    public static Money readPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        String input = Console.readLine();
        int amount = validatePurchaseAmount(input);
        return Money.from(amount);
    }

    public static List<Integer> readWinningNumbers() {
        System.out.println();
        System.out.println("당첨 번호를 입력해 주세요.");
        String input = Console.readLine();
        return validateWinningNumbers(input);
    }

    public static int readBonusNumber() {
        System.out.println();
        System.out.println("보너스 번호를 입력해 주세요.");
        String input = Console.readLine();
        return validateBonusNumber(input);
    }

    static int validatePurchaseAmount(String input) {
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 숫자여야 합니다.");
        }
    }

    static List<Integer> validateWinningNumbers(String input) {
        String[] tokens = input.split(DELIMITER);
        validateWinningNumbersSize(tokens);

        List<Integer> numbers = new ArrayList<>();
        for (String token : tokens) {
            numbers.add(parseNumber(token));
        }
        validateEachNumber(numbers);
        return numbers;
    }

    private static void validateWinningNumbersSize(String[] tokens) {
        if (tokens.length != WINNING_NUMBERS_SIZE) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 " + WINNING_NUMBERS_SIZE + "개여야 합니다.");
        }
    }

    private static int parseNumber(String token) {
        try {
            return Integer.parseInt(token.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 숫자여야 합니다.");
        }
    }

    private static void validateEachNumber(List<Integer> numbers) {
        for (int number : numbers) {
            LottoNumber.from(number);
        }
    }

    static int validateBonusNumber(String input) {
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 숫자여야 합니다.");
        }
    }
}