import jakarta.persistence.EntityManager;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.cfg.Configuration;


import java.util.List;

public class Main {
    private static SessionFactory factory;
    public static void main(String[] args) {
        try {
            factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory");
            throw new ExceptionInInitializerError(ex);
        }
        //#################################
        Session session = factory.openSession();
        getProductsWithOverFiftyCalciumAndIron(session);
    }
    public static void getProductsWithOverFiftyCalciumAndIron(Session session){
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String hql = "select count(P) from ProductsModel P";
            Query result = session.createQuery(hql);
            System.out.println(result.uniqueResult());
            tx.commit();
        } catch (HibernateException e){
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    public static void getAverageCaloriesOfProductsWithBacon(EntityManager entityManager){
        try {

        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void getMaxCholesteroleByCategory(EntityManager entityManager){
        try {

        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void getCoffesWithoutFiber(EntityManager entityManager){
        try {

        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void getCalloriesOfMcMuffins(EntityManager entityManager){
        try {

        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void getUniqueCarbValues(EntityManager entityManager){
        try {

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
