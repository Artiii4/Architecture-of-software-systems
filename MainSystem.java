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
    private double time=0;

    public MainSystem(int queueBufferCapacity, int technicianQuantity, int requestsQuantity) {
        this.requestsQuantity=requestsQuantity;
        this.queueBufferCapacity = queueBufferCapacity;
        this.technicianQuantity = technicianQuantity;
    }

    public void run() {
        boolean isStepMode=false;
        Scanner in= new Scanner(System.in);
        System.out.println("2 modes are supported (automatic and step-by-step). " +
                "To use the automatic mode, enter the letter A, to use the step-by-step mode, enter the letter S");
        String getit= in.next().toLowerCase(Locale.ROOT);
        switch (getit){
            case "a":
                System.out.println("You selected automatic mod");
                break;
            case  "s":
                isStepMode=true;
                System.out.println("You selected step-by-step mod\n\nTime: 0\n");
                break;
            default:
                System.out.println("Invalid input!");
                return;
        }

        RequestGenerator generator = new RequestGenerator();
        Buffer buffer = new Buffer(queueBufferCapacity);
        List<Technician> technicians = new ArrayList<>();

        Random random = new Random();
        List<String> firstNames = Arrays.asList("John", "Bob", "Mike", "Denis", "David", "Ivan", "Tom", "Andrew", "Kevin", "Thomsa");
        List<String> lastNames = Arrays.asList("Smith", "Green", "Kelly", "Brown", "Starov", "Miller", "Sergeev", "Anderson");
        String firstName, lastName;
        for (int i = 1; i <= technicianQuantity; i++) {
            firstName= firstNames.get(random.nextInt(firstNames.size()));
            lastName= lastNames.get(random.nextInt(lastNames.size()));
            technicians.add(new Technician(i, firstName+" "+lastName, 0.5));
        }

        boolean timeChecker=false;
        RequestDistribution distribution = new RequestDistribution(technicians, buffer, isStepMode);

        for (int i = 0; i < requestsQuantity; i++) {
            if (timeChecker&& isStepMode){
                System.out.println("\n\nTime: "+getTime());
                timeChecker=false;
            }
                if (isStepMode){
                    in.nextLine();
                }
                Request request = generator.generateRequest(isStepMode);
                distribution.distributeRequests(request);

                if (random.nextInt(3)==1) {
                    if (isStepMode){
                        System.out.println("Service execution process");
                    }
                    distribution.updateTime();
                    timeChecker=true;
                }
        }

        in.close();
        if (isStepMode){
            System.out.println("\nAt the end:");
            for (Technician technician : technicians) {
                System.out.println(technician.getName() + " - Current Request: " + technician.getCurrentRequestId());
            }
        }else{
            System.out.println("\nApplications received "+requestsQuantity+" requests and removed "+distribution.showRemovedAmount());
            System.out.println("\nInformation about technicians:");
            System.out.printf("%-10s %-20s %-30s %-30s%n", "Id", "Full Name", "Completed Requests", "Current Request");
            System.out.println("=".repeat(90));
            for (Technician technician : technicians) {
                System.out.printf("%-10d %-20s %-30d %-30s%n",
                        technician.getId(),
                        technician.getName(),
                        technician.getDoneRequestsAmount(),
                        technician.getCurrentRequestId() == -1 ? "None" : technician.getCurrentRequestId());
            }
            System.out.println("-".repeat(90));
            distribution.printBufferDetails();

        }
    }

    public double getTime(){
        return this.time+=0.5;
    }

}