import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;

class MainSystem {
    private final int queueBufferCapacity;
    private final int technicianQuantity;
    private final int requestsQuantity;
    private final int generatorsAmount;

    private int time=0;
    private int deniedRequests = 0;

    public MainSystem(int queueBufferCapacity, int technicianQuantity, int requestsQuantity, int generatorsAmount) {
        this.requestsQuantity=requestsQuantity;
        this.queueBufferCapacity = queueBufferCapacity;
        this.technicianQuantity = technicianQuantity;
        this.generatorsAmount=generatorsAmount;
    }

    public void run(int leftBorderOfUniformDistribution, int rightBorderOfUniformDistribution, int lowTypeTechniciansAmount) {
        boolean isStepMode = false;
        Scanner in = new Scanner(System.in);
        System.out.println("2 modes are supported (automatic and step-by-step). " +
                "To use the automatic mode, enter the letter A, to use the step-by-step mode, enter the letter S");
        String getit = in.next().toLowerCase(Locale.ROOT);
        switch (getit) {
            case "a":
                System.out.println("You selected automatic mod");
                break;
            case "s":
                isStepMode = true;
                System.out.println("You selected step-by-step mod\n\nTime: 0\n");
                break;
            default:
                System.out.println("Invalid input!");
                return;
        }

        Buffer buffer = new Buffer(queueBufferCapacity);
        List<Technician> technicians = new ArrayList<>();

        Random random = new Random();
        List<String> firstNames = Arrays.asList("John", "Bob", "Mike", "Denis", "David", "Ivan", "Tom", "Andrew", "Kevin", "Thomsa");
        List<String> lastNames = Arrays.asList("Smith", "Green", "Kelly", "Brown", "Starov", "Miller", "Sergeev", "Anderson");
        String firstName, lastName;
        for (int i = 1; i < lowTypeTechniciansAmount+1; i++) {
            firstName = firstNames.get(random.nextInt(firstNames.size()));
            lastName = lastNames.get(random.nextInt(lastNames.size()));
            technicians.add(new Technician(i, firstName + " " + lastName, 0.5, true));
        }
        for (int i=lowTypeTechniciansAmount+1;i<technicianQuantity+1;i++){
            firstName = firstNames.get(random.nextInt(firstNames.size()));
            lastName = lastNames.get(random.nextInt(lastNames.size()));
            technicians.add(new Technician(i, firstName + " " + lastName, 0.5, false));
        }
        ArrayList<RequestGenerator> generators= new ArrayList<>(generatorsAmount);
        for (int i=0; i<generatorsAmount;i++){
            generators.add(new RequestGenerator(leftBorderOfUniformDistribution, rightBorderOfUniformDistribution, i));
        }
        RequestDistribution distribution = new RequestDistribution(technicians, buffer, isStepMode, generators);

        Request request = null;
        for (int i = 0; i < requestsQuantity;) {
            if (isStepMode){
                buffer.printAllRequests();
                in.nextLine();
            }
            for (RequestGenerator generator: generators){
                if (generator.isValidRequest()){
                    request = generator.generateRequest(isStepMode);
                    boolean success = distribution.distributeRequests(request);
                    if (!success) {
                        deniedRequests++;
                    }
                    if (i>=requestsQuantity){
                        break;
                    }
                    i++;
                }
            }
            time++;
            distribution.updateTime();
        }
        in.close();

        if (!isStepMode) {
            printStatistics(technicians, leftBorderOfUniformDistribution, rightBorderOfUniformDistribution, generators, generatorsAmount);
        }
    }

    private void printStatistics(List<Technician> technicians, int leftBorderOfUniformDistribution, int rightBorderOfUniformDistribution, ArrayList<RequestGenerator> generators, int generatorsAmount) {
        double rejectionProbability = (double) deniedRequests / requestsQuantity;
        double totalBusyTime = technicians.stream()
                .mapToDouble(Technician::getTotalBusyTime).sum();
        double averageLoad = totalBusyTime / (time * technicianQuantity);

        System.out.println("\nSummary Statistics:");
        String summaryHeader = "| %-10s | %-10s | %-15s | %-15s | %-15s | %-25s | %-20s | %-25s  %-15s | %-15s |%n";
        System.out.printf(summaryHeader,
                "Requests", "Generators", "Technicians", "Buffer size", "Time in system",
                "Rejection probability", "Average workload",
                "Uniform distribution law:", "Left boundary", "Right boundary");
        System.out.println("=".repeat(195));
        System.out.printf("| %-10d | %-10d | %-15d | %-15d | %-15d | %-25.2f | %-20.2f | %-25s  %-15d | %-15d |%n",
                requestsQuantity, generatorsAmount, technicianQuantity, queueBufferCapacity, time,
                rejectionProbability * 100, averageLoad * 100, " ", leftBorderOfUniformDistribution, rightBorderOfUniformDistribution);
        System.out.println("-".repeat(195));

        System.out.println("\nTechnician Workloads:");
        String techHeader = "| %-4s | %-15.15s | %-12.12s | %-8.8s |%n";
        System.out.printf(techHeader, "ID", "Name", "Busy Time", "Load (%)");
        System.out.println("=".repeat(52));
        String techData = "| %-4d | %-15.15s | %-12.1f | %-8.1f |%n";
        for (Technician tech : technicians) {
            double techLoad = (tech.getTotalBusyTime() / time) * 100;
            System.out.printf(techData, tech.getId(), tech.getName(), tech.getTotalBusyTime(), techLoad);
        }
        System.out.println("-".repeat(52));

        System.out.println("\nRequest Generators Info:");
        String genHeader = "| %-4s | %-20.20s | %-20.20s | %-15.15s | %-20.15s |%n";
        System.out.printf(genHeader, "ID", "Created Requests", "Removed Requests", "Remaining Requests", "Rejection Probability (%)");
        System.out.println("=".repeat(95));
        String genData = "| %-4d | %-20d | %-20d | %-15d | %-20.2f |%n";
        for (RequestGenerator generator : generators) {
            int createdRequests = generator.getRequestsAmount();
            int removedRequests = generator.getRemovedRequestsCount();
            int remainingRequests = createdRequests - removedRequests;
            double rejectionRate = createdRequests > 0 ? (double) removedRequests / createdRequests * 100 : 0.0;
            System.out.printf(genData, generator.getGeneratorId(), createdRequests, removedRequests, remainingRequests, rejectionRate);
        }
        System.out.println("-".repeat(95));
    }
}
