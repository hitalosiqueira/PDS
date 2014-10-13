/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.*;
import persistence.*;

/**
 *
 * @author daniel
 */
@WebServlet(name = "BuscaProduto", urlPatterns = {"/BuscaProduto"})
public class ServletProduto extends HttpServlet {

    private void busca(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        ProdutoDAO produtodao = new ProdutoDAO();
        List<Produto> produto = null;
        produto = produtodao.buscaTodos();
        request.setAttribute("listProdutos", produto);
        
        ClienteDAO clientedao = new ClienteDAO();
        List<Cliente> clientes = null;
        clientes = clientedao.buscaTodos();
        request.setAttribute("listClientes", clientes);
        
        VendaDAO vendadao = new VendaDAO();
        List<Venda> vendas = null;
        vendas = vendadao.buscaTodos();
        request.setAttribute("listVendas", vendas);

        RequestDispatcher rd = null;
        rd = request.getRequestDispatcher("/main.jsp");
        rd.forward(request, response);
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

        try {
            busca(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ServletProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
