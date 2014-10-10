/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.*;
import persistence.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author daniel
 */
public class ValidaLogin extends HttpServlet {


     /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
     protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tipo=request.getParameter("tipo");
        if (tipo.compareTo("login")==0)
            login(request,response);
        else if (tipo.compareTo("logout")==0)
            logout(request,response);
    }

    //logout
    private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);//pega uma sessao que ja existe
        session.invalidate(); //invalida sessao
        response.sendRedirect("index.jsp");
    }

    //login
    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       //capturar o login
       Usuario objUsuario = new Usuario();
       objUsuario.setLogin(request.getParameter("login"));
       objUsuario.setSenha(request.getParameter("senha"));

         try {
             //checa se o usuario e valido, se for cria session
             UsuarioDAO usuario = new UsuarioDAO();
               
             
             if(usuario.isUsuarioValido(objUsuario,1)){
                 HttpSession session = request.getSession();//cria uma sessao(se tiver outra ele destroi)
                 //UsuarioBean user = new UsuarioBean();        
                 //user.setEmail(login);
                 session.setAttribute("Usuario",objUsuario);
                 response.sendRedirect("main.jsp");
       
             }else{
                 HttpSession session = request.getSession();
                 String invalido = "invalido";
                 session.setAttribute("Usuario",invalido);
                 response.sendRedirect("index.jsp");                

             }
         } catch (DAOException ex) {
             Logger.getLogger(ValidaLogin.class.getName()).log(Level.SEVERE, null, ex);
         }
   }
    
    
    
    
}
