$(document).ready(function () {
    $("#senha").keyup(function () {
        var senha = document.getElementById("senha").value;
        var forca = 0;
        if (senha.length > 7) {
            forca += 60;
        }
        if (senha.match(/[a-z]+/)) {
            forca += 20;
        }
        if (senha.match(/[A-Z]+/)) {
            forca += 20;
        }

        if (forca < 30) {

            document.getElementById("barra_prog").style.width = "10%";
            document.getElementById("barra_prog").className = "progress-bar progress-bar-danger";
            document.getElementById("tipo_senha").innerHTML = "<p align = \"left\">Senha fraca!</p> ";

        } else if ((forca >= 30) && (forca < 60)) {
            document.getElementById("barra_prog").style.width = "30%";
            document.getElementById("barra_prog").className = "progress-bar progress-bar-warning";
            document.getElementById("tipo_senha").innerHTML = "<p align = \"left\">Senha mediana!</p> ";
        } else if ((forca >= 60) && (forca < 85)) {
            document.getElementById("barra_prog").style.width = "60%";
            document.getElementById("barra_prog").className = "progress-bar progress-bar-warning";
            document.getElementById("tipo_senha").innerHTML = "<p align = \"left\">Senha mediana!</p> ";
        } else {
            document.getElementById("barra_prog").style.width = "100%";
            document.getElementById("barra_prog").className = "progress-bar progress-bar-success";
            document.getElementById("tipo_senha").innerHTML = "<p align = \"left\">Senha forte!</p> ";
        }

    });

});

$(document).ready(function () {
    $("#conf_senha").focusout(function () {
        var senha1 = document.getElementById("senha").value;
        var senha2 = document.getElementById("conf_senha").value;
        
        if(senha1!=senha2){
            document.getElementById("conf_senha").value="";
            document.getElementById("tipo_senha").innerHTML = "<p align = \"left\" style=\"color:red\">As senhas n\u00e3o se correspondem!</p>";
        }
        
    });
});

function validarSenha() {
    var senha1 = document.getElementById("senha").value;
        var senha2 = document.getElementById("conf_senha").value;
        
        if(senha1!=senha2){
            document.getElementById("conf_senha").value="";
            document.getElementById("tipo_senha").innerHTML = "<p align = \"left\" style=\"color:red\">As senhas n\u00e3o se correspondem!</p>";
            document.getElementById("chave").value = 0;
        }else{
            document.getElementById("chave").value = 1;
            
        }
    
}








