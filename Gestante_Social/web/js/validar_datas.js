function validarNascimento() {
    var data = document.getElementById("dataNascimento").value;
    var dia;
    if (data.charAt(0) == '0') {
        dia = data.charAt(1);
    } else {
        dia = data.charAt(0) + data.charAt(1);
    }
    var mes;
    if (data.charAt(3) == '0') {
        mes = data.charAt(4);
    } else {
        mes = data.charAt(3) + data.charAt(4);
    }
    var ano = data.charAt(6) + data.charAt(7) + data.charAt(8) + data.charAt(9);


        var dt = new Date();
        diahoje = dt.getDate();
        meshoje = dt.getMonth() + 1;
        anohoje = dt.getFullYear();

        var idade = anohoje - ano;

        if (meshoje < mes) {
            idade = idade - 1;
        } else if (meshoje == mes && diahoje < dia) {
            idade = idade - 1;
        }

        if (ano > anohoje) {
            alert("Data de nascimento invalida!!");
            document.getElementById("dataNascimento").value = '';
        } else if (ano == anohoje && mes > meshoje) {
            alert("Data de nascimento invalida!!");
            document.getElementById("dataNascimento").value = '';
        } else if (ano == anohoje && mes == meshoje && dia > diahoje) {
            alert("Data de nascimento invalida!!");
            document.getElementById("dataNascimento").value = '';
        }
}

function validarQntGestacao() {
    var data = document.getElementById("gestacaoUsuario").value;
    if(data.length<10){
        alert("Quantidade de caracteres da data de gestacao invalida!!");
        document.getElementById("gestacaoUsuario").value = '';
    }
}

function validarGestacao() {
    var data = document.getElementById("gestacaoUsuario").value;
    var dia;
    if (data.charAt(0) == '0') {
        dia = data.charAt(1);
    } else {
        dia = data.charAt(0) + data.charAt(1);
    }
    var mes;
    if (data.charAt(3) == '0') {
        mes = data.charAt(4);
    } else {
        mes = data.charAt(3) + data.charAt(4);
    }
    var ano = data.charAt(6) + data.charAt(7) + data.charAt(8) + data.charAt(9);


        var dt = new Date();
        diahoje = dt.getDate();
        meshoje = dt.getMonth() + 1;
        anohoje = dt.getFullYear();

        var idade = anohoje - ano;

        if (meshoje < mes) {
            idade = idade - 1;
        } else if (meshoje == mes && diahoje < dia) {
            idade = idade - 1;
        }

        if (ano > anohoje) {
            alert("Data de gestacao invalida!!");
            document.getElementById("gestacaoUsuario").value = '';
        } else if (ano == anohoje && mes > meshoje) {
            alert("Data de gestacao invalida!!");
            document.getElementById("gestacaoUsuario").value = '';
        } else if (ano == anohoje && mes == meshoje && dia > diahoje) {
            alert("Data de gestacao invalida!!");
            document.getElementById("gestacaoUsuario").value = '';
        }
}

function validarQntSenha() {
    var senha = document.getElementById("senha").value;
    if (senha.length < 8) {
        document.getElementById("conf_senha").value = '';
        document.getElementById("senha").value = '';
        document.getElementById("tipo_senha").innerHTML = "<p align = \"left\" style=\"color:red\">A senha deve conter pelo menos 8 caracteres!!! </p>";
        document.getElementById("chave").value = 0;
    }else{
        document.getElementById("chave").value = 1;
    }
}
