package in.co.sunrays.proj3.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hibernate DataSource for Data Connection Pool
 * 
 * @author Bridge
 * @version 1.0
 * @Copyright (c) SUNRAYS Technologies
 * 
 */

public class HibDataSource {

    private static SessionFactory sessionFactory = null;

    /**
     * Get the instance of Session Factory
     * 
     * @return sessionFactory
     */
    public static SessionFactory getSessionFactory() {
    	System.out.println("Session Factory Intiated");
        if (sessionFactory == null) {
        	try{
        	sessionFactory = new Configuration().configure()
                    .buildSessionFactory();
        	}
        	catch(Exception e){
        		e.printStackTrace();
        		}
        }
        System.out.println("session Factory completed");
        return sessionFactory;
    }

    /**
     * Get Session by SessionFactory
     * 
     * @return session
     */
    public static Session getSession() {
        Session session = getSessionFactory().openSession();
        System.out.println("got the session object fron getSession()");
        return session;
    }

    /**
     * Get Session by SessionFactory
     * 
     * @return session
     */
    public static void closeSession(Session session) {
        if (session != null) {
            session.close();
        }
    }

}