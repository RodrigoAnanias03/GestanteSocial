<%@page import="Gestante_Social.dao.AdministradorDAO"%>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation" style="background-color: #808080">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
        <a href="IniciarAdm"><button class="btn btn-default" style="margin: 10px">Atualizar</button></a>



        <%
            AdministradorDAO dao = new AdministradorDAO();
            String sol = String.valueOf(dao.buscarQntSolProfissionais());
            if (!sol.equals("0")) {%>
        <a href="BuscarSolicitacoesInscMedicas"><button class="btn btn-default" style="margin: 10px"><label style="color: red"><%=sol%> Solicitações profissionais</label></button></a>
        <%} else {%>
        <button class="btn btn-default" style="margin: 10px">Solicitações Profissionais</button>
        <%}%>
        <a href="alterar_senha_adm.jsp"><button class="btn btn-default" style="margin: 10px">Modificar Senha</button></a>
        <a href="EncerrarCessaoAdm"><button class="btn btn-default" style="margin: 10px">Sair</button></a>

    </div>
    <!-- Top Menu Items -->

    <!-- /.navbar-collapse -->
</nav>