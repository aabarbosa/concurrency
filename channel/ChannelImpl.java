package channel;

import channel.Channel;
import java.util.List;
import java.util.LinkedList;
import java.lang.*;

/*
    A channel is a wired structure that holds messages prior to processing. 
*/
public class ChannelImpl implements Channel {

    volatile boolean police;
    volatile int crimeID;
    volatile String crime;

    public ChannelImpl(int crimeID) {
        this.police = false;
        this.crimeID = crimeID;
    }

    public void putMessage(String crime) throws InterruptedException {

       try {
        while (police == false) { 
            wait();
        }
        } catch (Exception e) {
            //TODO: handle exception
        }
        police = false;
        System.out.println("The crime scene...this is a crime: " + crimeID);
        //System.out.println(crime + ": " + crimeID);
        notify();
    } 
    
    public synchronized String takeMessage() throws InterruptedException {
        String str = "Police returns";
        while (police = true) {
            try { 	wait(); }
            catch (InterruptedException e) {throw e;};
        }

        police = true;
        notify();
        System.out.println("reading the channel..." + str);
        return str;
    }

}

    static class DrugDealer implements Runnable {
        private Channel channel;
        String crime;
    
        public DrugDealer (String crime, Channel channel) {
            this.crime = crime;
            this.channel = channel; 
        }
    
        public void run() {
            while (true) {
                int crimeNumber = 0;
                int lowStamina = (int) Math.random() * 100;
                try {
                    Thread.sleep(lowStamina);
                } catch (InterruptedException e) {return;}
                
                try {
                    channel.putMessage(this.crime + " " + crimeNumber);
                } catch (InterruptedException e) {return;}
                
            }
        }

        public void currentCrime(String crime) {
            this.crime = crime;
        }

    }

    static class PoliceWorker implements Runnable {
        private Channel channel;
    
        public PoliceWorker (Channel channel) {
            this.channel = channel; 
        }
         public void run() {
            while (true) {
                int lowStamina = (int) Math.random() * 100;
                try {
                    System.out.println(
                    "Busy..busy.. I didn't found a suspect of any crime.");
                    Thread.sleep(lowStamina);
                } catch (InterruptedException e) {return;}
                try {
                    channel.takeMessage();
                } catch (InterruptedException e) {return;}
                
            }
        }

    /* Work in progress */
    public static void main(String[] args) {
        int crimes = 10;
        Channel channel = new ChannelImpl(crimes);
        Thread police = new Thread(new PoliceWorker(channel));
        Thread dealer = new Thread(new DrugDealer("bank robbery star", channel));
        police.start();
        dealer.start();

        /*
        List<String> bookOfCrimes = new LinkedList<>(); 
        bookOfCrimes.add("sexy ball"); 
        bookOfCrimes.add("masfarapo ball");
    
        String currentCrime;
        double coin = Math.random();
        if (coin > 0.5) {
            currentCrime = bookOfCrimes.get(0);
        } else {
            currentCrime = bookOfCrimes.get(1);
        }

        channel.takeMessage();
        channel.putMessage(currentCrime);
        */
    }

}