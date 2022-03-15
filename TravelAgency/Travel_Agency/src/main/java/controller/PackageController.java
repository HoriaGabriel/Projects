package controller;

import entity.Vacation_Destination;
import entity.Vacation_Package;
import service.PackageService;

import java.util.ArrayList;
import java.util.List;

public class PackageController {

    PackageService ps = new PackageService();

    public void insertPackage(String s1, Integer i1, Integer i2, Integer i3, String s2, Vacation_Destination vd) {

        ps.insertService(s1,i1,i2,i3,s2,vd);
    }

    public void deletePackage(String s) {

        Vacation_Package vp = ps.findService(s);
        ps.deleteService(s);
    }

    public Vacation_Package findPackage(String s){

        Vacation_Package vp = ps.findService(s);

        if(vp!=null){
            return vp;
        }

        return null;
    }


    public void updatePackage(String n, String pl, String prd, String price, String extraDet, Vacation_Destination vd, Integer id) {


            ps.updateService(n,pl,prd,price,extraDet,vd,id);
    }

    public List<Vacation_Package> getPackages() {

        List<Vacation_Package> packages;
        packages = ps.getService();

        if(!packages.isEmpty()){
            return packages;
        }
        else{
            return null;
        }
    }

    public List<Vacation_Package> getPriceFilteredPackages(List<Vacation_Package> lvp, String price) {

        List<Vacation_Package> packages = new ArrayList();
        if(!price.isEmpty() && price!=null){

            for(Vacation_Package v: lvp){

                if(v.getPrice()<=Integer.valueOf(price)){
                    packages.add(v);
                }
            }

            return packages;
        }

        return lvp;
    }

    public List<Vacation_Package> getDestinationFilteredPackages(List<Vacation_Package> lvp2, String destination) {

        List<Vacation_Package> packages = new ArrayList();

        if(!destination.isEmpty() && destination!=null){

            for(Vacation_Package v: lvp2){

                if(v.getVacation_destination().getDestinationName().equals(destination)){
                    packages.add(v);
                }
            }

            return packages;
        }

        return lvp2;
    }

    public List<Vacation_Package> getPeriodFilteredPackages(List<Vacation_Package> lvp3, String period) {

        List<Vacation_Package> packages = new ArrayList();
        if(!period.isEmpty() && period!=null){

            for(Vacation_Package v: lvp3){

                if(v.getPeriod()==Integer.valueOf(period)){
                    packages.add(v);
                }
            }

            return packages;
        }

        return lvp3;
    }

    public void updatePackageAfterReservation(Vacation_Package p) {

        ps.updateServiceAfterReservation(p);
    }
}
