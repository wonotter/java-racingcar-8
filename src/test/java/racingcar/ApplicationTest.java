package racingcar;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class ApplicationTest extends NsTest {
    private static final int MOVING_FORWARD = 4;
    private static final int STOP = 3;

    @Test
    void 기능_테스트() {
        assertRandomNumberInRangeTest(
                () -> {
                    run("pobi,woni", "1");
                    assertThat(output()).contains("pobi : -", "woni : ", "최종 우승자 : pobi");
                },
                MOVING_FORWARD, STOP
        );
    }

    @Test
    void 예외_테스트() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("pobi,javaji", "1"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Nested
    @DisplayName("정상 기능 테스트")
    class NormalFunctionTest {

        @Test
        @DisplayName("공동 우승자 테스트")
        void multipleWinner() {
            assertRandomNumberInRangeTest(
                    () -> {
                        run("pobi,woni", "1");
                        assertThat(output())
                                .contains("pobi : -")
                                .contains("woni : -")
                                .contains("최종 우승자 : pobi, woni");
                    },
                    MOVING_FORWARD, MOVING_FORWARD
            );
        }

        @Test
        @DisplayName("여러 라운드 진행 테스트")
        void multipleRounds() {
            assertRandomNumberInRangeTest(
                    () -> {
                        run("pobi,woni", "5");
                        String output = output();

                        assertThat(output).contains("실행 결과");

                        assertThat(output).contains("최종 우승자");
                    },
                    MOVING_FORWARD, MOVING_FORWARD, MOVING_FORWARD, MOVING_FORWARD, MOVING_FORWARD,
                    MOVING_FORWARD, MOVING_FORWARD, STOP, MOVING_FORWARD, STOP
            );
        }

        @Test
        @DisplayName("공백 포함 자동차 이름 입력 테스트")
        void carNamesWithBlank() {
            assertRandomNumberInRangeTest(
                    () -> {
                        run("pobi, woni, jun  ", "1");
                        assertThat(output())
                                .contains("pobi")
                                .contains("woni")
                                .contains("jun");
                    },
                    MOVING_FORWARD, MOVING_FORWARD, MOVING_FORWARD
            );
        }
    }

    @Nested
    @DisplayName("예외 상황 테스트")
    class ExceptionTest {

        @Test
        @DisplayName("빈 자동차 이름 입력")
        void carNamesWithNothing() {
            assertSimpleTest(() ->
                    assertThatThrownBy(() -> runException("", "1"))
                            .isInstanceOf(IllegalArgumentException.class)
                            .hasMessageContaining("입력값이 비어있습니다.")
            );
        }

        @Test
        @DisplayName("공백만 입력 - 자동차 이름")
        void carNamesWithBlank() {
            assertSimpleTest(() ->
                    assertThatThrownBy(() -> runException("   ", "1"))
                            .isInstanceOf(IllegalArgumentException.class)
                            .hasMessageContaining("입력값이 비어있습니다.")
            );
        }

        @Test
        @DisplayName("빈 자동차 이름 포함 - 쉼표만 연속")
        void carNamesWithMoreComma() {
            assertSimpleTest(() ->
                    assertThatThrownBy(() -> runException("pobi,,woni", "1"))
                            .isInstanceOf(IllegalArgumentException.class)
                            .hasMessageContaining("자동차 이름이 비어있습니다.")
            );
        }

        @Test
        @DisplayName("중복된 자동차 이름")
        void carNamesDuplicated() {
            assertSimpleTest(() ->
                    assertThatThrownBy(() -> runException("pobi,pobi", "1"))
                            .isInstanceOf(IllegalArgumentException.class)
                            .hasMessageContaining("자동차 이름이 중복되었습니다.")
            );
        }

        @Test
        @DisplayName("잘못된 시도 횟수 형식")
        void wrongRoundInput() {
            assertSimpleTest(() ->
                    assertThatThrownBy(() -> runException("pobi,woni", "abc"))
                            .isInstanceOf(IllegalArgumentException.class)
                            .hasMessageContaining("시도 횟수는 숫자로 입력해야 합니다.")
            );
        }

        @Test
        @DisplayName("시도 횟수 실수 입력")
        void floatRoundInput() {
            assertSimpleTest(() ->
                    assertThatThrownBy(() -> runException("pobi,woni", "3.14"))
                            .isInstanceOf(IllegalArgumentException.class)
                            .hasMessageContaining("시도 횟수는 숫자로 입력해야 합니다.")
            );
        }

        @Test
        @DisplayName("시도 횟수 0 입력")
        void RoundInput0() {
            assertSimpleTest(() ->
                    assertThatThrownBy(() -> runException("pobi,woni", "0"))
                            .isInstanceOf(IllegalArgumentException.class)
                            .hasMessageContaining("시도 횟수는 양수여야 합니다.")
            );
        }

        @Test
        @DisplayName("시도 횟수 음수 입력")
        void RoundInputNegativeNumber() {
            assertSimpleTest(() ->
                    assertThatThrownBy(() -> runException("pobi,woni", "-1"))
                            .isInstanceOf(IllegalArgumentException.class)
                            .hasMessageContaining("시도 횟수는 양수여야 합니다.")
            );
        }
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
