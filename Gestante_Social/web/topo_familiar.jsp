
<%@page import="Gestante_Social.dao.SolicitacoesDAO"%>
<%@page import="Gestante_Social.model.Familiar"%>
<%
    Familiar user = (Familiar) session.getAttribute("usuAutenticado");


%>

<!-- Navigation -->

<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation" style="background-color: #808080">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
        <form style="margin: 10px" method="POST" action="BuscarPesquisaGestantes" accept-charset="iso-8859-1">
            <input size="30%" placeholder="Buscar Amigos (M�nimo 4 letras)" type="text" id="nomeAmigo" name="nomeAmigo" minlength="4" required=""/>
            <button type="submit">Buscar</button>
        </form>
    </div>
    <p style="margin: 8px; color: white; font-family: initial; font-size: 150%">FAMILIAR</p>
    <!-- Top Menu Items -->

    <!-- /.navbar-collapse -->
</nav>

<div class="col-lg-2" style=" height: 100%; margin-top: 10px; padding: 0">
    <ul class="nav navbar-default nav-stacked ">
        <div style="height: 230px; width: 100%">
            <center>
                <div style="padding-top: 20px"><img style="border-radius: 30%;" id="foto" src="img/imgperfilfamiliar/<%=user.getFoto()%>" alt="your image" width="160px" height="160px" />
                    <p style="font-size: 200%; font-family: serift"><%=user.getNome()%></p></div>
            </center>
        </div>
        <hr/>
        <%
            Familiar famili = (Familiar) session.getAttribute("usuAutenticado");
            SolicitacoesDAO dao = new SolicitacoesDAO();
            int solicitacoes = dao.bucarSolicitacoesGestanteFamiliar(famili.getId());
            if(solicitacoes>0){
        %>
        <li role="presentation"><a href="BuscarSolicitacoesGestanteFamiliar"><center><label style="color: red"><%=solicitacoes%> Solicita��es</label></center></a></li>
        
        <%}%>
        <li role="presentation" class="active"><a href="IniciarFamiliar"><center><i class="fa fa-fw fa-caret-up"></i>In�cio / Recarregar</center></a></li>
        <li role="presentation"><a href="BuscarDadosPerfilFamiliar" style="color: #696969"><center><i class="fa fa-fw fa-user"></i>Perfil</center></a></li>
        <li role="presentation"><a href="EncerrarSessaoFamiliar" style="color: #696969"><center><i class="fa fa-fw fa-close"></i>Sair</center></a></li>
    </ul>
</div>