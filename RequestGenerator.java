import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

class RequestGenerator {
    private final int leftBorderOfUniformDistribution;
    private final int rightBorderOfUniformDistribution;
    private int requestsAmount;
    private int removedRequestsCount = 0;
    private final int generatorId;
    private int time=0;
    private static final AtomicInteger requestIdCounter = new AtomicInteger(1);
    private static final String[] descriptions = {"Fix printer", "Install software", "Hard drive issue", "Board burned out",
            "Network issue","Upgrade of accessories", "Viruses cleaning", "Replace hardware", "Upgrade OS"};
    private final Random random = new Random();

    public RequestGenerator(int leftBorderOfUniformDistribution, int rightBorderOfUniformDistribution, int generatorId){
        this.leftBorderOfUniformDistribution=leftBorderOfUniformDistribution;
        this.rightBorderOfUniformDistribution=rightBorderOfUniformDistribution;
        this.generatorId=generatorId;
    }

    public int getRequestsAmount() {
        return requestsAmount;
    }

    public int getGeneratorId() {
        return generatorId;
    }

    public Request generateRequest(boolean informationOutput) {
        requestsAmount++;
        int id = requestIdCounter.getAndIncrement();
        String description = descriptions[random.nextInt(descriptions.length)];
        if (informationOutput) {
            System.out.println("Generated Request ID: " + id + ", Description: " + description);
        }
        return new Request(id, description, generatorId);
    }

    public void incrementRemovedRequestsCount() {
        removedRequestsCount++;
    }

    public int getRemovedRequestsCount() {
        return removedRequestsCount;
    }

    public boolean isValidRequest() {
        if (time>=rightBorderOfUniformDistribution){
            time=0;
            return true;
        }else if (time>=leftBorderOfUniformDistribution){
            if (random.nextInt(rightBorderOfUniformDistribution-leftBorderOfUniformDistribution)==time){
                time=0;
                return true;
            }
        }
        time++;
        return  false;
    }
}
