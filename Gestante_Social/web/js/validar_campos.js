function validarCpf() {
    var cpf = document.getElementById("cpfUsuario").value;
    var x = cpf.length;
    if (x < 14) {
        alert("Campo CPF preenchido incorretamente!");
        document.getElementById("cpfUsuario").value = "";
    }
}

function validarTelefone() {
    var telefone = document.getElementById("telefoneUsuario").value;
    var x = telefone.length;
    
    if (x < 13) {
        alert("Campo Telefone preenchido incorretamente!");
        document.getElementById("telefoneUsuario").value = "";
        document.getElementById("chave").value = 0;
    }else{
        document.getElementById("chave").value = 1;
    }
}

$(document).ready(function () {
    $("#cpfUsuario").keyup(function () {
        var cpf = document.getElementById("cpfUsuario").value;
        if (cpf == "..-" || cpf=="." || cpf==".." || cpf==".-" || cpf=="-"){
            alert("CPF inv\u00e1lido");
            document.getElementById('cpfUsuario').value = '';
            document.getElementById("chave").value = 0;
        }
        cpf = cpf.replace(".", ""); //tira ponto
        cpf = cpf.replace(".", ""); //tira ponto
        cpf = cpf.replace("-", ""); //tira hifen
        cpf = cpf.replace("_", "");
        cpf = cpf.replace("_", "");
        cpf = cpf.replace("_", "");
        cpf = cpf.replace("_", "");
        cpf = cpf.replace("_", "");
        cpf = cpf.replace("-", "");
        cpf = cpf.replace("_", "");
        cpf = cpf.replace("_", "");
        cpf = cpf.replace("_", "");
        cpf = cpf.replace("_", "");
        cpf = cpf.replace("_", "");
        

        var numero = cpf;

        var num1 = numero.charAt(0);
        var num2 = numero.charAt(1);
        var num3 = numero.charAt(2);
        var num4 = numero.charAt(3);
        var num5 = numero.charAt(4);
        var num6 = numero.charAt(5);
        var num7 = numero.charAt(6);
        var num8 = numero.charAt(7);
        var num9 = numero.charAt(8);
        var num10 = numero.charAt(9);
        var num11 = numero.charAt(10);
        var resultado1 = 0, resultado2 = 0;
        resultado1 = num1 * 10 + num2 * 9 + num3 * 8 + num4 * 7 + num5 * 6 + num6 * 5 + num7 * 4 + num8 * 3 + num9 * 2;
        resultado1 = (resultado1 * 10) % 11;
        if (resultado1 == 10) {
            resultado1 = 0;
        }
        resultado2 = num1 * 11 + num2 * 10 + num3 * 9 + num4 * 8 + num5 * 7 + num6 * 6 + num7 * 5 + num8 * 4 + num9 * 3 + num10 * 2;
        resultado2 = (resultado2 * 10) % 11;
        if (resultado2 == 10) {
            resultado2 = 0;
        }
        if (resultado1 == num10 && resultado2 == num11) {
            if (numero != ("11111111111") && numero != ("22222222222") && numero != ("33333333333") && numero != ("44444444444")
                    && numero != ("55555555555") && numero != ("66666666666") && numero != ("77777777777")
                    && numero != ("88888888888") && numero != ("99999999999") && numero != ("00000000000")) {
                document.getElementById('textocpf').className = 'form-group has-success';
                document.getElementById("chave").value = 1;
            } else {
                document.getElementById('textocpf').className = 'form-group has-warning';
                if (numero.length == 11) {
                    alert("CPF inv\u00e1lido");
                    document.getElementById('cpfUsuario').value = '';
                    document.getElementById("chave").value = 0;
                }
            }
        } else {
            document.getElementById('textocpf').className = 'form-group has-warning';
            if (numero.length == 11) {
                alert("CPF inv\u00e1lido");
                document.getElementById('cpfUsuario').value = '';
                document.getElementById("chave").value = 0;
            }
        }

    });
});

