package service;

import entity.Vacation_Destination;
import repository.DestinationRepository;

import javax.swing.*;
import java.util.List;

public class DestinationService {

    DestinationRepository dr = new DestinationRepository();

    public void insertService(String destinationName) {

        if(!destinationName.isEmpty() && destinationName!=null){

            try{
                Vacation_Destination vDest = new Vacation_Destination(destinationName);
                dr.insertQuery(vDest);
            } catch(Exception e){
                String message = "Problem";
                JOptionPane.showMessageDialog(new JFrame(), message, "Error",
                        JOptionPane.ERROR_MESSAGE);
            } finally{
                final JPanel panel = new JPanel();
                JOptionPane.showMessageDialog(panel, "Destination Added", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }else{
            String message = "No Destination Added";
            JOptionPane.showMessageDialog(new JFrame(), message, "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public List<Vacation_Destination> getService() {

        try{
            List<Vacation_Destination> destinations;
            destinations = dr.getDestinationQuery();
            return destinations;
        } catch(Exception e){
            System.out.println("Bad");
        }

        return null;
    }

    public Vacation_Destination findService(String s) {

        if(!s.isEmpty() && s!=null){

            try{
                Vacation_Destination vd = dr.searchQuery(s);
                return vd;
            } catch(Exception e){
                String message = "Destination not found";
                JOptionPane.showMessageDialog(new JFrame(), message, "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

        }

        return null;
    }

    public void deleteService(String s) {

        if(!s.isEmpty() && s!=null){
            dr.deleteQuery(s);
        }
        else{
            String message = "Incorrect input -> field is empty";
            JOptionPane.showMessageDialog(new JFrame(), message, "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
