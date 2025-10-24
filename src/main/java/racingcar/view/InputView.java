package racingcar.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import racingcar.exception.ErrorMessage;

public class InputView {

    private static final String CAR_NAMES_PROMPT = "경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)";
    private static final String ROUNDS_PROMPT = "시도할 횟수는 몇 회인가요?";
    private static final String DELIMITER = ",";

    public List<String> readCarNames() {
        System.out.println(CAR_NAMES_PROMPT);

        String input = Console.readLine();
        validateNotBlank(input);

        return parseCarNames(input);
    }

    public int readRounds() {
        System.out.println(ROUNDS_PROMPT);

        String input = Console.readLine();
        validateNotBlank(input);

        return parseRounds(input);
    }

    private void validateNotBlank(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(ErrorMessage.BLANK_INPUT.getMessage());
        }
    }

    private List<String> parseCarNames(String input) {
        return Arrays.stream(input.split(DELIMITER))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    private int parseRounds(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ROUND_FORMAT.getMessage());
        }
    }
}
