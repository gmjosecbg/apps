/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bll;

import dao.HibernateUtil;
import dao.Operaciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.SessionFactory;
import pojo.Direccion;
import pojo.Persona;

/**
 *
 * @author Jose
 */
public class ServletDistpatcher extends HttpServlet {

    private SessionFactory SessionBuilder;

    @Override
    public void init() throws ServletException {
        this.SessionBuilder = HibernateUtil.getSessionFactory();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            Operaciones ObjOperacion = new Operaciones();

            HttpSession sesion = request.getSession(true);

            String eleccion = request.getParameter("eleccion");
            String nif = request.getParameter("nif");
            String nifemail = request.getParameter("nifemail");
            String nombre = request.getParameter("nombre");
            String domicilio = request.getParameter("domicilio");
            String email = request.getParameter("email");
            String emailemail = request.getParameter("emailemail");

            out.print("<h1>+" + eleccion + "</h1>");

            switch (eleccion) {
                case "Crear Persona": {
                    Persona p1 = new Persona(nif, nombre, domicilio);
                    
                    if (!email.equals("null")){ 
                        Direccion dir = new Direccion(p1, email);
                        Set emailses = new HashSet();
                        emailses.add(dir);
                        p1.setDireccions(emailses);
                        ObjOperacion.insertarPersona(p1, SessionBuilder);
                    }
                    else {
                        
                        ObjOperacion.insertarPersona(p1, SessionBuilder);
                    }
                    
                    out.print("<h1>+" + p1.getDireccions() + "</h1>");

                    sesion.setAttribute("Mensaje", "Persona creada");
                    response.sendRedirect("index.html");
                    break;
                }
                case "Listar": {
                    List lista = ObjOperacion.listarPersonas(SessionBuilder);

                    sesion.setAttribute("APersonas", lista);
                    
                    out.print("<h1>+" + lista.size() + "</h1>");
                    
                    response.sendRedirect("vista/listado.jsp");
                    break;
                }
                case "Insertar": {

                    Persona p1 = ObjOperacion.buscarPersonaNif(nifemail, SessionBuilder);

                    Direccion dir = new Direccion(p1, emailemail);
                    Set emailses = new HashSet();
                    emailses.add(dir);
                    p1.setDireccions(emailses); 
                    
                    ObjOperacion.insertarEmails(dir, SessionBuilder);
                    
                    
                    //out.print("<h1>+" + p1.getDireccions() + "</h1>");
                    
                    sesion.setAttribute("Mensaje", "Email insertado");
                    response.sendRedirect("index.html");
                    break;
                }
                case "Borrar": {

                    Persona p1 = ObjOperacion.buscarPersonaNif(nifemail, SessionBuilder);
 
                    try {
                        ObjOperacion.borrarPersona(p1, SessionBuilder);
                    } catch (Exception ex) {
                    sesion.setAttribute("Mensaje", "No se pudo borrar");
                        
                    }
                     
                    sesion.setAttribute("Mensaje", "Persona borrada.");
                    response.sendRedirect("index.html");
                    break;
                }
            }

           // Insertar en tablas
        }
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

}
