package repository;

import entity.User;
import entity.Vacation_Package;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.swing.*;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class UserRepository {

    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("ro.tutorial.lab.SD");

    public void insertQuery(User u) {

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(u);
        em.getTransaction().commit();
        em.close();
    }

    public User searchQuery(String username, String password) {

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        try{
            return em.createQuery("SELECT u from User u WHERE u.username LIKE :username AND u.password LIKE :password"
                    , User.class)
                    .setParameter("username",username)
                    .setParameter("password",password)
                    .getSingleResult();
        } catch(NoResultException e){
            String message = "User not found";
            JOptionPane.showMessageDialog(new JFrame(), message, "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        em.getTransaction().commit();
        em.close();

        return null;
    }


    public User searchQueryWithName(String username) {

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        try{
            return em.createQuery("SELECT u from User u WHERE u.username LIKE :username"
                    , User.class)
                    .setParameter("username",username)
                    .getSingleResult();
        } catch(NoResultException e){
            String message = "User not found";
            JOptionPane.showMessageDialog(new JFrame(), message, "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        em.getTransaction().commit();
        em.close();

        return null;
    }

    public void updateQueryAfterReservation(User u) {

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.merge(u);
        em.getTransaction().commit();
        em.close();
    }

    public User searchQueryWithPassword(String password) {

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        try{
            return em.createQuery("SELECT u from User u WHERE u.password LIKE :password"
                    , User.class)
                    .setParameter("password",password)
                    .getSingleResult();
        } catch(NoResultException e){
            String message = "User not found";
            JOptionPane.showMessageDialog(new JFrame(), message, "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        em.getTransaction().commit();
        em.close();

        return null;
    }
}
