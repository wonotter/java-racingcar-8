package racingcar.model.generator;

public class FixedRandomNumberGenerator implements RandomNumberGenerator {

    private final int fixedValue;

    public FixedRandomNumberGenerator(int fixedValue) {
        this.fixedValue = fixedValue;
    }

    @Override
    public int generate() {
        return fixedValue;
    }
}
