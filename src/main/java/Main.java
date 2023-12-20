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
        getAverageCaloriesOfProductsWithBacon(session);
        getMaxCholesteroleByCategory(session);
    }
    public static void getProductsWithOverFiftyCalciumAndIron(Session session){
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String hql = "select (select count(P) from ProductsModel P where P.iron + P.calcium > 50)/(select count (P) from ProductsModel P)";
            Query result = session.createQuery(hql);
            System.out.println(result.uniqueResult());
            tx.commit();
        } catch (HibernateException e){
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }
    }
    public static void getAverageCaloriesOfProductsWithBacon(Session session){
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String hql = "select avg(P.calories) from ProductsModel P where P.itemName like '%bacon%'";
            Query result = session.createQuery(hql);
            System.out.println(result.uniqueResult());
            tx.commit();
        } catch (HibernateException e){
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }
    }
    public static void getMaxCholesteroleByCategory(Session session){
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String hql = "select max(P.cholesterole) from ProductsModel P group by P.category";
            Query result = session.createQuery(hql);
            List<Object> resultList = result.getResultList();
            resultList.forEach(value -> System.out.println(value));
            tx.commit();
        } catch (HibernateException e){
            if (tx!=null) tx.rollback();
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
