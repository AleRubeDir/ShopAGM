/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.progettoacademy;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javafile.driverDB;
import javafile.Prodotto;
/**
 *
 * @author Alessandro
 */
@WebServlet(name = "prodottiServlet", urlPatterns = {"/prodotti"})
public class prodottiServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    Connection c;
    public void init(ServletConfig config) throws ServletException { 
        super.init(config); 
        String initial = config.getInitParameter("initial"); 
        c = driverDB.getConn(); 
    } 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Prodotti</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet prodotti at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        Statement st;
        try {
            st = this.c.createStatement();
            String query = "SELECT * from prodotto";
                    ResultSet rs = st.executeQuery(query);
                    ArrayList listaProdotti = new ArrayList<Prodotto>();
                    while(rs.next()){
                        Prodotto p = new Prodotto();
                        p.setCodice(rs.getInt("codice"));
                        p.setNome(rs.getString("nome"));
                        p.setPrezzo(rs.getDouble("prezzo"));
                        p.setImg(rs.getString("img"));
                        listaProdotti.add(p);
                    }
                    request.setAttribute("list", listaProdotti);
                    processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(prodottiServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
                    

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