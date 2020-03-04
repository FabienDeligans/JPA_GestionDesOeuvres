/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleurs;

import dal.Adherent;
import dal.Proprietaire;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import outils.Utilitaire;
import session.AdherentFacade;
import session.ProprietaireFacade;

/**
 *
 * @author Admin
 */
public class SrvlLogin extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    String erreur; 
    @EJB
    ProprietaireFacade proprietaireF; 
    @EJB
    AdherentFacade adherentF; 
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String demande;
        String vueReponse = "/home.jsp";
        erreur = "";
        try {
            demande = getDemande(request);

            if (demande.equalsIgnoreCase("login.log")) {
                vueReponse = login(request);
            } else if (demande.equalsIgnoreCase("connecter.log")) {
                vueReponse = connecter(request);
            } else if (demande.equalsIgnoreCase("deconnecter.log")) {
                vueReponse = deconnecter(request);
            } 

        } catch (Exception e) {
            erreur = Utilitaire.getExceptionCause(e); 
        } finally {
            request.setAttribute("erreurR", erreur);
            request.setAttribute("pageR", vueReponse);
            RequestDispatcher dsp = request.getRequestDispatcher("/index.jsp");
            if (vueReponse.contains(".enf")) {
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

    private String login(HttpServletRequest request) throws Exception{

        try {
            return ("/login.jsp"); 
        } catch (Exception e) {
            throw e; 
        }

    }

    private String connecter(HttpServletRequest request) throws Exception{

        String login, pwd, vueReponse; 
        int userId; 
        
        try {
            login = request.getParameter("login"); 
            pwd = request.getParameter("pwd"); 
            Adherent adherentE = adherentF.getAdherentByLogin(login, pwd); 
            
            if(adherentE != null) {
                HttpSession session = request.getSession(true); 
                userId = adherentE.getIdAdherent(); 
                session.setAttribute("userIdS", userId);
                session.setAttribute("userTypeS", "A");
                vueReponse = "/home.jsp"; 
            } else {
                Proprietaire proprietaireE = proprietaireF.getProprietaireByLogin(login, pwd); 
                if(proprietaireE != null) {
                    HttpSession session = request.getSession(true); 
                    userId = proprietaireE.getIdProprietaire(); 
                    session.setAttribute("userIdS", userId); 
                    session.setAttribute("userTypeS", "P"); 
                    vueReponse = "/home.jsp"; 
                } else{
                    vueReponse = "/login.jsp"; 
                    erreur = "Login ou mot de passe inconnus(s) ! "; 
                }
                
            }
            return vueReponse; 
            
        } catch (Exception e) {
            throw e; 
        }

    }

    private String deconnecter(HttpServletRequest request) throws Exception{

        try {
            HttpSession session = request.getSession(true); 
            session.setAttribute("userIdS", null);
            session.setAttribute("userTypeS", null);
            return ("/home.jsp"); 
        } catch (Exception e) {
            throw e; 
        }

    }

}
