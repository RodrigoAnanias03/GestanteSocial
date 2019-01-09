<!DOCTYPE html>
<html lang="pt-br">

    <head>

        <meta charset="utf-8">
        <meta accept-charset="iso-8859-1">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Gestante Social</title>
        <link rel="shortcut icon" href="img/favicon.png">

        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/landing-page.css" rel="stylesheet">
        <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css">

        <style type="text/css">
            
            #voltatopo{
                height: 80px;
                width: 80px;
            }
            
            #voltatopo:hover, .active {
                height: 100px;
                width: 100px;
                transform: rotate(30deg);
            }
            
            #imgLogo:hover, .active{
                filter: opacity(20%);
            }
            
            .ft:hover, .active{
                filter: opacity(20%);
            }
            
            .men:hover, .active{
                font-size: 150%
            }
        </style>


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
                    <a class="navbar-brand topnav" href="#">Gestante Social</a>
                </div>
                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav navbar-right">
                        <li>
                            <a href="#" class="men">Home</a>
                        </li>
                        <li>
                            <a href="#oquee" class="men">O que é?</a>
                        </li>
                        <li>
                            <a href="#comousar" class="men">Como usar?</a>
                        </li>
                    </ul>
                </div>
                <!-- /.navbar-collapse -->
            </div>
            <!-- /.container -->
        </nav>

        <a name="about"></a>
        <div class="intro-header">
            <div class="container">

                <div class="row">
                    <div class="col-lg-12">
                        <div class="intro-message">
                            <center><img id="imgLogo" src="img/logo.png" class="img-responsive"/></center>
                            <hr class="intro-divider">
                            <h2><font color="black">Acessar como:</font></h2>
                            <ul class="list-inline intro-social-buttons">
                                <li>
                                    <a href="acesso_gestante.jsp" class="btn btn-default btn-lg"><i class="fa fa-female fa-fw"></i> <span class="network-name">Gestante</span></a>
                                </li>
                                <li>
                                    <a href="acesso_familiar.jsp" class="btn btn-default btn-lg"><i class="fa fa-group fa-fw"></i> <span class="network-name">Familiar</span></a>
                                </li>
                                <li>
                                    <a href="acesso_medico.jsp" class="btn btn-default btn-lg"><i class="fa fa-plus fa-fw"></i> <span class="network-name">Profissional</span></a>
                                </li>
                                <li>
                                    <a href="acesso_administrador.jsp" class="btn btn-default btn-lg"><i class="fa fa-unlock-alt fa-fw"></i> <span class="network-name">Administrador</span></a>
                                </li>
                            </ul>
                            <center><a href="http://www.facebook.com/sharer.php?u=http://170.78.231.26:4000/Gestante_Social" ><button style="width: 150px; margin-top: 50px" class="btn btn-primary"><i style="color: white" class="fa fa-facebook" > <b> Compartilhar</b></i></button></a></center>
                        </div>
                    </div>
                </div>


            </div>
            <!-- /.container -->

        </div>
        <!-- /.intro-header -->

        <!-- Page Content -->

        <a  name="services"></a>
        <div id="oquee" class="content-section-a">

            <div class="container">
                <div class="row">
                    <div class="col-lg-8">
                        <hr class="section-heading-spacer">
                        <div class="clearfix"></div>
                        <h2 class="section-heading">O que é o Gestante Social?</h2>
                        <p class="lead">Gestante Social é um site desenvolvido para que as gestantes possam compartilhar momentos
                            de sua gravidez com muito mais pessoas e ter a oportunidade de ajudar ou ser ajudada por outros integrantes
                            além de permitir uma comunicação em tempo real com médicos e especialistas.</p>
                    </div>
                    <div class="col-lg-4">
                        <center><img class="img-responsive ft" src="img/imgindex/ft1.png" alt=""></center>
                    </div>
                </div>

            </div>
            <!-- /.container -->

        </div>
        <!-- /.content-section-a -->

        <div id="comousar" class="content-section-b">

            <div class="container">

                <div class="row">
                    <div class="col-lg-4">
                        <center><img class="img-responsive ft" src="img/imgindex/ft2.png" alt=""></center>
                    </div>
                    <div class="col-lg-8">
                        <hr class="section-heading-spacer">
                        <div class="clearfix"></div>
                        <h2 class="section-heading">Como usar o sistema?</h2>
                        <p class="lead">Para você Gestante, basta selecionar o botão Gestante para acessar sua página específica e efetuar o cadastro.
                            Familiares poderão acessar somente com cadastro feito pela gestante. Médicos que queiram usar o sistema deverão
                            acessar <a href="cadastro_medico.jsp">este link</a>.</p>
                    </div>
                </div>

            </div>
            <!-- /.container -->

        </div>
        <!-- /.content-section-b -->
        <!-- /.content-section-a -->


        <!-- /.banner -->

        <!-- Footer -->
        <footer>
            <center>
                <div class="container">
                    <div class="row">
                        <div class="col-lg-12">
                            <ul class="list-inline">
                                <li>
                                    <a href="#">Home</a>
                                </li>
                                <li class="footer-menu-divider">&sdot;</li>
                                <li>
                                    <a href="contato.jsp">Contato</a>
                                </li>
                            </ul>
                            <p class="copyright text-muted small">Copyright &copy; Jonatas Henrique Teodoro & Rodrigo Ananias Souza 2017. Todos os direitos reservados</p>
                        </div>
                    </div>
                </div>
            </center>
        </footer>

        <div id="voltatopo" style="position: fixed; bottom: 0; right: 10px;">
            <a href="#"><img class="img-responsive"  src="img/voltartopo.png"></a>
        </div>

        <!-- jQuery -->
        <script src="js/jquery.js"></script>

        <!-- Bootstrap Core JavaScript -->
        <script src="js/bootstrap.min.js"></script>





    </body>

</html>
