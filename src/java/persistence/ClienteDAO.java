/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;

/**
 *
 * @author daniel
 */
public class ClienteDAO {
    
    private Connection c = ConnectionFactory.getConexao();

    public List<Cliente> buscaTodos() {
        String sql = "SELECT * FROM cliente";
        List<Cliente> lista = new ArrayList<>();
        
        try {
            PreparedStatement p = c.prepareStatement(sql);
            ResultSet resultado = p.executeQuery();

            while (resultado.next()) {
                Cliente cli = new Cliente();
                
                cli.setCodigo(resultado.getInt("codigo"));
                cli.setNome(resultado.getString("nome"));
                cli.setRamo(resultado.getString("ramo"));
                cli.setEsp_ramo(resultado.getString("esp_ramo"));
                lista.add(cli);
            }
            
            p.close();
            System.out.println("busca realizada com sucesso");
        } catch (SQLException ex) {
            System.out.println("falha na busca");
            ex.printStackTrace();
        }

        return lista;
    }

}
