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
import persistence.DAOException;
import persistence.LoteDAO;

/**
 *
 * @author hitalo
 */
public class TesteLoteDAO {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws DAOException, SQLException {

        List<Lote> lista = new ArrayList<Lote>();
        LoteDAO l = new LoteDAO();

        lista = l.buscaCodProdutoValidade(1, "2016-02-12");

        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.get(i).getCodigo());
            System.out.println(lista.get(i).getCodigo_produto());
            System.out.println(lista.get(i).getDt_fabricacao());
            System.out.println(lista.get(i).getDt_validade());
            System.out.println(lista.get(i).getQtde_inicial());
            System.out.println(lista.get(i).getQtde_atual());
            System.out.println();
            System.out.println();
        }

    }

}
