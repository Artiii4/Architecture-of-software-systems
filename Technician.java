class Technician {
    private final int id;
    private final String fullName;
    private boolean isAvailable;
    private int currentRequestId;
    private double remainingServiceTime;
    private ExponentialDistribution exponentialDistribution;
    private int doneRequestsAmount=0;

    public Technician(int id, String fullName, double lambda) {
        this.id = id;
        this.fullName = fullName;
        this.isAvailable = true;
        this.currentRequestId = -1;
        this.remainingServiceTime = 0.0;
        this.exponentialDistribution= new ExponentialDistribution(lambda);
    }

    public int getId(){
        return id;
    }

    public String getName() {
        return fullName;
    }

    public int getDoneRequestsAmount(){
        return doneRequestsAmount;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public int getCurrentRequestId() {
        return currentRequestId;
    }

    public void processRequest(Request request, boolean informationOutput) {
        if(this.isAvailable){
            this.isAvailable = false;
            this.currentRequestId = request.getId();
            this.remainingServiceTime= exponentialDistribution.generateServiceTime();
            if (informationOutput){
                System.out.println("Technician " + fullName + " is processing request " + request.getId()+ "  service time "+remainingServiceTime);
            }
        }
    }

    public void updateServiceTime(boolean informationOutput){
        if (!isAvailable){
            remainingServiceTime-=1;
            if (remainingServiceTime<=0){
                completeRequest();
                if (informationOutput){
                    System.out.println("Technician "+ fullName+ " is free for now");
                }
            }
        }
    }

    private void completeRequest() {
        doneRequestsAmount++;
        isAvailable = true;
        currentRequestId = -1;
    }
}