package Google;

import java.util.concurrent.ThreadLocalRandom;

    abstract class AbstractEmployee {
    protected int getRandomSalary(int minimum, int maximum) {
        return ThreadLocalRandom.current().nextInt(minimum, maximum);
    }
    protected final int HUNGRED_PROCENT = 100;
    protected final int FIVE_PROCENT = 5;
}
