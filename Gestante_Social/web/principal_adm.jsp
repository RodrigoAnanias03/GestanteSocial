
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


        <title>ADM</title>
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

        <div class="col-md-3" style="margin-top: 60px">
            <center>
                <h1>Modificar Perfil</h1>
                <a href="buscar_gestante_adm.jsp"><button style="margin: 5px; width: 70%" class="btn btn-default form-inline">Gestante</button></a><br/>
                <a href="buscar_familiar_adm.jsp"><button style="margin: 5px; width: 70%" class="btn btn-default form-inline">Familiar</button></a><br/>
                <a href="buscar_medico_adm.jsp"><button style="margin: 5px; width: 70%" class="btn btn-default form-inline">Profissional</button></a>
            </center>
        </div>
        <div class="col-md-6" style="margin-top: 60px">
            <center>
                <h1 style="font-family: cursive">Administração GS</h1>
                <%
                    AdministradorDAO bd;
                    bd = new AdministradorDAO();
                    int qntGestante = bd.quantidadeGestante();
                    bd = new AdministradorDAO();
                    int qntProfissionais = bd.quantidadeProfissionais();
                    bd = new AdministradorDAO();
                    int qntFamiliares = bd.quantidadeFamiliares();
                %>
                <img height="200px" src="img/logo.png"/><br/>
                <h1>Usuários</h1><br/>
                <h3><%=qntGestante%> Gestantes Ativas</h3>
                <h3><%=qntProfissionais%> Profissionais Ativos</h3>
                <h3><%=qntFamiliares%> Familiares Ativos</h3>


            </center>
        </div>
        <div class="col-md-3" style="margin-top: 60px">
            <center>
                <h1>Mensagens</h1>
                <%
                    bd = new AdministradorDAO();
                    int qntAtivacao = bd.msgAtivacao();
                    bd = new AdministradorDAO();
                    int qntDuvida = bd.msgDuvida();
                    bd = new AdministradorDAO();
                    int qntErro = bd.msgErro();
                    bd = new AdministradorDAO();
                    int qntOutro = bd.msgOutro();
                    
                
                %>
                <a href="BuscarContatos?assunto=1"><button style="margin: 5px; width: 70%" class="btn btn-default form-inline"><%=qntAtivacao%> Ativação de conta médica</button></a><br/>
                <a href="BuscarContatos?assunto=2"><button style="margin: 5px; width: 70%" class="btn btn-default form-inline"><%=qntErro%> Erro do sistema</button></a><br/>
                <a href="BuscarContatos?assunto=3"><button style="margin: 5px; width: 70%" class="btn btn-default form-inline"><%=qntDuvida%> Dúvidas</button></a>
                <a href="BuscarContatos?assunto=4"><button style="margin: 5px; width: 70%" class="btn btn-default form-inline"><%=qntOutro%> Outro...</button></a>
            </center>
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
