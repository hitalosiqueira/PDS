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
 * @author hitalo
 */
public class VendaDAO {

    private Connection c = ConnectionFactory.getConexao();

    public List<Venda> buscaTodos() {
        List<Venda> lista = new ArrayList<>();
        List<Lote> lotes = new ArrayList<>();
        LoteDAO lotedao = new LoteDAO();
        
        String sql = "select c.codigo as CodigoCliente, c.nome as NomeCliente, c.ramo, c.esp_ramo, v.codigo as CodigoVenda from cliente c, venda v where c.codigo = v.codigo_cliente;";

        try {
            PreparedStatement p = c.prepareStatement(sql);
            ResultSet resultado = p.executeQuery();            
            ResultSet resultado2 = null;
            
            int i = 0;
            while (resultado.next()) {
                Venda v = new Venda();
                Cliente cli = new Cliente();
                
                v.setCodigo(resultado.getInt("codigovenda"));
                
                cli.setCodigo(resultado.getInt("codigocliente"));
                cli.setNome(resultado.getString("nomecliente"));
                cli.setRamo(resultado.getString("ramo"));
                cli.setEsp_ramo(resultado.getString("esp_ramo"));                
                v.setCliente(cli);
                
                v.setLotes(lotedao.buscaLotesVenda(v.getCodigo()));
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

    public Venda buscaID(int codigo) {
        Venda v = new Venda();
        Cliente cli = new Cliente();
        List<Lote> lotes = new ArrayList<>();
        LoteDAO lotedao = new LoteDAO();
        
        String sql = "select c.codigo as CodigoCliente, c.nome as NomeCliente, c.ramo, c.esp_ramo, v.codigo as CodigoVenda from cliente c, venda v where c.codigo = v.codigo_cliente AND v.codigo =" + codigo;

        try {
            PreparedStatement p = c.prepareStatement(sql);
            ResultSet resultado = p.executeQuery();            
            ResultSet resultado2 = null;
            
            int i = 0;
            while (resultado.next()) {                
                v.setCodigo(resultado.getInt("codigovenda"));
                
                cli.setCodigo(resultado.getInt("codigocliente"));
                cli.setNome(resultado.getString("nomecliente"));
                cli.setRamo(resultado.getString("ramo"));
                cli.setEsp_ramo(resultado.getString("esp_ramo"));                
                v.setCliente(cli);
                
                v.setLotes(lotedao.buscaLotesVenda(v.getCodigo()));
            }
            p.close();
            System.out.println("busca realizada com sucesso");
        } catch (SQLException ex) {
            System.out.println("falha na busca");
            ex.printStackTrace();
        }

        return v;
    }
}
