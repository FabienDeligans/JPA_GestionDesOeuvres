/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import dal.Adherent;
import dal.Oeuvre;
import dal.Reservation;
import dal.ReservationPK;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Admin
 */
@Stateless
@LocalBean
 
public class ReservationFacade {

    @PersistenceContext(unitName = "Oeuvres-ejbPU")
    private EntityManager em; 

    @EJB
    AdherentFacade adherentF;     
    
    @EJB
    OeuvreFacade oeuvreF; 
    
    public void addReservation(Date dateReservation, int idOeuvre, int idAdherent) throws Exception {

        try {
            ReservationPK reservationPKE = new ReservationPK(dateReservation, idOeuvre); 
            Reservation reservationE = new Reservation(reservationPKE, "En attente"); 
            Adherent adherentE = adherentF.getAdherentById(idAdherent); 
            reservationE.setAdherent(adherentE);
            Oeuvre oeuvreE = oeuvreF.getOeuvreById(idOeuvre); 
            reservationE.setOeuvre(oeuvreE); 
            em.persist(reservationE);
            
        } catch (Exception e) {
            throw e; 
        }

    }
    
}
