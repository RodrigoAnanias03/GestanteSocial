<%@page import="Gestante_Social.model.Usuario"%>
<%@page import="Gestante_Social.model.Comentario"%>
<%@page import="java.util.Date"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="Gestante_Social.model.Postagem"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html lang="pt-br">

    <head>

        <meta charset="utf-8">
        <meta accept-charset="iso-8859-1">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Comentários</title>
        <link rel="shortcut icon" href="img/favicon.png">

        <link href="css/bootstrap.min.css" rel="stylesheet">


        <link href="css/sb-admin.css" rel="stylesheet">


        <link href="css/plugins/morris.css" rel="stylesheet">


        <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">






    </head>

    <body  style="background-color: white">



        <%
            ArrayList<Comentario> comentarios = (ArrayList) request.getAttribute("comentarios");

            for (Comentario obj : comentarios) {
                if (obj.getUsuario() != null) {
        %>

        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-body">

                    <div class="col-md-1">
                        <img style="border-radius: 30%; margin-right: 10px" id="blah" src="img/imgperfilusuario/<%=obj.getUsuario().getFoto()%>" alt="your image" width="50px" height="50px" />

                    </div>
                    <div class="col-md-11">
                        <%
                            long semanas = 0;
                            if (obj.getUsuario().getInicioGestacao() != null) {
                                Date hoje = new Date();
                                semanas = hoje.getTime() - obj.getUsuario().getInicioGestacao().getTime();
                                semanas = ((semanas / (24 * 60 * 60 * 1000)) / 7) + 1;
                            }

                            //encripta

                        %>
                        <form action="BuscarInfoAmigos" method="POST" accept-charset="iso-8859-1">
                            <input type="hidden" id="idAmigo" name="idAmigo" value="<%=obj.getUsuario().getLogin()%>"/>
                            <input type="hidden" id="idUser" name="idUser" value="${idUsuario}"/>
                            <%if (obj.getUsuario().getInicioGestacao() != null) {%>
                            <label><%=obj.getUsuario().getNome() + " - " + semanas + "ª semana"%></label>
                            <%} else {%>
                            <label><%=obj.getUsuario().getNome()%></label>

                            <%}%>
                            <br/><label><input type="submit" value="Ver Perfil"/></label>
                        </form>
                    </div>
                    <div class="col-md-12">
                        <label><%=obj.getComentario()%></label>
                    </div>
                    <%
                        Usuario us = (Usuario) session.getAttribute("usuAutenticado");

                        if (us.getIdUsuario() == obj.getUsuario().getIdUsuario()) {
                    %>
                    <div class="col-md-12">
                        <form action="ExcluirComentario" method="POST" accept-charset="iso-8859-1">
                            <input type="hidden" id="idComentario" name="idComentario" value="<%=obj.getIdComentario()%>"/>
                            <input type="hidden" id="idPostagem" name="idPostagem" value="${idpost}"/>
                            <input type="hidden" id="loginAmigo" name="loginAmigo" value="${loginAmigo}"/>
                            <button class="btn btn-danger" type="submit">Excluir</button>
                        </form>
                    </div>
                    <%}%>


                </div>
            </div>
        </div>
        <%} else if(obj.getFamiliar()!=null){%>
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-body">
                    <div class="col-md-1">
                        <img style="border-radius: 30%; margin-right: 10px" id="blah" src="img/imgperfilfamiliar/<%=obj.getFamiliar().getFoto()%>" alt="your image" width="50px" height="50px" />

                    </div>
                    <div class="col-md-11">
                        <label><%=obj.getFamiliar().getNome()%> <%=obj.getFamiliar().getSobreNome()%></label><label style="color: red"> - Familiar</label>
                    </div>
                    <div class="col-md-12">
                        <label><%=obj.getComentario()%></label>
                    </div>
                </div>
            </div>
        </div>
        <%}else{%>
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-body">
                    <div class="col-md-1">
                        <img style="border-radius: 30%; margin-right: 10px" id="blah" src="img/imgperfilmedico/<%=obj.getMedico().getFoto()%>" alt="your image" width="50px" height="50px" />

                    </div>
                    <div class="col-md-11">
                        <label><%=obj.getMedico().getNome()%> <%=obj.getMedico().getSobreNome()%></label><label style="color: red"> - Profissional</label>
                    </div>
                    <div class="col-md-12">
                        <label><%=obj.getComentario()%></label>
                    </div>
                </div>
            </div>
        </div>

        <%}%>
        <%}%>





        <!-- /#wrapper -->

        <!-- jQuery -->
        <script src="js/jquery.js"></script>

        <!-- Bootstrap Core JavaScript -->
        <script src="js/bootstrap.min.js"></script>

        <!-- Morris Charts JavaScript -->
        <script src="js/plugins/morris/raphael.min.js"></script>
        <script src="js/plugins/morris/morris.min.js"></script>
        <script src="js/plugins/morris/morris-data.js"></script>

    </body>

</html>

