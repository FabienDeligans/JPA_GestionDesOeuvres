/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleurs;

import dal.Oeuvre;
import dal.Reservation;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import outils.Utilitaire;
import session.OeuvreFacade;
import session.ReservationFacade;

/**
 *
 * @author Admin
 */
public class SrvlReservation extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @EJB
    OeuvreFacade oeuvreF;
    @EJB
    ReservationFacade reservationF;

    String erreur;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String demande;
        String vueReponse = "/home.jsp";
        erreur = null;

        try {
            demande = getDemande(request);
            if (demande.equalsIgnoreCase("reserver.res")) {
                vueReponse = reserverOeuvre(request);
            } else if (demande.equalsIgnoreCase("enregistrerReservation.res")) {
                vueReponse = enreigstrerReservation(request);
            } else if (demande.equalsIgnoreCase("getReservations.res")) {
                vueReponse = getReservations(request);
            } else if (demande.equalsIgnoreCase("confirmerReservation.res")){
                vueReponse = confirmerReservation(request); 
            } else if(demande.equalsIgnoreCase("supprimmerReservation.res")){
                vueReponse = supprimmerReservation(request); 
            }
        } catch (Exception e) {
            erreur = Utilitaire.getExceptionCause(e);
        } finally {
            request.setAttribute("erreurR", erreur);
            request.setAttribute("pageR", vueReponse);
            RequestDispatcher dsp = request.getRequestDispatcher("/index.jsp");
            if (vueReponse.contains(".res")) {
                dsp = request.getRequestDispatcher(vueReponse);
            }
            dsp.forward(request, response);
        }

    }

    private String getDemande(HttpServletRequest request) {
        String demande = "";
        demande = request.getRequestURI();
        demande = demande.substring(demande.lastIndexOf("/") + 1);
        return demande;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private String reserverOeuvre(HttpServletRequest request) throws Exception {

        try {
            int idOeuvre = Integer.parseInt(request.getParameter("id"));
            Oeuvre oeuvreE = oeuvreF.getOeuvreById(idOeuvre);
            request.setAttribute("oeuvreR", oeuvreE);
            return "/reservation.jsp";
        } catch (Exception e) {
            throw e;
        }
    }

    private String enreigstrerReservation(HttpServletRequest request) throws Exception {

        Date dateReservation = null;
        String titre = "";

        try {
            int idOeuvre = Integer.parseInt(request.getParameter("id"));
            Oeuvre oeuvreE = oeuvreF.getOeuvreById(idOeuvre);
            titre = oeuvreE.getTitre();
            String date = request.getParameter("dateReservation");
            dateReservation = Utilitaire.StrToDate(date, "yyyy-MM-dd");
            
            HttpSession session = request.getSession(true);
            int idAdherent = (Integer) session.getAttribute("userIdS");
            
            reservationF.addReservation(dateReservation, idOeuvre, idAdherent);
            
            return "getReservation.res";
        } catch (Exception e) {
            erreur = Utilitaire.getExceptionCause(e);
            if (erreur.contains("PRIMARY")) {
                erreur = "l'oeuvre " + titre + " a déjà été réservée pour le : " + Utilitaire.DateToStr(dateReservation, "dd/MM/yyyy") + " !";
            }
            throw new Exception(erreur);
        }

    }

    private String getReservations(HttpServletRequest request) throws Exception {

        try {
            List<Reservation> lstReservationsE = reservationF.getReservations();
            request.setAttribute("lstReservationsR", lstReservationsE);
            return ("/listeReservations.jsp");

        } catch (Exception e) {
            throw e; 
        }

    }

    private String confirmerReservation(HttpServletRequest request) throws Exception{
        
        try {
            int idOeuvre = Integer.parseInt(request.getParameter("id")); 
            String date = request.getParameter("dateres").replace("'", "");
            java.util.Date dateReservation = Utilitaire.StrToDate(date, "yyyy-MM-dd");
            reservationF.modifyReservation(dateReservation, idOeuvre); 
            return "/getReservations.res";
        } catch (Exception e) {
            throw e; 
        }
        
    }

    private String supprimmerReservation(HttpServletRequest request) throws Exception{
        
        try {
            int idOeuvre = Integer.parseInt(request.getParameter("id")); 
            String date = request.getParameter("dateres").replace("'", "");
            java.util.Date dateReservation = Utilitaire.StrToDate(date, "yyyy-MM-dd");
            reservationF.deleteReservation(dateReservation, idOeuvre); 
            return "/getReservations.res";
        } catch (Exception e) {
            throw e; 
        }
        
    }


}
