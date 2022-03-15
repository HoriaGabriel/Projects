package service;

import entity.User;
import repository.UserRepository;

import javax.swing.*;

public class UserService {

    UserRepository ur = new UserRepository();

    public void insertService(String text, String text1, String text2, String text3) {


        if(!text.isEmpty() && text!=null && !text1.isEmpty() && text1!=null && !text2.isEmpty() && text2!=null
           && !text3.isEmpty() && text3!=null){

            if(text3.length()>=6) {
                try {
                    User u = new User(text, text1, text2, text3);
                    ur.insertQuery(u);
                } catch (Exception e) {
                    String message = "Error";
                    JOptionPane.showMessageDialog(new JFrame(), message, "Error",
                            JOptionPane.ERROR_MESSAGE);
                } finally {
                    final JPanel panel = new JPanel();
                    JOptionPane.showMessageDialog(panel, "User Added", "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
            else{
                String message = "Password must have at least six characters";
                JOptionPane.showMessageDialog(new JFrame(), message, "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        else{
            String message = "Some fields are incompleted";
            JOptionPane.showMessageDialog(new JFrame(), message, "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public User findService(String text, String text1) {

        if(!text.isEmpty() && text!=null && !text1.isEmpty() && text1!=null){

            try{
                User u = ur.searchQuery(text,text1);
                return u;
            } catch(Exception e){
                String message = "User not found";
                JOptionPane.showMessageDialog(new JFrame(), message, "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

        }
        else{
            String message = "Incompleted field";
            JOptionPane.showMessageDialog(new JFrame(), message, "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    public User findServiceWithName(String text) {

        if(!text.isEmpty() && text!=null){

            try{
                User u = ur.searchQueryWithName(text);
                return u;
            } catch(Exception e){
                String message = "User not found";
                JOptionPane.showMessageDialog(new JFrame(), message, "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

        }
        else{
            String message = "Incompleted field";
            JOptionPane.showMessageDialog(new JFrame(), message, "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    public User findServiceWithPassword(String text) {

        if(!text.isEmpty() && text!=null){

            try{
                User u = ur.searchQueryWithPassword(text);
                return u;
            } catch(Exception e){
                String message = "User not found";
                JOptionPane.showMessageDialog(new JFrame(), message, "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

        }
        else{
            String message = "Incompleted field";
            JOptionPane.showMessageDialog(new JFrame(), message, "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    public void updateServiceAfterReservation(User u) {

        ur.updateQueryAfterReservation(u);
    }
}
