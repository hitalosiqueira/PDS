<%-- 
    Document   : index
    Created on : 13/10/2014, 11:35:26
    Author     : daniel
--%>

<%@page import="model.*"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
    <head>
        <title>ERP Jabuti</title>
        <%@include file="WEB-INF/jspf/head.jspf"%>
    </head>
    <body>
        <%//recupera a sessao
            HttpSession s = request.getSession(false);
            Usuario login = null;
            if (s != null) {
                login = (Usuario) s.getAttribute("Usuario");
            }
        %>
        <div id="wrapper">
            <!-- Navigation -->
            <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="/ServletProduto">ERP Jabuti</a>
                </div>
                <!-- /.navbar-header -->

                <ul class="nav navbar-top-links navbar-right">
                    <li class="dropdown">
                        <form method="post" action="ValidaLogin" id="formlogout" style="margin-top: 15px;">
                            <a class="dropdown-toggle" data-toggle="dropdown" href="javascript:{}" onclick="document.getElementById('formlogout').submit(); return false;">
                                <i class="fa fa-user fa-fw"></i><b><%= login.getLogin()%></b><i class="fa fa-sign-out fa-fw"></i>
                            </a>
                            <input type="hidden" name="tipo" value="logout">
                        </form>       
                        <!-- /.dropdown-user -->
                    </li>
                </ul>
            </nav>
            <div id="page-wrapper">
                <div id="VendasEfetuadas">
                    <div class="row">
                        <div class="col-lg-12">
                            <h1 class="page-header">Vendas Efetuadas</h1>
                        </div>
                        <!-- /.col-lg-12 -->
                    </div>
                    <!-- /.row -->
                    <div class="row">
                        <div class="col-lg-12">
                            lalalal
                        </div>
                    </div>
                </div>
            </div>
            <!-- /#wrapper -->
    </body>
</html>
