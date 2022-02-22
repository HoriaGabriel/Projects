package Controller;

import Model.Client;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import Exceptions.ImproperInputException;

public class SimulationManager implements Runnable{

    private List<Client> generatedClients = new ArrayList<>();
    public int interval;
    public int clientNr;
    public int peakHour=0;
    public int maxClients=0;
    public float averageServiceTime;
    private Scheduler scheduler;
    private JTextArea lgField;

    /**
     * The function takes the input strings and puts them in the variables to be used in further functions
     * @param    clientString string representing number of clients
     * @param    queueText  string representing number of queues
     * @param    intervalText  string representing the simulation time
     * @param    minArrivalText  string representing the  minimum arrival time
     * @param    maxArrivalText  string representing the  maximum arrival time
     * @param    minServiceText  string representing the  minimum service time
     * @param    maxServiceText  string representing the  maximum service time
     */

    public void initialize(String clientString, String queueText, String intervalText, String minArrivalText,
                           String maxArrivalText, String minServiceText, String maxServiceText, JTextArea logsField) throws ImproperInputException {

        clientNr=Integer.parseInt(clientString);
        int queueNr=Integer.parseInt(queueText);
        interval=Integer.parseInt(intervalText);
        int minArrival=Integer.parseInt(minArrivalText);
        int maxArrival=Integer.parseInt(maxArrivalText);
        int minService=Integer.parseInt(minServiceText);
        int maxService=Integer.parseInt(maxServiceText);
        this.lgField=logsField;

        if(minArrival>maxArrival || minService>maxService || maxArrival>interval){
            throw new ImproperInputException();
        }

        scheduler = new Scheduler(queueNr);
        generateNRandomClients(clientNr,minArrival,maxArrival,minService,maxService);
    }

    /**
     * The function generates the clients of the waiting list and adds the to the list of clients
     * @param    clientNr integer representing number of clients
     * @param    minArrival  integer representing the  minimum arrival time
     * @param    maxArrival  integer representing the  maximum arrival time
     * @param    minService  integer representing the  minimum service time
     * @param    maxService  integer representing the  maximum service time
     */

    private void generateNRandomClients(int clientNr, int minArrival, int maxArrival, int minService, int maxService) {

        averageServiceTime = 0;
        for(int i=1;i<=clientNr;i++){

            Client c = new Client();
            c.setID(i);

            Random r = new Random();
            int r1 = r.nextInt(maxArrival-minArrival)+minArrival;
            AtomicInteger r2 = new AtomicInteger();
            r2.set(r.nextInt(maxService-minService)+minService);

            c.setArrivalTime(r1);
            c.setServiceTime(r2);

            averageServiceTime = averageServiceTime + r2.get();
            generatedClients.add(c);
        }

        Collections.sort(generatedClients,Client.ArrivComp);
    }

    /**
     * The function starts at the time zero and goes at first for as long as there are clients in the list
     * and compares the arrival time with the current time to see whether or not they are the same and if
     * they are than the scheduler dispatchClient method is called and the client is removed from the waiting list
     * If the next client has the same arrival time the time will not be incremented but either way the thread
     * sleeps for 1 second
     */

    @Override
    public void run() {
        int currentTime=0;
        new File("C:\\PT2021_30421_Rusu_Horia_Assignment_2\\QueueLog.txt");
        while(currentTime<interval && generatedClients.isEmpty()==false){
            try {
                FileWriter myWriter = new FileWriter("QueueLog.txt",true);
                lgField.append("\n"); myWriter.write("\n");
                Client caux=generatedClients.get(0);
                if(caux.getArrivalTime()==currentTime){
                    scheduler.dispatchClient(generatedClients.get(0));
                    generatedClients.remove(generatedClients.get(0));
                }
                if(generatedClients.isEmpty()==false){
                    if(generatedClients.get(0).getArrivalTime()!=currentTime){
                        printStart(myWriter, currentTime);
                        peakHourCalculator(currentTime);
                        currentTime++;
                        threadSleep();
                    }
                } else{
                    printContinuation(myWriter,currentTime);
                    peakHourCalculator(currentTime);
                    currentTime++;
                }
                myWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        threadSleep();
        while(currentTime<interval && scheduler.getMaxNrClients()>0){
            printRest(currentTime);
            currentTime++;
        }
        endResults();
    }

    /**
     * The function calls the printContinuation method and sleeps for 1 second
     * @param    currentTime integer representing the current simulation time
     */

    public void printRest(int currentTime){
        FileWriter myWriter = null;
        try {
            myWriter = new FileWriter("QueueLog.txt",true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        printContinuation(myWriter,currentTime);
        threadSleep();
        try {
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The function prints the computation results, aka average waiting time, average service time and peak hour
     */

    public void endResults(){
        float aux=scheduler.getAverageTime();
        aux=aux/clientNr;
        float aux2= averageServiceTime/clientNr;
        try {
            FileWriter myWriter = new FileWriter("QueueLog.txt",true);
            myWriter.write("Average Waiting Time: " + aux + "\n");
            myWriter.write("Average Service Time: " + aux2 + "\n");
            myWriter.write("Peak Hour: " + peakHour + "\n");
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        lgField.append("Average Waiting Time: " + aux + "\n");
        lgField.append("Average Service Time: " + aux2 + "\n");
        lgField.append("Peak Hour: " + peakHour + "\n");
    }

    /**
     * The function calculates the peak hour by comparing the current peak hour with the newly calculated one
     * @param    currentTime integer representing the current simulation time
     */

    public void peakHourCalculator(int currentTime){
        int max= scheduler.getMaxNrClients();
        if(maxClients<max){
            maxClients=max;
            peakHour=currentTime;
        }
    }

    /**
     * The function prints the current time and the list of clients in the queues
     * @param    currentTime integer representing the current simulation time
     * @param    myWriter  integer representing the  file writer
     */

    public void printContinuation(FileWriter myWriter, int currentTime){
        lgField.append("Time: " + currentTime + "\n" + "Waiting clients: " + "\n\n");
        try {
            myWriter.write("Time: " + currentTime + "\n" + "Waiting clients: " + "\n\n");
            scheduler.displayQueues(lgField,myWriter);
            lgField.append("\n");myWriter.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The function prints the current time and the clients in the waiting list
     * @param    currentTime integer representing the current simulation time
     * @param    myWriter  integer representing the  file writer
     */

    public void printStart(FileWriter myWriter, int currentTime){
        lgField.append("Time: " + currentTime + "\n" + "Waiting clients: " + "\n");
        try {
            myWriter.write("Time: " + currentTime + "\n" + "Waiting clients: " + "\n");
            for(Client c: generatedClients){
                lgField.append("(" + c.getID() + "," + c.getArrivalTime() + "," + c.getServiceTime() + ")" + "\n");
                myWriter.write("(" + c.getID() + "," + c.getArrivalTime() + "," + c.getServiceTime() + ")" + "\n");
            }
            scheduler.displayQueues(lgField,myWriter);
            lgField.append("\n");myWriter.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The function puts the thread to sleep for a second
     */

    public void threadSleep(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

