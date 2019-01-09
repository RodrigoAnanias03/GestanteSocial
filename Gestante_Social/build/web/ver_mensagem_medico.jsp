
<%@page import="Gestante_Social.model.MensagemMedico"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="java.util.ArrayList"%>
<%@page import="Gestante_Social.model.Mensagem"%>
<!DOCTYPE html>
<html lang="pt-br">

    <head>

        <meta charset="utf-8">
        <meta accept-charset="iso-8859-1">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">


        <title>Mensagem</title>
        <link rel="shortcut icon" href="img/favicon.png">

        <!-- Bootstrap Core CSS -->




        <link href="css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom CSS -->
        <link href="css/sb-admin.css" rel="stylesheet">

        <!-- Morris Charts CSS -->
        <link href="css/plugins/morris.css" rel="stylesheet">

        <!-- Custom Fonts -->
        <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.min.js" type="text/javascript"></script>
        <script src="js/buscarpaginas.js"></script>
        <script src="js/carregarposgestantes.js"></script>

    </head>

    <body style="background-color: white">

        <jsp:include page="topo_medico.jsp" />

        <div class="col-lg-8" >
            <div class="container-fluid" style="margin-top: 10px">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <%
                            MensagemMedico obj = (MensagemMedico) request.getAttribute("mensagem");
                        %>

                        <div class="col-md-1">
                            <img style="border-radius: 30%; margin-right: 10px" id="blah" src="img/imgperfilusuario/<%=obj.getUsuario().getFoto()%>" alt="your image" width="50px" height="50px" />
                        </div>
                        <div class="col-md-11">
                            <p><%=obj.getUsuario().getNome()%> <%=obj.getUsuario().getSobrenome()%></p>
                            <p>Recebido dia: <fmt:formatDate value='<%=obj.getDataMensagem()%>' type="date" pattern="dd/MM/yyyy"/></p>
                            <form accept-charset="iso-8859-1" action="BuscarInfoAmigosMedico" method="POST">
                                <input type="hidden" id="loginAmigo" name="loginAmigo" value="<%=obj.getUsuario().getLogin()%>" />
                                <button class="btn btn-default" type="submit">Ver Perfil</button>
                            </form>
                        </div>
                        <div style="margin-top: 10px" class="col-md-12">
                            <div class="panel panel-default">
                                <div class="panel-body">
                                    <label><%=obj.getMensagem()%></label>
                                    <%
                                        if(obj.getArquivo()!= null){
                                            
                                        
                                    %>
                                    <hr/>
                                    <a href="img/arquivosmedicos/<%=obj.getArquivo()%>"><button class="btn btn-info">Ver arquivo</button></a>
                                    <%}%>
                                </div>
                            </div>
                            <form method="POST" action="ExcluirMensagemMedico">
                                <input type="hidden" id="idMensagem" name="idMensagem" value="<%=obj.getIdMensagem()%>">
                                <button type="submit" class="btn btn-danger">Excluir</button>
                            </form>
                        </div>
                        <div style="margin-top: 5px" class="col-md-12">
                            <h3>Responder</h3>
                            <form accept-charset="iso-8859-1" action="EnviarMensagemMedicoUsuarios" method="POST">
                                <input type="hidden" id="loginAmigo" name="loginAmigo" value="<%=obj.getUsuario().getLogin()%>">
                                <textarea id="mensagem" name="mensagem" rows="5" class="form-control" required=""></textarea>
                                <button style="margin-top: 5px" type="submit" class="btn btn-success">Responder</button>
                            </form>
                        </div>

                    </div>
                </div>
            </div>
        </div>
        <!-- /.container-fluid -->



        <!-- /#wrapper -->

        <!-- jQuery -->
        <script src="js/jquery.js"></script>

        <!-- Bootstrap Core JavaScript -->
        <script src="js/bootstrap.min.js"></script>

        <script src="js/postagem.js"></script>

        <!-- Morris Charts JavaScript -->
        <script src="js/plugins/morris/raphael.min.js"></script>
        <script src="js/plugins/morris/morris.min.js"></script>
        <script src="js/plugins/morris/morris-data.js"></script>






    </body>

</html>
