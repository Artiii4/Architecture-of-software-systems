import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

class RequestGenerator {
    private static final AtomicInteger requestIdCounter = new AtomicInteger(1);
    private static final String[] descriptions = {"Fix printer", "Install software", "Hard drive issue", "Board burned out",
            "Network issue","Upgrade of accessories", "Viruses cleaning", "Replace hardware", "Upgrade OS"};
    private final Random random = new Random();

    public Request generateRequest(boolean informationOutput) {
        int id = requestIdCounter.getAndIncrement();
        String description = descriptions[random.nextInt(descriptions.length)];
        if (informationOutput){
            System.out.println("System has received an request with issue: "+ description);
        }
        return new Request(id, description);
    }
}
