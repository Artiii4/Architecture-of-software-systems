import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in =new Scanner(System.in);
        System.out.println("If you want to enter your data, press E, otherwise any other button");
        String getIt= in.next().toLowerCase(Locale.ROOT);
        int queueBufferCapacity=4;
        int technicianQuantity=2;
        int requestQuantity=15;
        if (getIt.equals("e")){
            System.out.println("Enter the buffer capacity (only the number >0 is accepted)");
            queueBufferCapacity=in.nextInt();
            System.out.println("Enter the number of technicians (only the number >0 is accepted)");
            technicianQuantity=in.nextInt();
            System.out.println("Enter the number of requests (only the number >0 is accepted)");
            requestQuantity=in.nextInt();
            if (queueBufferCapacity<=0 || technicianQuantity<=0 || requestQuantity<=0){
                System.out.println("Invalid input!");
                return;
            }
        }
        System.out.println("In this example we have "+technicianQuantity+" technicians,"+requestQuantity+" requests, buffer capacity = "+queueBufferCapacity);
        MainSystem system = new MainSystem(queueBufferCapacity, technicianQuantity, requestQuantity);
        system.run();
        in.close();
    }
}
