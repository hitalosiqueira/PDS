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
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.*;

/**
 *
 * @author daniel
 */
public class LoteDAO {

    private Connection c = ConnectionFactory.getConexao();

    public List<Lote> buscaTodos() {
        String sql = "SELECT * FROM lote";
        List<Lote> lista = new ArrayList<>();

        try {
            PreparedStatement p = c.prepareStatement(sql);
            ResultSet resultado = p.executeQuery();

            while (resultado.next()) {
                Lote l = new Lote();

                l.setCodigo(resultado.getInt("codigo"));
                l.setCodigo_produto(resultado.getInt("codigo_produto"));
                l.setDt_fabricacao(resultado.getString("dt_fabricacao"));
                l.setDt_validade(resultado.getString("dt_validade"));
                l.setQtde_inicial(resultado.getInt("qtde_inicial"));
                l.setQtde_atual(resultado.getInt("qtde_atual"));
                lista.add(l);
            }
            p.close();
            System.out.println("busca realizada com sucesso");
        } catch (SQLException ex) {
            System.out.println("falha na busca");
            ex.printStackTrace();
        }

        return lista;
    }
    
        public List<Lote> buscaCodProdutoValidade(int codigo, String dt_validade) {
        String sql = "SELECT * FROM lote where codigo = ? and dt_validade = ?";
        List<Lote> lista = new ArrayList<>();

        try {
            PreparedStatement p = c.prepareStatement(sql);
            p.setInt(1,codigo);
            p.setString(2, dt_validade);
            ResultSet resultado = p.executeQuery();

            while (resultado.next()) {
                Lote l = new Lote();

                l.setCodigo(resultado.getInt("codigo"));
                l.setCodigo_produto(resultado.getInt("codigo_produto"));
                l.setDt_fabricacao(resultado.getString("dt_fabricacao"));
                l.setDt_validade(resultado.getString("dt_validade"));
                l.setQtde_inicial(resultado.getInt("qtde_inicial"));
                l.setQtde_atual(resultado.getInt("qtde_atual"));
                lista.add(l);
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
