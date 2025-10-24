package racingcar.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import racingcar.exception.ErrorMessage;
import racingcar.model.generator.RandomNumberGenerator;

public class CarGroup {

    private static final int MAX_CAR_COUNT = 100;
    private final List<Car> carGroup;

    private CarGroup(List<Car> carGroup) {
        this.carGroup = carGroup;
    }

    public static CarGroup from(List<String> names) {
        validateNotNullOrEmpty(names);
        validateCarCount(names);
        validateNoDuplicate(names);

        List<Car> cars = new ArrayList<>();
        for (String name : names) {
            cars.add(new Car(name));
        }

        return new CarGroup(cars);
    }

    private static void validateNotNullOrEmpty(List<String> names) {
        if (names == null || names.isEmpty()) {
            throw new IllegalArgumentException(ErrorMessage.BLANK_INPUT.getMessage());
        }
    }

    private static void validateCarCount(List<String> names) {
        if (names.size() > MAX_CAR_COUNT) {
            throw new IllegalArgumentException(ErrorMessage.CAR_COUNT_EXCEEDED.getMessage());
        }
    }

    private static void validateNoDuplicate(List<String> names) {
        if (names.size() != new HashSet<>(names).size()) {
            throw new IllegalArgumentException(ErrorMessage.DUPLICATE_CAR_NAME.getMessage());
        }
    }

    public void moveAll(RandomNumberGenerator generator) {
        for (Car car : carGroup) {
            car.move(generator.generate());
        }
    }

    public List<Car> findWinners() {
        int maxPosition = findMaxPosition();
        
        return carGroup.stream()
                .filter(car -> car.getPosition() == maxPosition)
                .collect(Collectors.toList());
    }

    private int findMaxPosition() {
        return carGroup.stream()
                .mapToInt(Car::getPosition)
                .max()
                .orElse(0);
    }

    public List<Car> getCars() {
        return new ArrayList<>(carGroup);
    }
}
