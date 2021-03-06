
<%@page import="Gestante_Social.model.Contato"%>
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


        <title>Contatos</title>
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

                        
                        <%
                            Contato obj = (Contato) request.getAttribute("contato");

                        %>
                        <center><h1>Mensagem N�<%=obj.getId_contato()%></h1></center>
                        
                        <div style="margin-top: 5px" class="panel panel-default">


                            <div class="panel-body">

                                <div class="col-md-12">

                                    <p>Nome: <%=obj.getNome()%> <%=obj.getSobrenome()%></p>
                                    <p>CPF: <%=obj.getCpf()%></p>
                                    <p>Email: <%=obj.getEmail()%></p>
                                    <p>Assunto: <label style="color: red"><%=obj.getAssunto()%></label></p>
                                    <p>Mensagem: <b><%=obj.getMensagem()%></b><p>
                                </div>
                            </div>


                        </div>

                        <div style="margin-top: 5px" class="panel panel-default">


                            <div class="panel-body">

                                <div class="col-md-12">
                                    <h3>Responder</h3>
                                    
                                    <form id="formulario" action="ResponderContatoAdm" method="POST" accept-charset="iso-8859-1">
                                        <textarea id="texto" name="texto" class="form-control" rows="4" autofocus="" required=""></textarea>
                                        <input type="hidden" id="idContato" name="idContato" value="<%=obj.getId_contato()%>">
                                        <div class="col-md-2"><button type="submit" id="btnPublicar" onclick="responderContato()" style="margin-top: 3px; width: 100%" class="btn btn-success">Enviar</button></div>
                                    </form>
                                    
                                        <div class="col-md-2"><a href="IgnorarMsgAdm?id=<%=obj.getId_contato()%>"><button style="margin-top: 3px; width: 100%" class="btn btn-danger">Ignorar</button></a></div>
                                </div>
                            </div>





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
