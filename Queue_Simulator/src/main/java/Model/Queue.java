package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Queue implements Runnable{

    private List<Client> clientList= new ArrayList<>();
    private AtomicInteger waitingPeriod;
    private int qNumber;
    private Thread t;
    private int run;

    /**
     * The function sets up the thread of the queue created in the scheduler
     */

    public Queue(){

        this.t = new Thread(this);
    }

    /**
     * client list getter
     * @return returns the client list of the queue
     */
    public List<Client> getClientList() {
        return clientList;
    }

    /**
     * average time setter
     * @param    clientList sets the clientList of the queue
     */
    public void setClientList(List<Client> clientList) {
        this.clientList = clientList;
    }

    /**
     * waiting period getter
     * @return returns the waiting period of the queue
     */
    public AtomicInteger getWaitingPeriod() {
        return waitingPeriod;
    }

    /**
     * average time setter
     * @param    waitingPeriod sets the waitingPeriod of the queue
     */
    public void setWaitingPeriod(AtomicInteger waitingPeriod) {
        this.waitingPeriod = waitingPeriod;
    }

    /**
     * qNumber getter
     * @return returns the qNumber of the queue
     */
    public int getqNumber() {
        return qNumber;
    }

    /**
     * qNumber setter
     * @param    qNumber sets the qNumber of the queue
     */
    public void setqNumber(int qNumber) {
        this.qNumber = qNumber;
    }

    /**
     * Function returns the number of clients in the queue, used to calculate the peak hour
     * @return returns the number of clients in the queue
     */
    public int numberOfClients(){

        return this.clientList.size();
    }

    /**
     * Function sets the client in the current queue and increments the waiting period according to the
     * clients service time
     * @param    c client to be sat in the queue
     */
    public void addClient(Client c){

        this.clientList.add(c);
        int aux=0;
        while(aux<c.getServiceTime().get()) {
            this.waitingPeriod.getAndIncrement();
            aux++;
        }
    }

    /**
     * Function starts the thread of the queue
     */
    public void startQ(){
        this.run=1;
        this.t.start();
    }

    /**
     * Function waits for clients to appear in the queue and when they do the program sleeps for the
     * number of seconds of the clients service time
     */
    @Override
    public void run() {
        while(this.clientList.size()>0 || this.run==1){
            if(clientList.isEmpty()==false){
                Client c = this.clientList.get(0);
                int time=c.getServiceTime().get();

                try {
                    Thread.sleep(1000 * time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                clientList.remove(0);
            }
            else{
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

