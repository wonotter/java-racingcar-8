package racingcar.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import racingcar.model.generator.FixedRandomNumberGenerator;
import racingcar.model.generator.RandomNumberGenerator;
import racingcar.model.generator.SequentialRandomNumberGenerator;

@DisplayName("CarGroup 클래스")
class CarGroupTest {

    @Nested
    @DisplayName("생성 테스트")
    class CreateTest {

        @Test
        @DisplayName("유효한 이름 리스트로 CarGroup을 생성한다")
        void createCarGroupWithValidNames() {
            // given
            List<String> names = Arrays.asList("pobi", "woni", "jun");

            // when
            CarGroup carGroup = CarGroup.from(names);

            // then
            assertThat(carGroup.getCars()).hasSize(3);
            assertThat(carGroup.getCars())
                    .extracting(Car::getName)
                    .containsExactly("pobi", "woni", "jun");
        }

        @Test
        @DisplayName("빈 리스트로 생성 시 예외가 발생한다")
        void throwExceptionWhenNamesIsEmpty() {
            // given
            List<String> emptyList = Collections.emptyList();

            // when & then
            assertThatThrownBy(() -> CarGroup.from(emptyList))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("입력값이 비어있습니다");
        }

        @Test
        @DisplayName("중복된 이름으로 생성 시 예외가 발생한다")
        void throwExceptionWhenNamesHaveDuplicates() {
            // given
            List<String> duplicateNames = Arrays.asList("pobi", "woni", "pobi");

            // when & then
            assertThatThrownBy(() -> CarGroup.from(duplicateNames))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("자동차 이름이 중복되었습니다");
        }

        @Test
        @DisplayName("100대를 초과하여 생성 시 예외가 발생한다")
        void throwExceptionWhenCarCountExceeds100() {
            // given
            List<String> manyNames = IntStream.rangeClosed(1, 101)
                    .mapToObj(i -> "car" + i)
                    .collect(Collectors.toList());

            // when & then
            assertThatThrownBy(() -> CarGroup.from(manyNames))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("자동차는 최대 100대까지 입력 가능합니다");
        }
    }

    @Nested
    @DisplayName("moveAll() 메서드 테스트")
    class MoveAllTest {

        @Test
        @DisplayName("모든 자동차가 이동한다")
        void moveAllCars() {
            // given
            List<String> names = Arrays.asList("pobi", "woni");
            CarGroup carGroup = CarGroup.from(names);
            RandomNumberGenerator generator = new FixedRandomNumberGenerator(4);

            // when
            carGroup.moveAll(generator);

            // then
            assertThat(carGroup.getCars())
                    .allMatch(car -> car.getPosition() == 1);  // 모든 자동차가 위치 1
        }

        @Test
        @DisplayName("랜덤 값에 따라 자동차들이 다르게 이동한다")
        void moveCarsWithDifferentRandomValues() {
            // given
            List<String> names = Arrays.asList("pobi", "woni");
            CarGroup carGroup = CarGroup.from(names);
            RandomNumberGenerator generator = new SequentialRandomNumberGenerator(4, 3);

            // when
            carGroup.moveAll(generator);

            // then
            List<Car> cars = carGroup.getCars();
            assertThat(cars.get(0).getPosition()).isEqualTo(1);  // pobi는 전진
            assertThat(cars.get(1).getPosition()).isEqualTo(0);  // woni는 정지
        }
    }

    @Nested
    @DisplayName("findWinners() 메서드 테스트")
    class FindWinnerTest {

        @Test
        @DisplayName("우승자가 1명인 경우")
        void findSingleWinner() {
            // given
            List<String> names = Arrays.asList("pobi", "woni");
            CarGroup carGroup = CarGroup.from(names);
            RandomNumberGenerator generator = new SequentialRandomNumberGenerator(4, 3);
            carGroup.moveAll(generator);

            // when
            List<Car> winners = carGroup.findWinners();

            // then
            assertThat(winners).hasSize(1);
            assertThat(winners.get(0).getName()).isEqualTo("pobi");
        }

        @Test
        @DisplayName("우승자가 여러 명인 경우")
        void findMultipleWinners() {
            // given
            List<String> names = Arrays.asList("pobi", "woni", "jun");
            CarGroup carGroup = CarGroup.from(names);
            RandomNumberGenerator generator = new SequentialRandomNumberGenerator(4, 4, 3);
            carGroup.moveAll(generator);

            // when
            List<Car> winners = carGroup.findWinners();

            // then
            assertThat(winners).hasSize(2);
            assertThat(winners)
                    .extracting(Car::getName)
                    .containsExactlyInAnyOrder("pobi", "woni");
        }
    }
}