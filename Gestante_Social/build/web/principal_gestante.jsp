
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
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.min.js" type="text/javascript"></script>
        <script src="js/buscarpaginas.js"></script>
        <script src="js/carregarposgestantes.js"></script>

    </head>

    <jsp:include page="topo_gestante.jsp" />

    <body style="background-color: white">

        <div class="col-lg-8" >

            <div class="container-fluid" style="margin-top: 10px">



                <div class="col-md-12" style="margin: 0; padding: 0">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <form id="formulario" action="PostagemGestante" method="POST" enctype="multipart/form-data" accept-charset="iso-8859-1">
                                <label style="color: #808080">Como você está hoje?</label>
                                <textarea id="texto" name="texto" class="form-control" rows="4" autofocus="" required=""></textarea>
                                <input type='file' class="form-control" id="imagem" name="imagem" accept="image/png, image/jpeg" data-val="true" data-val-regex-pattern="^.*\.(jpg|JPG|jpeg|JPEG|png|PNG|bmp|BMP)$"
                                       data-val-regex="Insira somente arquivos de foto"/>
                                <p>${retorno}</p>
                                <button onclick="salvarPublicacao()" style="margin-top: 5px" id="btnPublicar" type="submit" class="btn btn-success" >Publicar</button>
                                <button style="margin-top: 5px" type="reset" class="btn btn-danger">Limpar</button>
                            </form>
                        </div>
                    </div>
                    <div class="panel panel-default" >
                        <form>
                            <input type="hidden" id="maxi" name="maxi" value="${maximo}">
                        </form>
                        <div id="conteudo" name="conteudo" style="height:500px;  overflow-y:scroll">
                            <center><i class='fa fa-spinner fa-spin' style='font-size:20px; margin-top: 5px'></i> Carregando...</center>
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
        <script src="js/postagem.js"></script>
        <script src="js/mascara_campos.js"></script>
        <script src="js/validar_datas.js"></script>
        <script src="js/jquery_validate.js"></script>
        <script src="js/bootstrap-datepicker.min.js"></script>
        <script src="js/bootstrap-datepicker.pt-BR.min.js"></script>
        <script src="js/locastyle.js"></script>
        <link href="css/bootstrap-datepicker.css" rel="stylesheet">

        <!-- Bootstrap Core JavaScript -->
        <script src="js/bootstrap.min.js"></script>



        <!-- Morris Charts JavaScript -->
        <script src="js/plugins/morris/raphael.min.js"></script>
        <script src="js/plugins/morris/morris.min.js"></script>
        <script src="js/plugins/morris/morris-data.js"></script>






    </body>

</html>
