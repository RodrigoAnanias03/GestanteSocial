function salvarDados() {
    document.getElementById("botaoSalvar").innerHTML = "<i class='fa fa-spinner fa-spin' style='font-size:20px'></i> Salvando";
    document.getElementById("botaoSalvar").disabled=true;
    document.getElementById("formulario").submit();
}

$(document).ready(function () {
    var x = document.getElementById("cod_senha").value;
    if (x == 1) {
        $("#demo").fadeIn(200);
        document.getElementById("senhaAtual").focus();
    }else{
        document.getElementById("nomeUsuario").focus();
    }
});

function verificarSenhas(){
    var senha1 = document.getElementById("senha").value;
    var senha2 = document.getElementById("repete").value;
    if(senha1.length < 7){
        document.getElementById("mensagem_senha").innerHTML = "A senha deve conter 8 caracteres ou mais!";
    }else if(senha1 !== senha2){
        document.getElementById("mensagem_senha").innerHTML = "As senhas não correspondem!";
    }else{
        document.alterar_senha.submit();
    }
}
