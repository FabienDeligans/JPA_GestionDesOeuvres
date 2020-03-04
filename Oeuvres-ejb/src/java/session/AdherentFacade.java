/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import dal.Adherent;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import outils.Utilitaire;

/**
 *
 * @author Admin
 */
@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
public class AdherentFacade {

    @PersistenceContext(unitName = "Oeuvres-ejbPU")
    private EntityManager em;

    public Adherent getAdherentByLogin(String login, String pwd) throws Exception {
        Adherent adherentE = null;
        try {
            Query query = em.createNamedQuery("Adherent.findByLogin");
            query.setParameter("login", login);
            try {
                adherentE = (Adherent) query.getSingleResult();
            } catch (Exception e) {
                String msg = Utilitaire.getExceptionCause(e); 
                if(!msg.contains("No entity found for query")) {
                    throw e;
                }
            }
            if (adherentE != null){
                if(adherentE.getPwd().compareTo(pwd)!= 0){
                    adherentE = null; 
                }
            }
            return adherentE;
        } catch (Exception e) {
            throw e;
        }
    }

    public Adherent getAdherentById(int idAdherent) throws Exception{
        Adherent adherentE = null; 
        try {
            Query query = em.createNamedQuery("Adherent.findByIdAdherent");
            query.setParameter("idAdherent", idAdherent); 
            try {
                adherentE= (Adherent) query.getSingleResult(); 
            } catch (Exception e) {
                String msg = Utilitaire.getExceptionCause(e); 
                if(!msg.contains("No entity found for query")) {
                    throw e; 
                }
            }
            return adherentE; 
        } catch (Exception e) {
            throw e; 
        }
    }
}
