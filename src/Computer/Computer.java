package Computer;

import java.util.Random;

public class Computer {
    private Random rand;

    public Computer() {
        long seed = System.currentTimeMillis();
        this.rand = new Random(seed);
    }

    public int getRandInt(int n) {
        return rand.nextInt(n);
    }
}
