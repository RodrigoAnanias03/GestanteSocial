$(document).ready(function () {
    var x = 0;
    var maximo = document.getElementById("maxi").value;

    x += 5;
    $("#conteudo").load("BuscarPostagensGestante?incremento=" + x + "&maxid=" + maximo);

    $("#conteudo").scroll(function () {
        if ($(this).scrollTop() + $(this).height() == $(this).get(0).scrollHeight) {
            console.log("fim");

            $.ajax({
                success: function (data) {
                    //manipula os dados
                    x += 5;
                    $("#conteudo").load("BuscarPostagensGestante?incremento=" + x + "&maxid=" + maximo);

                },
                error: function () {
                }
            });
        }
    });
});


function click() {
    if (event.button == 2 || event.button == 3) {
        oncontextmenu = 'return false';
        alert('Todos os direitos reservados');
    }
}
document.onmousedown = click
document.oncontextmenu = new Function("return false;")
