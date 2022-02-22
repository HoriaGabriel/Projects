package DataLayer;

import BusinessLayer.DeliveryService;

import java.io.*;

public class DeliverySerializator {

    public static void serialize(DeliveryService dService) {
        try {
            FileOutputStream fileOut = new FileOutputStream("Delivery.txt");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(dService);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static DeliveryService DEserialize() {
        DeliveryService dService;
        try {
            FileInputStream fileIn = new FileInputStream("Delivery.txt");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            dService = (DeliveryService) in.readObject();
            in.close();
            fileIn.close();
            System.out.println(dService);
            return dService;
        } catch (IOException | ClassNotFoundException i) {
            System.out.println(i);
            dService = new DeliveryService();
            serialize(dService);
            return dService;
        }
    }
}

