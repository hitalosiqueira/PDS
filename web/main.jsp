<%-- 
    Document   : index
    Created on : 07/10/2014, 10:11:26
    Author     : daniel
--%>

<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="model.Usuario"%>
<%@page import="model.Produto"%>
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

            <%@include file="WEB-INF/jspf/navigation.jspf"%>

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
                            <div class="table-responsive">
                                <table class="table table-bordered table-hover">
                                    <thead>
                                        <tr>
                                            <th>Page</th>
                                            <th>Visits</th>
                                            <th>% New Visits</th>
                                            <th>Revenue</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td>/index.html</td>
                                            <td>1265</td>
                                            <td>32.3%</td>
                                            <td>$321.33</td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="NovaVenda">
                    <div class="row">
                        <div class="col-lg-12">
                            <h1 class="page-header">Nova Venda</h1>
                        </div>
                        <!-- /.col-lg-12 -->
                    </div>
                    <!-- /.row -->
                    <div class="row">

                        <div class="col-lg-6">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h3 class="panel-title">Produtos</h3>
                                </div>
                                <div class="panel-body">
                                    <table class="display" id="TableProdutos">
                                        <thead>
                                            <tr>
                                                <th>#Código</th>
                                                <th>Nome</th>
                                                <th>Qtde. disponível</th>
                                                <th>Sel. quantidade</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                                List<Produto> produtos = (List<Produto>) request.getAttribute("listProdutos");
                                                if (!produtos.isEmpty()) {
                                                    for (Iterator iterator = produtos.iterator(); iterator.hasNext();) {
                                                        Produto produto = (Produto) iterator.next();
                                            %>
                                            <tr class="gradeA">
                                                <td><%=produto.getCodigo()%></td>
                                                <td><%=produto.getNome()%></td>
                                                <td></td>
                                                <td>
                                                    <input type="number" id="QtdeProd" min="0" max="100" value="1" >
                                                    <button type="button" class="btn btn-primary btn-xs pull-right" id="BotaoAdd">Add. Produto >></button>
                                                </td>
                                            </tr>
                                            <%
                                                    }
                                                }
                                            %>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-6">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h3 class="panel-title">Itens na Venda</h3>
                                </div>
                                <div class="panel-body">
                                    <table class="table table-striped table-hover">
                                        <thead>
                                            <tr>
                                                <th>#Código</th>
                                                <th>Nome</th>
                                                <th>Qtde.</th>
                                                <th>Lote</th>
                                                <th>Validade</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr class="gradeA">
                                                <td>x</td>
                                                <td>produto 1</td>
                                                <td>10</td>
                                                <td>123</td>
                                                <td>10/01/1991</td>
                                            </tr>
                                            <tr class="gradeA">
                                                <td>x</td>
                                                <td>produto 1</td>
                                                <td>10</td>
                                                <td>123</td>
                                                <td>10/01/1991</td>
                                            </tr>
                                            <tr class="gradeA">
                                                <td>x</td>
                                                <td>produto 1</td>
                                                <td>10</td>
                                                <td>123</td>
                                                <td>10/01/1991</td>
                                            </tr>
                                            <tr class="gradeA">
                                                <td>x</td>
                                                <td>produto 1</td>
                                                <td>10</td>
                                                <td>123</td>
                                                <td>10/01/1991</td>
                                            </tr>
                                        </tbody>
                                    </table>
                                    <button type="button" class="btn btn-success pull-right">Finalizar Venda</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- /#wrapper -->
    </body>
</html>
