package channel;

public interface Channel {

    public  void putMessage(int message) throws InterruptedException;

    public  String takeMessage() throws InterruptedException;

}