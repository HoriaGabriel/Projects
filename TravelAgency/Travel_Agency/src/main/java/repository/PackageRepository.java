package repository;

import entity.User;
import entity.Vacation_Destination;
import entity.Vacation_Package;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class PackageRepository {

    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("ro.tutorial.lab.SD");

    public void insertQuery(Vacation_Package vPack) {

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(vPack);
        em.getTransaction().commit();
        em.close();
    }

    public Vacation_Package searchQuery(String packageName) {

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        try{
             return em.createQuery("SELECT vp from Vacation_Package vp WHERE vp.packageName LIKE :packageName"
                     ,Vacation_Package.class)
                     .setParameter("packageName",packageName).getSingleResult();
        } catch(NoResultException e){
            String message = "Package not found";
            JOptionPane.showMessageDialog(new JFrame(), message, "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        em.getTransaction().commit();
        em.close();

        return null;
    }

    public void deleteQuery(String packageName) {

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        try{
            em.createQuery("DELETE from Vacation_Package vp WHERE vp.packageName LIKE :packageName")
                    .setParameter("packageName",packageName).executeUpdate();
        } catch(Exception e){
            String message = "Deletion Failed";
            JOptionPane.showMessageDialog(new JFrame(), message, "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        em.getTransaction().commit();
        em.close();
    }

    public void updateQuery(String packageName, Integer availablePlaces, Integer period, Integer price, String extraDetails, Vacation_Destination vacation_destination, Integer id) {

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        try{
            em.createQuery("UPDATE Vacation_Package vp SET vp.packageName =  :packageName, " +
                    "vp.availablePlaces= :availablePlaces, vp.period= :period, vp.price= :price," +
                    "vp.extraDetails= :extraDetails, vp.vacation_destination= :vacation_destination" + " WHERE vp.id = :id")
                    .setParameter("packageName",packageName)
                    .setParameter("availablePlaces",availablePlaces)
                    .setParameter("period",period)
                    .setParameter("price",price)
                    .setParameter("extraDetails",extraDetails)
                    .setParameter("vacation_destination",vacation_destination)
                    .setParameter("id",id)
                    .executeUpdate();
        } catch(Exception e){
            String message = "Update Failed";
            JOptionPane.showMessageDialog(new JFrame(), message, "Error",
                    JOptionPane.ERROR_MESSAGE);
        }finally{
            final JPanel panel = new JPanel();
            JOptionPane.showMessageDialog(panel, "Package Edited", "Success",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        em.getTransaction().commit();
        em.close();
    }

    public List<Vacation_Package> getPackagesQuery() {

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        try{
            return em.createQuery("SELECT v from Vacation_Package v").getResultList();

        } catch(NoResultException e){
            System.out.println("Packages not found");
        }
        em.getTransaction().commit();
        em.close();
        return null;
    }

    public void updateQueryAfterReservation(Vacation_Package p) {

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.merge(p);
        em.getTransaction().commit();
        em.close();
    }
}
