package racingcar.model.generator;

import java.util.Arrays;
import java.util.List;

public class SequentialRandomNumberGenerator implements RandomNumberGenerator {

    private final List<Integer> values;
    private int index = 0;

    public SequentialRandomNumberGenerator(Integer... values) {
        this.values = Arrays.asList(values);
    }

    @Override
    public int generate() {
        return values.get(index++);
    }
}
