<!DOCTYPE html>
<html lang="pt-br">

    <head>

        <meta charset="utf-8">
        <meta accept-charset="iso-8859-1">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Profissional</title>
        <link rel="shortcut icon" href="img/favicon.png">

        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/landing-page.css" rel="stylesheet">
        <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css">


    </head>

    <body>


        <nav class="navbar navbar-inverse navbar-fixed-top">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#">Acesso à profissionais</a>
                </div>
                <div id="navbar" class="navbar-collapse collapse">
                    <form class="navbar-form navbar-right" action="EfetuarLoginMedico" method="POST">
                        <div class="form-group">
                            <input type="text" id="usuario" name="usuario" placeholder="Usuário" class="form-control" autofocus="" required="">
                        </div>
                        <div class="form-group">
                            <input type="password" id="senha" name="senha" placeholder="Senha" class="form-control" required="">
                        </div>
                        <button type="submit" class="btn btn-success">Entrar</button>
                        <a href="cadastro_medico.jsp"><button type="button" class="btn btn-default">Cadastrar</button></a>
                        <div><a href="recup_senha_medico.jsp">Esqueci minha senha</a></div>
                    </form>
                </div>

                <!--/.navbar-collapse -->
            </div>

        </nav>
        <%
            String info = (String) request.getAttribute("info");
            if (info != null) {
        %>
        <div id="oquee" class="content-section-a">


            <div class="container">

                <div style="padding-top: 25px" class="row">
                    <div class="alert alert-danger" role="alert">
                        <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                        <span class="sr-only">Erro:</span>
                        ${info}
                    </div>
                </div>
            </div>

        </div>
        <%}%>



        <!-- /.intro-header -->

        <!-- Page Content -->

        <a  name="services"></a>
        <div id="oquee" class="content-section-a" style="
             top:0;
             left:0;
             width:100%;
             height:100%;">


            <div class="container">

                <div class="row">
                    <div class="col-lg-8" style="padding-top: 20px">
                        <hr class="section-heading-spacer">
                        <div class="clearfix"></div>
                        <h2 class="section-heading">Acesso à profissionais</h2>
                        <p class="lead">Através desta página o profissional será capaz de auxiliar e acompanhar
                            o andamento da gestação a fim de obter uma comuniação mais rápida
                            e eficiente, responder dúvidas e aconselhar suas pacientes.</p>
                    </div>
                    <div class="col-lg-4">
                        <center><img class="img-responsive" src="img/imgacessomedico/medico.png" style="padding-top: 40px" alt=""></center>
                    </div>
                </div>

            </div>
            <!-- /.container -->

        </div>

        <footer style="position: fixed; bottom: 0; right: 0; left: 0">
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
                                    <a href="contato.jsp">Contato</a>
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
