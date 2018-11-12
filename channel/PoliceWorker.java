package channel;

class PoliceWorker implements Runnable {
    private String crime;
    private Channel channel;

    public PoliceWorker (String crime, ChannelImpl channel) {
        this.crime = crime;
        this.channel = channel; 
    }
     public void run() {
        while (true) {
            int lowStamina = (int) Math.random() * 100;
            try {
                Thread.sleep(lowStamina);
            } catch (InterruptedException e) {return;}
            
            try {
                channel.takeMessage(i + 1);
            } catch (InterruptedException e) {return;}
            
        }
    }
}