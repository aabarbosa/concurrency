//package channel;

import channel.*;

public class ChannelImpl implements Channel {
    
    private int crimeid;
    private boolean hasCrime = true;


    public synchronized void putMessage(int crimeid) throws InterruptedException {

        while (hasCrime == true) {
            try {wait();}
        catch (InterruptedException e) {throw e;}
        }
        this.crimeid = crimeid;
        hasCrime = true;
        System.out.println("Putting a massage, this city has " + crimeid);
        notify();
    }

    public synchronized String takeMessage() throws InterruptedException {
        while (hasCrime = false) {
            try {wait();} 
            catch (InterruptedException e) {
                System.out.println("police under arrest"); 
            }
        }

        hasCrime = false;
        notify();
        System.out.println("A drug dealer has been captured...crime ID" + crimeid);
        crimeid--;
        return genericMessage;
    }
}