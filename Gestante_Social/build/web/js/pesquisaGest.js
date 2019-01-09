function verificarCamposVazios() {
    var nome = document.getElementById("nomeUsuario").value;
    var email = document.getElementById("emailUsuario").value;
    var cpf = document.getElementById("cpfUsuario").value;



    if (nome == "" && email == "" && cpf == "") {
        alert("Pelo menos 1 dos compas deve ser preenchido");
    } else {
        if (nome != "" && nome.length < 4) {
            alert("Digite pelo menos 4 letras no campo nome");
        } else {
            document.getElementById("formulario").submit();
        }
    }



}

$(document).ready(function () {
    var msg = document.getElementById("mensagem").value;
    if(msg!=""){
        alert(msg);
    }
});

