package Model;

import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;

public class Client {

    private int ID;
    private int arrivalTime;
    private AtomicInteger serviceTime = new AtomicInteger();

    /**
     * Id getter
     * @return returns the id of the client
     */
    public int getID() {
        return ID;
    }

    /**
     * qNumber setter
     * @param    ID sets the id of the client
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * arrival time getter
     * @return returns the arrival time of the client
     */
    public int getArrivalTime() {
        return arrivalTime;
    }

    /**
     * arrival time setter
     * @param    arrivalTime sets the arrival time of the client
     */
    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    /**
     * service time getter
     * @return returns the service time of the client
     */
    public AtomicInteger getServiceTime() {
        return serviceTime;
    }

    /**
     * service time setter
     * @param    serviceTime sets the service time of the client
     */
    public void setServiceTime(AtomicInteger serviceTime) {
        this.serviceTime = serviceTime;
    }

    /**
     * Compares the arrival times of the clients and reorders them in the ascending order
     * @param    o1 first client to be compared
     * @param    o2 second client to be compared
     */
    public static Comparator<Client> ArrivComp = new Comparator<Client>() {

        @Override
        public int compare(Client o1, Client o2) {

            int r1=o1.getArrivalTime();
            int r2=o2.getArrivalTime();

            return r1-r2;
        }
    };
}

