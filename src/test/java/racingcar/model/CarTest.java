package racingcar.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Car 클래스")
class CarTest {

    @Nested
    @DisplayName("생성자 테스트")
    class ConstructorTest {

        @Test
        @DisplayName("유효한 이름으로 자동차를 생성한다")
        void createCarWithValidName() {
            // given
            String validName = "pobi";

            // when
            Car car = new Car(validName);

            // then
            assertThat(car.getName()).isEqualTo(validName);
            assertThat(car.getPosition()).isEqualTo(0);
        }

        @Test
        @DisplayName("5자를 초과하는 이름으로 자동차 생성 시 예외가 발생한다")
        void throwExceptionWhenNameExceedsMaxLength() {
            // given
            String longName = "abcdef";

            // when & then
            assertThatThrownBy(() -> new Car(longName))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("자동차 이름은 5자 이하여야 합니다.");
        }

        @Test
        @DisplayName("공백만 있는 이름으로 자동차 생성 시 예외가 발생한다")
        void throwExceptionWhenNameIsBlank() {
            // given
            String blankName = "   ";

            // when & then
            assertThatThrownBy(() -> new Car(blankName))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("자동차 이름이 비어있습니다");
        }

        @Test
        @DisplayName("빈 문자열로 자동차 생성 시 예외가 발생한다")
        void throwExceptionWhenNameIsEmpty() {
            // given
            String emptyName = "";

            // when & then
            assertThatThrownBy(() -> new Car(emptyName))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("자동차 이름이 비어있습니다");
        }

        @Test
        @DisplayName("null 이름으로 자동차 생성 시 예외가 발생한다")
        void throwExceptionWhenNameIsNull() {
            // given
            String nullName = null;

            // when & then
            assertThatThrownBy(() -> new Car(nullName))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("입력값이 비어있습니다");
        }
    }

    @Nested
    @DisplayName("move() 메서드 테스트")
    class MoveTest {

        @Test
        @DisplayName("랜덤 값이 4 이상일 때 전진한다")
        void moveForwardWhenRandomValueIsGreaterThanOrEqualTo4() {
            // given
            Car car = new Car("pobi");
            int initialPosition = car.getPosition();

            // when
            car.move(4);

            // then
            assertThat(car.getPosition()).isEqualTo(initialPosition + 1);
        }

        @Test
        @DisplayName("랜덤 값이 3 이하일 때 정지한다")
        void stopWhenRandomValueIsLessThan4() {
            // given
            Car car = new Car("pobi");
            int initialPosition = car.getPosition();

            // when
            car.move(3);

            // then
            assertThat(car.getPosition()).isEqualTo(initialPosition);
        }

        @Test
        @DisplayName("여러 번 전진할 수 있다")
        void moveMultipleTimes() {
            // given
            Car car = new Car("pobi");

            // when
            car.move(4);
            car.move(5);
            car.move(6);

            // then
            assertThat(car.getPosition()).isEqualTo(3);
        }
    }
}