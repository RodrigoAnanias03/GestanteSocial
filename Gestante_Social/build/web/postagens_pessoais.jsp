<%@page import="Gestante_Social.model.Familiar"%>
<%@page import="Gestante_Social.model.Usuario"%>
<%@page import="java.util.Date"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="Gestante_Social.model.Postagem"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html lang="pt-br">

    <head>

        <meta charset="utf-8">
        <meta accept-charset="iso-8859-1">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Acesso à gestante</title>
        <link rel="shortcut icon" href="img/favicon.png">

        <link href="css/bootstrap.min.css" rel="stylesheet">


        <link href="css/sb-admin.css" rel="stylesheet">


        <link href="css/plugins/morris.css" rel="stylesheet">


        <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">






    </head>

    <body  style="background-color: white">



        <%
            Usuario user = new Usuario();
            Familiar fami = new Familiar();
            try {
                user = (Usuario) session.getAttribute("usuAutenticado");
            } catch (Exception ex) {
               fami = (Familiar) session.getAttribute("usuAutenticado");
            }

            ArrayList<Postagem> postagens = (ArrayList) request.getAttribute("postagens");

            for (Postagem obj : postagens) {
        %>

        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-body">

                    <div class="col-md-1">
                        <img style="border-radius: 30%; margin-right: 10px" id="blah" src="img/imgperfilusuario/<%=obj.getUsuario().getFoto()%>" alt="your image" width="50px" height="50px" />

                    </div>
                    <div class="col-md-11">
                        <%
                            long semanas=0;
                            if(obj.getUsuario().getInicioGestacao()!=null){
                            Date hoje = new Date();
                            semanas = hoje.getTime() - obj.getUsuario().getInicioGestacao().getTime();
                            semanas = ((semanas / (24 * 60 * 60 * 1000)) / 7) + 1;
                            }

                            //encripta
                            String login_usuario = obj.getUsuario().getLogin();
                            int contador, tamanho, codigoASCII;
                            String loginUserCriptografada = "";
                            tamanho = login_usuario.length();
                            //senha = senha.toUpperCase();
                            contador = 0;
                            while (contador < tamanho) {
                                codigoASCII = login_usuario.charAt(contador) + 130;
                                loginUserCriptografada = loginUserCriptografada + (char) codigoASCII;
                                contador++;
                            }

                        %>
                        
                        
                        <%if(obj.getUsuario().getInicioGestacao()!=null){%>
                        <label><%=obj.getUsuario().getNome() + " " + obj.getUsuario().getSobrenome() + " - " + semanas + "ª semana"%></label>
                        <%}else{%>
                        <label><%=obj.getUsuario().getNome() + " " + obj.getUsuario().getSobrenome()%></label>
                        <%}%>
                        
                        
                        <p><fmt:formatDate value='<%=obj.getData()%>' type="date" pattern="dd/MM/yyyy"/></p>
                    </div>
                    <div class="col-md-12">
                        <label><%=obj.getMensagem()%></label>
                    </div>
                    <%if (!obj.getUrl_imagem().equals("")) {%>
                    <div class="col-md-12" style="max-width: 70%">
                        <img class="img-responsive img-thumbnail" style="border-radius: 0%" id="blah" src="img/imgpostagem/<%=obj.getUrl_imagem()%>" alt="Recarregue a página" />
                    </div>
                    <%}%>
                    <div class="col-md-12">
                        <%

                            //Criptografa a String passada por parâmetro
                            String idPos = String.valueOf(obj.getId_postagem());
                            contador = 0;
                            tamanho = 0;
                            codigoASCII = 0;
                            String idPosCriptografada = "";
                            tamanho = idPos.length();
                            //senha = senha.toUpperCase();
                            contador = 0;
                            while (contador < tamanho) {
                                codigoASCII = idPos.charAt(contador) + 130;
                                idPosCriptografada = idPosCriptografada + (char) codigoASCII;
                                contador++;
                            }

                            if (obj.getUsuario().getLogin().equals(user.getLogin())) {
                        %>
                        <div class="col-md-1" style="margin-right: 15px">
                            <form action="ExcluirPostagem" method="POST" accept-charset="iso-8859-1">
                                <input type="hidden" id="idPostagem" name="idPostagem" value="<%=idPosCriptografada%>"/>
                                <button class="btn btn-danger" style="margin-top: 3px" type="submit">Excluir</button>
                            </form>
                        </div>
                        <%}%>
                        <div  class="col-md-5">
                            <form action="BuscarComentarios" method="POST" accept-charset="iso-8859-1">
                                <input type="hidden" id="loginAmigo" name="loginAmigo" value="<%=loginUserCriptografada%>"/>
                                <input type="hidden" id="idPostagem" name="idPostagem" value="<%=idPosCriptografada%>"/>
                                <button class="btn btn-default col-md-5" style="margin-top: 3px" type="submit">Comentários</button>
                            </form>
                        </div>
                    </div>

                </div>
            </div>
        </div>
        <%}%>
        <%
         if(postagens.size()>4){
        %>
        <div id="carr" class="col-md-12">
            
        </div>
        <%}%>





        <!-- /#wrapper -->

        <!-- jQuery -->
        <script src="js/jquery.js"></script>
        <script src="js/publicacoes.js"></script>

        <!-- Bootstrap Core JavaScript -->
        <script src="js/bootstrap.min.js"></script>

        <!-- Morris Charts JavaScript -->
        <script src="js/plugins/morris/raphael.min.js"></script>
        <script src="js/plugins/morris/morris.min.js"></script>
        <script src="js/plugins/morris/morris-data.js"></script>

    </body>

</html>

