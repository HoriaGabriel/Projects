package service;

import entity.Status;
import entity.Vacation_Destination;
import entity.Vacation_Package;
import repository.PackageRepository;

import javax.swing.*;
import java.util.List;

public class PackageService {


    PackageRepository pr = new PackageRepository();

    public void insertService(String s1, Integer i1, Integer i2, Integer i3, String s2, Vacation_Destination vd) {

        if(!s1.isEmpty() && s1!=null && !s2.isEmpty() && s2!=null){

            if(i1>0 && i2>0 && i3>0){

                try{
                    Vacation_Package vPack = new Vacation_Package(s1,i1,i2,i3,s2,vd);
                    vPack.setStatus(Status.NOT_BOOKED);
                    pr.insertQuery(vPack);
                }catch(Exception e){
                    String message = "Incorrect input";
                    JOptionPane.showMessageDialog(new JFrame(), message, "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }else{
                String message = "Incorrect input -> negative numbers";
                JOptionPane.showMessageDialog(new JFrame(), message, "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }else{
            String message = "Incorrect input -> some fields are empty";
            JOptionPane.showMessageDialog(new JFrame(), message, "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void deleteService(String s) {

        if(!s.isEmpty() && s!=null){
            pr.deleteQuery(s);
        }
        else{
            String message = "Incorrect input -> field is empty";
            JOptionPane.showMessageDialog(new JFrame(), message, "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public Vacation_Package findService(String s) {

        if(!s.isEmpty() && s!=null){

            try{
                Vacation_Package vp = pr.searchQuery(s);
                return vp;
            } catch(Exception e){
                String message = "Package not found";
                JOptionPane.showMessageDialog(new JFrame(), message, "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

        }else{
            String message = "Search Field not completed";
            JOptionPane.showMessageDialog(new JFrame(), message, "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

        return null;
    }

    public void updateService(String n, String pl, String prd, String price, String extraDet, Vacation_Destination vd, Integer id) {

            if (!n.isEmpty() && n != null && !extraDet.isEmpty() && extraDet != null && !pl.isEmpty() && pl != null && !prd.isEmpty() && prd != null && !price.isEmpty() && price != null) {

                try{
                    Integer i1 = Integer.valueOf(pl);
                    Integer i2 = Integer.valueOf(prd);
                    Integer i3 = Integer.valueOf(price);

                    if (i1 >= 0 && i2 >= 0 && i3 >= 0) {

                        pr.updateQuery(n, i1, i2, i3, extraDet, vd, id);
                    }else{
                        String message = "Negative Numbers";
                        JOptionPane.showMessageDialog(new JFrame(), message, "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch(Exception e1){
                    String message = "Not numbers";
                    JOptionPane.showMessageDialog(new JFrame(), message, "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            } else {
                String message = "Some fields are empty";
                JOptionPane.showMessageDialog(new JFrame(), message, "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
    }

    public List<Vacation_Package> getService() {

        try{
            List<Vacation_Package> packages;
            packages = pr.getPackagesQuery();

            return packages;
        } catch(Exception e){
            System.out.println("Bad");
        }

        return null;
    }

    public void updateServiceAfterReservation(Vacation_Package p) {

        pr.updateQueryAfterReservation(p);
    }

}
