package BusinessLayer;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class ReportMaker {

    /**
     * Function makes the first report taking into account oreders made within the given
     * time interval
     * @param sHour the starting hour
     * @param fHour the final hour
     * @param d the delivery service where various operations are performed with the given data
     */
    public void makeReport1(int sHour, int fHour, DeliveryService d) throws IOException {

        List<Order> filtered1;
        filtered1 = d.getOrderList().stream()
                .filter(order -> order.getHour() < fHour)
                .filter(order -> order.getHour() > sHour)
                .collect(Collectors.toList());

        Iterator<Order> iterator = filtered1.iterator();
        FileWriter myWriter = new FileWriter("Report1.txt");
        myWriter.append("Orders made in the given interval are:\n");

        while(iterator.hasNext()){

            Order aux = iterator.next();
            Integer b = aux.getOrderId();
            myWriter.append(b.toString());myWriter.append("\n");
        }
        myWriter.close();
    }

    /**
     * Function makes the second report taking into account the number of times a product was ordered
     * @param times the number of times
     * @param d the delivery service where various operations are performed with the given data
     */
    public void makeReport2(int times, DeliveryService d) throws IOException {

        ArrayList<MenuItem> filter1;

        filter1= (ArrayList<MenuItem>) d.getOrderMenuMap().values().stream()
                .flatMap(ArrayList<MenuItem>::stream)
                .collect(Collectors.toList());

        Map<String, Integer> hm = new HashMap<String, Integer>();

        for(MenuItem i: filter1){

            Integer j = hm.get(i.getName());
            hm.put(i.getName(), (j == null) ? 1 : j + 1);
        }

        List<Map.Entry<String, Integer>> hm2;
        hm2=  hm.entrySet().stream().filter(e->e.getValue()>times).collect(Collectors.toList());
        Integer ab=times;

        Iterator<Map.Entry<String, Integer>> it = hm2.iterator();
        FileWriter myWriter = new FileWriter("Report2.txt");
        myWriter.append("Products ordered more than "); myWriter.append(ab.toString()); myWriter.append(" times:\n");
        while(it.hasNext()) {
            Map.Entry<String, Integer> e = it.next();
            String key   = e.getKey();
            Integer value = e.getValue();
            myWriter.append(key);
            myWriter.append(value.toString());myWriter.append("\n");
        }
        myWriter.close();
    }

    /**
     * Function makes the third report taking into account oreders made the number of times
     * given and with the value of the order
     * @param times2 the number of times
     * @param value the order value
     * @param d the delivery service where various operations are performed with the given data
     */
    public void makeReport3(int times2, int value, DeliveryService d) throws IOException {

        List<Order> filtered1;
        filtered1 = d.getOrderList().stream()
                .filter(order -> order.getPrice() > value)
                .collect(Collectors.toList());

        Map<Integer, Integer> hm = new HashMap<Integer, Integer>();

        for(Order i: filtered1){

            Integer j = hm.get(i.getClientId());
            hm.put(i.getClientId(), (j == null) ? 1 : j + 1);
        }

        List<Map.Entry<Integer, Integer>> hm2;
        hm2=  hm.entrySet().stream().filter(e->e.getValue()>times2).collect(Collectors.toList());
        FileWriter myWriter = new FileWriter("Report3.txt");
        myWriter.append("Clients that have ordered more than the specified number of times and order value greater" +
                " than specified are:\n");
        Iterator<Map.Entry<Integer, Integer>> it = hm2.iterator();
        while(it.hasNext()) {
            Map.Entry<Integer, Integer> e = it.next();
            Integer key   = e.getKey();
            myWriter.append(key.toString()); myWriter.append("\n");
        }
        myWriter.close();
    }

    /**
     * Function makes the fourth report taking into account the date of the order
     * @param date the date when the order was made
     * @param d the delivery service where various operations are performed with the given data
     */
    public void makeReport4(String date, DeliveryService d) throws IOException {

        Map<Order,ArrayList<MenuItem>> filter1;

        filter1= d.getOrderMenuMap().entrySet().stream()
                .filter(e->e.getKey().getOrderDate().toString().equals(date))
                .collect(Collectors.toMap(map -> map.getKey(), map -> map.getValue()));

        ArrayList<MenuItem> filter2;

        filter2= (ArrayList<MenuItem>) filter1.values().stream()
                .flatMap(ArrayList<MenuItem>::stream)
                .collect(Collectors.toList());

        Map<String, Integer> hm = new HashMap<String, Integer>();

        for(MenuItem i: filter2){

            Integer j = hm.get(i.getName());
            hm.put(i.getName(), (j == null) ? 1 : j + 1);
        }

        Iterator<Map.Entry<String, Integer>> it = hm.entrySet().iterator();
        FileWriter myWriter = new FileWriter("Report4.txt");
        myWriter.append("Products ordered on the specified day are:\n");
        while(it.hasNext()) {
            Map.Entry<String, Integer> e = it.next();
            String key   = e.getKey();
            myWriter.append(key);myWriter.append("\n");
        }
        myWriter.close();
    }
}

