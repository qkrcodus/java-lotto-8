package lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.HashSet;

public class Lotto {
    private static final int LOTTO_SIZE = 6;
    private static final int LOTTO_MIN = 1;
    private static final int LOTTO_MAX = 45;

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = sortNumbers(numbers);
    }

    private void validate(List<Integer> numbers) {
        validateSize(numbers);
        validateEachNumber(numbers);
        validateDuplicate(numbers);
    }

    private void validateSize(List<Integer> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }
    }

    private void validateEachNumber(List<Integer> numbers) {
        for (int number : numbers) {
            if (number > LOTTO_MAX | number < LOTTO_MIN) {
                throw new IllegalArgumentException("[ERROR] 로또 번호는 1~45 사이의 수여야 합니다.");
            }
        }
    }

    private void validateDuplicate(List<Integer> numbers) {
        if (new HashSet<>(numbers).size() != numbers.size()) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 중복될 수 없습니다.");
        }
    }

    private List<Integer> sortNumbers(List<Integer> numbers) {
        List<Integer> sortedNumbers = new ArrayList<>(numbers);
        Collections.sort(sortedNumbers);
        return sortedNumbers;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

}
