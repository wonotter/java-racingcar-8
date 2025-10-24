package racingcar.model;

import java.util.List;
import racingcar.exception.ErrorMessage;
import racingcar.model.generator.RandomNumberGenerator;

public class RacingGame {

    private final CarGroup carGroup;
    private final int rounds;
    private final RandomNumberGenerator generator;

    public RacingGame(CarGroup carGroup, int rounds, RandomNumberGenerator generator) {
        validateRounds(rounds);
        this.carGroup = carGroup;
        this.rounds = rounds;
        this.generator = generator;
    }

    private void validateRounds(int rounds) {
        if (rounds <= 0) {
            throw new IllegalArgumentException(ErrorMessage.ROUND_NOT_POSITIVE.getMessage());
        }
    }

    public void playRound() {
        carGroup.moveAll(generator);
    }

    public int getRounds() {
        return rounds;
    }

    public CarGroup getCarGroup() {
        return carGroup;
    }

    public List<Car> determineWinners() {
        return carGroup.findWinners();
    }
}
