/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import persistence.ConnectionFactory;
import persistence.DAOException;


/**
 *
 * @author hitalo
 */

public class TesteConexao {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws DAOException {
        ConnectionFactory.getConexao();
    }
    
}
