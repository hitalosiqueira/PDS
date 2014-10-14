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
import model.*;

/**
 *
 * @author daniel
 */
public class LoteDAO {

    private Connection c = ConnectionFactory.getConexao();

    public List<Lote> buscaLotesVenda(int codigo) {
        String sql = "SELECT l.codigo, p.codigo as codigo_produto, p.nome, pv.quantidade, l.dt_fabricacao, l.dt_validade FROM produtos_venda pv, lote l, produto p WHERE l.codigo = pv.codigo_lote AND l.codigo_produto = p.codigo AND pv.codigo_venda ="+codigo;
        List<Lote> lista = new ArrayList<>();

        try {
            PreparedStatement p = c.prepareStatement(sql);
            ResultSet resultado = p.executeQuery();

            while (resultado.next()) {
                Lote l = new Lote();
                Produto pro = new Produto();

                l.setCodigo(resultado.getInt("codigo"));
                pro.setCodigo(resultado.getInt("codigo_produto"));
                pro.setNome(resultado.getString("nome"));
                l.setProduto(pro);
                l.setDt_fabricacao(resultado.getDate("dt_fabricacao"));
                l.setDt_validade(resultado.getDate("dt_validade"));
                l.setQtde_pedido(resultado.getInt("quantidade"));
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
    
    public List<Lote> buscaLotesProduto(int codigo) {
        String sql = "SELECT * FROM lote WHERE dt_validade > current_date AND codigo_produto="+codigo+" AND qtde_atual > 0 ORDER BY dt_validade";
        List<Lote> lista = new ArrayList<>();

        try {
            PreparedStatement p = c.prepareStatement(sql);
            ResultSet resultado = p.executeQuery();

            while (resultado.next()) {
                Lote l = new Lote();

                l.setCodigo(resultado.getInt("codigo"));
                l.setDt_fabricacao(resultado.getDate("dt_fabricacao"));
                l.setDt_validade(resultado.getDate("dt_validade"));
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
    
//    public List<Lote> buscaCodProdutoValidade(int codigo, String dt_validade) {
//    String sql = "SELECT * FROM lote where codigo = ? and dt_validade = ?";
//    List<Lote> lista = new ArrayList<>();
//
//    try {
//        PreparedStatement p = c.prepareStatement(sql);
//        p.setInt(1,codigo);
//        p.setDate(2, java.sql.Date.valueOf(dt_validade));
//        ResultSet resultado = p.executeQuery();
//
//        while (resultado.next()) {
//            Lote l = new Lote();
//
//            l.setCodigo(resultado.getInt("codigo"));
//            l.setCodigo_produto(resultado.getInt("codigo_produto"));
//            l.setDt_fabricacao(resultado.getDate("dt_fabricacao"));
//            l.setDt_validade(resultado.getDate("dt_validade"));
//            l.setQtde_inicial(resultado.getInt("qtde_inicial"));
//            l.setQtde_atual(resultado.getInt("qtde_atual"));
//            lista.add(l);
//        }
//        p.close();
//        System.out.println("busca realizada com sucesso");
//    } catch (SQLException ex) {
//        System.out.println("falha na busca");
//        ex.printStackTrace();
//    }
//
//    return lista;
//}

}
