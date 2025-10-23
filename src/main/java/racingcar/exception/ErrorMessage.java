package racingcar.exception;

public enum ErrorMessage {
    // 공통 입력 검증
    BLANK_INPUT("입력값이 비어있습니다."),

    // 자동차 이름 관련
    EMPTY_CAR_NAME("자동차 이름이 비어있습니다."),
    CAR_NAME_LENGTH_EXCEEDED("자동차 이름은 5자 이하여야 합니다."),
    DUPLICATE_CAR_NAME("자동차 이름이 중복되었습니다."),
    CAR_COUNT_EXCEEDED("자동차는 최대 100대까지 입력 가능합니다."),

    // 시도 횟수 관련
    INVALID_ROUND_FORMAT("시도 횟수는 숫자로 입력해야 합니다."),
    ROUND_NOT_POSITIVE("시도 횟수는 양수여야 합니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
