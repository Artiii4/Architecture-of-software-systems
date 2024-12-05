class Technician {
    private final int id;
    private final String fullName;
    private boolean isAvailable;
    private int currentRequestId;
    private double remainingServiceTime;
    private ExponentialDistribution exponentialDistribution;

    public Technician(int id, String fullName, double lambda) {
        this.id = id;
        this.fullName = fullName;
        this.isAvailable = true;
        this.currentRequestId = -1;
        this.remainingServiceTime = 0.0;
        this.exponentialDistribution= new ExponentialDistribution(lambda);
    }

    public String getName() {
        return fullName;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public int getCurrentRequestId() {
        return currentRequestId;
    }

    public void processRequest(Request request) {
        if(this.isAvailable){
            this.isAvailable = false;
            this.currentRequestId = request.getId();
            this.remainingServiceTime= exponentialDistribution.generateServiceTime();
            System.out.println("Technician " + fullName + " is processing request " + request.getId()+ "  service time "+remainingServiceTime);
        }
    }

    public void updateServiceTime(){
        if (!isAvailable){
            remainingServiceTime-=0.5;
            if (remainingServiceTime<=0){
                completeRequest();
                System.out.println("Technician "+ fullName+ " is free for now");
            }
        }
    }

    private void completeRequest() {
        this.isAvailable = true;
        this.currentRequestId = -1;
    }
}