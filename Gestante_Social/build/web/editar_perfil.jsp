

<%@page import="java.text.SimpleDateFormat"%>
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



        <title>Editar Perfil</title>
        <link rel="shortcut icon" href="img/favicon.png">

        <!-- Bootstrap Core CSS -->




        <link href="css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom CSS -->
        <link href="css/sb-admin.css" rel="stylesheet">

        <!-- Morris Charts CSS -->
        <link href="css/plugins/morris.css" rel="stylesheet">

        <!-- Custom Fonts -->
        <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <script src="js/buscarpaginas.js"></script>
        <script src="js/carregarposgestantes.js"></script>

        <script>
            function desativarConta() {
                alert("Conta desativada!\nPara ativar novamente basta efetuar login!");
            }
        </script>


    </head>

    <body style="background-color: white">

        <jsp:include page="topo_gestante.jsp" />

        <%
            Usuario obj = (Usuario) request.getAttribute("Usuario");
        %>
        <div class="col-lg-8" >

            <div class="container-fluid" style="margin-top: 10px">



                <div class="col-md-12" style="margin: 0; padding: 0">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="col-lg-12" style="padding-top: 20px">
                                <div class="panel panel-success">
                                    <div class="panel-heading">
                                        <center><h3 class="panel-title"><b>Editar cadastro</b></h3></center>
                                    </div>
                                    <div class="panel-body">
                                    </div>
                                    <div class="container-fluid">
                                        <div class="row">
                                            <form role="form" id="formulario" action="EditarCadastro" enctype="multipart/form-data" method="post" accept-charset="iso-8859-1">
                                                <div class="col-lg-12">

                                                    <center>

                                                        <div style="padding-bottom: 20px"><img style="border-radius: 30%;" id="blah" src="img/imgperfilusuario/<%=obj.getFoto()%>" alt="your image" width="200px" height="200px" /></div>
                                                        <input type='file' id="imagem" name="imagem" data-val="true" accept="image/png, image/jpeg" data-val-regex-pattern="^.*\.(jpg|JPG|jpeg|JPEG|png|PNG|bmp|BMP)$"
                                                               data-val-regex="Insira somente arquivos de foto"/>
                                                        <hr/>
                                                    </center>	
                                                </div>
                                                <div class="col-lg-6 text-center">

                                                    <div class="form-group">
                                                        <p align="left"><label>*Nome:</label></p>
                                                        <input value="<%=obj.getNome()%>" class="form-control" id="nomeUsuario" name="nomeUsuario" placeholder="Insira seu nome" required="">
                                                    </div>
                                                    <div id="textocpf" class="form-group">
                                                        <p align="left"><label>*CPF:</label></p>
                                                        <input disabled="" value="<%=obj.getCpf()%>" class="form-control" id="cpfUsuario" name="cpfUsuario" placeholder="Insira seu CPF" required="">
                                                    </div>
                                                </div>
                                                <div class="col-lg-6 text-center">
                                                    <div class="form-group">
                                                        <p align="left"><label>*Sobrenome:</label></p>
                                                        <input value="<%=obj.getSobrenome()%>" class="form-control" id="sobrenomeUsuario" name="sobrenomeUsuario" required="" placeholder="Insira seu sobrenome">
                                                    </div>
                                                    <div class="form-group">
                                                        <p align="left"><label>*Telefone:</label></p>
                                                        <input value="<%=obj.getTelefone()%>" class="form-control" id="telefoneUsuario" name="telefoneUsuario" placeholder="Insira seu telefone" required="">
                                                    </div>
                                                </div>
                                                <div class="col-lg-12 text-center">
                                                    <div class="form-group">
                                                        <p align="left"><label>*Email:</label></p>
                                                        <input disabled="" value="<%=obj.getEmail()%>" type="email" id="emailUsuario" name="emailUsuario" class="form-control" required="" placeholder="exeplo@email.com">

                                                    </div>
                                                    <div class="form-group">
                                                        <p align="left"><label>*Usuário:</label></p>
                                                        <input disabled="" value="<%=obj.getLogin()%>" type="text" class="form-control" id="loginUsuario" name="loginUsuario" required="" placeholder="Usuário que deseja utilizar">

                                                    </div>
                                                    <%
                                                        SimpleDateFormat formatoDesejado = new SimpleDateFormat("dd/MM/yyyy");
                                                        String dataFormatada = null;
                                                        dataFormatada = formatoDesejado.format(obj.getDataNascimento());

                                                    %>
                                                    <div class="form-group">	
                                                        <p align="left"><label>*Data de nascimento</label></p>
                                                        <input disabled="" value="<%=dataFormatada%>" type="text" class="form-control" required="">
                                                    </div>
                                                </div>
                                                <div class="col-lg-10 text-center">
                                                    <div class="form-group">
                                                        <p align="left"><label>*Cidade:</label></p>
                                                        <input value="<%=obj.getCidade()%>" class="form-control" id="cidadeUsuario" name="cidadeUsuario" required="" placeholder="Insira sua cidade">
                                                    </div>
                                                </div>
                                                <div class="col-lg-2 text-center">
                                                    <div class="form-group">
                                                        <p align="left"><label>*Estado:</label></p>
                                                        <input value="<%=obj.getEstado()%>" class="form-control" id="estadoUsuario" name="estadoUsuario" required="" placeholder="UF">
                                                    </div>
                                                </div>
                                                <div class="col-lg-8 text-center">
                                                    <div class="form-group">
                                                        <p align="left"><label>*Rua:</label></p>
                                                        <input value="<%=obj.getRua()%>" class="form-control" id="ruaUsuario" name="ruaUsuario" required="" placeholder="Insira sua rua">
                                                    </div>
                                                </div>
                                                <div class="col-lg-4 text-center">
                                                    <div class="form-group">
                                                        <p align="left"><label>*Nº:</label></p>
                                                        <input value="<%=obj.getNumero()%>" class="form-control" id="numeroUsuario" name="numeroUsuario" required="" placeholder="Nº">
                                                    </div>
                                                </div>
                                                <div class="col-lg-6 text-center">
                                                    <div class="form-group">
                                                        <p align="left"><label>*Bairro:</label></p>
                                                        <input value="<%=obj.getBairro()%>" class="form-control" id="numeroUsuario" name="bairroUsuario" required="" placeholder="Insira seu bairro">
                                                    </div>
                                                </div>
                                                <div class="col-lg-6 text-center">
                                                    <div class="form-group">
                                                        <p align="left"><label>*CEP:</label></p>
                                                        <input value="<%=obj.getCep()%>" class="form-control" id="cepUsuario" name="cepUsuario" required="" placeholder="Insira seu CEP">
                                                    </div>
                                                </div>
                                                <div class="col-lg-12 text-center">
                                                    <%
                                                        String dataGestacaoFormatada = null;
                                                        if (obj.getInicioGestacao() != null) {
                                                            SimpleDateFormat formatoNormal = new SimpleDateFormat("dd/MM/yyyy");
                                                            
                                                            dataGestacaoFormatada = formatoDesejado.format(obj.getInicioGestacao());
                                                        
                                                    %>
                                                    <div class="form-group">
                                                        <p align="left"><label>*Data de início da gestação:</label></p>
                                                        <input disabled="" value="<%=dataGestacaoFormatada%>" class="form-control" id="gestacaoUsuario" name="gestacaoUsuario" required="" onblur="validarGestacao()" placeholder="Início da gestação">
                                                    </div>
                                                    <%}else{%>
                                                        <div class="form-group">
                                                        <p align="left"><label>*Data de início da gestação:</label></p>
                                                        <input disabled="" value="" class="form-control" id="gestacaoUsuario" name="gestacaoUsuario" required="" onblur="validarGestacao()" placeholder="Não cadastrado">
                                                    </div>
                                                    <%}%>
                                                </div>



                                                <div class="col-lg-12 text-center">
                                                    <div class="form-group">
                                                        <p align="right">(*)Campos obrigatórios</p>

                                                        <button type="submit" id="botaoSalvar" class="btn btn-default" onclick="salvarDados()">Salvar</button>
                                                        <a href="DesativarContaGestante"><button type="button" class="btn btn-info" onclick="desativarConta()">Desativar conta</button></a>

                                                    </div>
                                                </div>
                                            </form>
                                            <div class="col-lg-12">
                                                <center><button style="margin-bottom: 5px" type="button" class="btn btn-danger" data-toggle="collapse" data-target="#demo">Alterar senha</button></center>
                                                <div id="demo" class="collapse">
                                                    <form method="POST" id="alterar_senha" name="alterar_senha" action="EditarSenhaUsuario" accept-charset="iso-8859-1">
                                                        <div class="col-lg-2">
                                                        </div>
                                                        <div class="col-lg-8">
                                                            <div class="form-group">
                                                                <p align="left"><label>*Senha Atual:</label></p>
                                                                <input type="hidden" value="${cod_senha}" id="cod_senha" name="cod_senha"/>
                                                                <input type="hidden" value="${id}" id="id_usuario" name="id_usuario"/>
                                                                <input style="margin-top: 5px" type="password" id="senhaAtual" name="senhaAtual" class="form-control" placeholder="Insira sua senha atual" required="" >
                                                            </div>
                                                            <div class="form-group">
                                                                <p align="left"><label>*Nova Senha:</label></p>
                                                                <input style="margin-top: 5px" type="password" id="senha" name="senha" class="form-control" placeholder="Insira sua nova senha" required="" >
                                                                <p align="left"><div class="progress">
                                                                    <div id="barra_prog" class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 0%;"><span class="sr-only">60% Complete</span>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="form-group">
                                                                <p align="left"><label>*Repita a senha:</label></p>
                                                                <input style="margin-top: 5px" type="password" id="repete" name="repete" class="form-control" placeholder="Insira novamente sua senha" required="" >
                                                                <label id="mensagem_senha" style="color: red; font-size: 150%;">${mensagem_senha}</label>
                                                            </div>
                                                            <button type="button" style="margin-bottom: 5px" onclick="verificarSenhas()" class="btn btn-default">Alterar</button>

                                                        </div>
                                                        <div class="col-lg-2">
                                                        </div>

                                                    </form>

                                                </div>
                                            </div>


                                        </div>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>


            </div>
        </div>
        <!-- /.container-fluid -->

        <!-- /#page-wrapper -->

        <!-- /#wrapper -->

        <!-- jQuery -->
        <script src="js/jquery.js"></script>

        <!-- Bootstrap Core JavaScript -->
        <script src="js/bootstrap.min.js"></script>
        <script src="js/cad.js"></script>
        <script src="js/validar_datas.js"></script>
        <script src="js/pegarimagem.js"></script>
        <script src="js/mascara_campos.js"></script>
        <script src="js/validar_campos.js"></script>
        <script src="js/jquery_validate.js"></script>
        <script src="js/bootstrap-datepicker.min.js"></script>
        <script src="js/bootstrap-datepicker.pt-BR.min.js"></script>
        <script src="js/locastyle.js"></script>
        <script src="js/editar_perfilusuario.js"></script>
        <link href="css/bootstrap-datepicker.css" rel="stylesheet">

        <!-- Morris Charts JavaScript -->
        <script src="js/plugins/morris/raphael.min.js"></script>
        <script src="js/plugins/morris/morris.min.js"></script>
        <script src="js/plugins/morris/morris-data.js"></script>






    </body>

</html>
