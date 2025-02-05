package config;

import entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;
    private SessionFactory sessionFactory;

    private FactoryConfiguration(){
        Configuration configuration = new Configuration().configure();
        configuration.addAnnotatedClass(Customer.class);
        //HERE ADD THE ALL ENTITY CLASSES

        sessionFactory = configuration.buildSessionFactory();
        //AFTER THAT CREATE THE SESSION
    }

    public static FactoryConfiguration getInstance(){
        if(factoryConfiguration == null){
            factoryConfiguration = new FactoryConfiguration();
        }

        return factoryConfiguration;
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }
}