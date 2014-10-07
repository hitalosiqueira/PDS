/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.Usuario;

/**
 *
 * @author daniel
 */
public class UsuarioDAO {

    private Connection conn;

    public UsuarioDAO() throws DAOException {
        try {
            this.conn = ConnectionFactory.getConnection();

        } catch (Exception e) {
            throw new DAOException("Erro:\n" + e.getMessage());
        }
    }

//    // inclui filme no BD
//    public void salvar(UsuarioBean usuario) throws DAOException {
//
//        Statement ps = null;
//        Connection conn = null;
//
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            String conexao = "jdbc:mysql://localhost:3306/web2";
//            String usuarioa = "root", senha = "1234";
//            conn = DriverManager.getConnection(conexao, usuarioa, senha);
//        } catch (Exception e) {
//            System.err.println(e);
//        }
//
//        if (usuario == null) {
//            throw new DAOException("O valor passado nÃ£o pode ser nulo");
//        }
//
//        try {
//            String nome = usuario.getNome();
//            String email = usuario.getEmail();
//            String senha = usuario.getSenha();
//
//            //MUDAR AQUI A QUERRY
//            String SQL = "INSERT INTO `usuario`(`email`, `senha`, `nome`) VALUES ('" + email + "','" + senha + "','" + nome + "');";
//
//            ps = conn.createStatement();
//            ps.executeUpdate(SQL);
//
//        } catch (SQLException sqle) {
//            throw new DAOException("Erro ao inserir dados " + sqle);
//        } finally {
//            ConnectionFactory.closeConnection(conn, ps);
//
//        }
//    }// fim salvar

    public boolean isUsuarioValido(Usuario usuario, int x) throws DAOException {

        String login = usuario.getLogin();
        String senha = usuario.getSenha();


        Usuario novo = null;
        Statement ps = null;
        ResultSet rs = null;
        Connection conn = this.conn;

        //FAZER BUSCA SQL AQUI

        if (login == null || senha == null) {
            throw new DAOException("O valor passado não pode ser nulo");
        }

        try {
            String SQL = "SELECT * FROM `Usuario` WHERE `login` = '" + login + "' AND `senha` = '" + senha + "'";
            ps = conn.createStatement();
            rs = ps.executeQuery(SQL);

            while (rs.next()) {
                String loginb = rs.getObject("nome_usuario").toString();
                String password = rs.getObject("senha").toString();
                novo = new Usuario();
                novo.setLogin(loginb);
                novo.setSenha(password);
            }

        } catch (SQLException sqle) {
            throw new DAOException("Erro ao Logar " + sqle);
        } finally {
            ConnectionFactory.closeConnection(conn, (PreparedStatement) ps);

        }
        if (x == 1) {
            if (novo != null) {
                if (((novo.getLogin()).equals(login)) && ((novo.getSenha()).equals(senha))) {
                    return true;
                }
            }
        } else {
            if (novo != null) {
                return true;
            }
        }
        return false;

    }
}
