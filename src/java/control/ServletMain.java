/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.IOException;
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
public class ServletMain extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String tipo= (String)request.getAttribute("tipo");
        
        if (tipo.compareTo("carrega")==0)
            carregaMain(request, response);
    }

    private void carregaMain(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        ProdutoDAO produtodao = new ProdutoDAO();
        List<Produto> produtos = null;
        produtos = produtodao.buscaTodos();
        request.setAttribute("listProdutos", produtos);
        
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
}
