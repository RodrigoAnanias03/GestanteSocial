$(document).ready(function () {
    var x = 0;
    var maximo = document.getElementById("maxi").value;
    var nomeamigo = document.getElementById("nome_amigo").value;
    var idusuario = document.getElementById("id_usuario").value;

    x += 5;
    $("#conteudo").load("BuscarPostagensGestantePessoal?incremento=" + x + "&maxid=" + maximo + "&nomeamigo=" + nomeamigo +"&idusuario="+idusuario);

    $("#conteudo").scroll(function () {
        if ($(this).scrollTop() + $(this).height() == $(this).get(0).scrollHeight) {
            console.log("fim");

            $.ajax({
                success: function (data) {
                    //manipula os dados
                    x += 5;
                    $("#conteudo").load("BuscarPostagensGestantePessoal?incremento=" + x + "&maxid=" + maximo + "&nomeamigo=" + nomeamigo +"&idusuario="+idusuario);

                },
                error: function () {
                }
            });
        }
    });
});

