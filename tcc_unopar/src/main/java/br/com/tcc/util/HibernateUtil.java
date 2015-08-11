package br.com.tcc.util;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 *
 * @author Jean
 */
public class HibernateUtil {
    private static final SessionFactory sessionFactory;
    public static final String HIBERNATE_SESSION = "hibernate_session";
    
    static {
        try {
            System.out.println("Inicio de confi de Session Factory");
            Configuration configuration = new Configuration().configure();
            ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().
                    applySettings(configuration.getProperties()).buildServiceRegistry();
            
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
         
            System.out.println("Fim de confi de Session Factory");
            
        } catch (Exception ex) {
            System.err.print("Erro ao iniciar Session Factory. "+ex);
            throw new ExceptionInInitializerError(ex);
        }      
    
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
      
}
