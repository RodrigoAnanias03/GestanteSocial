<!DOCTYPE html>
<html lang="pt-br">

    <head>

        <meta charset="utf-8">
        <meta accept-charset="iso-8859-1">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Contato</title>
        <link rel="shortcut icon" href="img/favicon.png">

        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/landing-page.css" rel="stylesheet">
        <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css">



    </head>

    <body>




        <!-- /.intro-header -->

        <!-- Page Content -->

        <a  name="services"></a>
        <div id="oquee" class="content-section-a">

            <div class="row">
                <div class="col-lg-3" style="padding-top: 20px">

                </div>
                <form id="formulario" action="EfetuarContato" method="post" accept-charset="iso-8859-1">
                    <div class="col-lg-6" style="padding-top: 20px">
                        <div class="panel panel-success">
                            <div class="panel-heading">
                                <center><h3 class="panel-title"><b>Contato</b></h3></center>
                            </div>
                            <div class="panel-body">
                                <div class="container-fluid">
                                    <div class="row">

                                        <div class="col-md-6 form-group">
                                            <p>*Nome:</p>
                                            <input required="" placeholder="Insira seu nome" type="text" autofocus="" id="nome" name="nome" class="form-control"/>
                                        </div>
                                        <div class="col-md-6 form-group">
                                            <p>*Sobrenome:</p>
                                            <input required="" placeholder="Insira seu sobrenome" type="text" id="sobrenome" name="sobrenome" class="form-control"/>
                                        </div>

                                        <div class="col-md-6">
                                            <div id="textocpf" class=" form-group">
                                                <p>*CPF:</p>
                                                <input required="" placeholder="Insira seu CPF" type="text" id="cpfUsuario" name="cpfUsuario" class="form-control"/>
                                            </div>
                                        </div>

                                        <div class="col-md-6 form-group">
                                            <p>*E-mail:</p>
                                            <input required="" placeholder="Insira seu e-mail" type="email" id="email" name="email" class="form-control"/>
                                        </div>

                                        <div class="col-md-12 form-group">
                                            <p>*Assunto:</p>
                                            <select id="assunto" name="assunto" class="form-control" required="">
                                                <option value="">Assunto...</option>
                                                <option value="Ativação de conta médica" >Ativação de conta médica</option>
                                                <option value="Erro do sistema" >Erro do sistema</option>
                                                <option value="Dúvidas" >Dúvidas</option>
                                                <option value="Outro" >Outro...</option>
                                            </select>
                                        </div>

                                        <div class="col-md-12 form-group">

                                            <p>*Mensagem:</p>
                                            <textarea maxlength="300" onkeyup="qntCaracteres()" id='mensagem' name="mensagem" class="form-control" rows="4" autofocus="" required=""></textarea>
                                            <p id="qntcara" align="right">0/300</p>

                                        </div>
                                        <center><button onclick="salvarContato()" id="botao" name="botao" type="submit" class="btn btn-success">Enviar</button></center>
                                    </div>



                                </div>
                            </div>

                        </div>
                    </div>
                </form>
                <div class="col-lg-3" style="padding-top: 20px">

                </div>
            </div>
            
            <%
                String retorno = String.valueOf(request.getAttribute("retorno"));
                if(retorno.equals("0")){
            %>


            <div class="modal fade" id="myModal" role="dialog">
                <div class="modal-dialog">

                    <!-- Modal content-->
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title">Contato efetuado com sucesso!</h4>
                        </div>
                        <div class="modal-body">
                            <p>Obrigado por entrar em contato!</p>
                            <p>Assim que respondido você receberá um e-mail!</p>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        </div>
                    </div>

                </div>
            </div>
            
            <%}else if(retorno.equals("1")){%>
            
            <div class="modal fade" id="myModal" role="dialog">
                <div class="modal-dialog">

                    <!-- Modal content-->
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title">Erro ao entrar em contato!!</h4>
                        </div>
                        <div class="modal-body">
                            <p>Ocorreu um erro durante o contato :(</p>
                            <p>Tente novamente mais tarde!</p>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        </div>
                    </div>

                </div>
            </div>
            
            <%}%>






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
        <script src="js/contato.js"></script>

        <script src="js/cad.js"></script>


        <script src="js/mascara_campos.js"></script>
        <script src="js/validar_campos.js"></script>
        <script src="js/jquery_validate.js"></script>
        <script src="js/bootstrap-datepicker.min.js"></script>
        <script src="js/bootstrap-datepicker.pt-BR.min.js"></script>
        <script src="js/locastyle.js"></script>
        <link href="css/bootstrap-datepicker.css" rel="stylesheet">

        <!-- Bootstrap Core JavaScript -->
        <script src="js/bootstrap.min.js"></script>

        <script src="js/plugins/morris/raphael.min.js"></script>
        <script src="js/plugins/morris/morris.min.js"></script>
        <script src="js/plugins/morris/morris-data.js"></script>



    </body>

</html>
