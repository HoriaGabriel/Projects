package Controller;

import Model.Client;
import Model.Queue;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Scheduler {

    private List<Queue> qList = new ArrayList<>();
    private float averageTime;

    /**
     * The function creates the queues and starts the running of their threads
     * @param    queueNr integer representing the number of queues
     */

    Scheduler(int queueNr){

        this.setAverageTime(0);
        for(int i=0;i<queueNr;i++){
            Queue q1 = new Queue();
            AtomicInteger g =new AtomicInteger();
            q1.setqNumber(i);
            q1.setWaitingPeriod(g);
            qList.add(q1);
            q1.startQ();
        }
    }

    /**
     * average time getter
     * @return returns the average time of the queue scheduler
     */

    public float getAverageTime() {
        return averageTime;
    }

    /**
     * average time setter
     * @param    averageTime sets the average time of the scheduler
     */

    public void setAverageTime(float averageTime) {
        this.averageTime = averageTime;
    }

    /**
     * The function returns the maximum number of clients in all the queues in the scheduler
     * @return returns the number of clients
     */

    public int getMaxNrClients(){
        int nrClients=0;
        for(Queue q: qList){
            nrClients=nrClients+q.numberOfClients();
        }
        return nrClients;
    }

    /**
     * The function takes the waiting periods of each of the queues and chooses the smallest one
     * and dispatches the client to that queue
     * @param  c the client to be dispatched to the queue
     */

    public void dispatchClient(Client c){

        int min=qList.get(0).getWaitingPeriod().get();
        int minIndex=0;
        for(Queue q: qList){

            if(q.getWaitingPeriod().get()<min){
                min=q.getWaitingPeriod().get();
                minIndex=q.getqNumber();
            }
        }

        this.setAverageTime(averageTime+min+c.getServiceTime().get());
        qList.get(minIndex).addClient(c);
    }

    /**
     * Displays the queues and the clients in each of the queues
     * @param   myWriter the file writer
     * @param   lgField the field for the queue log in the user interface
     */

    public void displayQueues(JTextArea lgField,FileWriter myWriter){
        for(Queue q: qList){
            int aux=q.getqNumber()+1;
            try {
                lgField.append("Queue " + aux + ": ");
                myWriter.write("Queue " + aux + ": ");
                for(Client c2: q.getClientList()){
                    if(c2==q.getClientList().get(0)) {
                        lgField.append("(" + c2.getID() + "," + c2.getArrivalTime() + "," + c2.getServiceTime().get() + ");");
                        myWriter.write("(" + c2.getID() + "," + c2.getArrivalTime() + "," + c2.getServiceTime() + ");");
                        AtomicInteger aux2 = c2.getServiceTime();
                        aux2.getAndDecrement();
                        c2.setServiceTime(aux2);
                        q.getWaitingPeriod().getAndDecrement();
                    } else {
                        lgField.append("(" + c2.getID() + "," + c2.getArrivalTime() + "," + c2.getServiceTime() + "); ");
                        myWriter.write("(" + c2.getID() + "," + c2.getArrivalTime() + "," + c2.getServiceTime() + "); ");
                    }
                }
                lgField.append("\n");myWriter.write("\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            lgField.append("---------------------------------------" + "\n");
            myWriter.write("---------------------------------------" + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

