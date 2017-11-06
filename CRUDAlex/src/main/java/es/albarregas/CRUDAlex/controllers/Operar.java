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
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 *
 * @author Alex
 */
@WebServlet(name = "Operar", urlPatterns = {"/Operar"})
public class Operar extends HttpServlet {

    @Resource(name = "jdbc/Pool")
    DataSource dataSource;

    @Override
    public void init(ServletConfig config) throws ServletException {
        try {
            Context initialContext = new InitialContext();
            dataSource = (DataSource) initialContext.lookup("java:comp/env/jdbc/Pool");
        } catch (NamingException ex) {
            System.out.println("Problemas en el acceso a la BD.");
            ex.printStackTrace();
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

        Connection conexion = null;
        Statement sentencia = null;

        PreparedStatement preparada = null;
        ResultSet resultado = null;
        Ave ave = null;
        List<Ave> aves = null;
        String url = null;
        String sql = null;

        if (request.getParameter("enviar") != null) {
            if (request.getParameter("opcion") != null) {

                switch (request.getParameter("opcion")) {

                    case "leer":
                        url = request.getContextPath() + "/JSP/leer.jsp";
                        break;

                    case "insertar":
                        url = request.getContextPath() + "/JSP/inicioInsertar.jsp";
                        break;

                    case "actualizar":
                        url = request.getContextPath() + "/JSP/leerActualizar.jsp";
                        break;

                    case "eliminar":
                        url = request.getContextPath() + "/JSP/leerEliminar.jsp";
                        break;

                    default:
                        request.getSession().setAttribute("error", "Opción incorrecta.");
                        url = request.getContextPath() + "/JSP/error.jsp";
                }

                if (!request.getParameter("opcion").equals("insertar")) {
                    try {
                        conexion = dataSource.getConnection();
                        sql = "SELECT * FROM pajaros";

                        sentencia = conexion.createStatement();
                        resultado = sentencia.executeQuery(sql);
                        aves = new ArrayList<Ave>();

                        if (resultado.last()) {
                            resultado.beforeFirst();
                            while (resultado.next()) {
                                ave = new Ave(resultado.getString(1), resultado.getString(2), resultado.getString(3), resultado.getDate(4));
                                aves.add(ave);
                            }
                        } else {
                            throw new SQLException();
                        }
                        HttpSession session = request.getSession();
                        session.setAttribute("pajaros", aves);
                        //request.setAttribute("aves", aves);

                    } catch (SQLException e) {

                        request.getSession().setAttribute("error", "Ha ocurrido un error al acceder a la tabla.");
                        url = request.getContextPath() + "/JSP/error.jsp";

                    }
                } else {
                    url = request.getContextPath() + "/JSP/inicioInsertar.jsp";
                }

            } else {
                request.getSession().setAttribute("error", "Debe seleccionar una opción.");
                url = request.getContextPath() + "/JSP/error.jsp";
            }
        }

        response.sendRedirect(url);
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
