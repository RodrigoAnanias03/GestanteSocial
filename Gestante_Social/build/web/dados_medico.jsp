
<%@page import="Gestante_Social.model.Medico"%>
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



    <body style="background-color: white">
        <jsp:include page="topo_adm.jsp" />
        <div class="col-lg-2"></div>
        <div class="col-lg-8" >

            <div class="container-fluid" style="margin-top: 10px">



                <div class="col-md-12" style="margin: 0; padding: 0">
                    <%

                        Medico obj = (Medico) request.getAttribute("medico");
                        String dataFormatada = "";
                        if (obj == null) {
                            obj = new Medico();
                        } else {

                            SimpleDateFormat formatoDesejado = new SimpleDateFormat("dd/MM/yyyy");
                            dataFormatada = formatoDesejado.format(obj.getDataNascimento());
                        }


                    %>


                    <div class="panel panel-default" >
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-12">
                                    <center>
                                        <div style="padding-bottom: 20px"><img style="border-radius: 30%;" id="blah" src="img/imgperfilmedico/<%=obj.getFoto()%>" alt="your image" width="200px" height="200px" /></div>

                                        <hr/>
                                        <p align="left"><font color="red">${erro}</font></p>
                                    </center>	
                                </div>
                                <div class="col-lg-6 text-center">
                                    <div class="form-group">
                                        <p align="left"><label>*Nome:</label></p>
                                        <input readonly="" value="<%=obj.getNome()%>" class="form-control" id="nomeUsuario" name="nomeUsuario" placeholder="Insira seu nome" required=""  autofocus="">
                                    </div>
                                    <div id="textocpf" class="form-group">
                                        <p align="left"><label>*CPF:</label></p>
                                        <input readonly="" value="<%=obj.getCpf()%>" class="form-control" id="cpfUsuario" name="cpfUsuario" placeholder="Insira seu CPF" required="">
                                    </div>
                                </div>
                                <div class="col-lg-6 text-center">
                                    <div class="form-group">
                                        <p align="left"><label>*Sobrenome:</label></p>
                                        <input readonly="" value="<%=obj.getSobreNome()%>" class="form-control" id="sobrenomeUsuario" name="sobrenomeUsuario" required="" placeholder="Insira seu sobrenome">
                                    </div>
                                    <div class="form-group">
                                        <p align="left"><label>*Telefone:</label></p>
                                        <input readonly="" value="<%=obj.getTelefone()%>" class="form-control" id="telefoneUsuario" name="telefoneUsuario" placeholder="Insira seu telefone" required="">
                                    </div>
                                </div>

                                <div class="row">

                                    <div style="margin-bottom: 10px" class="col-md-12">
                                        <div class="col-md-6">
                                            <label>*Profissão</label>

                                            <input readonly="" value="<%=obj.getProfissao()%>" id="profissao" name="profissao" class="form-control" required="">


                                        </div>
                                        <div class="col-md-6">

                                            <label id="nomeDocumento">*Documento</label>
                                            <input type="text" value="<%=obj.getDocumento()%>" readonly="" id="documento" required="" placeholder="Insira seu documento" name="documento" class="form-control"/>

                                        </div>


                                    </div>
                                </div>


                                <div class="col-lg-12 text-center">



                                    <div class="form-group">
                                        <p align="left"><label>*Email:</label></p>
                                        <input readonly="" value="<%=obj.getEmail()%>" type="email" id="emailUsuario" name="emailUsuario" class="form-control" required="" placeholder="exeplo@email.com">

                                    </div>

                                    <div class="form-group">	
                                        <p align="left"><label>*Data de nascimento</label></p>
                                        <input readonly="" value="<%=dataFormatada%>" placeholder="Insira sua data de nascimento" type="text" class="form-control" name="dataNascimento" id="dataNascimento" onblur="validarNascimento()" required="">
                                    </div>
                                </div>
                                <div class="col-lg-10 text-center">
                                    <div class="form-group">
                                        <p align="left"><label>*Cidade:</label></p>
                                        <input readonly="" value="<%=obj.getCidade()%>" class="form-control" id="cidadeUsuario" name="cidadeUsuario" required="" placeholder="Insira sua cidade">
                                    </div>
                                </div>
                                <div class="col-lg-2 text-center">
                                    <div class="form-group">
                                        <p align="left"><label>*Estado:</label></p>
                                        <input readonly="" value="<%=obj.getEstado()%>" class="form-control" id="estadoUsuario" name="estadoUsuario" required="" placeholder="UF">
                                    </div>
                                </div>
                                <div class="col-lg-8 text-center">
                                    <div class="form-group">
                                        <p align="left"><label>*Rua:</label></p>
                                        <input readonly="" value="<%=obj.getRua()%>" class="form-control" id="ruaUsuario" name="ruaUsuario" required="" placeholder="Insira sua rua">
                                    </div>
                                </div>
                                <div class="col-lg-4 text-center">
                                    <div class="form-group">
                                        <p align="left"><label>*Nº:</label></p>
                                        <input readonly="" value="<%=obj.getNumero()%>" class="form-control" id="numeroUsuario" name="numeroUsuario" required="" placeholder="Nº">
                                    </div>
                                </div>
                                <div class="col-lg-6 text-center">
                                    <div class="form-group">
                                        <p align="left"><label>*Bairro:</label></p>
                                        <input readonly="" value="<%=obj.getBairro()%>" class="form-control" id="numeroUsuario" name="bairroUsuario" required="" placeholder="Insira seu bairro">
                                    </div>
                                </div>
                                <div class="col-lg-6 text-center">
                                    <div class="form-group">
                                        <p align="left"><label>*CEP:</label></p>
                                        <input readonly="" value="<%=obj.getCep()%>" class="form-control" id="cepUsuario" name="cepUsuario" required="" placeholder="Insira seu CEP">
                                    </div>
                                </div>

                                <div class="col-lg-12 text-center">
                                    <div class="form-group">
                                        <p align="left"><label>*Usuário:</label></p>
                                        <input readonly="" value="<%=obj.getUsuario()%>" type="text" class="form-control" id="loginUsuario" name="loginUsuario" required="" placeholder="Usuário que deseja utilizar">

                                    </div>   
                                </div>

                                <center><a href="https://www.cremesp.org.br/?siteAcao=GuiaMedico&pesquisa=avancada">Analisar Médico</a> | 
                                    <a href="http://www.coren-sp.gov.br/?q=inscritos">Analisar Enfermeiro</a></center>

                                <center><form role="form" action="AtivarMedico"  method="post" accept-charset="iso-8859-1">
                                    <input type="hidden" id="idMedico" name="idMedico" value="<%=obj.getId()%>">
                                    <input type="hidden" id="email" name="email" value="<%=obj.getEmail()%>">
                                    <button style="margin-bottom: 3px; margin-top: 3px" class="btn btn-success">Ativar</button>

                                </form>
                                <form role="form" action="ExcluirMedico"  method="post" accept-charset="iso-8859-1">
                                    <input type="hidden" id="idMedico" name="idMedico" value="<%=obj.getId()%>">
                                    <input type="hidden" id="email" name="email" value="<%=obj.getEmail()%>">
                                    <button class="btn btn-danger">Excluir</button>

                                </form></center>



                            </div>



                        </div>
                    </div>
                </div>


            </div>
        </div>
        <div class="col-lg-2"></div>
        <!-- /.container-fluid -->

        <!-- /#page-wrapper -->



        <!-- /#wrapper -->

        <!-- jQuery -->

        <script src="js/jquery.js"></script>

        <script src="js/editar_perfilusuario.js"></script>
        <script src="js/cad.js"></script>
        <script src="js/mascara_campos.js"></script>
        <script src="js/bootstrap-datepicker.min.js"></script>
        <script src="js/bootstrap-datepicker.pt-BR.min.js"></script>
        <script src="js/locastyle.js"></script>
        <link href="css/bootstrap-datepicker.css" rel="stylesheet">

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
