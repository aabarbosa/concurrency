import java.util.*;

/*
    A channel is a wired structure that holds messages prior to processing. 
*/
class City {
    /* Work in progress */
    public static void main(String[] args) {
        int criminality = 10;
        ChannelImpl channel = new ChannelImpl();
        Thread dealer = new Thread(new DrugDealer(criminality, channel));
        Thread police = new Thread(new PoliceWorker(criminality, channel));
        police.start();
        dealer.start();
    }

    //For the sake of simplitity in the compiling process I choose to create static classes
    static class ChannelImpl {
    
        private int criminality;
        private boolean hasCrime = true;


        public synchronized void putMessage(int criminality) throws InterruptedException {

            while (hasCrime == true) {
                try {wait();}
            catch (InterruptedException e) {throw e;}
            }
            this.criminality = criminality;
            hasCrime = true;
            System.out.println("Putting a massage, this city has " + this.criminality);
            notify();
        }

        public synchronized String takeMessage() throws InterruptedException {
            while (hasCrime = false) {
                try {wait();} 
                catch (InterruptedException e) {throw e;} 
                
            }

            hasCrime = false;
            notify();
            System.out.println("A drug dealer has been captured...crime ID " + criminality);
            return "Captured";
        }

    }
        
    static class DrugDealer implements Runnable {
        private ChannelImpl channel;
        private int criminality;

        public DrugDealer(int criminality, ChannelImpl channel) {
            this.criminality = criminality;
            this.channel = channel;
        }

        public void run() {

            int crime = 1;
            while (crime <= criminality) { //Drug dealers has a clear limit of crimes they can commit
                try {
                    Thread.sleep( (int) Math.random() * 100);
                } catch (InterruptedException e) {return;}
                
                try {
                    channel.putMessage(crime);
                    crime++;
                } catch (InterruptedException e) {return;}
                
            }
        }

    }

    static class PoliceWorker implements Runnable {
        private ChannelImpl channel;
        private int criminality;

        public PoliceWorker(int criminality, ChannelImpl channel) {
            this.channel = channel;
            this.criminality = criminality;
        }
        
        //There's no guarantee that all crime will be solved
        public void run() {
            int dealersCaptured = 0;
            while (dealersCaptured < criminality*100) { // 100x more effort 
                try {
                    channel.takeMessage();
                    dealersCaptured++;
                } catch (InterruptedException e) {
                    return;
                }
                try {
                    Thread.sleep( (int) Math.random() * 100); 
                } catch (InterruptedException e) {return;}
            }
        }
    }
}