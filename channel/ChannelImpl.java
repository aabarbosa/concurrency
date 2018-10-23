package channel;


import java.util.concurrent.ThreadPoolExecutor;
import java.util.Queue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/*
    A channel is a wired structure that holds messages prior to processing. 
*/
public class ChannelImpl {

    Queue<String> bucketOfMessages;

    public ChannelImpl(int numberOfMessages) {
        this.bucketOfMessages = new SynchronousQueue<String>();
    }

    public synchronized void putMessage(String str) {
        System.out.println("writing to the channel... " + str);
        bucketOfMessages.offer(str);
    }

    /*

    */
    public synchronized String takeMessage() {
        System.out.println("reading the channel..." 
        + bucketOfMessages.peek());
        return bucketOfMessages.poll();
    } 

    public static void main(String[] args) {
        /*
        Executor threads = new 
        ThreadPoolExecutor(1, 4, 1, TimeUnit.SECONDS, workQueue);

        threads.execute(command);
        */

        ChannelImpl c = new ChannelImpl(10);

        /* Linear */
        c.putMessage("These guy are pretty smart");
        c.putMessage("They talk about complexity smartly");
        c.putMessage("They talk about complexity smartly");
        
        c.takeMessage();
        c.takeMessage();
    }

}