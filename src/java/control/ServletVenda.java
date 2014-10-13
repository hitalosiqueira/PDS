/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Venda;
import persistence.VendaDAO;

/**
 *
 * @author daniel
 */
public class ServletVenda extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String tipo = request.getParameter("tipo");
        
        if(tipo.equals("visualiza")){
            int codigo = Integer.parseInt(request.getParameter("cod"));
            visualizaVenda(request, response, codigo);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
    
    private void visualizaVenda(HttpServletRequest request, HttpServletResponse response, int codigo) throws ServletException, IOException{
        
        Venda venda = new Venda();
        VendaDAO vendadao = new VendaDAO();
        
        venda = vendadao.buscaID(codigo);
        request.setAttribute("venda", venda);
        
        RequestDispatcher rd = null;
        rd = request.getRequestDispatcher("/viewVenda.jsp");
        rd.forward(request, response);
    }
}
