/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import dal.Oeuvre;
import dal.Proprietaire;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
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
public class OeuvreFacade {

    @PersistenceContext(unitName = "Oeuvres-ejbPU")
    private EntityManager em;
    @EJB
    private ProprietaireFacade proprietaireF; 

    public Oeuvre getOeuvreById(int idOeuvre) throws Exception {
        try {
            return em.find(Oeuvre.class, idOeuvre);
        } catch (Exception e) {
            throw e;
        }
    }

    public List<Oeuvre> getOeuvres() throws Exception {
        try {
            return (em.createNamedQuery("Oeuvre.findAll").getResultList());
        } catch (Exception e) {
            throw e;
        }
    }

    public List<Oeuvre> getOeuvresByProprietaire(Proprietaire proprietaireE) throws Exception {

        List<Oeuvre> oeuvres = null;
        try {
            Query query = em.createNamedQuery("Oeuvre.findByProprietaire");
            query.setParameter("proprietaire", proprietaireE);
            try {
                oeuvres = (List<Oeuvre>) query.getResultList();
            } catch (Exception e) {
                String msg = Utilitaire.getExceptionCause(e);
                if (!msg.contains("No entity found for query")) {
                    throw e;
                }
            }
            return oeuvres;

        } catch (Exception e) {
            throw e;
        }
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void addOeuvre(String titre, double prix, int idProprietaire) throws Exception {
        try {
            Oeuvre oeuvreE = new Oeuvre(); 
            oeuvreE.setTitre(titre);
            oeuvreE.setPrix(BigDecimal.valueOf(prix));
            Proprietaire proprietaireE = proprietaireF.getProprietaireById(idProprietaire); 
            oeuvreE.setProprietaire(proprietaireE); 
            em.persist(oeuvreE);
        } catch (Exception e) {
            throw e; 
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deleteOeuvre(int idOeuvre) throws Exception {

        try {
            Oeuvre oeuvreE = getOeuvreById(idOeuvre); 
            em.remove(oeuvreE);
        } catch (Exception e) {
            throw e;            
        }
    }

}
