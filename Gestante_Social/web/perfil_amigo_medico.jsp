
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

        <title>Amigo</title>
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
        <script>
            $(document).ready(function () {
                var x = 0;
                var maximo = document.getElementById("maxi").value;
                var nomeAmigo = document.getElementById("login_amigo").value;

                x += 5;
                $("#conteudo").load("BuscarPostagensGestanteMedico?incremento=" + x + "&maxid=" + maximo + "&nomeamigo=" + nomeAmigo);

                $("#conteudo").scroll(function () {
                    if ($(this).scrollTop() + $(this).height() == $(this).get(0).scrollHeight) {
                        console.log("fim");

                        $.ajax({
                            success: function (data) {
                                //manipula os dados
                                x += 5;
                                $("#conteudo").load("BuscarPostagensGestanteMedico?incremento=" + x + "&maxid=" + maximo + "&nomeamigo=" + nomeAmigo);

                            },
                            error: function () {
                            }
                        });
                    }
                });
            });
        </script>
    </head>

    <body style="background-color: white">
        <jsp:include page="topo_medico.jsp" />
        <div class="col-lg-8" >

            <div class="container-fluid" style="margin-top: 10px">



                <div class="col-md-12" style="margin: 0; padding: 0">
                    <div class="panel panel-default">
                        <div class="panel-body">

                            <div class="col-md-5" style="padding-top: 5px">
                                <input type="hidden" id="id_amigo" name="id_amigo" value="${id_amigo}">
                                <img align="right" style="border-radius: 30%;" id="blah" src="img/imgperfilusuario/${imagem_amigo}" alt="your image" width="160px" height="160px" />
                            </div>
                            <div style="margin-top: 15px"><center>
                                    <label>${nome_amigo} ${sobrenome_amigo}</label>
                                    <br/>
                                    <br/>
                                    <%String a = String.valueOf(request.getAttribute("semanas_amigo"));
                                        if (!a.equals("null")) {%>
                                    <label>${semanas_amigo}ª Semana</label>
                                    <%}%>
                                    <br/>
                                    <br/>
                                    <label>${situacao_amigo}</label>
                                    <%String x = String.valueOf(request.getAttribute("botao"));
                                        if (!x.equals("Você")) {
                                    %>
                                    <br/>
                                    <form accept-charset="iso-8859-1" action="EditarAmizadeMedico" method="POST">
                                        <input type="hidden" id="login_amigo" name="login_amigo" value="${login_amigo}"/>
                                        <%if (!x.equals("Amigos")) {%>
                                        <button id="acao" name="acao" type="submit" value="${botao}" class="btn btn-default">${botao}</button>
                                        <%} else {%>
                                        <button title="Amigos"  type="button"  class="btn btn-default">${botao}</button>
                                        <button title="Desfazer Amizade" id="acao" name="acao" type="submit" value="${botao}" class="btn btn-danger">Desfazer Amizade</button>
                                        <%}%>
                                    </form>
                                    <%
                                        if (x.equals("Aceitar Solicitação")) {
                                    %>
                                    <form style="margin-top: 5px" accept-charset="iso-8859-1" action="EditarAmizadeMedico" method="POST">
                                        <input type="hidden" id="login_amigo" name="login_amigo" value="${login_amigo}"/>
                                        <button title="Não Aceitar" id="acao" name="acao" type="submit" value="Não aceitar" class="btn btn-default">Não aceitar</button>
                                    </form>
                                    <%}%>
                                    <%} else {%>
                                    <form accept-charset="iso-8859-1">
                                        <input type="hidden" id="login_amigo" name="login_amigo" value="${login_amigo}"/>
                                    </form>
                                    <%}%></center>
                            </div>


                        </div>
                    </div>

                    <center><label style="color: red">${mensagem}</label></center>

                    <div class="panel panel-default" >
                        <%
                            if (x.equals("Amigos") || x.equals("Você")) {
                        %>
                        <form>
                            <input type="hidden" id="maxi" name="maxi" value="${maximo}">
                        </form>
                        <div id="conteudo" name="conteudo" style="height:500px;  overflow-y:scroll">
                            <center><i class='fa fa-spinner fa-spin' style='font-size:20px; margin-top: 5px'></i> Carregando...</center>
                        </div>
                        <%} else {%>
                        <center><label>Apenas amigos podem ver as postagens!</label></center>
                            <%}%>
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

        <!-- Morris Charts JavaScript -->
        <script src="js/plugins/morris/raphael.min.js"></script>
        <script src="js/plugins/morris/morris.min.js"></script>
        <script src="js/plugins/morris/morris-data.js"></script>






    </body>

</html>
