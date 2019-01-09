
<%@page import="Gestante_Social.model.Medico"%>
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


        <title>Gestante</title>
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
        <jsp:include page="topo_adm.jsp" />

        <div class="col-lg-2" ></div>
        
        <input type="hidden" id="mensagem" name="mensagem" value="${msg}">

        <div class="col-lg-8" >


            <div class="container-fluid" style="margin-top: 13px">

                <div class="panel panel-default">
                    <div class="panel-body">
                        <form id="formulario" action="BuscarGestanteAdm" method="post" accept-charset="iso-8859-1">
                            <div class="col-md-5">
                                <label>Nome:</label>
                                <input autofocus="" class="form-control" type="text" minlength="4" id="nomeUsuario" name="nomeUsuario">
                            </div>
                            <div class="col-md-4">
                                <label>Email:</label>
                                <input class="form-control" type="email" id="emailUsuario" name="emailUsuario">
                            </div>
                            <div class="col-md-3">
                                <label>CPF:</label>
                                <input class="form-control" type="text" id="cpfUsuario" name="cpfUsuario">
                            </div>
                            <div class="col-md-12" style="margin-top: 5px">
                                <button type="button" onclick="verificarCamposVazios()" class="btn btn-success">Buscar</button>
                                <button type="reset" class="btn btn-danger">Limpar</button>
                            </div>
                        </form>
                    </div>
                </div>

                <div class="panel panel-default">
                    <div class="panel-body">


                        <%
                            ArrayList<Usuario> usuarios = (ArrayList) request.getAttribute("usuarios");
                            if (usuarios != null) {
                                for (Usuario obj : usuarios) {

                        %>
                        <div style="margin-top: 5px" class="panel panel-default">


                            <div class="panel-body">

                                <div class="col-md-12">
                                    <div class="col-md-2">
                                        <img style="border-radius: 30%; margin-right: 10px" id="blah" src="img/imgperfilusuario/<%=obj.getFoto()%>" alt="your image" width="100px" height="100px" />
                                    </div>
                                    <div class="col-md-10">



                                        <p>Nome: <%=obj.getNome()%> <%=obj.getSobrenome()%></p>
                                        <p>Email: <%=obj.getEmail()%></p>
                                        <p>CPF: <%=obj.getCpf()%></p>



                                        <%
                                            if (obj.getEstado().equals("true")) {
                                        %>
                                        <form id="lermsg" accept-charset="iso-8859-1" action="VerDadosGestanteAdm" method="POST">
                                            <input type="hidden" id="idUsuario" name="idUsuario" value="<%=obj.getIdUsuario()%>" />
                                            <label style="color: blue">ATIVO</label>
                                            <button  style="margin-right: 5px">Ver</button>
                                        </form>
                                        <%} else {%>
                                        <form id="lermsg" accept-charset="iso-8859-1" action="AtivarGestanteAdm" method="POST">
                                            <input type="hidden" id="idUsuario" name="idUsuario" value="<%=obj.getIdUsuario()%>" />
                                            <label style="color: red">INATIVO</label>
                                            <button  style="margin-right: 5px">Ativar</button>
                                        </form>
                                        <%}%>

                                    </div>
                                </div>
                            </div>
                        </div>


                        <%}%>
                        <%}%>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-lg-2" ></div>
        <!-- /.container-fluid -->


        <!-- /#wrapper -->

        <!-- jQuery -->
        <script src="js/jquery.js"></script>
        <script src="js/mascara_campos.js"></script>
        <script src="js/pesquisaGest.js"></script>
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
