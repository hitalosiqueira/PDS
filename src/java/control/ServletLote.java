/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.*;
import persistence.*;

/**
 *
 * @author daniel
 */
public class ServletLote extends HttpServlet {

    public void busca(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, DAOException{
        
      
       
       String codigo = request.getParameter("codigo");
       String codigo_produto = request.getParameter("codigo_produto");
       String dt_validade= request.getParameter("dt_validade");
       String dt_fabricacao = request.getParameter("dt_fabricacao");
       String qtde_inicial = request.getParameter("qtde_inicial");
       String qtde_atual = request.getParameter("qtde_atual");
        try{
            
            LoteDAO lotedao = new LoteDAO();
            List<Lote> lotes = null;
            lotes = lotedao.buscaLote(codigo, codigo_produto, dt_fabricacao, dt_validade);
            
            request.setAttribute("listLotes", lotes);
        }catch(DAOException e){
            request.setAttribute("listLotes", null);
        }
        
        RequestDispatcher rd = null;
	rd = request.getRequestDispatcher("/viewLotes.jsp");
	rd.forward(request, response);
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
