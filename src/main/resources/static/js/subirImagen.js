var fileUpload = document.getElementById('file-upload');
fileUpload.onchange = function (e) {
    var file = $('#file-upload').prop('files');
    readImage(file);
}

function readImage  (input) {
    var data = new FormData();
    data.append('file' , input[0]);
    var reader = new FileReader();
    reader.onload = function (e) {
        var img = document.getElementById('imcargar');
        img.src = e.target.result;
        img.style.width = "105%";
        img.style.height = "auto";
        var url = document.getElementById('agregarUrl');
        url.value = e.target.result;
        var cambio = document.getElementById('cambioUrl');
        cambio.value = true;
    }

    reader.readAsDataURL(input[0]);
}