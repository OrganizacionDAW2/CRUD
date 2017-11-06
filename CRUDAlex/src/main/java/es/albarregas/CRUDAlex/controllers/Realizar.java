/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.CRUDAlex.controllers;

import es.albarregas.CRUDAlex.beans.Ave;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 *
 * @author Alex
 */
@WebServlet(name = "Realizar", urlPatterns = {"/Realizar"})
public class Realizar extends HttpServlet {
    
    @Resource(name = "jdbc/Pool")
    DataSource dataSource;

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
        doPost(request, response);
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

        Date fecha = null;
        SimpleDateFormat ft = new SimpleDateFormat("yyyy/MM/dd");
        String operacion = request.getParameter("operacion");
        
        Connection conexion = null;
        PreparedStatement preparada = null;
        ResultSet resultado = null;
        Ave ave = null;
        List<Ave> aves = null;
        String url = null;
        String sql = null;

        if (operacion.equals("insertar")) {

            String anilla = request.getParameter("anilla");
            String especie = request.getParameter("especie");
            String lugar = request.getParameter("lugar");
            
            try {
                fecha = ft.parse(request.getParameter("fecha"));
            } catch (ParseException ex) {
                request.getSession().setAttribute("error", "Error en la fecha.");
                url = request.getContextPath() + "/JSP/error.jsp";
            }
            
            Ave miAve = new Ave (anilla, especie, lugar, fecha);
            
            try {
                    conexion = dataSource.getConnection();
                    sql = "INSERT INTO pajaros(anilla, especie, lugar, fecha) VALUES (?, ?, ?, ?)";

                    preparada = conexion.prepareStatement(sql);
                    
                    preparada.setString(1, miAve.getAnilla());
                    preparada.setString(2, miAve.getEspecie());
                    preparada.setString(3, miAve.getLugar());
                    
                    java.util.Date utilDate = miAve.getFecha();
                    java.sql.Date fechaConvertida = new java.sql.Date (utilDate.getTime());
                    
                    preparada.setDate(4, fechaConvertida);
                    
                    preparada.execute();
                    
            }
            catch (SQLException e) {
                request.getSession().setAttribute("error", "Ha ocurrido un error al acceder a la tabla.");
                url = request.getContextPath() + "/JSP/error.jsp";
            }
            
            
        } 
        else if (operacion.equals("actualizar")) {

        } 
        else if (operacion.equals("eliminar")) {

        } 
        else {
            request.getSession().setAttribute("error", "Error desconocido.");
            url = request.getContextPath() + "/JSP/error.jsp";
        }

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
