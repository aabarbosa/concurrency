package channel;

import channel.Channel;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;

/*
    A channel is a wired structure that holds messages prior to processing. 
*/
public class ChannelImpl implements Channel {

    BlockingQueue<String> bucketOfMessages;

    public ChannelImpl(int numberOfMessages) {
        this.bucketOfMessages = getInstance();
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

    public BlockingQueue getInstance(){
        return bucketOfMessages;
    }
    public String toString(){
        return bucketOfMessages.toString();
    }


    /* Work in progress */
    public static void main(String[] args) throws InterruptedException {

        ChannelImpl c = new ChannelImpl(10);
                
        ExecutorService threads = new //TODO: Solve null pointer exception
        ThreadPoolExecutor(1, 4, 1, TimeUnit.SECONDS, c.getInstance());

        int numberOfMessages = 10; //x2
        for (int i = 0; i < numberOfMessages; i++) {
            c.putMessage("These guy are pretty smart " + i);
            c.putMessage("They talk about complexity smartly " + i);
        }

        while (!c.getInstance().isEmpty()){
            threads.execute(()-> c.takeMessage());
        }
        threads.shutdown();
		threads.awaitTermination(4000, TimeUnit.SECONDS);

        System.out.println("out: " + c.toString());
    }

}