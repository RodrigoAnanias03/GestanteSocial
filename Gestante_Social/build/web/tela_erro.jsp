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

        <title>Erro!</title>
        <link rel="shortcut icon" href="img/favicon.png">

        <link href="css/bootstrap.min.css" rel="stylesheet">


        <link href="css/sb-admin.css" rel="stylesheet">


        <link href="css/plugins/morris.css" rel="stylesheet">


        <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">






    </head>

    <body  style="background-color: white">

        <div class="col-md-12">
            <center><img src="img/Erro.png" />
            <h1>Erro!</h1>
            <h3 style="color: red">${erro}</h3>
            <h2>- Verifique se está logado no sistema</h2>
            <h2>- Verifique sua conexao com a internet</h2>
            <h2>- Tente efetuar o login novamente</h2>
            <h2>- Caso o erro persista entre em contato</h2>
            <a href="index.jsp"><button class="btn btn-default">Voltar para o início</button></a></center>
            
        </div>





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

