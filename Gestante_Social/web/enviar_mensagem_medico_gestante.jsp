
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

        <title>Enviar Mensagem</title>
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

        <%
            Usuario amigo = (Usuario) request.getAttribute("amigo");
        %>


        <div class="col-lg-8" >

            <div class="container-fluid" style="margin-top: 10px">

                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="col-md-1">
                            <img style="border-radius: 30%; margin-right: 10px" id="blah" src="img/imgperfilusuario/<%=amigo.getFoto()%>" alt="your image" width="50px" height="50px" />
                        </div>
                        <div class="col-md-11">
                            <p><label><%=amigo.getNome()%> <%=amigo.getSobrenome()%></label></p>
                        </div>
                        <div style="margin-top: 5px" class="col-md-12">
                            <form action="EnviarMensagemMedicoUsuarios" accept-charset="iso-8859-1" method="POST">
                                <input type="hidden" id="loginAmigo" name="loginAmigo" value="<%=amigo.getLogin()%>" />
                                <textarea id="mensagem" name="mensagem" required="" rows="5" class="form-control"></textarea>
                                <button style="margin-top: 5px" class="btn btn-success">Enviar</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- /.container-fluid -->

        <!-- /#page-wrapper -->

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
