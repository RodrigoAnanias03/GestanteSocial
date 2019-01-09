$(document).ready(function () {
    $("#dataNascimento").mask("99/99/9999");
});

function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            $('#blah').attr('src', e.target.result);
        }

        reader.readAsDataURL(input.files[0]);
    }
}

$("#imagem").change(function(){
    readURL(this);
});
