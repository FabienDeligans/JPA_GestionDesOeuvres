/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import dal.Oeuvre;
import dal.Proprietaire;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.hibernate.Hibernate;
import outils.Utilitaire;

/**
 *
 * @author Admin
 */
@Stateless
@LocalBean
public class ProprietaireFacade {

    @PersistenceContext(unitName = "Oeuvres-ejbPU")
    private EntityManager em;

    public Proprietaire getProprietaireByLogin(String login, String pwd) throws Exception {
        Proprietaire proprietaireE = null;
        try {
            Query query = em.createNamedQuery("Proprietaire.findByLogin");
            query.setParameter("login", login);
            try {
                proprietaireE = (Proprietaire) query.getSingleResult();
            } catch (Exception e) {
                String msg = Utilitaire.getExceptionCause(e);
                if (!msg.contains("No entity found for query")) {
                    throw e;
                }
            }
            if (proprietaireE != null) {
                if (proprietaireE.getPwd().compareTo(pwd) != 0) {
                    proprietaireE = null;
                }
            }
            return proprietaireE;
        } catch (Exception e) {
            throw e;
        }
    }
    
    public List<Oeuvre> getMyOeuvres(int idProprietaire) throws Exception {
        try {
            Proprietaire proprietaireE = getProprietaireById(idProprietaire); 
            return proprietaireE.getOeuvreList(); 
        } catch (Exception e) {
            throw e; 
        }
    }

    public Proprietaire getProprietaireById(int idProprietaire) throws Exception {
        
        Proprietaire proprietaireE = null; 
        try {
            proprietaireE = em.find(Proprietaire.class, idProprietaire); 
            return proprietaireE; 
        } catch (Exception e) {
            throw e; 
        }
    }
}
