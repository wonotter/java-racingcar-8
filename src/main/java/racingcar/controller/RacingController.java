package racingcar.controller;

import java.util.List;
import racingcar.model.Car;
import racingcar.model.CarGroup;
import racingcar.model.RacingGame;
import racingcar.model.generator.RandomNumberGenerator;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingController {

    private final InputView inputView;
    private final OutputView outputView;
    private final RandomNumberGenerator generator;

    public RacingController(InputView inputView, OutputView outputView, RandomNumberGenerator generator) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.generator = generator;
    }

    public void run() {
        CarGroup carGroup = createCars();
        int rounds = inputView.readRounds();

        RacingGame game = new RacingGame(carGroup, rounds, generator);
        playGame(game);

        List<Car> winners = game.determineWinners();
        outputView.printWinners(winners);
    }

    private CarGroup createCars() {
        List<String> carNames = inputView.readCarNames();
        return CarGroup.from(carNames);
    }

    private void playGame(RacingGame game) {
        outputView.printResultMessage();

        for (int i = 0; i < game.getRounds(); i++) {
            game.playRound();
            outputView.printRoundResult(game.getCarGroup().getCars());
        }
    }
}
