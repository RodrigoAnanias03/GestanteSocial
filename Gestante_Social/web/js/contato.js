$(document).ready(function(){
    
        $("#myModal").modal();
    
});

function salvarContato() {
    
    
    var mensagem = document.getElementById("mensagem").value;
    var nome = document.getElementById("nome").value;
    var sobrenome = document.getElementById("sobrenome").value;
    var assunto = document.getElementById("assunto").value;

    if (mensagem !== "" && nome!=="" && sobrenome!=="" && assunto!=="") {

        document.getElementById("botao").innerHTML = "<i class='fa fa-spinner fa-spin' style='font-size:20px'></i> Enviando";
        document.getElementById("botao").disabled=true;
        document.getElementById("formulario").submit();
        
    }
}

function qntCaracteres(){
    var texto = document.getElementById("mensagem").value;
    if(texto.length>=300){
        alert("Limite de 300 caracteres!");
    }
    document.getElementById("qntcara").innerHTML = texto.length+"/300";
    
}


