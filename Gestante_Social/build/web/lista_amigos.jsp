
<%@page import="java.util.ArrayList"%>
<%@page import="Gestante_Social.model.Usuario"%>
<!DOCTYPE html>
<html lang="pt-br">

    <head>

        <meta charset="utf-8">
        <meta accept-charset="iso-8859-1">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">


        <title>Amigos</title>
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

        <jsp:include page="topo_gestante.jsp" />

        <div class="col-lg-8" >

            <div class="container-fluid" style="margin-top: 10px">


                <div class="col-md-12">
                    <center>
                        <label>${quantidade_amigos} amigos</label>
                    </center>
                </div>
                <div class="col-md-12" style="margin: 0; padding: 0">
                    <div class="panel panel-default">
                        <div class="panel-body">


                            <%
                                ArrayList<Usuario> amigos = (ArrayList) request.getAttribute("amigos");
                                for (Usuario amigo : amigos) {
                            %>
                            <div class="col-md-3">
                                <div class="panel panel-default">
                                    <div class="panel-body">
                                        <center>
                                            <div style="margin-bottom: 5px" class="col-md-12">
                                                <img style="border-radius: 30%;" id="blah" src="img/imgperfilusuario/<%=amigo.getFoto()%>" alt="your image" width="100px" height="100px" />
                                            </div>
                                            <p><label><%=amigo.getNome()%> <%=amigo.getSobrenome()%></label></p>
                                            <form style="margin-bottom: 4px" accept-charset="iso-8859-1" action="BuscarInfoAmigos" method="POST">
                                                <input type="hidden" id="idAmigo" name="idAmigo" value="<%=amigo.getLogin()%>"/>
                                                <button class="btn btn-block" type="submit">Perfil</button>
                                            </form>
                                            <form accept-charset="iso-8859-1" action="BuscarTelaEnviarMensagem" method="POST">
                                                <input type="hidden" id="idAmigo" name="idAmigo" value="<%=amigo.getLogin()%>"/>
                                                <button class="btn btn-block" type="submit">Enviar Mensagem</button>
                                            </form>
                                        </center>
                                    </div>
                                </div>
                            </div>
                            <%}%>




                        </div>
                    </div>
                </div>


            </div>
        </div>
        <!-- /.container-fluid -->

        <!-- /#page-wrapper -->
        <form accept-charset="iso-8859-1" id="recarregarpaginaprincipalgestante" name="recarregarpaginaprincipalgestante" action="RecarregarPaginaGestante" method="POST" >
            <input type="hidden" value="${id}" id="id_usuario" name="id_usuario"/>
        </form>

        <form accept-charset="iso-8859-1" id="buscarsolicita" name="buscarsolicita" action="BuscarSolicitacoes" method="POST" >
            <input type="hidden" value="${id}" id="id_usuario" name="id_usuario"/>
        </form>

        <form accept-charset="iso-8859-1" id="buscarperfilpessoal" name="buscarperfilpessoal" action="BuscarPerfilPessoal" method="POST" >
            <input type="hidden" value="${id}" id="id_usuario" name="id_usuario"/>
        </form>

        <!-- /#wrapper -->

        <!-- jQuery -->
        <script src="js/jquery.js"></script>
        
        <script src="js/mascara_campos.js"></script>
        <script src="js/validar_datas.js"></script>
        <script src="js/jquery_validate.js"></script>
        <script src="js/bootstrap-datepicker.min.js"></script>
        <script src="js/bootstrap-datepicker.pt-BR.min.js"></script>
        <script src="js/locastyle.js"></script>
        <link href="css/bootstrap-datepicker.css" rel="stylesheet">

        <!-- Bootstrap Core JavaScript -->
        <script src="js/bootstrap.min.js"></script>

        <script src="js/postagem.js"></script>

        <!-- Morris Charts JavaScript -->
        <script src="js/plugins/morris/raphael.min.js"></script>
        <script src="js/plugins/morris/morris.min.js"></script>
        <script src="js/plugins/morris/morris-data.js"></script>






    </body>

</html>
