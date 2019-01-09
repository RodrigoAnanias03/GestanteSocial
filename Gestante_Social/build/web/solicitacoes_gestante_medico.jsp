
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
        
       <jsp:include page="topo_medico.jsp" />
       
       
        <div class="col-lg-8" >

            <div class="container-fluid" style="margin-top: 10px">
                <!--resultados-->
                
                        <%ArrayList<Usuario> usuarios = (ArrayList) request.getAttribute("usuarios");

                            for (Usuario obj : usuarios) {
                        %>

                <div class="col-md-12">
                    <div class="panel panel-default">
                        <div class="panel-body">

                            <div class="col-md-1">
                                <img style="border-radius: 30%; margin-right: 10px" id="blah" src="img/imgperfilusuario/<%=obj.getFoto()%>" alt="your image" width="50px" height="50px" />

                            </div>
                            <div class="col-md-11">
                                <form action="BuscarInfoAmigosMedico" method="POST" accept-charset="iso-8859-1">
                                    <input type="hidden" id="loginAmigo" name="loginAmigo" value="<%=obj.getLogin()%>"/>
                                    <label><%=obj.getNome() + " " + obj.getSobrenome()%></label>
                                    <br/><label><input type="submit" value="Ver Perfil"/></label>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <%}%>
                   
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
