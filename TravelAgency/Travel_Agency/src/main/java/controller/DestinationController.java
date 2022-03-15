package controller;

import entity.Vacation_Destination;
import service.DestinationService;

import javax.swing.*;
import java.util.List;

public class DestinationController {

    DestinationService ds = new DestinationService();

    public void insertDestination(String s) {

         Vacation_Destination vd = ds.findService(s);
         if(vd==null){
             ds.insertService(s);
         }else{
             String message = "Destination Already Exists";
             JOptionPane.showMessageDialog(new JFrame(), message, "Error",
                     JOptionPane.ERROR_MESSAGE);
         }
    }

    public List<Vacation_Destination> getDestinations() {

        List<Vacation_Destination> destinations;
        destinations = ds.getService();

        if(!destinations.isEmpty()){
            return destinations;
        }
        else{
            return null;
        }
    }

    public void deleteDestination(String s) {

        Vacation_Destination vd = ds.findService(s);
        ds.deleteService(s);
    }
}
