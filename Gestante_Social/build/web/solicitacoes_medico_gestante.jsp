
<%@page import="Gestante_Social.model.Medico"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Gestante_Social.model.Familiar"%>
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


        <title>M�dicos</title>
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

    <jsp:include page="topo_gestante.jsp" />

    <body style="background-color: white">

        <div class="col-lg-8" >


            <div class="container-fluid" style="margin-top: 10px">

                





                



                <div class="col-md-12" style="margin: 0px; padding: 0">


                    <div class="panel panel-default">
                        <div class="panel-body">
                            <%
                                ArrayList<Medico> med = (ArrayList) request.getAttribute("medico");
                                for (Medico obj : med) {
                            %>
                            <div class="panel panel-default">
                                <div class="panel-body">

                                    <div class="col-md-1">
                                        <img style="border-radius: 30%; margin-right: 10px" id="blah" src="img/imgperfilmedico/<%=obj.getFoto()%>" alt="your image" width="50px" height="50px" />
                                    </div>
                                    <div class="col-md-11">
                                        <label><%=obj.getNome()%> <%=obj.getSobreNome()%></label>
                                        <label style="color: red"> - PEDIU SUA AMIZADE</label>
                                        
                                            <form accept-charset="iso-8859-1" action="AceitarAmizadeMedico" method="POST">
                                                <input type="hidden" id="loginMedico" name="loginMedico" value="<%=obj.getUsuario()%>">
                                                <button type="submit" class="btn btn-info">Aceitar Solicita��o</button>
                                            </form>
                                        
                                        
                                                <form style="margin-top: 5px" accept-charset="iso-8859-1" action="DesfazerAmizadeMedico" method="POST">
                                                <input type="hidden" id="loginMedico" name="loginMedico" value="<%=obj.getUsuario()%>">
                                                <button type="submit" class="btn btn-info">N�o aceitar Solicita��o</button>
                                            </form>
                                        

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
