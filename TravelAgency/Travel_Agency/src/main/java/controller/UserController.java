package controller;

import entity.User;
import service.UserService;

import javax.swing.*;

public class UserController {

    UserService us = new UserService();

    public void insertClient(String text, String text1, String text2, String text3) {

        User u = us.findServiceWithPassword(text3);

        if(u==null){
            us.insertService(text,text1,text2,text3);
        }
        else{
            String message = "Change Password; It already exists";
            JOptionPane.showMessageDialog(new JFrame(), message, "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public User findClient(String text, String text1) {

        User u = us.findService(text,text1);

        if(u!=null){
            return u;
        }
        else{
            return null;
        }
    }

    public User findClientWithName(String text) {

        User u = us.findServiceWithName(text);

        if(u!=null){
            return u;
        }
        else{
            return null;
        }
    }

    public void updateClientAfterReservation(User u) {

        us.updateServiceAfterReservation(u);
    }
}
