
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="Gestante_Social.model.Familiar"%>
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


        <title>Home</title>
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

    <jsp:include page="topo_medico.jsp" />

    <body style="background-color: white">

        <div class="col-lg-8" >

            <div class="container-fluid" style="margin-top: 10px">



                <div class="col-md-12" style="margin: 0; padding: 0">
                    <%

                        Usuario obj = (Usuario) request.getAttribute("usuario");

                    %>


                    <div class="panel panel-default" >
                        <div class="panel-body">


                            <div class="container-fluid">
                                <div class="row">
                                    <div class="col-lg-12">
                                        <center>
                                            <div style="padding-bottom: 20px"><img style="border-radius: 30%;" id="blah" src="img/imgperfilusuario/<%=obj.getFoto()%>" alt="your image" width="200px" height="200px" /></div>

                                            <hr/>
                                        </center>	
                                    </div>
                                    <div class="col-lg-6 text-center">
                                        <div class="form-group">
                                            <p align="left"><label>Nome:</label></p>
                                            <input readonly="" value="<%=obj.getNome()%>" class="form-control" id="nomeUsuario" name="nomeUsuario" placeholder="Insira seu nome" required="" >
                                        </div>
                                        <div id="textocpf" class="form-group">
                                            <p align="left"><label>CPF:</label></p>
                                            <input readonly="" value="<%=obj.getCpf()%>" class="form-control" id="cpfUsuario" name="cpfUsuario" placeholder="Insira seu CPF" required="">
                                        </div>
                                    </div>
                                    <div class="col-lg-6 text-center">
                                        <div class="form-group">
                                            <p align="left"><label>Sobrenome:</label></p>
                                            <input readonly="" value="<%=obj.getSobrenome()%>" class="form-control" id="sobrenomeUsuario" name="sobrenomeUsuario" required="" placeholder="Insira seu sobrenome">
                                        </div>
                                        <div class="form-group">
                                            <p align="left"><label>Telefone:</label></p>
                                            <input readonly="" value="<%=obj.getTelefone()%>" class="form-control" id="telefoneUsuario" name="telefoneUsuario" placeholder="Insira seu telefone" required="">
                                        </div>
                                    </div>
                                    <div class="col-lg-12 text-center">
                                        <div class="form-group">
                                            <p align="left"><label>Email:</label></p>
                                            <input readonly="" value="<%=obj.getEmail()%>" type="email" id="emailUsuario" name="emailUsuario" class="form-control" required="" placeholder="exeplo@email.com">
                                            <p align="left"><font color="red">${erroEmail}</font></p>
                                        </div>
                                        <div class="form-group">
                                            <p align="left"><label>Data de início da gestação:</label></p>

                                            <%
                                                String dataInicioGestacao = null;
                                                if (obj.getInicioGestacao() != null) {

                                                    SimpleDateFormat formatoNormal = new SimpleDateFormat("dd/MM/yyyy");

                                                    dataInicioGestacao = formatoNormal.format(obj.getInicioGestacao());
                                                }

                                            %>


                                            <%if (obj.getInicioGestacao() != null) {%>
                                            <input readonly="" value="<%=dataInicioGestacao%>" class="form-control" id="gestacaoUsuario" name="gestacaoUsuario" required="" onblur="validarGestacao()" placeholder="Início da gestação">
                                            <%} else {%>
                                            <input readonly="" value="" class="form-control" id="gestacaoUsuario" name="gestacaoUsuario" required="" onblur="validarGestacao()" placeholder="Não cadastrado">
                                            <%}%>
                                        </div>
                                        <div class="form-group">	
                                            <p align="left"><label>Data de nascimento</label></p>

                                            <%

                                                SimpleDateFormat Normal = new SimpleDateFormat("dd/MM/yyyy");
                                                String dataNascimento = null;
                                                dataNascimento = Normal.format(obj.getDataNascimento());

                                            %>

                                            <input readonly="" value="<%=dataNascimento%>" placeholder="Insira sua data de nascimento" type="text" class="form-control" name="dataNascimento" id="dataNascimento" onblur="validarNascimento()" required="">
                                        </div>
                                    </div>
                                    <div class="col-lg-10 text-center">
                                        <div class="form-group">
                                            <p align="left"><label>Cidade:</label></p>
                                            <input readonly="" value="<%=obj.getCidade()%>" class="form-control" id="cidadeUsuario" name="cidadeUsuario" required="" placeholder="Insira sua cidade">
                                        </div>
                                    </div>
                                    <div class="col-lg-2 text-center">
                                        <div class="form-group">
                                            <p align="left"><label>Estado:</label></p>
                                            <input readonly="" value="<%=obj.getEstado()%>" class="form-control" id="estadoUsuario" name="estadoUsuario" required="" placeholder="UF">
                                        </div>
                                    </div>
                                    <div class="col-lg-8 text-center">
                                        <div class="form-group">
                                            <p align="left"><label>Rua:</label></p>
                                            <input readonly="" value="<%=obj.getRua()%>" class="form-control" id="ruaUsuario" name="ruaUsuario" required="" placeholder="Insira sua rua">
                                        </div>
                                    </div>
                                    <div class="col-lg-4 text-center">
                                        <div class="form-group">
                                            <p align="left"><label>Nº:</label></p>
                                            <input readonly="" value="<%=obj.getNumero()%>" class="form-control" id="numeroUsuario" name="numeroUsuario" required="" placeholder="Nº">
                                        </div>
                                    </div>
                                    <div class="col-lg-6 text-center">
                                        <div class="form-group">
                                            <p align="left"><label>Bairro:</label></p>
                                            <input readonly="" value="<%=obj.getBairro()%>" class="form-control" id="numeroUsuario" name="bairroUsuario" required="" placeholder="Insira seu bairro">
                                        </div>
                                    </div>
                                    <div class="col-lg-6 text-center">
                                        <div class="form-group">
                                            <p align="left"><label>CEP:</label></p>
                                            <input readonly="" value="<%=obj.getCep()%>" class="form-control" id="cepUsuario" name="cepUsuario" required="" placeholder="Insira seu CEP">
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
        <script src="js/editar_perfilusuario.js"></script>
        <script src="js/cad.js"></script>
        <!-- Bootstrap Core JavaScript -->
        <script src="js/bootstrap.min.js"></script>

        <script src="js/postagem.js"></script>
        <script src="js/pegarimagem.js"></script>



        <!-- Morris Charts JavaScript -->
        <script src="js/plugins/morris/raphael.min.js"></script>
        <script src="js/plugins/morris/morris.min.js"></script>
        <script src="js/plugins/morris/morris-data.js"></script>






    </body>

</html>
