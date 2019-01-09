<!DOCTYPE html>
<html lang="pt-br">

    <head>

        <meta charset="utf-8">
        <meta accept-charset="iso-8859-1">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Recuperação de senha</title>
        <link rel="shortcut icon" href="img/favicon.png">

        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/landing-page.css" rel="stylesheet">
        <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css">


    </head>

    <body>
        <nav class="navbar navbar-default navbar-fixed-top topnav" role="navigation">
            <div class="container topnav">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand topnav" href="#">Cadastro realizado</a>
                </div>
                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav navbar-right">
                        <li>
                            <a href="index.jsp">Home</a>
                        </li>
                        <li>
                            <a href="acesso_gestante.jsp">Acessar Gestante</a>
                        </li>
                    </ul>
                </div>
                <!-- /.navbar-collapse -->
            </div>
            <!-- /.container -->
        </nav>
        <a  name="services"></a>
        <div id="oquee" class="content-section-a">


            <div class="container">

                <div class="row">
                    <div class="col-lg-2" style="padding-top: 20px"></div>
                    <div class="col-lg-8" style="padding-top: 20px">
                        <div class="panel panel-default">
                            <div class="panel-body">
                                <div class="form-group">
                                    <form role="form" action="recuperarsenha" method="post">
                                        <h3><font color="red">Para recuperar os dados preencha o campo!</font></h3></center>
                                        <p align="left"><label>Email:</label></p>
                                        <input class="form-control" id="emailUsuario" name="emailUsuario" placeholder="Insira seu Email" autofocus="">
                                        <p align="left"><font color="red">${mensagem}</font></p>
                                        <button type="submit" class="btn btn-default">Avançar</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-2" style="padding-top: 20px"></div>
            </div>

        </div>
        <!-- /.container -->

    </div>

</div>
<footer>
    <center>
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <ul class="list-inline">
                        <li>
                            <a href="index.jsp">Home</a>
                        </li>
                        <li class="footer-menu-divider">&sdot;</li>
                        <li>
                            <a href="#about">Contato</a>
                        </li>
                    </ul>
                    <p class="copyright text-muted small">Copyright &copy; Jonatas Henrique Teodoro & Rodrigo Ananias Souza 2017. Todos os direitos reservados</p>
                </div>
            </div>
        </div>
    </center>
</footer>

<!-- jQuery -->
<script src="js/jquery.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="js/bootstrap.min.js"></script>

</body>

</html>
