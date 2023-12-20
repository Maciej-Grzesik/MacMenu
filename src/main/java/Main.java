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

        Session session = factory.openSession();
        getProductsWithOverFiftyCalciumAndIron(session);
        getAverageCaloriesOfProductsWithBacon(session);
        getMaxCholesteroleByCategory(session);
        getCoffesWithoutFiber(session);
        getCalloriesOfMcMuffins(session);
        getUniqueCarbValues(session);
    }
    public static Object executeQuery(Session session, String hqlQuery, boolean isUniqueResult) {
        Transaction tx = null;
        Object result = null;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery(hqlQuery);
            if (isUniqueResult) {
                result = query.uniqueResult();
            } else {
                result = query.getResultList();
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return result;
    }
    public static void getProductsWithOverFiftyCalciumAndIron(Session session){
        String hql = "select round(((select count(P) from ProductsModel P where P.calcium + P.iron > 50)) * 100.0 / ((select count(P) from ProductsModel P)), 2)";
        System.out.println("Procent produktów z sumą wapnia i żelaza większą od 50%");
        System.out.println(executeQuery(session, hql, true));
    }
    public static void getAverageCaloriesOfProductsWithBacon(Session session){
        String hql = "select avg(P.calories) from ProductsModel P where P.itemName like '%bacon%'";
        System.out.println("Średnia wartość kaloryczna produktów z bekonem:");
        System.out.println(executeQuery(session, hql, true));
    }
    public static void getMaxCholesteroleByCategory(Session session){
        String hql = "select max(P.cholesterole), category from ProductsModel P group by P.category";
        List<Object[]> resultList = (List<Object[]>) executeQuery(session, hql, false);
        resultList.forEach(objArr -> {
                System.out.println("Max Cholesterol: " + objArr[0] + ", Category: " + objArr[1]);
        });
    }
    public static void getCoffesWithoutFiber(Session session){
        String hql = "select count(P) from ProductsModel P where (P.itemName like '%Mocha%' or P.itemName like '%Coffee%') and P.fiber = 0";
        System.out.println("Liczba kaw bez błonnika");
        System.out.println(executeQuery(session, hql, true));

    }
    public static void getCalloriesOfMcMuffins(Session session){
        String hql = "select P.itemName, P.calories * 4.184 from ProductsModel P where P.itemName like '%McMuffin%'";
        List<Object[]> resultList = (List<Object[]>) executeQuery(session, hql, false);
            resultList.forEach(objects -> {
                System.out.println(objects[0] + ": " + objects[1] + "kJ");
            });
    }
    public static void getUniqueCarbValues(Session session){
        String hql = "select count(distinct(P.carbs)) from ProductsModel P";
        System.out.println("Liczba unikalnych wartości węglowodanów:");
        System.out.println(executeQuery(session, hql, true));
    }
}
