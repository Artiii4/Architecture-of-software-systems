class Request {
    private int id=0;
    private final String description;

    public Request(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public  String getDescription(){
        return this.description;
    }

    public int getId() {
        return id;
    }
}
