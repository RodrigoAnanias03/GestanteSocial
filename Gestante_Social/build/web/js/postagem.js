function salvarPublicacao() {
    var x = document.getElementById("texto").value;

    if (x !== "") {

        document.getElementById("btnPublicar").innerHTML = "<i class='fa fa-spinner fa-spin' style='font-size:20px'></i> Publicando";
        document.getElementById("btnPublicar").disabled = true;

        document.getElementById("formulario").submit();

    }
}

function responderContato() {
    var x = document.getElementById("texto").value;

    if (x !== "") {

        document.getElementById("btnPublicar").innerHTML = "<i class='fa fa-spinner fa-spin' style='font-size:20px'></i> ...";
        document.getElementById("btnPublicar").disabled = true;

        document.getElementById("formulario").submit();

    }
}

function cadastrar() {

    var chave = document.getElementById("chave").value;
    if (chave == 1) {
        document.getElementById("btnSalvar").innerHTML = "<i class='fa fa-spinner fa-spin' style='font-size:20px'></i> Salvando";
        document.getElementById("btnSalvar").disabled = true;
    }


}

