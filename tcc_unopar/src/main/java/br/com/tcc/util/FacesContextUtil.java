package br.com.tcc.util;

import javax.faces.context.FacesContext;
import org.hibernate.Session;

/**
 *
 * @author Jean
 */
public class FacesContextUtil {
    private static final String HIBERNATE_SESSION ="hibernate_sessin";
 
    public static void setRequestSession(Session session) {
        FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put(HIBERNATE_SESSION, session);
    }
    
    public static Session getRequestSession() {
        return (Session)FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
                .get(HIBERNATE_SESSION);
    }
    
    
    
}
