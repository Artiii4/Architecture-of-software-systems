import java.util.List;

class RequestDistribution {
    private final List<Technician> technicians;
    private Buffer buffer;
    private int lastTechnicianId = 0;

    public RequestDistribution(List<Technician> technicians, Buffer buffer) {
        this.technicians = technicians;
        this.buffer = buffer;
    }

    public void distributeRequests(Request requestGet) {
        Technician technician = getFreeTechnician();
        if (buffer.isEmpty()&& technician!=null){
            technician.processRequest(requestGet);
            System.out.println("Assigned Request " + requestGet.getId() + " to Technician " + technician.getName());
        }else {
            buffer.addRequest(requestGet);
            if (technician != null) {
                Request requestToDo = buffer.getNextRequest();
                technician.processRequest(requestToDo);
                buffer.removeRequest(requestToDo.getId());
                System.out.println("Assigned Request " + requestToDo.getId() + " to Technician " + technician.getName());
            }
        }

        for (Technician techn: technicians){
            techn.updateServiceTime();
        }
        buffer.printAllRequests();
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
