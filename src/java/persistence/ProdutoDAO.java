/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Lote;
import model.Produto;

/**
 *
 * @author daniel
 */
public class ProdutoDAO {
    
    private Connection c = ConnectionFactory.getConexao();

    public List<Produto> buscaTodos() {
        String sql = "SELECT * FROM produto";
        List<Produto> lista = new ArrayList<>();
        
        try {
            PreparedStatement p = c.prepareStatement(sql);
            ResultSet resultado = p.executeQuery();

            while (resultado.next()) {
                Produto prod = new Produto();
                
                prod.setCodigo(resultado.getInt("codigo"));
                prod.setNome(resultado.getString("nome"));
                lista.add(prod);
            }
            p.close();
            System.out.println("busca realizada com sucesso");
        } catch (SQLException ex) {
            System.out.println("falha na busca");
            ex.printStackTrace();
        }

        return lista;
    }
    
    public List<Produto> buscaNome(String nome) {
        String sql = "SELECT * FROM produto where lower(nome) like ?";
        List<Produto> lista = new ArrayList<>();

        try {
            PreparedStatement p = c.prepareStatement(sql);
            p.setString(1,"%" + nome.toLowerCase() + "%");
            ResultSet resultado = p.executeQuery();

            while (resultado.next()) {
                Produto prod = new Produto();
                
                prod.setCodigo(resultado.getInt("codigo"));
                prod.setNome(resultado.getString("nome"));
                lista.add(prod);
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
