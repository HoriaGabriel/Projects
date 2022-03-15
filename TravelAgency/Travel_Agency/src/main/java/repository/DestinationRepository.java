package repository;

import entity.Vacation_Destination;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.swing.*;
import java.util.List;

public class DestinationRepository {

    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("ro.tutorial.lab.SD");

    public void insertQuery(Vacation_Destination vDest) {

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(vDest);
        em.getTransaction().commit();
        em.close();

    }

    public List<Vacation_Destination> getDestinationQuery() {

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        try{
            return em.createQuery("SELECT c from Vacation_Destination c").getResultList();

        } catch(NoResultException e){
            System.out.println("Destinations not found");
        }
        em.getTransaction().commit();
        em.close();
        return null;
    }

    public Vacation_Destination searchQuery(String destinationName) {

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        try{
            return em.createQuery("SELECT vd from Vacation_Destination vd WHERE vd.destinationName LIKE :destinationName"
                    , Vacation_Destination.class)
                    .setParameter("destinationName",destinationName).getSingleResult();
        } catch(NoResultException e){
            String message = "Destination Not Found";
            JOptionPane.showMessageDialog(new JFrame(), message, "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        em.getTransaction().commit();
        em.close();

        return null;
    }

    public void deleteQuery(String destinationName) {

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        try{
            em.createQuery("DELETE from Vacation_Destination vd WHERE vd.destinationName LIKE :destinationName")
                    .setParameter("destinationName",destinationName).executeUpdate();
        } catch(Exception e){
            String message = "Deletion Failed";
            JOptionPane.showMessageDialog(new JFrame(), message, "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        em.getTransaction().commit();
        em.close();
    }
}
