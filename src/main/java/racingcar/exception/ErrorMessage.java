package racingcar.exception;

public enum ErrorMessage {

    BLANK_INPUT("입력값이 비어있습니다."),
    EMPTY_CAR_NAME("자동차 이름이 비어있습니다."),
    CAR_NAME_LENGTH_EXCEEDED("자동차 이름은 5자 이하여야 합니다."),
    DUPLICATE_CAR_NAME("자동차 이름이 중복되었습니다."),
    CAR_COUNT_EXCEEDED("자동차는 최대 100대까지 입력 가능합니다."),
    INVALID_ROUND_FORMAT("시도 횟수는 숫자로 입력해야 합니다."),
    ROUND_NOT_POSITIVE("시도 횟수는 양수여야 합니다."),
    OVERFLOW_ROUND_VALUE("시도 횟수가 int 범위를 벗어났습니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
