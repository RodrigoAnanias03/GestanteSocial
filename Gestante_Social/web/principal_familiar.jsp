
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


        <title>Home</title>
        <link rel="shortcut icon" href="img/favicon.png">

        <!-- Bootstrap Core CSS -->




        <link href="css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom CSS -->
        <link href="css/sb-admin.css" rel="stylesheet">

        <!-- Morris Charts CSS -->
        <link href="css/plugins/morris.css" rel="stylesheet">

        <!-- Custom Fonts -->
        <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    </head>

    <jsp:include page="topo_familiar.jsp" />

    <body style="background-color: white">

        <div class="col-lg-8" >

            <div class="container-fluid" style="margin-top: 10px">



                <div class="col-md-12" style="margin: 0; padding: 0">
                    <center>${quantidade} amigos</center>

                    <div class="panel panel-default" >
                        <div class="panel-body">
                            <%
                                ArrayList<Usuario> usuarios = (ArrayList) request.getAttribute("usuarios");
                                for(Usuario obj : usuarios){
                            %>
                            <div class="col-md-3">
                                <div class="panel panel-default">
                                    <div class="panel-body">
                                        <center>
                                            <div style="margin-bottom: 5px" class="col-md-12">
                                                <img style="border-radius: 30%;" id="blah" src="img/imgperfilusuario/<%=obj.getFoto()%>" alt="your image" width="100px" height="100px" />
                                            </div>
                                            <p><label><%=obj.getNome()%> <%=obj.getSobrenome()%></label></p>
                                            <form style="margin-bottom: 4px" accept-charset="iso-8859-1" action="BuscarInfoGestantes" method="POST">
                                                <input type="hidden" id="loginAmigo" name="loginAmigo" value="<%=obj.getLogin()%>"/>
                                                <button class="btn btn-block" type="submit">Perfil</button>
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
