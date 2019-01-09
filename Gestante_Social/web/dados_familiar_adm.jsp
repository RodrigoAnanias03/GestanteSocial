
<%@page import="Gestante_Social.model.Familiar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="Gestante_Social.model.Medico"%>
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


        <title>Familiar</title>
        <link rel="shortcut icon" href="img/favicon.png">

        <!-- Bootstrap Core CSS -->




        <link href="css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom CSS -->
        <link href="css/sb-admin.css" rel="stylesheet">

        <!-- Morris Charts CSS -->
        <link href="css/plugins/morris.css" rel="stylesheet">

        <!-- Custom Fonts -->
        <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.min.js" type="text/javascript"></script>
        <script src="js/buscarpaginas.js"></script>
        <script src="js/carregarposgestantes.js"></script>

    </head>

    <body style="background-color: white">
        <jsp:include page="topo_adm.jsp" />

        <div class="col-lg-2" ></div>

        <div class="col-lg-8" >

            <div class="container-fluid" style="margin-top: 10px">



                <div class="col-md-12" style="margin: 0; padding: 0">
                    <%

                        Familiar obj = (Familiar) request.getAttribute("Familiar");

                    %>


                    <div class="panel panel-default" >
                        <div class="panel-body">
                            <form role="form" id="formulario" action="EditarCadastroFamiliarAdm" method="post" accept-charset="iso-8859-1">
                                <div class="row">
                                    <div class="col-lg-12">
                                        <center>
                                            <div style="padding-bottom: 20px"><img style="border-radius: 30%;" id="blah" src="img/imgperfilfamiliar/<%=obj.getFoto()%>" alt="your image" width="200px" height="200px" /></div>
                                            <hr/>
                                        </center>	
                                    </div>
                                    <div class="col-lg-6 text-center">
                                        <div class="form-group">
                                            <p align="left"><label>*Nome:</label></p>
                                            <input value="<%=obj.getNome()%>" class="form-control" id="nomeUsuario" name="nomeUsuario" placeholder="Insira seu nome" required=""  autofocus="">
                                        </div>
                                        <div id="textocpf" class="form-group">
                                            <p align="left"><label>*CPF:</label></p>
                                            <input value="<%=obj.getCpf()%>" class="form-control" id="cpfUsuario" name="cpfUsuario" placeholder="Insira seu CPF" required="">
                                        </div>
                                    </div>
                                    <div class="col-lg-6 text-center">
                                        <div class="form-group">
                                            <p align="left"><label>*Sobrenome:</label></p>
                                            <input value="<%=obj.getSobreNome()%>" class="form-control" id="sobrenomeUsuario" name="sobrenomeUsuario" required="" placeholder="Insira seu sobrenome">
                                        </div>
                                    </div>
                                    <div class="col-lg-12 text-center">
                                        <div class="form-group">
                                            <p align="left"><label>*Email:</label></p>
                                            <input value="<%=obj.getEmail()%>" readonly="" type="email" id="emailUsuario" name="emailUsuario" class="form-control" required="" placeholder="exeplo@email.com">
                                            <p align="left"><font color="red">${erroEmail}</font></p>
                                        </div>
                                        <div class="form-group">
                                            <%

                                                SimpleDateFormat formatoNormal = new SimpleDateFormat("dd/MM/yyyy");
                                                String dataNascimentoFormatada = null;
                                                dataNascimentoFormatada = formatoNormal.format(obj.getDataNascimento());

                                            %>
                                            <p align="left"><label>*Data de nascimento</label></p>
                                            <input value="<%=dataNascimentoFormatada%>" placeholder="Insira sua data de nascimento" type="text" class="form-control" name="dataNascimento" id="dataNascimento" onblur="validarNascimento()" required="">
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

                                    <div class="col-lg-12 text-center">
                                        <div class="form-group">
                                            <p align="left"><label>*Usuário:</label></p>
                                            <input value="<%=obj.getUsuario()%>" readonly="" type="text" class="form-control" id="loginUsuario" name="loginUsuario" required="" placeholder="Usuário que deseja utilizar">
                                            <p align="left"><font color="red">${erroLogin}</font></p>
                                        </div>   
                                    </div>

                                        <input type="hidden" id="idFamiliar" name="idFamiliar" value="<%=obj.getId()%>">

                                    <div class="col-lg-12 text-center">
                                        <div class="form-group">
                                            <p align="right">(*)Campos obrigatórios</p>
                                            <button type="submit" id="botaoSalvar" class="btn btn-default" onclick="salvarDados()" >Salvar</button>
                                            <a href="DesativarContaFamiliarAdm?id=<%=obj.getId()%>"><button type="button" class="btn btn-info">Desativar Conta</button></a>

                                        </div>
                                    </div>

                                </div>
                            </form>
                            
                        </div>
                    </div>
                </div>


            </div>
            
        </div>
        <div class="col-lg-2" ></div>
        <!-- /.container-fluid -->


        <!-- /#wrapper -->

        <!-- jQuery -->
        <script src="js/jquery.js"></script>
        <script src="js/mascara_campos.js"></script>
        <script src="js/pegarimagem.js"></script>
        <script src="js/pesquisaGest.js"></script>
        <script src="js/jquery_validate.js"></script>
        <script src="js/bootstrap-datepicker.min.js"></script>
        <script src="js/bootstrap-datepicker.pt-BR.min.js"></script>
        <script src="js/locastyle.js"></script>
        <link href="css/bootstrap-datepicker.css" rel="stylesheet">


        <!-- Bootstrap Core JavaScript -->
        <script src="js/bootstrap.min.js"></script>

        <script src="js/postagem.js"></script>

        <!-- Morris Charts JavaScript -->
        <script src="js/plugins/morris/raphael.min.js"></script>
        <script src="js/plugins/morris/morris.min.js"></script>
        <script src="js/plugins/morris/morris-data.js"></script>






    </body>

</html>
