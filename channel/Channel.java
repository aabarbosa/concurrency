package channel;

public interface Channel {

    public  void putMessage(String message) throws InterruptedException;

    public  String takeMessage() throws InterruptedException;

}