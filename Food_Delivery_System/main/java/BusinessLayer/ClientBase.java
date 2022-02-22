package BusinessLayer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ClientBase implements Serializable{

    List<Client> clientList = new ArrayList<>();

    /**
     * client list getter
     * @return returns the list of clients
     */
    public List<Client> getClientList() {
        return clientList;
    }

    /**
     * client list setter
     * @param clientList sets the list of clients
     */
    public void setClientList(List<Client> clientList) {
        this.clientList = clientList;
    }

    /**
     * Function adds client to this client list
     * @param client the client to be added
     */
    public void addClient(Client client){

        assert client!=null;
        int aux= clientList.size();

        clientList.add(client);

        assert aux+1== clientList.size();

    }

    /**
     * Function searches for a client based on name and password and returns true
     * if he is found and false otherwise
     * @param name the name of the client to be searched for
     * @param password the password of the client to be searched for
     * @return returns true or false depending on whether it is found or not
     */
    public Boolean searchClient(String name, String password) {

        Iterator<Client> iterator = clientList.iterator();

        while(iterator.hasNext()){

            Client aux = iterator.next();
            if(aux.getName().equals(name) && aux.getPassword().equals(password)){
                return true;
            }
        }

        return false;
    }

    /**
     * Function searches for a client based on name and returns the client
     * if he is found and null otherwise
     * @param name the name of the client to be searched for
     * @return returns the client when found
     */
    public Client searchClient2(String name) {

        Iterator<Client> iterator = clientList.iterator();

        while(iterator.hasNext()){

            Client aux = iterator.next();
            if(aux.getName().equals(name)){
                return aux;
            }
        }

        return null;
    }
}

