package racingcar.model;

import racingcar.exception.ErrorMessage;

public class Car {

    private static final int MAX_NAME_LENGTH = 5;
    private static final int FORWARD_THRESHOLD = 4;
    
    private final String name;
    private int position;

    public Car(String name) {
        validateName(name);
        this.name = name;
        this.position = 0;
    }

    private void validateName(String name) {
        if (name == null) {
            throw new IllegalArgumentException(ErrorMessage.BLANK_INPUT.getMessage());
        }
        if (name.isBlank()) {
            throw new IllegalArgumentException(ErrorMessage.EMPTY_CAR_NAME.getMessage());
        }
        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException(ErrorMessage.CAR_NAME_LENGTH_EXCEEDED.getMessage());
        }
    }

    public void move(int randomValue) {
        if (randomValue >= FORWARD_THRESHOLD) {
            position++;
        }
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }
}
