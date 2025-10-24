package racingcar;

import racingcar.controller.RacingController;
import racingcar.model.generator.DefaultRandomNumberGenerator;
import racingcar.model.generator.RandomNumberGenerator;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        RandomNumberGenerator generator = new DefaultRandomNumberGenerator();
        RacingController controller = new RacingController(inputView, outputView, generator);

        controller.run();
    }
}
