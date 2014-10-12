/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.IOException;
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
    
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
    }

    public void busca(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, DAOException{
        
      
       
       String codigo = request.getParameter("codigo");
       String codigo_produto = request.getParameter("codigo_produto");
       String dt_validade= request.getParameter("dt_validade");
       String dt_fabricacao = request.getParameter("dt_fabricacao");
       String qtde_inicial = request.getParameter("qtde_inicial");
       String qtde_atual = request.getParameter("qtde_atual");
       LoteDAO lotedao = new LoteDAO();
       List<Lote> lotes = null;
       request.setAttribute("listLotes", lotes);
       
        RequestDispatcher rd = null;
	rd = request.getRequestDispatcher("/viewLotes.jsp");
	rd.forward(request, response);
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
