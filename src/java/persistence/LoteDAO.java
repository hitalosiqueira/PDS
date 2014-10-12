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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.*;

/**
 *
 * @author daniel
 */
public class LoteDAO {
    
    private Connection conn;
    
    public LoteDAO() throws DAOException {
        try {
            this.conn = ConnectionFactory.getConnection();

        } catch (Exception e) {
            throw new DAOException("Erro:\n" + e.getMessage());
        }
    }
    
    public List<Lote> buscaLote(String codigo, String codigo_produto)
            throws SQLException, DAOException{
        
        
        List<Lote> lotes = new ArrayList<Lote>();
        
        Statement statement = null;
	Connection conn = this.conn;
        PreparedStatement ps = null;
        ResultSet set;
        String SQL = null;
        
        try {
            SQL = "SELECT * FROM lote WHERE lote.codigo = ? AND lote.codigo_produto = ?";
            ps = conn.prepareStatement(SQL);
            ps.setString( 1, codigo );
            ps.setString( 2, codigo_produto );
            
            set = ps.executeQuery();
            while(set.next()){
                Lote result = new Lote();
                
                result.setCodigo(Integer.parseInt(set.getObject("codigo").toString()));
                result.setCodigo_produto(Integer.parseInt(set.getObject("codigo_produto").toString()));
                result.setDt_fabricacao(set.getObject("dt_fabricacao").toString());
                result.setDt_validade(set.getObject("dt_validade").toString());
                result.setQtde_atual(Integer.parseInt(set.getObject("qtde_atual").toString()));
                result.setQtde_inicial(Integer.parseInt(set.getObject("qtde_inicial").toString()));
                
                lotes.add(result);
            }
            
        }
        catch(SQLException sqle){
            throw new DAOException("Erro ao buscar dados " );
        }finally{
            ConnectionFactory.closeConnection(conn, ps);
        }
        return lotes;
    }

    
}
