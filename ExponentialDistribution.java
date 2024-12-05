import java.util.Random;

public class ExponentialDistribution {
    private static final Random random = new Random();
    private double lambda;

    public ExponentialDistribution(double lambda) {
        this.lambda = lambda;
    }

    public double generateServiceTime() {
        return -Math.log(1 - random.nextDouble()) / lambda;
    }
}
