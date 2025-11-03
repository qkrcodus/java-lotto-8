package lotto;

import camp.nextstep.edu.missionutils.Console;
import lotto.controller.LottoController;

public class Application {
    public static void main(String[] args) {
        try {
            LottoController controller = new LottoController();
            controller.run();
        } finally {
            Console.close();
        }
    }
}
