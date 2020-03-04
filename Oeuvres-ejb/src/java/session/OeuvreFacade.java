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
import outils.Utilitaire;

/**
 *
 * @author Admin
 */
@Stateless
@LocalBean
public class OeuvreFacade {

    @PersistenceContext(unitName = "Oeuvres-ejbPU")
    private EntityManager em;

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
                oeuvres = (List<Oeuvre>)query.getResultList(); 
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

}
