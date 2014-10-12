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
import model.Lote;
import model.Produto;
import model.Venda;

/**
 *
 * @author hitalo
 */
public class VendaDAO {

    private Connection c = ConnectionFactory.getConexao();

    public List<Venda> buscaTodos() {
        String sql = "select pv.codigo_venda as CodigoVenda, c.codigo as CodigoCliente, c.nome as NomeCliente, l.codigo as Lote, p.nome as Produto, pv.quantidade as Quantidade from cliente c, produtos_venda pv, venda v, lote l, produto p where pv.codigo_venda = v.codigo and c.codigo = v.codigo_cliente and pv.codigo_lote = l.codigo and l.codigo_produto = p.codigo order by p.nome;";
        List<Venda> lista = new ArrayList<>();
        List<Produto> listaProd = new ArrayList<>();

        try {
            PreparedStatement p = c.prepareStatement(sql);
            ResultSet resultado = p.executeQuery();
            while (resultado.next()) {
                Venda v = new Venda();
                Produto prod = new Produto();
                
                v.setCodigo(resultado.getInt("CodigoVenda"));
                v.setCodigo_cliente(resultado.getInt("CodigoCliente"));
                v.setNome_cliente(resultado.getString("NomeCliente"));
                v.setnLote(resultado.getInt("Lote"));
                prod.setNome(resultado.getString("Produto"));
                listaProd.add(prod);
                v.setProdutos(listaProd);
                v.setQuantidade(resultado.getInt("Quantidade"));
                lista.add(v);

            }
            p.close();
            System.out.println("busca realizada com sucesso");
        } catch (SQLException ex) {
            System.out.println("falha na busca");
            ex.printStackTrace();
        }

        return lista;
    }

    public List<Venda> buscaId(int Id) {
        String sql = "select pv.codigo_venda as CodigoVenda, c.codigo as CodigoCliente, c.nome as NomeCliente, l.codigo as Lote, p.nome as Produto, pv.quantidade as Quantidade from cliente c, produtos_venda pv, venda v, lote l, produto p where pv.codigo_venda = v.codigo and c.codigo = v.codigo_cliente and pv.codigo_lote = l.codigo and l.codigo_produto = p.codigo order by p.nome;";
        List<Venda> lista = new ArrayList<>();
        List<Produto> listaProd = new ArrayList<>();

        try {
            PreparedStatement p = c.prepareStatement(sql);
            p.setInt(1,Id);
            ResultSet resultado = p.executeQuery();
            while (resultado.next()) {
                Venda v = new Venda();
                Produto prod = new Produto();
                
                v.setCodigo(resultado.getInt("CodigoVenda"));
                v.setCodigo_cliente(resultado.getInt("CodigoCliente"));
                v.setNome_cliente(resultado.getString("NomeCliente"));
                v.setnLote(resultado.getInt("Lote"));
                prod.setNome(resultado.getString("Produto"));
                listaProd.add(prod);
                v.setProdutos(listaProd);
                v.setQuantidade(resultado.getInt("Quantidade"));
                lista.add(v);

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
