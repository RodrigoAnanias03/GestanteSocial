<%@page import="Gestante_Social.dao.SolicitacoesDAO"%>
<%@page import="Gestante_Social.dao.MensagensDAO"%>
<%@page import="Gestante_Social.dao.EditarAmizadeDAO"%>
<%@page import="Gestante_Social.dao.EditarAmizadeDAO"%>
<%@page import="Gestante_Social.model.Usuario"%>
<%
    Usuario user = (Usuario) session.getAttribute("usuAutenticado");

    EditarAmizadeDAO bd;
    bd = new EditarAmizadeDAO();
    String solicitacoes = bd.buscarQuantidadeSolicitacoes(user);

    MensagensDAO bd2;
    bd2 = new MensagensDAO();
    int mensagensNaoLidas = bd2.mensagensNaoLidas(user);

    bd2 = new MensagensDAO();
    int mensagensMedicasNaoLidas = bd2.mensagensMedicasNaoLidas(user);

    SolicitacoesDAO bd3;
    bd3 = new SolicitacoesDAO();
    int solicitacoesFamiliar = bd3.bucarSolicitacoesFamiliarGestante(user.getIdUsuario());

    bd3 = new SolicitacoesDAO();
    int solicitacoesMedico = bd3.bucarSolicitacoesMedicoGestante(user.getIdUsuario());

    bd3 = new SolicitacoesDAO();
    int statusGestacao = bd3.buscarStatusGravidez(user.getIdUsuario());

%>

<!-- Navigation -->
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation" style="background-color: #808080">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
        <form style="margin: 10px" method="POST" action="BuscarPesquisaAmigos" accept-charset="iso-8859-1">
            <input size="30%" placeholder="Buscar Gestantes (Mínimo 4 letras)" type="text" id="nomeAmigo" name="nomeAmigo" minlength="4" required=""/>
            <button type="submit">Buscar</button>
        </form>
    </div>
    <!-- Top Menu Items -->

    <!-- /.navbar-collapse -->
</nav>
<div class="col-lg-2" style=" height: 100%; margin-top: 10px; padding: 0">
    <ul class="nav navbar-default nav-stacked ">
        <div style="height: 230px; width: 100%">
            <center>
                <div style="padding-top: 20px"><img style="border-radius: 30%;" id="foto" src="img/imgperfilusuario/<%=user.getFoto()%>" alt="your image" width="160px" height="160px" />
                    <p style="font-size: 200%; font-family: serift"><%=user.getNome()%></p></div>
            </center>
        </div>
        <hr/>

        <%if (statusGestacao == 0) {%>
        <center><button class="btn btn-default form-inline" style="margin: 3px; width: 70%" data-toggle="modal" data-target="#modalNovaGestacao">Nova Gestação</button></center>
            <%} else {%>
        <center><button class="btn btn-default form-inline disabled" style="margin: 3px; width: 70%">Nova Gestação</button></center>
            <%}%>

        <%if (statusGestacao == 1) {%>
        <center><button class="btn btn-success form-inline" style="margin: 3px; width: 70%" data-toggle="modal" data-target="#modalDeclararNascimento">Declarar Nascimento</button></center>

        <center><a href="DeclararNascimento"><button class="btn btn-danger form-inline" style="margin: 3px; width: 70%">Cancelar Gestação</button></a></center>
            <%} else {%>
        <center><button class="btn btn-success form-inline disabled" style="margin: 3px; width: 70%">Declarar Nascimento</button></center>

        <center><button class="btn btn-danger form-inline disabled" style="margin: 3px; width: 70%">Cancelar Gestação</button></center>
            <%}%>

        <center><a href="BaixarFotos"><button onclick="gerarFotos()" id="btnFoto" class="btn btn-block form-inline" style="margin: 3px; width: 70%">Álbum</button></a></center>

        <hr/>


        <%if (solicitacoesFamiliar != 0) {%>
        <li role="presentation" ><a href="BuscarSolicitacoesFamiliar" style="color: red"><center><%=solicitacoesFamiliar%> Solicitações de familiar</center></a></li>
                    <%}%>

        <%if (solicitacoesMedico != 0) {%>
        <li role="presentation" ><a href="BuscarSolicitacoesMedico" style="color: red"><center><%=solicitacoesMedico%> Solicitações de médico</center></a></li>
                    <%}%>

        <%if (!solicitacoes.equals("0")) {%>
        <li role="presentation" ><a href="BuscarSolicitacoes" style="color: red"><center><%=solicitacoes%> Solicitações</center></a></li>
                    <%}%>
        <li role="presentation" class="active"><a href="IniciarPostagens"><center><i class="fa fa-fw fa-caret-up"></i>Início / Recarregar</center></a></li>
        <li role="presentation"><a href="BuscarPerfilPessoal" style="color: #696969"><center><i class="fa fa-fw fa-user"></i>Perfil</center></a></li>

        <%if (mensagensNaoLidas > 0) {%>
        <li role="presentation"><a href="BuscarMensagens" style="color: #696969"><center><i class="fa fa-fw fa-envelope"></i><label style="color: red">Mensagens <%=mensagensNaoLidas%></label></center></a></li>
                        <%} else {%>
        <li role="presentation"><a href="BuscarMensagens" style="color: #696969"><center><i class="fa fa-fw fa-envelope"></i>Mensagens</center></a></li>
                    <%}%>

        <%if (mensagensMedicasNaoLidas > 0) {%>
        <li role="presentation"><a href="BuscarMensagensDeMedicos" style="color: #696969"><center><i class="fa fa-fw fa-envelope"></i><label style="color: red">Mensagens Médicas <%=mensagensMedicasNaoLidas%></label></center></a></li>
                        <%} else {%>
        <li role="presentation"><a href="BuscarMensagensDeMedicos" style="color: #696969"><center><i class="fa fa-fw fa-envelope"></i>Mensagens Médicas</center></a></li>
                    <%}%>


        <li role="presentation"><a  href="BuscarAmigos" style="color: #696969"><center><i class="fa fa-fw fa-group"></i>Amigos</center></a></li>
        <li role="presentation"><a href="BuscarFamiliaresGestante" style="color: #696969"><center><i class="fa fa-fw fa-tags"></i>Familiar</center></a></li>
        <li role="presentation"><a href="BuscarMedicosGestante" style="color: #696969"><center><i class="fa fa-fw fa-plus"></i>Profissionais</center></a></li>
        <li role="presentation"><a href="BuscarInfoAmigos" style="color: #696969"><center><i class="fa fa-fw fa-file-text-o"></i>Publicações</center></a></li>
        <li role="presentation"><a href="EncerrarSessaoGestante" style="color: #696969"><center><i class="fa fa-fw fa-close"></i>Sair</center></a></li>
    </ul>
</div>


<div class="modal fade" id="modalDeclararNascimento" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Parabéns!!!</h4>
            </div>
            <div class="modal-body">

                <form id="cadNasc" action="DeclararNascimento" method="POST" accept-charset="iso-8859-1">
                    <label>Declarar Nascimento!</label><br>
                    <input type="radio" name="publicar" id="publicar" checked="" value="sim">Publicar Nascimento<br>
                    <input type="radio" name="publicar" id="publicar" value="nao">Não Publicar<br>
                    <center><button type="submit" class="btn btn-success">Declarar</button></center>
                </form>


            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>

    </div>
</div>


<!-- Trigger the modal with a button -->

<!-- Modal -->
<div class="modal fade" id="modalNovaGestacao" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Nova Gestação</h4>
            </div>
            <div class="modal-body">

                <form id="cadGestacao" action="NovaGestacao" method="POST" accept-charset="iso-8859-1">
                    <label>Data do início da gestação</label>
                    <input required="" class="form-control" id="gestacaoUsuario" name="gestacaoUsuario"/>
                    <input type="radio" name="publicar" id="publicar" checked="" value="sim">Publicar Gravidez<br>
                    <input type="radio" name="publicar" id="publicar" value="nao">Não Publicar<br>
                    <center><button onclick="validarQntGestacao(), validarGestacao()" type="submit" class="btn btn-success">Salvar</button></center>
                </form>


            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>

    </div>
</div>











