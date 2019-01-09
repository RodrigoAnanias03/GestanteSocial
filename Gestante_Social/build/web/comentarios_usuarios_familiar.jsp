
<!DOCTYPE html>
<html lang="pt-br">

    <head>

        <meta charset="utf-8">
        <meta accept-charset="iso-8859-1">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Comentários</title>
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
                var id = document.getElementById("id_post").value;

                x += 5;
                $("#conteudo").load("BuscarResultadosComentariosFamiliar?incremento=" + x + "&maxid=" + maximo + "&idpost=" + id );

                $("#conteudo").scroll(function () {
                    if ($(this).scrollTop() + $(this).height() == $(this).get(0).scrollHeight) {
                        console.log("fim");

                        $.ajax({
                            success: function (data) {
                                //manipula os dados
                                x += 5;
                                $("#conteudo").load("BuscarResultadosComentariosFamiliar?incremento=" + x + "&maxid=" + maximo + "&idpost=" + id);

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

        <jsp:include page="topo_familiar.jsp" />
        
        <div class="col-lg-8" >

            <div class="container-fluid" style="margin-top: 10px">



                <div class="col-md-12" style="margin: 0; padding: 0">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="col-md-1">
                                <img style="border-radius: 30%; margin-right: 10px" id="blah" src="img/imgperfilusuario/${foto}" alt="your image" width="50px" height="50px" />
                            </div>
                            <div class="col-md-11">
                                <label>${nomeresponsavel}</label>
                                <input type="hidden" id="id_responsavel" name="id_responsavel" value="${id_responsavel}"/>

                            </div>
                            <div class="col-md-12">
                                <label>${postagem}</label>
                            </div>
                            <%String ft = String.valueOf(request.getAttribute("img"));
                                if (!ft.equals("")) {%>
                            <div class="col-md-12" style="max-width: 70%">
                                <img class="img-responsive img-thumbnail" style="border-radius: 0%" id="blah" src="img/imgpostagem/<%=ft%>" alt="Recarregue a página" />
                            </div>

                            <%}%>

                            <div class="col-md-12">
                                <form action="ComentarPublicacaoFamiliar" method="POST" accept-charset="iso-8859-1">
                                    <label style="color: #808080">Comentar:</label>
                                    <textarea id='texto' name="texto" class="form-control" rows="4" autofocus="" required=""></textarea>
                                    <input type="hidden" id="id_post" name="id_post" value="${id_post}"/>
                                    <input type="hidden" id="login_amigo" name="login_amigo" value="${login_amigo}"/>
                                    <button style="margin-top: 5px" type="submit" class="btn btn-success">Publicar</button>
                                    <button style="margin-top: 5px" type="reset" class="btn btn-danger">Limpar</button>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="panel panel-default" >
                        <form>
                            <input type="hidden" id="maxi" name="maxi" value="${maximo}">
                        </form>
                        <div id="conteudo" name="conteudo" style="height:500px;  overflow-y:scroll">

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

        <!-- Morris Charts JavaScript -->
        <script src="js/plugins/morris/raphael.min.js"></script>
        <script src="js/plugins/morris/morris.min.js"></script>
        <script src="js/plugins/morris/morris-data.js"></script>






    </body>

</html>
