package racingcar.model.generator;

import camp.nextstep.edu.missionutils.Randoms;

public class DefaultRandomNumberGenerator implements RandomNumberGenerator {

    private static final int MIN_VALUE = 0;
    private static final int MAX_VALUE = 9;

    @Override
    public int generate() {
        return Randoms.pickNumberInRange(MIN_VALUE, MAX_VALUE);
    }
}
