<!DOCTYPE html>
<html lang="pt-br">

    <head>

        <meta charset="utf-8">
        <meta accept-charset="iso-8859-1">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Novo Cadastro</title>
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
                <form role="form" action="cadastrarusuario" enctype="multipart/form-data" method="post" accept-charset="iso-8859-1">
                    <div class="col-lg-6" style="padding-top: 20px">
                        <div class="panel panel-success">
                            <div class="panel-heading">
                                <center><h3 class="panel-title"><b>Novo cadastro</b></h3></center>
                            </div>
                            <div class="panel-body">
                            </div>
                            <div class="container-fluid">
                                <div class="row">
                                    <div class="col-lg-12">
                                        <center>
                                            <div style="padding-bottom: 20px"><img style="border-radius: 30%;" id="blah" src="img/sem_imagem.jpg" alt="your image" width="200px" height="200px" /></div>
                                            <input required="" type='file' id="imagem" name="imagem" data-val="true" accept="image/png, image/jpeg" data-val-regex-pattern="^.*\.(jpg|JPG|jpeg|JPEG|png|PNG|bmp|BMP)$"
                                                   data-val-regex="Insira somente arquivos de foto"/>
                                            <hr/>
                                        </center>	
                                    </div>
                                    <div class="col-lg-6 text-center">
                                        <div class="form-group">
                                            <p align="left"><label>*Nome:</label></p>
                                            <input value="${nome}" maxlength="40" class="form-control" id="nomeUsuario" name="nomeUsuario" placeholder="Insira seu nome" required=""  autofocus="">
                                        </div>

                                    </div>
                                    <div class="col-lg-6 text-center">
                                        <div class="form-group">
                                            <p align="left"><label>*Sobrenome:</label></p>
                                            <input value="${sobrenome}" maxlength="50" class="form-control" id="sobrenomeUsuario" name="sobrenomeUsuario" required="" placeholder="Insira seu sobrenome">
                                        </div>
                                    </div>
                                    <div class="col-lg-6 text-center">
                                        <div id="textocpf" class="form-group">
                                            <p align="left"><label>*CPF:</label></p>
                                            <input value="${cpf}" class="form-control" id="cpfUsuario" name="cpfUsuario" placeholder="Insira seu CPF" required="">
                                        </div>

                                    </div>
                                    <div class="col-lg-6 text-center">
                                        <div class="form-group">
                                            <p align="left"><label>*Telefone:</label></p>
                                            <input value="${telefone}" class="form-control" onblur="validarTelefone()" id="telefoneUsuario" name="telefoneUsuario" placeholder="Insira seu telefone" required="">
                                        </div>
                                    </div>
                                    <div class="col-lg-12 text-center">
                                        <div class="form-group">
                                            <p align="left"><label>*Email:</label></p>
                                            <input value="${email}" maxlength="50" type="email" id="emailUsuario" name="emailUsuario" class="form-control" required="" placeholder="exeplo@email.com">
                                            <p align="left"><font color="red">${erroEmail}</font></p>
                                        </div>
                                        <div class="form-group">
                                            <p align="left"><label>*Data de início da gestação:</label></p>
                                            <input value="${gestacao}" class="form-control" id="gestacaoUsuario" name="gestacaoUsuario" required="" onblur="validarGestacao()" placeholder="Início da gestação">
                                        </div>
                                        <div class="form-group">	
                                            <p align="left"><label>*Data de nascimento</label></p>
                                            <input value="${nascimento}" placeholder="Insira sua data de nascimento" type="text" class="form-control" name="dataNascimento" id="dataNascimento" onblur="validarNascimento()" required="">
                                        </div>
                                    </div>
                                    <div class="col-lg-10 text-center">
                                        <div class="form-group">
                                            <p align="left"><label>*Cidade:</label></p>
                                            <input value="${cidade}" maxlength="50" class="form-control" id="cidadeUsuario" name="cidadeUsuario" required="" placeholder="Insira sua cidade">
                                        </div>
                                    </div>
                                    <div class="col-lg-2 text-center">
                                        <div class="form-group">
                                            <p align="left"><label>*Estado:</label></p>
                                            <input value="${estado}" maxlength="2" class="form-control" id="estadoUsuario" name="estadoUsuario" required="" placeholder="UF">
                                        </div>
                                    </div>
                                    <div class="col-lg-8 text-center">
                                        <div class="form-group">
                                            <p align="left"><label>*Rua:</label></p>
                                            <input value="${rua}" maxlength="50" class="form-control" id="ruaUsuario" name="ruaUsuario" required="" placeholder="Insira sua rua">
                                        </div>
                                    </div>
                                    <div class="col-lg-4 text-center">
                                        <div class="form-group">
                                            <p align="left"><label>*Nº:</label></p>
                                            <input value="${numero}" maxlength="15" class="form-control" id="numeroUsuario" name="numeroUsuario" required="" placeholder="Nº">
                                        </div>
                                    </div>
                                    <div class="col-lg-6 text-center">
                                        <div class="form-group">
                                            <p align="left"><label>*Bairro:</label></p>
                                            <input value="${bairro}" maxlength="50" class="form-control" id="numeroUsuario" name="bairroUsuario" required="" placeholder="Insira seu bairro">
                                        </div>
                                    </div>
                                    <div class="col-lg-6 text-center">
                                        <div class="form-group">
                                            <p align="left"><label>*CEP:</label></p>
                                            <input value="${cep}" class="form-control" id="cepUsuario" name="cepUsuario" required="" placeholder="Insira seu CEP">
                                        </div>
                                    </div>

                                    <div class="col-lg-12 text-center">
                                        <div class="form-group">
                                            <p align="left"><label>*Usuário:</label></p>
                                            <input value="${login}" maxlength="50" type="text" class="form-control" id="loginUsuario" name="loginUsuario" required="" placeholder="Usuário que deseja utilizar">
                                            <p align="left"><font color="red">${erroLogin}</font></p>
                                        </div>   
                                    </div>

                                    <div class="col-lg-6 text-center">
                                        <div class="form-group">
                                            <p align="left"><label>*Senha: (mínimo de 8 caracteres)</label></p>
                                            <input type="password" id="senha" name="senha" class="form-control" placeholder="Insira sua senha" required="" >
                                            <p align="left"><div class="progress">
                                                <div id="barra_prog" class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 0%;"><span class="sr-only">60% Complete</span>
                                                </div>
                                            </div></p>
                                        </div>
                                    </div>
                                    <div class="col-lg-6 text-center">
                                        <div class="form-group">
                                            <p align="left"><label>*Confirmar Senha:</label></p>
                                            <input id="conf_senha" name="conf_senha" type="password"  class="form-control" placeholder="Insira novamente sua senha" required="" >
                                        </div>
                                        <div id="tipo_senha" class="form-group">

                                        </div>
                                    </div>
                                    <input type="hidden" id="chave" value="0">
                                    <div class="col-lg-12 text-center">
                                        <div class="form-group">
                                            <p align="right">(*)Campos obrigatórios</p>
                                            <button type="submit" class="btn btn-default" onclick="validarSenha(), validarQntSenha(), validarCpf(), validarTelefone()">Cadastrar</button>
                                            <button type="reset" class="btn btn-default">Limpar</button>

                                        </div>
                                    </div>

                                </div>

                            </div>
                        </div>
                    </div>
                </form>
                <div class="col-lg-3" style="padding-top: 20px">

                </div>
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

        <script src="js/cad.js"></script>
        <script src="js/postagem.js"></script>
        <script src="js/validar_datas.js"></script>
        <script src="js/pegarimagem.js"></script>
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
