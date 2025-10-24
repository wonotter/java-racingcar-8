package racingcar.view;

import java.util.List;
import java.util.stream.Collectors;
import racingcar.model.Car;

public class OutputView {

    private static final String RESULT_MESSAGE = "\n실행 결과";
    private static final String POSITION_SYMBOL = "-";
    private static final String NAME_POSITION_DELIMITER = " : ";
    private static final String WINNER_PREFIX = "최종 우승자 : ";
    private static final String WINNER_DELIMITER = ", ";

    public void printResultMessage() {
        System.out.println(RESULT_MESSAGE);
    }

    public void printRoundResult(List<Car> carGroup) {
        for (Car car : carGroup) {
            printCarStatus(car);
        }

        System.out.println();
    }

    private void printCarStatus(Car car) {
        String position = POSITION_SYMBOL.repeat(car.getPosition());
        System.out.println(car.getName() + NAME_POSITION_DELIMITER + position);
    }

    public void printWinners(List<Car> winners) {
        String winnerNames = winners.stream()
                .map(Car::getName)
                .collect(Collectors.joining(WINNER_DELIMITER));
        
        System.out.println(WINNER_PREFIX + winnerNames);
    }
}
