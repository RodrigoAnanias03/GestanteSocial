
<%@page import="Gestante_Social.dao.AdministradorDAO"%>
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


        <title>Mudar senha ADM</title>
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



    <body style="background-color: white">

        <jsp:include page="topo_adm.jsp" />

        <div class="col-md-3"></div>

        <div style="margin-top: 60px" class="col-md-6">
            <form action="MudarSenhaAdm" method="post" accept-charset="iso-8859-1">
                <label>Senha atual:</label>
                <input required="" autofocus="" type="password" class="form-control" id="senhaAtual" name="senhaAtual">
                <label>Nova Senha:</label>
                <input required="" type="password" autofocus="" class="form-control" id="senha" name="senha">

                <p align="left"><div class="progress">
                    <div id="barra_prog" class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 0%;"><span class="sr-only">60% Complete</span>
                    </div>
                </div></p>

                <label>Repita a Senha:</label>
                <input required="" type="password" autofocus="" class="form-control" id="conf_senha" name="conf_senha">
                <div id="tipo_senha" class="form-group">
                    
                </div>
                <label style="color: red">${msg}</label><br/>
                <button type="submit" class="btn btn-success" onclick="validarSenha(), validarQntSenha()">Enviar</button>
                <button class="btn btn-danger" type="reset">Limpar</button>
            </form>

        </div>
        <div class="col-md-3"></div>




        <!-- /.container-fluid -->

        <!-- /#page-wrapper -->



        <!-- /#wrapper -->

        <!-- jQuery -->
        <script src="js/jquery.js"></script>
        <script src="js/cad.js"></script>
        <script src="js/validar_datas.js"></script>
        <!-- Bootstrap Core JavaScript -->
        <script src="js/bootstrap.min.js"></script>

        <script src="js/postagem.js"></script>

        <!-- Morris Charts JavaScript -->
        <script src="js/plugins/morris/raphael.min.js"></script>
        <script src="js/plugins/morris/morris.min.js"></script>
        <script src="js/plugins/morris/morris-data.js"></script>






    </body>

</html>
