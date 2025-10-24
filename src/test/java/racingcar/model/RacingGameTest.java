package racingcar.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import racingcar.model.generator.FixedRandomNumberGenerator;
import racingcar.model.generator.RandomNumberGenerator;
import racingcar.model.generator.SequentialRandomNumberGenerator;

@DisplayName("RacingGame 클래스")
class RacingGameTest {

    @Nested
    @DisplayName("생성자 테스트")
    class ConstructorTest {

        @Test
        @DisplayName("유효한 입력으로 게임을 생성한다")
        void createGameWithValidInput() {
            // given
            CarGroup carGroup = CarGroup.from(Arrays.asList("pobi", "woni"));
            int rounds = 5;
            RandomNumberGenerator generator = new FixedRandomNumberGenerator(4);

            // when
            RacingGame game = new RacingGame(carGroup, rounds, generator);

            // then
            assertThat(game.getRounds()).isEqualTo(5);
            assertThat(game.getCarGroup()).isEqualTo(carGroup);
        }

        @Test
        @DisplayName("0 이하의 라운드로 생성 시 예외가 발생한다")
        void throwExceptionWhenRoundsIsZero() {
            // given
            CarGroup carGroup = CarGroup.from(Arrays.asList("pobi", "woni"));
            int invalidRounds = 0;
            RandomNumberGenerator generator = new FixedRandomNumberGenerator(4);

            // when & then
            assertThatThrownBy(() -> new RacingGame(carGroup, invalidRounds, generator))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("시도 횟수는 양수여야 합니다");
        }

        @Test
        @DisplayName("음수 라운드로 생성 시 예외가 발생한다")
        void throwExceptionWhenRoundsIsNegative() {
            // given
            CarGroup carGroup = CarGroup.from(Arrays.asList("pobi", "woni"));
            int invalidRounds = -1;
            RandomNumberGenerator generator = new FixedRandomNumberGenerator(4);

            // when & then
            assertThatThrownBy(() -> new RacingGame(carGroup, invalidRounds, generator))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("시도 횟수는 양수여야 합니다");
        }
    }

    @Nested
    @DisplayName("playRound() 메서드 테스트")
    class PlayRoundTest {

        @Test
        @DisplayName("라운드를 진행하면 자동차들이 이동한다")
        void playRoundMovesCars() {
            // given
            CarGroup carGroup = CarGroup.from(Arrays.asList("pobi", "woni"));
            RandomNumberGenerator generator = new FixedRandomNumberGenerator(4);
            RacingGame game = new RacingGame(carGroup, 1, generator);

            // when
            game.playRound();

            // then
            assertThat(game.getCarGroup().getCars())
                    .allMatch(car -> car.getPosition() > 0);
        }
    }

    @Nested
    @DisplayName("determineWinners() 메서드 테스트")
    class DetermineWinnerTest {

        @Test
        @DisplayName("게임 종료 후 우승자를 결정한다")
        void determineWinnersAfterGame() {
            // given
            CarGroup carGroup = CarGroup.from(Arrays.asList("pobi", "woni"));
            RandomNumberGenerator generator = new SequentialRandomNumberGenerator(4, 3);
            RacingGame game = new RacingGame(carGroup, 1, generator);

            // when
            game.playRound();
            List<Car> winners = game.determineWinners();

            // then
            assertThat(winners).hasSize(1);
            assertThat(winners.get(0).getName()).isEqualTo("pobi");
        }
    }
}