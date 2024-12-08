import java.util.ArrayList;

class Buffer {
    private final int maxCapacity;
    private final ArrayList<Request> requestsBuffer;
    private int lastUsedBufferCellId = 0;
    private int removedAmount=0;

    public Buffer(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        this.requestsBuffer = new ArrayList<>(maxCapacity);
        for (int i = 0; i < maxCapacity; i++) {
            requestsBuffer.add(null);
        }
    }

    public int getMaxCapacity(){
        return maxCapacity;
    }

    public int getRemovedAmount(){
        return removedAmount;
    }

    public void printAllRequests() {
        if (isEmpty()) {
            System.out.println("The request buffer is empty.");
            return;
        }

        System.out.println("Current Requests in Buffer:");
        int i;
        for (i=0;i<lastUsedBufferCellId;i++){
            if (requestsBuffer.get(i)==null){
                System.out.print("      ");
            }else{
                System.out.print("   ");
            }
        }
        if (requestsBuffer.get(i)==null){
            System.out.print(" vv\n");
        }else{
            System.out.print("v\n");
        }
        for (Request request : requestsBuffer) {
            if (request == null) {
                System.out.print("null, ");
            } else {
                System.out.print(request.getId() + ", ");
            }
        }
        System.out.println();
    }

    public boolean addRequest(Request request) {
        if (requestsBuffer.contains(null)) {
            for (int i = 0; i < requestsBuffer.size(); i++) {
                if (requestsBuffer.get(i) == null) {
                    requestsBuffer.set(i, request);
                    return true;
                }
            }
        }

        int maxIdFound = 0;
        for (int i = 1; i < requestsBuffer.size(); i++) {
            if (requestsBuffer.get(i) != null &&
                    requestsBuffer.get(i).getId() > requestsBuffer.get(maxIdFound).getId()) {
                maxIdFound = i;
            }
        }
        removedAmount++;
        requestsBuffer.set(maxIdFound, request);
        return false;
    }

    public void removeRequest(int requestId) {
        for (int i=0;i<requestsBuffer.size();i++){
            if (requestsBuffer.get(i)!= null && requestsBuffer.get(i).getId()==requestId){
                requestsBuffer.set(i, null);
            }
        }
    }

    public Request get(int i){
        return requestsBuffer.get(i);
    }

    public Request getNextRequest() {
        for (int i = lastUsedBufferCellId; i < requestsBuffer.size(); i++) {
            if (requestsBuffer.get(i) != null) {
                lastUsedBufferCellId = (i + 1) % maxCapacity;
                return requestsBuffer.get(i);
            }
        }
        for (int i = 0; i < lastUsedBufferCellId; i++) {
            if (requestsBuffer.get(i) != null) {
                lastUsedBufferCellId = (i + 1) % maxCapacity;
                return requestsBuffer.get(i);
            }
        }
        return null;
    }

    public boolean isEmpty() {
        for (Request request : requestsBuffer) {
            if (request != null) {
                return false;
            }
        }
        return true;
    }
}
