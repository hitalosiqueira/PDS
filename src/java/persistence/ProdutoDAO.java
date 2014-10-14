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
import model.Produto;

/**
 *
 * @author daniel
 */
public class ProdutoDAO {
    
    private Connection c = ConnectionFactory.getConexao();

    public List<Produto> buscaTodos() {
        String sql = "select l.codigo_produto, p.nome, sum(qtde_atual) as qtdetotal\n" +
                    "	from lote l, produto p\n" +
                    "	where l.codigo_produto = p.codigo and l.dt_validade > current_date\n" +
                    "	group by l.codigo_produto, p.nome\n" +
                    "	order by l.codigo_produto";
        List<Produto> lista = new ArrayList<>();
        
        try {
            PreparedStatement p = c.prepareStatement(sql);
            ResultSet resultado = p.executeQuery();

            while (resultado.next()) {
                Produto prod = new Produto();
                
                prod.setCodigo(resultado.getInt("codigo_produto"));
                prod.setNome(resultado.getString("nome"));
                prod.setQuantidade(Integer.parseInt(resultado.getString("qtdetotal")));
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
    
    public Produto buscaCodigo(int codigo) {
        String sql = "SELECT * FROM produto WHERE codigo ="+codigo;
        Produto pro = new Produto();

        try {
            PreparedStatement p = c.prepareStatement(sql);
            ResultSet resultado = p.executeQuery();
            Produto prod = new Produto();
            
            while (resultado.next()) {
                prod.setCodigo(resultado.getInt("codigo"));
                prod.setNome(resultado.getString("nome"));
            }
            
            p.close();
            System.out.println("busca realizada com sucesso");
        } catch (SQLException ex) {
            System.out.println("falha na busca");
            ex.printStackTrace();
        }

        return pro;
    }
}
