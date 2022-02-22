package DataLayer;

import BusinessLayer.ClientBase;

import java.io.*;

public class ClientSerializator {

    public static void serialize(ClientBase cBase) {

        try {
            FileOutputStream fileOut = new FileOutputStream("ClientBase.txt");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(cBase);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static ClientBase DEserialize() {
        ClientBase cBase;
        try {
            FileInputStream fileIn = new FileInputStream("ClientBase.txt");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            cBase = (ClientBase) in.readObject();
            in.close();
            fileIn.close();
            System.out.println(cBase);
            return cBase;
        } catch (IOException | ClassNotFoundException i) {
            cBase = new ClientBase();
            serialize(cBase);
            return cBase;
        }
    }
}

