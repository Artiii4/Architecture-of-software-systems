import java.util.List;

class RequestDistribution {
    private final List<Technician> technicians;
    private Buffer buffer;
    private int lastTechnicianId = 0;
    private boolean informationOutput;

    public RequestDistribution(List<Technician> technicians, Buffer buffer, boolean informationOutput) {
        this.technicians = technicians;
        this.buffer = buffer;
        this.informationOutput= informationOutput;
    }

    public void distributeRequests(Request requestGet) {
        Technician technician = getFreeTechnician();
        if (buffer.isEmpty()&& technician!=null){
            technician.processRequest(requestGet, informationOutput);
            if (informationOutput){
                System.out.println("Assigned Request " + requestGet.getId()+" with discription"+requestGet.getDescription() + " to Technician " + technician.getName());
            }
        }else {
            buffer.addRequest(requestGet);
            if (technician != null) {
                Request requestToDo = buffer.getNextRequest();
                technician.processRequest(requestToDo, informationOutput);
                buffer.removeRequest(requestToDo.getId());
                if (informationOutput){
                    System.out.println("Assigned Request " + requestToDo.getId() + " with discription"+requestGet.getDescription() +" to Technician " + technician.getName());
                }
            }
        }
        if (informationOutput){
            buffer.printAllRequests();
        }
    }

    public int showRemovedAmount(){
        return buffer.getRemovedAmount();
    }

    public void printBufferDetails() {
        if (buffer.isEmpty()) {
            System.out.println("The request buffer is empty.");
            return;
        }
        System.out.println("\nInformation about buffer:");
        System.out.printf("%-10s %-30s%n", "Request ID", "Description");
        System.out.println("=".repeat(40));
        for (int i = 0; i < buffer.getMaxCapacity(); i++) {
            Request request = buffer.get(i);
            if (request == null) {
                System.out.printf("%-10s %-30s%n", "null", "No request");
            } else {
                System.out.printf("%-10d %-30s%n", request.getId(), request.getDescription());
            }
        }
        System.out.println("-".repeat(40));
    }

    public void updateTime(){
        for (Technician technician: technicians){
            technician.updateServiceTime(informationOutput);
        }
    }

    public Technician getFreeTechnician() {
        Technician technician= null;
        for (int i=lastTechnicianId; i<technicians.size();i++){
            technician=technicians.get(i);
            if (technician.isAvailable()){
                lastTechnicianId=i++;
                return technician;
            }
        }
        for (int i=0; i<lastTechnicianId;i++){
            technician=technicians.get(i);
            if (technician.isAvailable()){
                return technician;
            }
        }
        return null;
    }
}
