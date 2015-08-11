package br.com.tcc.util;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import org.hibernate.Session;

/**
 *
 * @author Jean
 */
public class PhaseListenerAulas implements PhaseListener{

    
    @Override
    public void beforePhase(PhaseEvent fase) {
        //se estiver tentando restauras a visao
       if(fase.getPhaseId().equals(PhaseId.RESTORE_VIEW)){
           System.out.println("Antes da fase: "+fase.getPhaseId().toString());
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        FacesContextUtil.setRequestSession(session);
        
           
       }
    }
    @Override
    public void afterPhase(PhaseEvent fase) {
        if(fase.getPhaseId().equals(PhaseId.RENDER_RESPONSE)){
         Session session = FacesContextUtil.getRequestSession();  
            try {
                session.getTransaction().commit();
            } catch (Exception ex) {
                System.err.println("ERRO na afterPhase: "+ex);
                if(session.getTransaction().isActive()){
                    session.getTransaction().rollback();
                }
            }finally{
                System.out.println("Finalizando Sessao");
                session.close();
            }
        }
            
    }
    //Quan fase sera invocado
    @Override
    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }
    
}
