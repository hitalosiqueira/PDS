/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Lote;
import model.Venda;
import persistence.DAOException;
import persistence.LoteDAO;
import persistence.VendaDAO;

/**
 *
 * @author hitalo
 */
public class TesteVendaDAO {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws DAOException, SQLException {

        List<Venda> lista = new ArrayList<Venda>();
        VendaDAO l = new VendaDAO();

        lista = l.buscaId(2);
        int j = 0;
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.get(i).getCodigo());
            System.out.println(lista.get(i).getCodigo_cliente());
            System.out.println(lista.get(i).getNome_cliente());
            System.out.println(lista.get(i).getnLote());
            System.out.println(lista.get(i).getProdutos().get(j));
            System.out.println(lista.get(i).getQuantidade());
            System.out.println();
            System.out.println();
            j++;
        }

    }
    
}
