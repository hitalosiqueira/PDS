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
import model.Produto;

/**
 *
 * @author daniel
 */
public class ProdutoDAO {

    private Connection conn;

    public ProdutoDAO() throws DAOException {
        try {
            this.conn = ConnectionFactory.getConexao();

        } catch (Exception e) {
            throw new DAOException("Erro:\n" + e.getMessage());
        }
    }

    public List<Produto> buscaProduto() throws SQLException, DAOException {

        List<Produto> produtos = new ArrayList<Produto>();
        Statement statement = null;
        ResultSet set;

        String SQL = null;

        SQL = "select * from produto";

        try {
            statement = conn.createStatement();
            set = statement.executeQuery(SQL);

            while (set.next()) {
                Produto result = new Produto();
                result.setNome(set.getObject("nome").toString());
                result.setCodigo((int) set.getObject("codigo"));
                produtos.add(result);
            }
        } catch (SQLException sqle) {
            throw new DAOException("Erro ao buscar dados " + sqle);
        }
        return produtos;
    }
}